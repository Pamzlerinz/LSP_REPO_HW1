package org.howard.edu.lsp.assignment3;

public class Product {
    
    /** Unique identifier for the product */
    private String productId;
    
    /** Name of the product */
    private String name;
    
    /** Price of the product in dollars */
    private double price;
    
    /** Category classification of the product */
    private String category;
    
    /** Price range classification (Low, Medium, High, Premium) */
    private String priceRange;
    
    /**
     * Default constructor creating an empty product.
     */
    public Product() {
        // Default constructor
    }
    
    /**
     * Constructs a new Product with specified attributes.
     * 
     * @param productId unique identifier for the product
     * @param name name of the product
     * @param price price in dollars
     * @param category category classification
     */
    public Product(String productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = ""; // Will be calculated during transformation
    }
    
    /**
     * Constructs a new Product with all attributes including price range.
     * 
     * @param productId unique identifier for the product
     * @param name name of the product
     * @param price price in dollars
     * @param category category classification
     * @param priceRange price range classification
     */
    public Product(String productId, String name, double price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }
    
    /**
     * Gets the product ID.
     * 
     * @return the unique product identifier
     */
    public String getProductId() {
        return productId;
    }
    
    /**
     * Sets the product ID.
     * 
     * @param productId the unique product identifier
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the product name.
     * 
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the product price.
     * 
     * @return the product price in dollars
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the product price.
     * 
     * @param price the product price in dollars
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Gets the product category.
     * 
     * @return the product category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the product category.
     * 
     * @param category the product category
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Gets the price range classification.
     * 
     * @return the price range (Low, Medium, High, Premium)
     */
    public String getPriceRange() {
        return priceRange;
    }
    
    /**
     * Sets the price range classification.
     * 
     * @param priceRange the price range classification
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
    
    /**
     * Returns a string representation of the product.
     * 
     * @return formatted string containing all product attributes
     */
    @Override
    public String toString() {
        return String.format("Product{productId='%s', name='%s', price=%.2f, category='%s', priceRange='%s'}",
                productId, name, price, category, priceRange);
    }
    
    /**
     * Checks if this product is equal to another object.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0 &&
               productId != null ? productId.equals(product.productId) : product.productId == null &&
               name != null ? name.equals(product.name) : product.name == null &&
               category != null ? category.equals(product.category) : product.category == null &&
               priceRange != null ? priceRange.equals(product.priceRange) : product.priceRange == null;
    }
    
    /**
     * Generates hash code for the product.
     * 
     * @return hash code value
     */
    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Double.hashCode(price);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (priceRange != null ? priceRange.hashCode() : 0);
        return result;
    }
}
