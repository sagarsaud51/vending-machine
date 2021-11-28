package io.saud.vending.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDTO {

    private UUID id;
    private String item;
    private Double amount;
    private LocalDateTime transactionDate;

    public TransactionDTO() {
    }

    public TransactionDTO(ItemDTO dto) {
        this.id = UUID.randomUUID();
        this.item = dto.getName();
        this.amount = dto.getAmount();
        this.transactionDate = LocalDateTime.now();
    }
    public TransactionDTO(UUID id, String item, Double amount, LocalDateTime transactionDate) {
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
