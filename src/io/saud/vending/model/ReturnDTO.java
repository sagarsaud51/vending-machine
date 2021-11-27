package io.saud.vending.model;

public class ReturnDTO {

    private Double returnAmount;
    private String returnedItem;
    private String message;

    public ReturnDTO(){}

    public ReturnDTO(Double returnAmount, String returnedItem, String message) {
        this.returnAmount = returnAmount;
        this.returnedItem = returnedItem;
        this.message = message;
    }

    public String getReturnedItem() {
        return returnedItem;
    }

    public void setReturnedItem(String returnedItem) {
        this.returnedItem = returnedItem;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
