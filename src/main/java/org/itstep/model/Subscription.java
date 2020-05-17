package org.itstep.model;

public class Subscription {
    private int id;
    private int userId;
    private int productId;
    private String productType;

    public Subscription() {
    }

    public Subscription(int userId, int productId, String productType) {
        this.userId = userId;
        this.productId = productId;
        this.productType = productType;
    }

    public Subscription(int id, int userId, int productId, String productType) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productType='" + productType + '\'' +
                '}';
    }
}
