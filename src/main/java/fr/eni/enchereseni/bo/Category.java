package fr.eni.enchereseni.bo;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int categoryNumber;
    private String categoryName;
    private List<SoldItem> items = new ArrayList<SoldItem>();

    // Empty constructor
    public Category() {
    }
    
    public Category(String categoryName) {
    	this.categoryName = categoryName;
    }

	// Constructor with all variables
    public Category(int categoryNumber, String categoryName) {
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
    }

    // Getters and setters for variables
    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public List<SoldItem> getItems() {
		return items;
	}

	public void setItems(List<SoldItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + "]";
	}

    
}
