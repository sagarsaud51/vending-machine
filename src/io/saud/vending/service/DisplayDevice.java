package io.saud.vending.service;

public interface DisplayDevice {

    void displayMessage(String message);
    String orderProduct(String orderCode);
}
