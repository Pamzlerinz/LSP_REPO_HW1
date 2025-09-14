package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**

- ETL Pipeline for CSV data processing
- Reads products.csv, applies transformations, and writes to transformed_products.csv
  */
  public class ETLPipeline {
  
  private static final String INPUT_FILE = “data/products.csv”;
  private static final String OUTPUT_FILE = “data/transformed_products.csv”;
  private static final String ELECTRONICS_CATEGORY = “Electronics”;
  private static final String PREMIUM_ELECTRONICS = “Premium Electronics”;
  private static final double DISCOUNT_RATE = 0.10; // 10% discount
  private static final double PREMIUM_THRESHOLD = 500.00;
  
  public static void main(String[] args) {
  ETLPipeline pipeline = new ETLPipeline();
  pipeline.runPipeline();
  }
  
  /**
  - Main pipeline execution method
    */
    public void runPipeline() {
    try {
    // Extract phase
    List<String[]> rawData = extract();
    System.out.println(“✓ Extract phase completed”);
    
    ```
     // Transform phase
     List<String[]> transformedData = transform(rawData);
     System.out.println("✓ Transform phase completed");
     
     // Load phase
     load(transformedData);
     System.out.println("✓ Load phase completed");
     
     // Print summary
     printSummary(rawData.size(), transformedData.size());
    ```
    
    } catch (IOException e) {
    System.err.println(“Error during ETL process: “ + e.getMessage());
    }
    }
  
  /**
  - Extract phase: Read CSV file and parse into array of string arrays
    */
    private List<String[]> extract() throws IOException {
    List<String[]> data = new ArrayList<>();
    
    // Check if file exists
    File inputFile = new File(INPUT_FILE);
    if (!inputFile.exists()) {
    System.err.println(“Error: Input file ‘” + INPUT_FILE + “’ not found.”);
    System.err.println(“Please ensure the file exists in the data/ directory.”);
    System.exit(1);
    }
    
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
    String line;
    boolean isFirstLine = true;
    
    ```
     while ((line = reader.readLine()) != null) {
         line = line.trim();
         if (!line.isEmpty()) {
             String[] columns = line.split(",");
             // Trim whitespace from each column
             for (int i = 0; i < columns.length; i++) {
                 columns[i] = columns[i].trim();
             }
             data.add(columns);
         }
     }
    ```
    
    }
    
    return data;
    }
  
  /**
  - Transform phase: Apply business rules to the data
    */
    private List<String[]> transform(List<String[]> rawData) {
    List<String[]> transformedData = new ArrayList<>();
    
    if (rawData.isEmpty()) {
    return transformedData;
    }
    
    // Add header row with new PriceRange column
    String[] header = {“ProductID”, “Name”, “Price”, “Category”, “PriceRange”};
    transformedData.add(header);
    
    // Skip header row in processing (start from index 1)
    for (int i = 1; i < rawData.size(); i++) {
    String[] row = rawData.get(i);
    
    ```
     if (row.length < 4) {
         System.err.println("Warning: Skipping malformed row " + i + " (insufficient columns)");
         continue;
     }
     
     try {
         String[] transformedRow = transformRow(row);
         transformedData.add(transformedRow);
     } catch (Exception e) {
         System.err.println("Warning: Skipping row " + i + " due to error: " + e.getMessage());
     }
    ```
    
    }
    
    return transformedData;
    }
  
  /**
  - Transform a single row according to business rules
    */
    private String[] transformRow(String[] row) {
    String productId = row[0];
    String name = row[1];
    String priceStr = row[2];
    String category = row[3];
    
    // Parse price
    double price = Double.parseDouble(priceStr);
    
    // Step 1: Convert name to uppercase
    name = name.toUpperCase();
    
    // Step 2: Apply 10% discount if Electronics category
    if (ELECTRONICS_CATEGORY.equals(category)) {
    price = price * (1 - DISCOUNT_RATE);
    }
    
    // Round price to 2 decimals (half up)
    price = roundToTwoDecimals(price);
    
    // Step 3: Check if should be recategorized to Premium Electronics
    if (price > PREMIUM_THRESHOLD && ELECTRONICS_CATEGORY.equals(row[3])) {
    category = PREMIUM_ELECTRONICS;
    }
    
    // Step 4: Determine price range based on final price
    String priceRange = determinePriceRange(price);
    
    // Format price to 2 decimal places
    String formattedPrice = String.format(”%.2f”, price);
    
    return new String[]{productId, name, formattedPrice, category, priceRange};
    }
  
  /**
  - Round price to 2 decimal places using HALF_UP rounding
    */
    private double roundToTwoDecimals(double price) {
    BigDecimal bd = new BigDecimal(price);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }
  
  /**
  - Determine price range category based on final price
    */
    private String determinePriceRange(double price) {
    if (price <= 10.00) {
    return “Low”;
    } else if (price <= 100.00) {
    return “Medium”;
    } else if (price <= 500.00) {
    return “High”;
    } else {
    return “Premium”;
    }
    }
  
  /**
  - Load phase: Write transformed data to output CSV file
    */
    private void load(List<String[]> transformedData) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))) {
    for (String[] row : transformedData) {
    writer.println(String.join(”,”, row));
    }
    }
    }
  
  /**
  - Print execution summary
    */
    private void printSummary(int totalRows, int transformedRows) {
    System.out.println(”\n” + “=”.repeat(50));
    System.out.println(“ETL PIPELINE EXECUTION SUMMARY”);
    System.out.println(”=”.repeat(50));
    System.out.println(“Input file: “ + INPUT_FILE);
    System.out.println(“Output file: “ + OUTPUT_FILE);
    System.out.println(“Total rows read: “ + totalRows);
    System.out.println(“Header rows: 1”);
    System.out.println(“Data rows processed: “ + (totalRows - 1));
    System.out.println(“Rows transformed: “ + (transformedRows - 1)); // Subtract header
    System.out.println(“Rows skipped: “ + Math.max(0, (totalRows - 1) - (transformedRows - 1)));
    System.out.println(“Output written to: “ + OUTPUT_FILE);
    System.out.println(”=”.repeat(50));
    }
    }
