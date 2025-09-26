# AI Prompts and Responses for Assignment 3

## Prompt 1: Initial Assignment Understanding and Design Help

**My Prompt:**
```
I need help with Assignment 3. I have to redesign my Assignment 2 ETL pipeline to be more object-oriented. Here's what I need to do:

Purpose: In Assignment 2, you built an ETL pipeline in Java. In this assignment, you will redesign your solution to be more object-oriented. The goal is to practice object-oriented decomposition, not perfection. You must also use a generative AI assistant to help with the redesign and explain how you used or adapted its suggestions.

Here's my Assignment 2 code: [provided my monolithic ETLPipeline.java code]

How should I break this down into multiple classes following object-oriented principles?
```

**AI Response Excerpt:**
```
I'll help you redesign your Assignment 2 ETL pipeline to be more object-oriented. Based on your monolithic ETLPipeline class, I recommend breaking it down into these classes:

1. Product - Data model encapsulating product attributes
2. CSVReader - Handles file input and parsing  
3. DataTransformer - Contains business logic for transformations
4. CSVWriter - Manages file output and formatting
5. ETLPipeline - Orchestrates the entire process

This follows the Single Responsibility Principle where each class has one clear purpose. Your current structure mixes file I/O, parsing, business logic, and coordination all in one class.
```

**How I Used This:**
I adopted the core structure but simplified it to 4 classes instead of 5. I decided to skip the Product class to keep the design simpler while still demonstrating object-oriented principles. I created CSVReader, DataTransformer, CSVWriter, and ETLPipeline classes.

---

## Prompt 2: Maintaining Exact Assignment 2 Functionality

**My Prompt:**
```
I need to ensure my Assignment 3 produces EXACTLY the same output as Assignment 2. What specific things should I pay attention to when redesigning this? Here are the key business rules from Assignment 2:

- 10% discount to Electronics category
- Convert names to UPPERCASE  
- Recategorize to "Premium Electronics" if post-discount price > $500 AND original category was Electronics
- Price ranges: Low (≤$10), Medium ($10.01-$100), High ($100.01-$500), Premium ($500.01+)
- Round prices using HALF_UP to 2 decimals
```

**AI Response Excerpt:**
```
To maintain exact functionality, pay special attention to:

1. Transform Order: Your Assignment 2 does: uppercase → discount → recategorization → price range
2. Business Rules: The Premium Electronics check must use the ORIGINAL category (before any changes)
3. Rounding: You use BigDecimal with HALF_UP rounding - keep this exact implementation
4. Output Format: ProductID,Name,Price,Category,PriceRange with 2 decimal price formatting
5. Error Handling: Same console messages for missing files and empty files

The key is preserving the exact sequence and logic while organizing it into separate classes.
```

**How I Used This:**
This was crucial for my DataTransformer implementation. I made sure to preserve the exact transformation order and used the same BigDecimal rounding logic. I was especially careful about the Premium Electronics logic - checking the original category before any modifications, just like Assignment 2.

---

## Prompt 3: Object-Oriented Design Principles Application

**My Prompt:**
```
How can I apply object-oriented principles like encapsulation and single responsibility to my ETL pipeline redesign? I want to make sure I'm demonstrating proper OO concepts.
```

**AI Response Excerpt:**
```
For your ETL pipeline, apply these OO principles:

Single Responsibility Principle:
- CSVReader: Only responsible for reading and parsing CSV files
- DataTransformer: Only handles business logic and data transformations  
- CSVWriter: Only manages output formatting and file writing
- ETLPipeline: Only coordinates the workflow

Encapsulation:
- Keep implementation details private within each class
- Provide clean public interfaces for class interactions
- Hide complex logic behind simple method calls

This is much better than your Assignment 2 where one class mixed file I/O, parsing, transformations, and coordination.
```

**How I Used This:**
I structured my classes exactly along these responsibility lines. Each class has a clear, single purpose and clean public methods. I made sure the DataTransformer only contains business logic, CSVReader only handles file reading, etc. This made testing much easier since I could test each component independently.

---

## Prompt 4: Error Handling Strategy

**My Prompt:**
```
My Assignment 2 has centralized error handling in the main runPipeline() method. How should I distribute error handling in an object-oriented design while maintaining the same user experience?
```

**AI Response Excerpt:**
```
For object-oriented error handling, use a layered approach:

1. Component-Level: Each class handles errors it can recover from or provides meaningful error messages
2. Coordination-Level: ETLPipeline catches and coordinates user-facing messages
3. Specific Exceptions: Use appropriate exception types (IOException for files, IllegalArgumentException for data)

For example:
- CSVReader throws IOException with specific file-related messages
- DataTransformer can validate inputs and throw IllegalArgumentException
- ETLPipeline catches these and provides the same user-friendly console output as Assignment 2
```

**How I Used This:**
I implemented this layered approach. My CSVReader throws specific IOExceptions for file problems, DataTransformer handles validation, and ETLPipeline coordinates the overall error handling to match Assignment 2's console output exactly. This preserved the user experience while improving code organization.

---

## Prompt 5: Code Implementation and Documentation

**My Prompt:**
```
Can you help me implement the DataTransformer class that applies the exact same business logic as my Assignment 2? I need to make sure the transformation sequence is identical.
```

**AI Response Excerpt:**
```
Here's a DataTransformer implementation that replicates your Assignment 2 logic:

```java
public class DataTransformer {
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    private static final String PREMIUM_ELECTRONICS = "Premium Electronics";
    private static final double DISCOUNT_RATE = 0.10;
    private static final double PREMIUM_THRESHOLD = 500.00;
    
    public List<String[]> transform(List<String[]> rawData) {
        // Implementation that follows your exact transformation sequence
    }
}
