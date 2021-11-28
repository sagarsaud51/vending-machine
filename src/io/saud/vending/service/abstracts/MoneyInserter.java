package io.saud.vending.service.abstracts;

import java.util.HashMap;
import java.util.Map;

public abstract class BillInserter {

    public  Double balance = 0D;
    public  Map<Double,Integer> denoCount = new HashMap<>();
    public abstract Double getAmount(Double amount);

    public void resetBalance(){
        balance = 0D;
        denoCount.clear();
    }
}
