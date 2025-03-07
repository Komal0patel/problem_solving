import pandas as pd
import matplotlib.pyplot as plt

# Read two different CSV files
df1 = pd.read_csv("Marks1.csv")
df2 = pd.read_csv("Marks2.csv")

# Assume both datasets have a common 'Date' column and different 'Value' columns
# Modify these column names based on your actual data
x1, y1 = df1['NAME'], df1['MARKS']  
x2, y2 = df2['NAME'], df2['MARKS']  

# Plot both datasets with different colors
plt.figure(figsize=(10, 5))
plt.plot(x1, y1, label="Dataset 1", color="blue", marker="o")
plt.plot(x2, y2, label="Dataset 2", color="red", linestyle="dashed", marker="s")

# Customize the plot
plt.xlabel("Names")
plt.ylabel("Marks")
plt.title("Comparison of Two Datasets")
plt.legend()
plt.xticks(rotation=45)  # Rotate x-axis labels if needed
plt.grid(True)

# Show the plot
plt.show()
