package io.saud.vending.service.impl;

import io.saud.vending.service.DisplayDevice;

public class CompanyBDisplayDeviceImpl implements DisplayDevice {
    @Override
    public void displayMessage(String welcomeMessage) {
        System.out.println(welcomeMessage);
    }


    @Override
    public String orderProduct(String orderCode) {
        return orderCode;
    }
}
