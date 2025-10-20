package org.howard.edu.lsp.midterm.question2;

public class Main {
    
    public static void main(String[] args) {
        // Test circle area
        double circleArea = AreaCalculator.area(3.0);
        System.out.println("Circle radius 3.0 → area = " + circleArea);
        
        // Test rectangle area
        double rectangleArea = AreaCalculator.area(5.0, 2.0);
        System.out.println("Rectangle 5.0 x 2.0 → area = " + rectangleArea);
        
        // Test triangle area
        double triangleArea = AreaCalculator.area(10, 6);
        System.out.println("Triangle base 10, height 6 → area = " + triangleArea);
        
        // Test square area
        double squareArea = AreaCalculator.area(4);
        System.out.println("Square side 4 → area = " + squareArea);
        
        // Demonstrate exception handling with invalid input
        try {
            // Attempting to calculate area with negative radius
            double invalidArea = AreaCalculator.area(-5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Cannot calculate area with negative dimensions.");
        }
    }
}
