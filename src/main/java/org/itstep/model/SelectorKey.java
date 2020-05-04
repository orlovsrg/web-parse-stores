package org.itstep.model;

public class SelectorKey {
    private int storeId;
    private String ProductType;
    private String selectorKey;

    public SelectorKey() {
    }

    public SelectorKey(int storeId, String productType, String selectorKey) {
        this.storeId = storeId;
        ProductType = productType;
        this.selectorKey = selectorKey;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getSelectorKey() {
        return selectorKey;
    }

    public void setSelectorKey(String selectorKey) {
        this.selectorKey = selectorKey;
    }

    @Override
    public String toString() {
        return "SelectorKey{" +
                "storeId=" + storeId +
                ", ProductType='" + ProductType + '\'' +
                ", selectorKey='" + selectorKey + '\'' +
                '}';
    }
}
