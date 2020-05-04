package org.itstep.service.parse;

import org.openqa.selenium.WebDriver;

public interface StoreService {
    void parse(String nameStore);
    void parsProduct(String urlParsingTypePage, String productType) throws InterruptedException;

}
