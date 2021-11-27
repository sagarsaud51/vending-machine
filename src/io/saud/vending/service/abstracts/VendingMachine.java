package io.saud.vending.service.abstracts;

import io.saud.vending.model.ItemDTO;
import io.saud.vending.model.ItemMenuDTO;
import io.saud.vending.service.DisplayDevice;

public abstract class VendingMachine {


    public final BillInserter billInserter;
    public final CoinInserter coinInserter;
    public final DisplayDevice displayDevice;
    public String machineName = "";
    public Double balance = 0D;
    public ItemMenuDTO itemMenuDTO = new ItemMenuDTO();

    public VendingMachine(BillInserter billInserter, CoinInserter coinInserter, DisplayDevice displayDevice) {
        this.billInserter = billInserter;
        this.coinInserter = coinInserter;
        this.displayDevice = displayDevice;
    }

    public abstract void addItem(String code, ItemDTO item);

    public abstract void addExistingItem(String code, Integer quantity);

    public abstract void updateItemPrice(String code, Double amount);

    public abstract void placeOrder(String orderCode);

    public abstract void loadCash(Double balance);

    public abstract void loadCoins(Double balance);

    public abstract void cancel();

    public abstract void refund(Double amount);


    public void resetBalance() {
        this.balance = 0D;
        this.billInserter.resetBalance();
        this.coinInserter.resetBalance();
    }

}
