package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * 
 * The pipeline processes CSV data through three phases:
 * 1. Extract: Read products from CSV file
 * 2. Transform: Apply business rules and transformations
 * 3. Load: Write transformed data to output CSV file
 * 
 * @author Kafilat Sarki-Umar
 */
public class ETLPipeline {
    
    /** Input file path constant from Assignment 2 */
    private static final String INPUT_FILE = "data/products.csv";
    
    /** Output file path constant from Assignment 2 */
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    /** Component responsible for reading CSV files */
    private final CSVReader csvReader;
    
    /** Component responsible for transforming product data */
    private final DataTransformer dataTransformer;
    
    /** Component responsible for writing CSV files */
    private final CSVWriter csvWriter;
    
    /**
     * Constructs a new ETL pipeline with default components.
     * Initializes all required components for the ETL process.
     */
    public ETLPipeline() {
        this.csvReader = new CSVReader();
        this.dataTransformer = new DataTransformer();
        this.csvWriter = new CSVWriter();
    }
    
    /**
     * Constructs a new ETL pipeline with custom components.
     * This constructor allows for dependency injection and easier testing.
     * 
     * @param csvReader custom CSV reader implementation
     * @param dataTransformer custom data transformer implementation
     * @param csvWriter custom CSV writer implementation
     */
    public ETLPipeline(CSVReader csvReader, DataTransformer dataTransformer, CSVWriter csvWriter) {
        this.csvReader = csvReader;
        this.dataTransformer = dataTransformer;
        this.csvWriter = csvWriter;
    }
    
    /**
     * Main pipeline execution method that replicates Assignment 2's runPipeline().
     * Executes the complete ETL process with the same console output and error handling.
     */
    public void runPipeline() {
        try {
            // Extract phase
            List<Product> products = csvReader.readProducts(INPUT_FILE);
            int totalRowsRead = products.size() + 1; // +1 for header row
            System.out.println("Extract phase completed");
            
            // Transform phase
            List<Product> transformedProducts = dataTransformer.transform(products);
            System.out.println("Transform phase completed");
            
            // Load phase
            csvWriter.writeProducts(transformedProducts, OUTPUT_FILE);
            System.out.println("Load phase completed");
            
            // Print summary (matching Assignment 2 format exactly)
            printSummary(totalRowsRead, transformedProducts.size() + 1); // +1 for header in output
            
        } catch (IOException e) {
            System.err.println("Error during ETL process: " + e.getMessage());
        }
    }
    
    /**
     * Executes the ETL pipeline with custom file paths.
     * 
     * @param inputFilePath path to the source CSV file
     * @param outputFilePath path where the transformed CSV file will be written
     * @throws IOException if file reading or writing operations fail
     * @throws IllegalArgumentException if file paths are null or empty
     */
    public void processProducts(String inputFilePath, String outputFilePath) throws IOException {
        validateFilePaths(inputFilePath, outputFilePath);
        
        try {
            // Extract Phase
            System.out.println("Starting ETL Pipeline...");
            System.out.println("Phase 1: Extracting data from " + inputFilePath);
            List<Product> products = csvReader.readProducts(inputFilePath);
            System.out.println("Successfully extracted " + products.size() + " products");
            
            // Transform Phase
            System.out.println("Phase 2: Transforming data...");
            List<Product> transformedProducts = dataTransformer.transform(products);
            System.out.println("Successfully transformed " + transformedProducts.size() + " products");
            
            // Load Phase
            System.out.println("Phase 3: Loading data to " + outputFilePath);
            csvWriter.writeProducts(transformedProducts, outputFilePath);
            System.out.println("ETL Pipeline completed successfully!");
            
        } catch (IOException e) {
            System.err.println("ETL Pipeline failed during file operation: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("ETL Pipeline failed with unexpected error: " + e.getMessage());
            throw new RuntimeException("ETL Pipeline execution failed", e);
        }
    }
    
    /**
     * Print execution summary that exactly matches Assignment 2's printSummary method.
     * 
     * @param totalRows total rows read from input (including header)
     * @param transformedRows total rows in output (including header)
     */
    private void printSummary(int totalRows, int transformedRows) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ETL PIPELINE EXECUTION SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Input file: " + INPUT_FILE);
        System.out.println("Output file: " + OUTPUT_FILE);
        System.out.println("Total rows read: " + totalRows);
        System.out.println("Header rows: 1");
        System.out.println("Data rows processed: " + (totalRows - 1));
        System.out.println("Rows transformed: " + (transformedRows - 1)); // Subtract header
        System.out.println("Rows skipped: " + Math.max(0, (totalRows - 1) - (transformedRows - 1)));
        System.out.println("Output written to: " + OUTPUT_FILE);
        System.out.println("=".repeat(50));
    }
    
    /**
     * Validates that the provided file paths are not null or empty.
     * 
     * @param inputFilePath the input file path to validate
     * @param outputFilePath the output file path to validate
     * @throws IllegalArgumentException if any file path is null or empty
     */
    private void validateFilePaths(String inputFilePath, String outputFilePath) {
        if (inputFilePath == null || inputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Input file path cannot be null or empty");
        }
        if (outputFilePath == null || outputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Output file path cannot be null or empty");
        }
    }
    
    /**
     * Gets the CSV reader component.
     * This method provides access to the reader for testing purposes.
     * 
     * @return the CSV reader instance
     */
    protected CSVReader getCsvReader() {
        return csvReader;
    }
    
    /**
     * Gets the data transformer component.
     * This method provides access to the transformer for testing purposes.
     * 
     * @return the data transformer instance
     */
    protected DataTransformer getDataTransformer() {
        return dataTransformer;
    }
    
    /**
     * Gets the CSV writer component.
     * This method provides access to the writer for testing purposes.
     * 
     * @return the CSV writer instance
     */
    protected CSVWriter getCsvWriter() {
        return csvWriter;
    }
    
    /**
     * Main method that replicates Assignment 2's main method exactly.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        pipeline.runPipeline();
    }
}
