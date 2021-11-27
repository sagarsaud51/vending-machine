package io.saud.vending.service;

import io.saud.vending.constants.MessageConstants;
import io.saud.vending.exception.VendingMachineException;
import io.saud.vending.helper.ItemInitializer;
import io.saud.vending.service.abstracts.VendingMachine;

import java.util.Scanner;

public class VendingProcessor {

    VendingMachine vendingMachine;
    Scanner scanner = new Scanner(System.in);

    //initialize vending machine
    public void init() {
        vendingMachine = new WowVendingMachine();
        ItemInitializer.addItemsInVendingMachine(vendingMachine);
        handleUserInput();
        vendingMachine.displayDevice.displayMessage("Terminating the program");
    }

    //handle user input
    private void handleUserInput() {
        //flag to check if user wants to exit
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String userInput = scanner.nextLine();
            try {
                switch (userInput) {
                    case "1":
                        vendingMachine.displayDevice.displayMessage("Enter amount to add cash");
                        vendingMachine.loadCash(validateAmount(scanner.nextDouble()));
                        break;
                    case "2":
                        vendingMachine.displayDevice.displayMessage("Enter the coin to add");
                        vendingMachine.loadCoins(validateAmount(scanner.nextDouble()));
                        break;
                    case "3":
                        vendingMachine.displayDevice.displayMessage("Enter the item code");
                        vendingMachine.placeOrder(scanner.nextLine().trim().toUpperCase());

                        break;
                    case "4":
                        vendingMachine.cancel();
                        break;
                    case "5":
                        exit = true;
                    default:
                        vendingMachine.displayDevice.displayMessage("Please enter a valid option");
                        break;
                }
            } catch (Exception e) {
                vendingMachine.displayDevice.displayMessage("Please enter a valid input");
            }
            scanner = new Scanner(System.in); //to flush the scanner
            sleepForFewSeconds();

        }
    }

    //waiting for few seconds to display the message
    void sleepForFewSeconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //display menu
    private void displayMenu() {
        for (int i = 0; i < 500; i++) {
            vendingMachine.displayDevice.displayMessage("\n");
        }
        vendingMachine.displayDevice.displayMessage("--------------------------------------------------------");
        vendingMachine.displayDevice.displayMessage(String.format("|            " + MessageConstants.WELCOME_MESSAGE + "           |", vendingMachine.machineName));
        vendingMachine.displayDevice.displayMessage("--------------------------------------------------------");
        final StringBuilder[] itemMenu = {new StringBuilder("       ")};
        final StringBuilder[] qtyMenu = {new StringBuilder("       ")};
        final StringBuilder[] priceMenu = {new StringBuilder("      ")};
        final StringBuilder[] codeMenu = {new StringBuilder("       ")};
        final int[] i = {0};
        vendingMachine.itemMenuDTO.getCodeItemMapper().keySet().forEach(item -> {
            itemMenu[0].append(vendingMachine.itemMenuDTO.getCodeItemMapper().get(item).getName()).append("       ");
            qtyMenu[0].append("   ").append(vendingMachine.itemMenuDTO.getCodeItemMapper().get(item).getStock()).append("       ");
            priceMenu[0].append(" ").append(vendingMachine.itemMenuDTO.getCodeItemMapper().get(item).getAmount().toString()).append("      ");
            codeMenu[0].append(" ").append(item).append("       ");
            i[0]++;
            if (i[0] % 3 == 0) {
                vendingMachine.displayDevice.displayMessage(itemMenu[0].toString());
                vendingMachine.displayDevice.displayMessage(priceMenu[0].toString());
                vendingMachine.displayDevice.displayMessage(qtyMenu[0].toString());
                vendingMachine.displayDevice.displayMessage(codeMenu[0].toString());
                vendingMachine.displayDevice.displayMessage("--------------------------------------------------------");
                itemMenu[0] = new StringBuilder("       ");
                priceMenu[0] = new StringBuilder("      ");
                qtyMenu[0] = new StringBuilder("      ");
                codeMenu[0] = new StringBuilder("       ");
            }
        });
        if (vendingMachine.balance > 0) {
            vendingMachine.displayDevice.displayMessage("Balance:" + vendingMachine.balance);
        }

        vendingMachine.displayDevice.displayMessage("Press (1) to add cash (2) to add coins (3) to Place order (4) to refund (5) to terminate");
    }

    //check if double is 0 or less than zero
    private Double validateAmount(double amount) {
        if (amount > 0) return amount;
        else throw new VendingMachineException("Invalid amount");
    }
}
