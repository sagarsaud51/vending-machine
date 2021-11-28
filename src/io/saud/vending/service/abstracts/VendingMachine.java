package io.saud.vending.service.abstracts;

import io.saud.vending.model.ItemDTO;
import io.saud.vending.service.DisplayDevice;

import java.util.HashMap;
import java.util.Map;

public abstract class VendingMachine {


    public final MoneyInserter billInserter;
    public final MoneyInserter coinInserter;
    public final DisplayDevice displayDevice;
    public String machineName = "";
    private Double balance = 0D;


    private final Map<String, ItemDTO> codeItemMapper = new HashMap<>();

    public VendingMachine(MoneyInserter billInserter, MoneyInserter coinInserter, DisplayDevice displayDevice) {
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

    public Map<String, ItemDTO> getCodeItemMapper() {
        return codeItemMapper;
    }

    public Double getBalance() {
        return balance;
    }

    public void addBalance(Double balance) {
        this.balance += balance;
    }
    public void reduceBalance(Double balance) {
        this.balance -= balance;
    }
}
