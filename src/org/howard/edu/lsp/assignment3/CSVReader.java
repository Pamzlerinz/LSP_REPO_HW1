package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    
    /** Expected number of columns in the input CSV file */
    private static final int EXPECTED_COLUMNS = 4;
    
    /**
     * Reads product data from a CSV file and converts it to a list of Product objects.
     * 
     * The method expects CSV files with the following format:
     * ProductID,Name,Price,Category
     * 
     * @param filePath the path to the CSV file to read
     * @return a list of Product objects parsed from the CSV file
     * @throws IOException if the file cannot be read or does not exist
     * @throws IllegalArgumentException if the file path is null or empty
     */
    public List<Product> readProducts(String filePath) throws IOException {
        validateFilePath(filePath);
        
        // Check if file exists first
        File inputFile = new File(filePath);
        if (!inputFile.exists()) {
            throw new IOException("Input file '" + filePath + "' not found. Please ensure the file exists.");
        }
        
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Skip header row (first line)
                if (lineNumber == 1) {
                    continue;
                }
                
                try {
                    Product product = parseProductFromLine(line, lineNumber);
                    if (product != null) {
                        products.add(product);
                    }
                } catch (Exception e) {
                    System.err.println("Warning: Skipping row " + lineNumber + " due to error: " + e.getMessage());
                    // Continue processing other lines instead of failing completely
                }
            }
            
        } catch (IOException e) {
            throw new IOException("Failed to read file: " + filePath + ". " + e.getMessage(), e);
        }
        
        return products;
    }
    
    /**
     * Parses a single CSV line into a Product object.
     * 
     * @param line the CSV line to parse
     * @param lineNumber the line number for error reporting
     * @return a Product object created from the CSV line
     * @throws IllegalArgumentException if the line format is invalid
     */
    private Product parseProductFromLine(String line, int lineNumber) {
        String[] columns = line.split(",");
        
        // Trim whitespace from each column
        for (int i = 0; i < columns.length; i++) {
            columns[i] = columns[i].trim();
        }
        
        if (columns.length < EXPECTED_COLUMNS) {
            throw new IllegalArgumentException(
                String.format("Insufficient columns: expected %d but found %d", 
                            EXPECTED_COLUMNS, columns.length));
        }
        
        try {
            String productId = columns[0];
            String name = columns[1];
            String priceStr = columns[2];
            String category = columns[3];
            
            // Parse price
            double price = Double.parseDouble(priceStr);
            
            // Basic validation
            validateProductData(productId, name, category, price, lineNumber);
            
            return new Product(productId, name, price, category);
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                String.format("Invalid price format in line %d: %s", lineNumber, e.getMessage()));
        }
    }
    
    /**
     * Validates product data for basic business rules.
     * 
     * @param productId product ID
     * @param name product name
     * @param category product category
     * @param price product price
     * @param lineNumber line number for error reporting
     * @throws IllegalArgumentException if validation fails
     */
    private void validateProductData(String productId, String name, String category, double price, int lineNumber) {
        if (productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty in line " + lineNumber);
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty in line " + lineNumber);
        }
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty in line " + lineNumber);
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative in line " + lineNumber);
        }
    }
    
    /**
     * Validates that the provided file path is not null or empty.
     * 
     * @param filePath the file path to validate
     * @throws IllegalArgumentException if the file path is null or empty
     */
    private void validateFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
    }
    
    /**
     * Gets the expected number of columns in the CSV file.
     * This method is provided for testing purposes.
     * 
     * @return the expected number of columns
     */
    protected int getExpectedColumns() {
        return EXPECTED_COLUMNS;
    }
}
