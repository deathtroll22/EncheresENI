package fr.eni.enchereseni.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class SoldItem {
    private int itemNumber;
    private String itemName;
    private String itemDescription;
    private Date auctionStartDate;
    private Date auctionEndDate;
    private double startingPrice;
    private double sellingPrice;
    private String saleStatus;
    private Category categoryItem;
    private Withdrawal withdrawalLocation;
    private List<Auction> auctions = new ArrayList<Auction>();
    private User user;

    // Empty constructor
    public SoldItem() {
    }
    
    public SoldItem(String itemName, String itemDescription, Date auctionStartDate, Date auctionEndDate,
            double startingPrice, int category, String saleStatus, Category categoryItem,
            String pickupStreet, String pickupPostalCode, String pickupCity, User user) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.startingPrice = startingPrice;
        this.sellingPrice = 0; 
        this.saleStatus = saleStatus;
        this.categoryItem = categoryItem;
        this.withdrawalLocation = new Withdrawal(pickupStreet, pickupPostalCode, pickupCity);
        this.user = user;
    }

    
    public SoldItem(String itemName, String itemDescription, Date auctionStartDate, Date auctionEndDate,
            double startingPrice, double sellingPrice, String saleStatus, Category categoryItem,
            Withdrawal withdrawalLocation, List<Auction> auctions, User user) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.auctionStartDate = auctionStartDate;
        this.auctionEndDate = auctionEndDate;
        this.startingPrice = startingPrice;
        this.sellingPrice = sellingPrice;
        this.saleStatus = saleStatus;
        this.categoryItem = categoryItem;
        this.withdrawalLocation = withdrawalLocation;
        this.auctions = auctions;
        this.user = user;
    }
    
    public SoldItem(String itemName, String itemDescription, LocalDate auctionStartDate, LocalDate auctionEndDate,
            double startingPrice, double sellingPrice, String saleStatus, int category,
            String pickupStreet, String pickupPostalCode, String pickupCity, User seller) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.auctionStartDate = Date.valueOf(auctionStartDate);
        this.auctionEndDate = Date.valueOf(auctionEndDate);
        this.startingPrice = startingPrice;
        this.sellingPrice = sellingPrice;
        this.saleStatus = saleStatus;
        this.categoryItem = new Category(category, ""); // Vous devrez peut-être ajuster cette ligne
        this.withdrawalLocation = new Withdrawal(pickupStreet, pickupPostalCode, pickupCity); // Vous devrez peut-être ajuster cette ligne
        this.user = seller;
    }
	
	public SoldItem(String itemName, String itemDescription, Date auctionStartDate, Date auctionEndDate,
	        double startingPrice, double sellingPrice, String saleStatus, int category, String pickupStreet,
	        String pickupPostalCode, String pickupCity, User user) {
	    this.itemName = itemName;
	    this.itemDescription = itemDescription;
	    this.auctionStartDate = auctionStartDate;
	    this.auctionEndDate = auctionEndDate;
	    this.startingPrice = startingPrice;
	    this.sellingPrice = sellingPrice;
	    this.saleStatus = saleStatus;
	    this.categoryItem = new Category(category, ""); // Vous devrez peut-être ajuster cette ligne
	    this.withdrawalLocation = new Withdrawal(pickupStreet, pickupPostalCode, pickupCity); // Vous devrez peut-être ajuster cette ligne
	    this.user = user;
	}

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

	public Category getCategoryItem() {
		return categoryItem;
	}

	public void setCategoryItem(Category categoryItem) {
		this.categoryItem = categoryItem;
	}

	public Withdrawal getWithdrawalLocation() {
		return withdrawalLocation;
	}

	public void setWithdrawalLocation(Withdrawal withdrawalLocation) {
		this.withdrawalLocation = withdrawalLocation;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SoldItem [itemNumber=" + itemNumber + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", auctionStartDate=" + auctionStartDate + ", auctionEndDate=" + auctionEndDate + ", startingPrice="
				+ startingPrice + ", sellingPrice=" + sellingPrice + ", saleStatus=" + saleStatus + ", categoryItem="
				+ categoryItem + "]";
	}

    
}
