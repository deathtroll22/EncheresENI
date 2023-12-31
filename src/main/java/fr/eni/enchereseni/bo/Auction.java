package fr.eni.enchereseni.bo;

import java.util.Date;

public class Auction {
	private User user;
	private SoldItem soldItem;
    private Date auctionDate;
    private Integer bidAmount;
    private int highestBidderUserId;

    
	public Auction() {
		super();
	}

	public Auction(User user, SoldItem soldItem, Date auctionDate, Integer bidAmount, int highestBidderUserId) {
	    super();
	    this.user = user;
	    this.soldItem = soldItem;
	    this.auctionDate = auctionDate;
	    this.bidAmount = bidAmount;
	    this.highestBidderUserId = highestBidderUserId;
	}

	public Auction(Date auctionDate, Integer bidAmount) {
		super();
		this.auctionDate = auctionDate;
		this.bidAmount = bidAmount;
	}

	public Auction(User user, SoldItem soldItem, Date auctionDate, int bidAmount) {
		super();
	    this.user = user;
	    this.soldItem = soldItem;
	    this.auctionDate = auctionDate;
	    this.bidAmount = bidAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SoldItem getSoldItem() {
		return soldItem;
	}

	public void setSoldItem(SoldItem soldItem) {
		this.soldItem = soldItem;
	}

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}

	public Integer getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(Integer bidAmount) {
		this.bidAmount = bidAmount;
	}
	
	public int getHighestBidderUserId() {
	    return highestBidderUserId;
	}

	public void setHighestBidderUserId(int highestBidderUserId) {
	    this.highestBidderUserId = highestBidderUserId;
	}


	@Override
	public String toString() {
	    return "Auction [user=" + user + ", soldItem=" + soldItem + ", auctionDate=" + auctionDate + ", bidAmount="
	            + bidAmount + ", highestBidderUserId=" + highestBidderUserId + "]";
	}

	

}