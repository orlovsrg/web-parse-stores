package org.itstep.model;


public class ModelEquipment {
    private int id;
    private String title;
    private int price;
    private String url;
    private String imgUrl;
    private int storeId;

    public ModelEquipment() {
    }

    public ModelEquipment(String title, int price, String url, String imgUrl, int storeId) {
        this.title = title;
        this.price = price;
        this.url = url;
        this.imgUrl = imgUrl;
        this.storeId = storeId;
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

    @Override
    public String toString() {
        return "ModelEquipment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
