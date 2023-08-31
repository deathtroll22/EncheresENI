package fr.eni.enchereseni.bo;

import java.util.Date;

public class SoldItem {
    private int itemNumber;
    private String itemName;
    private String itemDescription;
    private Date auctionStartDate;
    private Date auctionEndDate;
    private double startingPrice;
    private double sellingPrice;
    private String saleStatus;

    // Empty constructor
    public SoldItem() {
    }

    // Constructor with all variables
    public SoldItem(int itemNumber, String itemName, String itemDescription, Date auctionStartDate,
                    Date auctionEndDate, double startingPrice, double sellingPrice, String saleStatus) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.startingPrice = startingPrice;
        this.sellingPrice = sellingPrice;
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

    public Date getAuctionStartDate() {
        return auctionStartDate;
    }

    public void setAuctionStartDate(Date auctionStartDate) {
        this.auctionStartDate = auctionStartDate;
    }

    public Date getAuctionEndDate() {
        return auctionEndDate;
    }

    public void setAuctionEndDate(Date auctionEndDate) {
        this.auctionEndDate = auctionEndDate;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
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
                ", Auction Start Date: " + auctionStartDate +
                ", Auction End Date: " + auctionEndDate +
                ", Starting Price: " + startingPrice +
                ", Selling Price: " + sellingPrice +
                ", Sale Status: " + saleStatus;
    }
}
