package io.saud.vending.service.impl;

import io.saud.vending.service.abstracts.BillInserter;

public class CompanyABillInserterImpl extends BillInserter {

    //for exchange rate 1 = 1
    @Override
    public Double getAmount(Double amount) {
        if (amount == null || amount <= 0) {
            return 0.0;
        }
        if (denoCount.containsKey(amount)) {
            denoCount.put(amount, denoCount.get(amount) + 1);
        } else {
            denoCount.put(amount, 1);
        }
        balance += amount;
        return amount;
    }
}
