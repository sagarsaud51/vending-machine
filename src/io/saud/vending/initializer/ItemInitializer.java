package io.saud.vending.initializer;

import io.saud.vending.model.ItemDTO;
import io.saud.vending.service.abstracts.VendingMachine;

public class ItemInitializer {

    public static void addItemsInVendingMachine(VendingMachine vendingMachine){
        vendingMachine.addItem("001", new ItemDTO("Coke",20D,5));
        vendingMachine.addItem("002", new ItemDTO("FANTA",20D,5));
        vendingMachine.addItem("003", new ItemDTO("SPRITE",20D,5));
        vendingMachine.addItem("004", new ItemDTO("LAYS",20D,5));
        vendingMachine.addItem("005", new ItemDTO("BEER",50D,5));
        vendingMachine.addItem("006", new ItemDTO("MILK",20D,5));
        vendingMachine.addItem("007", new ItemDTO("CHIPS",20D,5));
        vendingMachine.addItem("008", new ItemDTO("Wine",20D,5));
        vendingMachine.addItem("009", new ItemDTO("Banana",20D,5));
        vendingMachine.addItem("010", new ItemDTO("CANDLE",20D,5));
    }
}
