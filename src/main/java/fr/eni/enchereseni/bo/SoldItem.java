package fr.eni.enchereseni.bo;

public class SoldItem {
    private int itemNumber;
    private String itemName;
    private String itemDescription;
    private double soldPrice;
    private String saleStatus;

    // Empty constructor
    public SoldItem() {
    }

    // Constructor with all variables
    public SoldItem(int itemNumber, String itemName, String itemDescription, double soldPrice, String saleStatus) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.soldPrice = soldPrice;
        this.saleStatus = saleStatus;
    }

    // Getters and setters for variables
    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    // toString method
    @Override
    public String toString() {
        return "Item Number: " + itemNumber +
                ", Item Name: " + itemName +
                ", Item Description: " + itemDescription +
                ", Sold Price: " + soldPrice +
                ", Sale Status: " + saleStatus;
    }
}
