package org.howard.edu.lsp.midterm.question4;


public class DoorLock extends Device implements Networked, BatteryPowered {
    
    /** Battery level as a percentage (0-100) */
    private int batteryPercent;
    
    /**
     * Constructs a new DoorLock with the specified ID, location, and initial battery level.
     * 
     * @param id the unique identifier for this door lock
     * @param location the physical location of the door lock
     * @param initialBattery the initial battery percentage (must be 0-100)
     * @throws IllegalArgumentException if id or location is invalid, or if initialBattery is outside 0-100
     */
    public DoorLock(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }
    
    /**
     * Connects the door lock to the network.
     */
    @Override
    public void connect() {
        setConnected(true);
    }
    
    /**
     * Disconnects the door lock from the network.
     */
    @Override
    public void disconnect() {
        setConnected(false);
    }
    
    /**
     * Checks if the door lock is currently connected to the network.
     * 
     * @return true if connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        return super.isConnected();
    }
    
    /**
     * Gets the current battery percentage of the door lock.
     * 
     * @return battery level as a percentage (0-100)
     */
    @Override
    public int getBatteryPercent() {
        return batteryPercent;
    }
    
    /**
     * Sets the battery percentage of the door lock.
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
     * Gets the current status of the door lock including ID, location, connection status, and battery level.
     * 
     * @return a formatted string describing the door lock's status
     */
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}
