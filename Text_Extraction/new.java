package org.example;

import org.apache.poi.ss.usermodel.*;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file path: ");

        try {
            String filePath = reader.readLine();
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            String extension = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
            String text = "";

            if (extension.matches("\\.(jpg|jpeg|png|webp)")) {
                text = extractTextFromImage(filePath);
                extractTestResults(text);
            } else if (extension.equals(".pdf")) {
                try {
                    String pdfText = extractTextFromPDF(filePath);
                    Map<String, String> testResults = extractTestValues(pdfText);

                    System.out.println("\nFinal Extracted Test Results from PDF:");
                    for (Map.Entry<String, String> entry : testResults.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                } catch (IOException e) {
                    System.err.println("Error reading PDF: " + e.getMessage());
                }
            } else if (extension.matches("\\.(xlsx|xls)")) {
                extractFromExcel(filePath);
            } else {
                System.out.println("Unsupported file type.");
                return;
            }

        } catch (IOException e) {
            System.out.println("Error reading file path: " + e.getMessage());
        }
    }

    // Extracts text from an image using OCR
    public static String extractTextFromImage(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("D:/Downloads/Tesseract-OCR/Tesseract-OCR/tessdata"); // Set correct Tesseract path
            tesseract.setLanguage("eng");
            return tesseract.doOCR(imageFile).toLowerCase();
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Error extracting text from image.";
        }
    }

    // Extracts test results using regex and writes to a CSV file
    public static void extractTestResults(String text) {
        String regex = "([a-z\\s/()-]*\\b(?:creatinine|sodium|potassium|chloride|electrolytes|blood urea nitrogen|bun|glomerular filtration rate|gfr)\\b[a-z\\s]*)\\s+\\b(\\d{1,3}(?:\\.\\d{1,2})?)\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        try (CSVWriter writer = new CSVWriter(new FileWriter("test_results.csv"))) {
            writer.writeNext(new String[]{"Test Name", "Result"});
            while (matcher.find()) {
                writer.writeNext(new String[]{matcher.group(1).trim(), matcher.group(2)});
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Extracts text from a PDF file
    private static String extractTextFromPDF(String filePath) throws IOException {
        try (org.apache.pdfbox.pdmodel.PDDocument document = org.apache.pdfbox.pdmodel.PDDocument.load(new File(filePath))) {
            org.apache.pdfbox.text.PDFTextStripper stripper = new org.apache.pdfbox.text.PDFTextStripper();
            return stripper.getText(document).toLowerCase();
        }
    }

    // Extracts test values from PDF text
    private static Map<String, String> extractTestValues(String text) {
        Map<String, String> extractedTests = new LinkedHashMap<>();
        Map<String, List<String>> requiredTests = new HashMap<>();

        requiredTests.put("creatinine", Arrays.asList("creatinine", "serum creatinine"));
        requiredTests.put("bun", Arrays.asList("bun", "blood urea nitrogen"));
        requiredTests.put("sodium", Arrays.asList("sodium", "serum sodium"));
        requiredTests.put("potassium", Arrays.asList("potassium", "serum potassium"));
        requiredTests.put("uacr", Arrays.asList("uacr", "urine albumin-to-creatinine ratio"));

        Pattern pattern = Pattern.compile("(\\D+?)\\s+([0-9]+\\.?[0-9]*\\s*(mg/dL|mmol/L|g/L)?)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String testName = matcher.group(1).trim();
            String testValue = matcher.group(2).trim();

            for (Map.Entry<String, List<String>> entry : requiredTests.entrySet()) {
                for (String alias : entry.getValue()) {
                    if (testName.contains(alias)) {
                        extractedTests.put(entry.getKey(), testValue);
                        break;
                    }
                }
            }
        }

        return extractedTests;
    }

    // Extracts kidney test results from an Excel file
    public static void extractFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            Map<String, String> results = new LinkedHashMap<>();

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        String cellValue = cell.getStringCellValue().toLowerCase();
                        if (cellValue.contains("creatinine")) {
                            results.put("creatinine", getNextValue(row, cell.getColumnIndex()));
                        } else if (cellValue.contains("bun") || cellValue.contains("blood urea nitrogen")) {
                            results.put("bun", getNextValue(row, cell.getColumnIndex()));
                        } else if (cellValue.contains("sodium")) {
                            results.put("sodium", getNextValue(row, cell.getColumnIndex()));
                        } else if (cellValue.contains("potassium")) {
                            results.put("potassium", getNextValue(row, cell.getColumnIndex()));
                        } else if (cellValue.contains("uacr") || cellValue.contains("urine albumin-to-creatinine ratio")) {
                           results.put("uacr", getNextValue(row, cell.getColumnIndex()));
                        }
                    }
                }
            }

            System.out.println("\nExtracted Test Results from Excel:");
            for (Map.Entry<String, String> entry : results.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        }
    }

    private static String getNextValue(Row row, int columnIndex) {
        if (row.getCell(columnIndex + 1) != null) {
            Cell nextCell = row.getCell(columnIndex + 1);
            if (nextCell.getCellType() == CellType.NUMERIC) {
                return String.valueOf(nextCell.getNumericCellValue());
            } else if (nextCell.getCellType() == CellType.STRING) {
                return nextCell.getStringCellValue();
            }
        }
        return "Value not found";
    }
}
