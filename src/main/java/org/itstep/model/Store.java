package org.itstep.model;

import java.util.Objects;

public class Store {
    private int id;
    private String name;
    private String storeUrl;

    public Store() {

    }

    public Store(int id, String name, String storeUrl) {
        this.id = id;
        this.name = name;
        this.storeUrl = storeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return name.equals(store.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeUrl='" + storeUrl + '\'' +
                '}';
    }

}
