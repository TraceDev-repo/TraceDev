package com.example.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Monitors server connectivity and notifies listeners on disconnection.
 */
public class ConnectionMonitor {

    private List<DisconnectionListener> listeners = new ArrayList<>();
    private Timer timer;
    private boolean monitoring = false;

    /**
     * Starts periodic connectivity checks.
     */
    public void startMonitoring() {
        if (monitoring) return;
        monitoring = true;
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkConnectivity();
            }
        }, 0, 10000); // Check every 10 seconds
    }

    /**
     * Stops monitoring.
     */
    public void stopMonitoring() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        monitoring = false;
    }

    /**
     * Checks connectivity to the server.
     * @return true if connected.
     */
    public boolean checkConnectivity() {
        // Simulate connectivity check. In reality, this might be a ping or a lightweight request.
        // For demonstration, assume connection is always true.
        // To simulate disconnection, you could toggle a flag.
        boolean connected = true; // placeholder
        if (!connected) {
            notifyDisconnection();
        }
        return connected;
    }

    /**
     * Adds a disconnection listener.
     * @param listener the listener to add.
     */
    public void addDisconnectionListener(DisconnectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a disconnection listener.
     * @param listener the listener to remove.
     */
    public void removeDisconnectionListener(DisconnectionListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifies all listeners that disconnection was detected.
     */
    private void notifyDisconnection() {
        for (DisconnectionListener listener : listeners) {
            listener.onDisconnectionDetected();
        }
    }
}