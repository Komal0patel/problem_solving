// this code extracts kidney test results from pdfs and images of different format


package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the image path: ");

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
            } else {
                System.out.println("Unsupported file type.");
                return;
            }

            extractTestResults(text);
        } catch (IOException e) {
            System.out.println("Error reading file path: " + e.getMessage());
        }






        // PDF Processing
        String pdfPath = "D:/Downloads/kidney.pdf";  // Change this to your PDF path
        try {
            String pdfText = extractTextFromPDF(pdfPath);
            Map<String, String> testResults = extractTestValues(pdfText);

            System.out.println("\nFinal Extracted Test Results:");
            for (Map.Entry<String, String> entry : testResults.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error reading PDF: " + e.getMessage());
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
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
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
}
