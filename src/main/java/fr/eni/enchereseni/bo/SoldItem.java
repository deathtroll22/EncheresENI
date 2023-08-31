package fr.eni.enchereseni.bo;

import java.util.ArrayList;
import java.util.Date;
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
			double startingPrice, double sellingPrice, String saleStatus, Category categoryItem,
			Withdrawal withdrawalLocation, List<Auction> auctions, User user) {
		super();
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

	public SoldItem(int itemNumber, String itemName, String itemDescription, Date auctionStartDate, Date auctionEndDate,
			double startingPrice, double sellingPrice, String saleStatus, Category categoryItem,
			Withdrawal withdrawalLocation, List<Auction> auctions, User user) {
		super();
		this.itemNumber = itemNumber;
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
