package fr.eni.enchereseni.bo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Integer userID; // noUtilisateur
	private String username; // pseudo
	private String lastName; // nom
	private String firstName; // prenom
	private String email; // email
	private String phoneNumber; // telephone
	private String street; // rue
	private String postalCode; // codePostal
	private String city; // ville
	private String password; // motDePasse
	private Integer credit; // credit
	private boolean isAdmin; // administrateur
	private List<Auction> listAuctions = new ArrayList<Auction>();
	private List<SoldItem> listSoldItems = new ArrayList<SoldItem>();

	public User() {
		super();
	}

	public User(String username, String lastName, String firstName, String email, String phoneNumber, String street,
			String postalCode, String city, String password) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
	}

	public User(String username, String lastName, String firstName, String email, String phoneNumber, String street,
			String postalCode, String city, String password, Integer credit, boolean isAdmin) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credit = credit;
		this.isAdmin = isAdmin;
	}	

	public User(String username, String lastName, String firstName, String email, String phoneNumber, String street,
			String postalCode, String city, String password, Integer credit, boolean isAdmin,
			List<Auction> listAuctions, List<SoldItem> listSoldItems) {
		super();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credit = credit;
		this.isAdmin = isAdmin;
		this.listAuctions = listAuctions;
		this.listSoldItems = listSoldItems;
	}
	

	public List<Auction> getListAuctions() {
		return listAuctions;
	}

	public void setListAuctions(List<Auction> listAuctions) {
		this.listAuctions = listAuctions;
	}

	public List<SoldItem> getListSoldItems() {
		return listSoldItems;
	}

	public void setListSoldItems(List<SoldItem> listSoldItems) {
		this.listSoldItems = listSoldItems;
	}

	public User(Integer userID, String username, String lastName, String firstName, String email, String phoneNumber,
			String street, String postalCode, String city, String password, Integer credit, boolean isAdmin) {
		super();
		this.userID = userID;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credit = credit;
		this.isAdmin = isAdmin;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Auction> getAuctions() {
		return listAuctions;
	}

	public void setAuctions(List<Auction> auctions) {
		listAuctions = auctions;
	}

	public List<SoldItem> getItems() {
		return listSoldItems;
	}

	public void setItems(List<SoldItem> items) {
		this.listSoldItems = items;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", lastName=" + lastName + ", firstName="
				+ firstName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", street=" + street
				+ ", postalCode=" + postalCode + ", city=" + city + ", password=" + password + ", credit=" + credit
				+ ", isAdmin=" + isAdmin + "]";
	}
	

}
