package org.howard.edu.lsp.midterm.question4;

public interface Networked {
    /**
     * Connects the device to the network, bringing it online.
     */
    void connect();
    
    /**
     * Disconnects the device from the network, taking it offline.
     */
    void disconnect();
    
    /**
     * Checks if the device is currently connected to the network.
     * 
     * @return true if connected, false otherwise
     */
    boolean isConnected();
}
