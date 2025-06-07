package com.ecommerce.project.model;

public class Category {
    private long categoryId;
    private String categoryName;

    public long getCategoryId() {
        return categoryId;
    }

    public Category(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
