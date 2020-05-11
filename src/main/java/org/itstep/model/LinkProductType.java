package org.itstep.model;

public class LinkProductType {
    private int storeId;
    private String productType;
    private String linkProduct;

    public LinkProductType() {
    }

    public LinkProductType(int storeId, String productType, String linkProduct) {
        this.storeId = storeId;
        this.productType = productType;
        this.linkProduct = linkProduct;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLinkProduct() {
        return linkProduct;
    }

    public void setLinkProduct(String linkProduct) {
        this.linkProduct = linkProduct;
    }
}
