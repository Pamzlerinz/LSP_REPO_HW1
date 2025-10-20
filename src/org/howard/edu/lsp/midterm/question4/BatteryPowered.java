package org.howard.edu.lsp.midterm.question4;


public interface BatteryPowered {
    /**
     * Gets the current battery percentage.
     * 
     * @return battery level as a percentage (0-100)
     */
    int getBatteryPercent();
    
    /**
     * Sets the battery percentage.
     * 
     * @param percent the new battery level (must be 0-100 inclusive)
     * @throws IllegalArgumentException if percent is outside the range 0-100
     */
    void setBatteryPercent(int percent);
}
