package fr.eni.enchereseni.bo;

public class Category {
    private int categoryNumber;
    private String categoryName;

    // Empty constructor
    public Category() {
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

    // toString method
    @Override
    public String toString() {
        return "Category Number: " + categoryNumber +
                ", Category Name: " + categoryName;
    }
}
