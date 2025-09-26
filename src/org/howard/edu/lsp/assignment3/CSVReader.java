package org.howard.edu.lsp.assignment3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class CSVWriter {
    
    /** CSV header matching Assignment 2 output format */
    private static final String CSV_HEADER = "ProductID,Name,Price,Category,PriceRange";
    
    /**
     * Writes a list of products to a CSV file with the exact format from Assignment 2.
     * 
     * The output format follows the pattern:
     * ProductID,Name,Price,Category,PriceRange
     * 
     * @param products the list of products to write
     * @param filePath the path where the CSV file will be created
     * @throws IOException if the file cannot be written
     * @throws IllegalArgumentException if products list or file path is null
     */
    public void writeProducts(List<Product> products, String filePath) throws IOException {
        validateInputs(products, filePath);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write header row
            writer.println(CSV_HEADER);
            
            // Write product data
            for (Product product : products) {
                if (product != null) {
                    String csvLine = formatProductToCsv(product);
                    writer.println(csvLine);
                }
            }
            
        } catch (IOException e) {
            throw new IOException("Failed to write to file: " + filePath + ". " + e.getMessage(), e);
        }
    }
    
    /**
     * Formats a single Product object into CSV format.
     * This method replicates the exact output format from Assignment 2.
     * 
     * @param product the product to format
     * @return a CSV-formatted string representing the product
     */
    private String formatProductToCsv(Product product) {
        // Format price to 2 decimal places (matching Assignment 2 format)
        String formattedPrice = String.format("%.2f", product.getPrice());
        
        // Create CSV line with exact column order from Assignment 2
        String[] csvFields = {
            product.getProductId(),
            product.getName(),
            formattedPrice,
            product.getCategory(),
            product.getPriceRange()
        };
        
        // Join with commas (using String.join like Assignment 2)
        return String.join(",", csvFields);
    }
    
    /**
     * Validates input parameters for the writeProducts method.
     * 
     * @param products the products list to validate
     * @param filePath the file path to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateInputs(List<Product> products, String filePath) {
        if (products == null) {
            throw new IllegalArgumentException("Products list cannot be null");
        }
        
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
    }
    
    /**
     * Gets the CSV header string.
     * This method is provided for testing purposes.
     * 
     * @return the CSV header string
     */
    protected String getCsvHeader() {
        return CSV_HEADER;
    }
}
