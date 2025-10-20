package org.howard.edu.lsp.midterm.question4;


public class Camera extends Device implements Networked, BatteryPowered {
    
    /** Battery level as a percentage (0-100) */
    private int batteryPercent;
    
    /**
     * Constructs a new Camera with the specified ID, location, and initial battery level.
     * 
     * @param id the unique identifier for this camera
     * @param location the physical location of the camera
     * @param initialBattery the initial battery percentage (must be 0-100)
     * @throws IllegalArgumentException if id or location is invalid, or if initialBattery is outside 0-100
     */
    public Camera(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }
    
    /**
     * Connects the camera to the network.
     */
    @Override
    public void connect() {
        setConnected(true);
    }
    
    /**
     * Disconnects the camera from the network.
     */
    @Override
    public void disconnect() {
        setConnected(false);
    }
    
    /**
     * Checks if the camera is currently connected to the network.
     * 
     * @return true if connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        return super.isConnected();
    }
    
    /**
     * Gets the current battery percentage of the camera.
     * 
     * @return battery level as a percentage (0-100)
     */
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }
    
    /**
     * Sets the battery percentage of the camera.
     * 
     * @param percent the new battery level (must be 0-100 inclusive)
     * @throws IllegalArgumentException if percent is outside the range 0-100
     */
    @Override
    public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("battery 0..100");
        }
        this.batteryPercent = percent;
    }
    
    /**
     * Gets the current status of the camera including ID, location, connection status, and battery level.
     * 
     * @return a formatted string describing the camera's status
     */
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Camera[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}
