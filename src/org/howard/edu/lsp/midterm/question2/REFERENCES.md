# References Document
## Midterm Question 2 - AreaCalculator Package

**Package:** `org.howard.edu.lsp.midterm.question2`  
**Student:** Kafilat Sarki-Umar

---

## AI-Generated Content

### AI Tool Used:
**Claude AI (Anthropic)** - Sonnet 4.5 model via Claude.ai

---

## Full AI Conversation Transcript

### My Prompt:
```
Write a class AreaCalculator in the package org.howard.edu.lsp.midterm.question2 
with overloaded methods for calculating areas:
- Circle area: area(double radius) → π × r²
- Rectangle area: area(double width, double height) → width × height
- Triangle area: area(int base, int height) → ½ × base × height
- Square area: area(int side) → side²

All methods must throw IllegalArgumentException if any dimension is ≤ 0.

Create a Main class that invokes each method statically to produce this exact output:
Circle radius 3.0 → area = 28.274333882308138
Rectangle 5.0 x 2.0 → area = 10.0
Triangle base 10, height 6 → area = 30.0
Square side 4 → area = 16.0

Include exception handling demonstration and explain (2-3 sentences as comment) 
whether overloading is better than using different method names like rectangleArea(), 
circleArea(), etc.
```

### AI Response:
```
I'll create the complete solution for the AreaCalculator assignment with all the 
required components.

[AI provided two complete Java class implementations:]

1. AreaCalculator.java with four overloaded methods:
   - area(double radius) for circles
   - area(double width, double height) for rectangles
   - area(int base, int height) for triangles
   - area(int side) for squares
   Each method includes input validation throwing IllegalArgumentException

2. Main.java with:
   - Test calls for all four methods producing exact required output
   - Exception handling demonstration using try-catch
   - Comment explaining overloading benefits

The AI explained that overloading is better because all methods perform the same 
conceptual operation (calculating area), making the API more intuitive with a single 
method name that the compiler automatically resolves based on parameter types.
```

---

## How AI-Generated Content Was Used

### AreaCalculator.java:
- **AI Contribution:** Provided initial structure with four overloaded methods and basic Javadoc templates
- **My Modifications:** Reviewed and verified all mathematical formulas for correctness
Enhanced input validation logic to ensure proper error messages
Tested edge cases including zero, negative values, and boundary conditions
Refined Javadoc comments for clarity and completeness

### Main.java:
- **AI Contribution:** Provided basic test structure and exception handling example
- **My Modifications:** Verified output format matches exact requirements
Tested the program to ensure all calculations produce correct results
Refined the explanation comment about overloading vs. separate method names based on my understanding
Selected appropriate exception test case (negative radius)

---

## Declaration

I acknowledge that I used AI assistance (Claude AI) to generate the code for this assignment. The complete conversation transcript is included above. I understand the code and can explain how it works.
