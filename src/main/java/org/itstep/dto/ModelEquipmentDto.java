package org.itstep.dto;

public class ModelEquipmentDto {
    private int id;
    private String title;
    private int price;
    private String url;
    private String imgUrl;
    private int storeId;
    private String storeName;
    private String type;


    public ModelEquipmentDto() {

    }

    public ModelEquipmentDto(String title, int price, String url, String imgUrl, int storeId, String storeName) {
        this.title = title;
        this.price = price;
        this.url = url;
        this.imgUrl = imgUrl;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public ModelEquipmentDto(int id, String title, int price, String url, String imgUrl, int storeId, String storeName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.url = url;
        this.imgUrl = imgUrl;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ModelEquipmentDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
