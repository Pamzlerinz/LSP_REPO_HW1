package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * JUnit test class for IntegerSet.
 * Tests all public methods with normal and edge cases.
 * 
 * @author Kafilat Sarki-Umar
 */
public class IntegerSetTest {
    
    private IntegerSet set1;
    private IntegerSet set2;
    
    /**
     * Sets up test fixtures before each test method.
     */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }
    
    /**
     * Tests the clear method.
     */
    @Test
    @DisplayName("Test clear method")
    public void testClear() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
        
        set1.clear();
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the length method with various set sizes.
     */
    @Test
    @DisplayName("Test length method")
    public void testLength() {
        assertEquals(0, set1.length());
        
        set1.add(1);
        assertEquals(1, set1.length());
        
        set1.add(2);
        set1.add(3);
        assertEquals(3, set1.length());
        
        // Adding duplicate should not change length
        set1.add(2);
        assertEquals(3, set1.length());
    }
    
    /**
     * Tests the equals method with identical sets.
     */
    @Test
    @DisplayName("Test equals with identical sets")
    public void testEqualsIdentical() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(1);
        set2.add(2);
        set2.add(3);
        
        assertTrue(set1.equals(set2));
        assertTrue(set2.equals(set1));
    }
    
    /**
     * Tests the equals method with sets in different order.
     */
    @Test
    @DisplayName("Test equals with different order")
    public void testEqualsDifferentOrder() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(3);
        set2.add(1);
        set2.add(2);
        
        assertTrue(set1.equals(set2));
    }
    
    /**
     * Tests the equals method with different sets.
     */
    @Test
    @DisplayName("Test equals with different sets")
    public void testEqualsDifferent() {
        set1.add(1);
        set1.add(2);
        
        set2.add(1);
        set2.add(3);
        
        assertFalse(set1.equals(set2));
    }
    
    /**
     * Tests the equals method with empty sets.
     */
    @Test
    @DisplayName("Test equals with empty sets")
    public void testEqualsEmpty() {
        assertTrue(set1.equals(set2));
    }
    
    /**
     * Tests the equals method with null and non-IntegerSet objects.
     */
    @Test
    @DisplayName("Test equals with null and other types")
    public void testEqualsNullAndOtherTypes() {
        assertFalse(set1.equals(null));
        assertFalse(set1.equals("not a set"));
        assertFalse(set1.equals(Integer.valueOf(5)));
    }
    
    /**
     * Tests the contains method.
     */
    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        assertFalse(set1.contains(1));
        
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(4));
    }
    
    /**
     * Tests the largest method with normal case.
     */
    @Test
    @DisplayName("Test largest method")
    public void testLargest() {
        set1.add(5);
        set1.add(10);
        set1.add(3);
        set1.add(7);
        
        assertEquals(10, set1.largest());
    }
    
    /**
     * Tests the largest method with single element.
     */
    @Test
    @DisplayName("Test largest with single element")
    public void testLargestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.largest());
    }
    
    /**
     * Tests that largest throws exception on empty set.
     */
    @Test
    @DisplayName("Test largest throws exception on empty set")
    public void testLargestEmptySet() {
        assertThrows(IllegalStateException.class, () -> set1.largest());
    }
    
    /**
     * Tests the smallest method with normal case.
     */
    @Test
    @DisplayName("Test smallest method")
    public void testSmallest() {
        set1.add(5);
        set1.add(10);
        set1.add(3);
        set1.add(7);
        
        assertEquals(3, set1.smallest());
    }
    
    /**
     * Tests the smallest method with single element.
     */
    @Test
    @DisplayName("Test smallest with single element")
    public void testSmallestSingleElement() {
        set1.add(42);
        assertEquals(42, set1.smallest());
    }
    
    /**
     * Tests that smallest throws exception on empty set.
     */
    @Test
    @DisplayName("Test smallest throws exception on empty set")
    public void testSmallestEmptySet() {
        assertThrows(IllegalStateException.class, () -> set1.smallest());
    }
    
    /**
     * Tests the add method.
     */
    @Test
    @DisplayName("Test add method")
    public void testAdd() {
        assertTrue(set1.isEmpty());
        
        set1.add(1);
        assertTrue(set1.contains(1));
        assertEquals(1, set1.length());
        
        // Adding duplicate should not increase length
        set1.add(1);
        assertEquals(1, set1.length());
        
        set1.add(2);
        assertEquals(2, set1.length());
    }
    
    /**
     * Tests the remove method.
     */
    @Test
    @DisplayName("Test remove method")
    public void testRemove() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
        
        // Removing non-existent element should do nothing
        set1.remove(99);
        assertEquals(2, set1.length());
    }
    
    /**
     * Tests the union method.
     */
    @Test
    @DisplayName("Test union method")
    public void testUnion() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.union(set2);
        
        assertEquals(4, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }
    
    /**
     * Tests union with empty set.
     */
    @Test
    @DisplayName("Test union with empty set")
    public void testUnionWithEmpty() {
        set1.add(1);
        set1.add(2);
        
        set1.union(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests union with disjoint sets.
     */
    @Test
    @DisplayName("Test union with disjoint sets")
    public void testUnionDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.union(set2);
        
        assertEquals(4, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }
    
    /**
     * Tests the intersect method.
     */
    @Test
    @DisplayName("Test intersect method")
    public void testIntersect() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(4));
    }
    
    /**
     * Tests intersect with no common elements.
     */
    @Test
    @DisplayName("Test intersect with disjoint sets")
    public void testIntersectDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.intersect(set2);
        
        assertEquals(0, set1.length());
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests intersect with empty set.
     */
    @Test
    @DisplayName("Test intersect with empty set")
    public void testIntersectWithEmpty() {
        set1.add(1);
        set1.add(2);
        
        set1.intersect(set2);
        
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the diff method.
     */
    @Test
    @DisplayName("Test diff method")
    public void testDiff() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        
        set2.add(3);
        set2.add(4);
        set2.add(5);
        
        set1.diff(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertFalse(set1.contains(3));
        assertFalse(set1.contains(4));
    }
    
    /**
     * Tests diff with no common elements.
     */
    @Test
    @DisplayName("Test diff with disjoint sets")
    public void testDiffDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.diff(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests diff with empty set.
     */
    @Test
    @DisplayName("Test diff with empty set")
    public void testDiffWithEmpty() {
        set1.add(1);
        set1.add(2);
        
        set1.diff(set2);
        
        assertEquals(2, set1.length());
    }
    
    /**
     * Tests the complement method.
     */
    @Test
    @DisplayName("Test complement method")
    public void testComplement() {
        set1.add(1);
        set1.add(2);
        
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }
    
    /**
     * Tests complement with disjoint sets.
     */
    @Test
    @DisplayName("Test complement with disjoint sets")
    public void testComplementDisjoint() {
        set1.add(1);
        set1.add(2);
        
        set2.add(3);
        set2.add(4);
        
        set1.complement(set2);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }
    
    /**
     * Tests complement with empty other set.
     */
    @Test
    @DisplayName("Test complement with empty other set")
    public void testComplementWithEmptyOther() {
        set1.add(1);
        set1.add(2);
        
        set1.complement(set2);
        
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the isEmpty method.
     */
    @Test
    @DisplayName("Test isEmpty method")
    public void testIsEmpty() {
        assertTrue(set1.isEmpty());
        
        set1.add(1);
        assertFalse(set1.isEmpty());
        
        set1.remove(1);
        assertTrue(set1.isEmpty());
    }
    
    /**
     * Tests the toString method.
     */
    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        assertEquals("[]", set1.toString());
        
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        String result = set1.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
    }
    
    /**
     * Tests that union modifies the original set (mutation check).
     */
    @Test
    @DisplayName("Test union mutates original set")
    public void testUnionMutation() {
        set1.add(1);
        IntegerSet originalSet1 = set1;
        
        set2.add(2);
        set1.union(set2);
        
        assertSame(originalSet1, set1);
        assertTrue(set1.contains(2));
    }
    
    /**
     * Tests operation with self (union with itself).
     */
    @Test
    @DisplayName("Test union with self")
    public void testUnionWithSelf() {
        set1.add(1);
        set1.add(2);
        
        set1.union(set1);
        
        assertEquals(2, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
    }
}
