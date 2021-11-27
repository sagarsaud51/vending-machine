package io.saud.vending.model;

public class ItemDTO {

    private String name;
    private Double amount;
    private Integer stock;

    public ItemDTO(String name, Double amount, Integer stock) {
        this.name = name;
        this.amount = amount;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void updateStock(Integer stock) {
        this.stock += stock;
    }
}
