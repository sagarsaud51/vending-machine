package io.saud.vending;

import io.saud.vending.service.VendingProcessor;

public class Main {

    public static void main(String[] args) {
        VendingProcessor vendingProcessor = new VendingProcessor();
        vendingProcessor.init();
    }
}
