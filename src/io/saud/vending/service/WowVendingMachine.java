package io.saud.vending.service;

import io.saud.vending.constants.MessageConstants;
import io.saud.vending.model.ItemDTO;
import io.saud.vending.service.abstracts.VendingMachine;
import io.saud.vending.service.impl.CompanyABillInserterImpl;
import io.saud.vending.service.impl.CompanyBDisplayDeviceImpl;
import io.saud.vending.service.impl.CompanyCCoinInserterImpl;

import java.util.logging.Logger;

public class WowVendingMachine extends VendingMachine {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    private final String MACHINE_NAME = "WoW";

    //Vending Machine with different Devices initialization
    public WowVendingMachine() {
        super(new CompanyABillInserterImpl(), new CompanyCCoinInserterImpl(), new CompanyBDisplayDeviceImpl());
        machineName = MACHINE_NAME;
    }

    @Override
    //add item to the vending machine
    public void addItem(String code, ItemDTO item) {
        if (doesCodeExists(code)) {
            log.severe("Item with code " + code + "already  exists for item" + itemMenuDTO.getCodeItemMapper().get(code).getName());
            return;
        }
        itemMenuDTO.getCodeItemMapper().put(code.toUpperCase(), item);
        log.info("Item " + item.getName() + " has been added with CODE: " + code);
    }

    @Override
    //adding existing item stock to the vending machine
    public void addExistingItem(String code, Integer quantity) {
        if (!doesCodeExists(code)) {
            log.severe("Item with code " + code + "does not exists! Please add the item first!");
            return;
        }
        itemMenuDTO.getCodeItemMapper().get(code).updateStock(quantity);
        log.info("Item " + itemMenuDTO.getCodeItemMapper().get(code).getName() + " Stock has been updated");
    }

    @Override
    //update the item price
    public void updateItemPrice(String code, Double amount) {
        if (!doesCodeExists(code)) {
            log.severe("Item with code " + code + "does not exists! Please add the item first!");
            return;
        }
        itemMenuDTO.getCodeItemMapper().get(code).setAmount(amount);
        log.info("Item " + itemMenuDTO.getCodeItemMapper().get(code).getName() + " Price has been updated");
    }

    @Override
    //place order
    public void placeOrder(String orderCode) {
        String userOrder = displayDevice.orderProduct(orderCode);

        if (balance == 0D) {
            displayDevice.displayMessage("Sorry! You do not have sufficient credit \nPlease add more coins/cash");
            return;
        }


        if (!doesCodeExists(userOrder)) {
            displayDevice.displayMessage("Order code " + orderCode + " is not found");
            return;
        }

        if (balance < itemMenuDTO.getCodeItemMapper().get(userOrder).getAmount()) {
            displayDevice.displayMessage("Sorry! You do not have sufficient credit \nPlease add more coins/cash");
            return;
        }

        if (itemMenuDTO.getCodeItemMapper().get(userOrder).getStock() == 0) {
            displayDevice.displayMessage("Sorry! " + itemMenuDTO.getCodeItemMapper().get(userOrder).getName() + "is out of stock");
            return;
        }

        displayDevice.displayMessage("Thank you for your order!\nPlease collect your " + itemMenuDTO.getCodeItemMapper().get(userOrder).getName());
        if (itemMenuDTO.getCodeItemMapper().get(userOrder).getAmount() > balance) {
            balance = balance - itemMenuDTO.getCodeItemMapper().get(userOrder).getAmount();
        }
        refund(balance);
        itemMenuDTO.getCodeItemMapper().get(userOrder).setStock(itemMenuDTO.getCodeItemMapper().get(userOrder).getStock() - 1);
    }

    @Override
    //add cash to balance
    public void loadCash(Double amount) {
        balance += billInserter.getAmount(amount);
        displayDevice.displayMessage("Your available balance is now " + amount);
    }

    @Override
    public void loadCoins(Double amount) {
        balance += billInserter.getAmount(amount);
        displayDevice.displayMessage("Your available balance is now " + amount);
    }

    @Override
    public void cancel() {
        log.info("Canceling the order!");
        if (balance != 0D) {
            refund(balance);
            resetBalance();
        }
        displayDevice.displayMessage(String.format(MessageConstants.CANCEL_MESSAGE, MACHINE_NAME));
    }

    @Override
    public void refund(Double amount) {
        displayDevice.displayMessage(String.format(MessageConstants.RETURN_MESSAGE, amount));
    }


    private Boolean doesCodeExists(String code) {
        return itemMenuDTO.getCodeItemMapper().containsKey(code);
    }
}
