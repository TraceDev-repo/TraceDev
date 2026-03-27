package com.etour.server;

/**
 * Interface for ETour server communication.
 */
public interface IETOURServer {
    boolean connect();
    void disconnect();
    boolean isConnected();
    boolean sendData(Object data);
}