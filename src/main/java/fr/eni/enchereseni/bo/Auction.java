package fr.eni.enchereseni.bo;

import java.util.Date;

public class Auction {
    private Date auctionDate;
    private double bidAmount;

    // Empty constructor
    public Auction() {
    }

    // Constructor with all variables
    public Auction(Date auctionDate, double bidAmount) {
        this.auctionDate = auctionDate;
        this.bidAmount = bidAmount;
    }

    // Getter for auctionDate
    public Date getAuctionDate() {
        return auctionDate;
    }

    // Setter for auctionDate
    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    // Getter for bidAmount
    public double getBidAmount() {
        return bidAmount;
    }

    // Setter for bidAmount
    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    // toString method
    @Override
    public String toString() {
        return "Auction Date: " + auctionDate + ", Bid Amount: " + bidAmount;
    }
}
