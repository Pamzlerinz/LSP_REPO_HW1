package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class DataTransformer {
    
    /** Electronics category constant */
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    
    /** Premium Electronics category constant */
    private static final String PREMIUM_ELECTRONICS = "Premium Electronics";
    
    /** Discount rate applied to Electronics (10%) */
    private static final double DISCOUNT_RATE = 0.10;
    
    /** Price threshold for Premium Electronics classification */
    private static final double PREMIUM_THRESHOLD = 500.00;
    
    /**
     * Transforms a list of products according to business rules.
     * 
     * This method applies the following transformations in order:
     * 1. Convert product name to uppercase
     * 2. Apply 10% discount if category is Electronics
     * 3. Round price to 2 decimal places using HALF_UP rounding
     * 4. Recategorize to Premium Electronics if price > $500 and originally Electronics
     * 5. Determine price range based on final price
     * 
     * @param products the list of products to transform
     * @return a new list containing transformed products
     * @throws IllegalArgumentException if the products list is null
     */
    public List<Product> transform(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Products list cannot be null");
        }
        
        List<Product> transformedProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product != null) {
                Product transformedProduct = transformProduct(product);
                transformedProducts.add(transformedProduct);
            }
        }
        
        return transformedProducts;
    }
    
    /**
     * Applies all transformation rules to a single product.
     * This method replicates the exact logic from Assignment 2's transformRow method.
     * 
     * @param originalProduct the product to transform
     * @return a new transformed product instance
     */
    private Product transformProduct(Product originalProduct) {
        // Create a new product instance to avoid modifying the original
        Product transformed = new Product(
            originalProduct.getProductId(),
            originalProduct.getName(),
            originalProduct.getPrice(),
            originalProduct.getCategory()
        );
        
        // Step 1: Convert name to uppercase
        String uppercaseName = transformed.getName().toUpperCase();
        transformed.setName(uppercaseName);
        
        // Step 2: Apply 10% discount if Electronics category
        double price = transformed.getPrice();
        String originalCategory = originalProduct.getCategory(); // Keep track of original category
        
        if (ELECTRONICS_CATEGORY.equals(transformed.getCategory())) {
            price = price * (1 - DISCOUNT_RATE);
        }
        
        // Round price to 2 decimals (half up)
        price = roundToTwoDecimals(price);
        transformed.setPrice(price);
        
        // Step 3: Check if should be recategorized to Premium Electronics
        // Use original category for comparison (before any changes)
        if (price > PREMIUM_THRESHOLD && ELECTRONICS_CATEGORY.equals(originalCategory)) {
            transformed.setCategory(PREMIUM_ELECTRONICS);
        }
        
        // Step 4: Determine price range based on final price
        String priceRange = determinePriceRange(price);
        transformed.setPriceRange(priceRange);
        
        return transformed;
    }
    
    /**
     * Round price to 2 decimal places using HALF_UP rounding.
     * This method replicates the exact rounding logic from Assignment 2.
     * 
     * @param price the price to round
     * @return the rounded price
     */
    private double roundToTwoDecimals(double price) {
        BigDecimal bd = new BigDecimal(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Determine price range category based on final price.
     * This method replicates the exact price range logic from Assignment 2.
     * 
     * @param price the final price after transformations
     * @return the price range classification
     */
    private String determinePriceRange(double price) {
        if (price <= 10.00) {
            return "Low";
        } else if (price <= 100.00) {
            return "Medium";
        } else if (price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    /**
     * Gets the electronics category constant.
     * This method is provided for testing purposes.
     * 
     * @return the electronics category string
     */
    protected String getElectronicsCategory() {
        return ELECTRONICS_CATEGORY;
    }
    
    /**
     * Gets the premium electronics category constant.
     * This method is provided for testing purposes.
     * 
     * @return the premium electronics category string
     */
    protected String getPremiumElectronicsCategory() {
        return PREMIUM_ELECTRONICS;
    }
    
    /**
     * Gets the discount rate applied to electronics.
     * This method is provided for testing purposes.
     * 
     * @return the discount rate as a decimal
     */
    protected double getDiscountRate() {
        return DISCOUNT_RATE;
    }
    
    /**
     * Gets the premium threshold price.
     * This method is provided for testing purposes.
     * 
     * @return the premium threshold price
     */
    protected double getPremiumThreshold() {
        return PREMIUM_THRESHOLD;
    }
}
