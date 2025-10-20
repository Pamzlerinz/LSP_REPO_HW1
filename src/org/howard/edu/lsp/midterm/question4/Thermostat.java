package org.howard.edu.lsp.midterm.question4;


public class Thermostat extends Device implements Networked {
    
    /** Current temperature setting in Celsius */
    private double temperatureC;
    
    /**
     * Constructs a new Thermostat with the specified ID, location, and initial temperature.
     * 
     * @param id the unique identifier for this thermostat
     * @param location the physical location of the thermostat
     * @param initialTempC the initial temperature setting in Celsius
     * @throws IllegalArgumentException if id or location is invalid
     */
    public Thermostat(String id, String location, double initialTempC) {
        super(id, location);
        this.temperatureC = initialTempC;
    }
    
    /**
     * Gets the current temperature setting in Celsius.
     * 
     * @return the temperature in Celsius
     */
    public double getTemperatureC() {
        return temperatureC;
    }
    
    /**
     * Sets the temperature setting in Celsius.
     * 
     * @param temperatureC the new temperature in Celsius
     */
    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }
    
    /**
     * Connects the thermostat to the network.
     */
    @Override
    public void connect() {
        setConnected(true);
    }
    
    /**
     * Disconnects the thermostat from the network.
     */
    @Override
    public void disconnect() {
        setConnected(false);
    }
    
    /**
     * Checks if the thermostat is currently connected to the network.
     * 
     * @return true if connected, false otherwise
     */
    @Override
    public boolean isConnected() {
        return super.isConnected();
    }
    
    /**
     * Gets the current status of the thermostat including ID, location, connection status, and temperature.
     * 
     * @return a formatted string describing the thermostat's status
     */
    @Override
    public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Thermostat[id=" + getId() + ", loc=" + getLocation() +
               ", conn=" + connStatus + ", tempC=" + temperatureC + "]";
    }
}
