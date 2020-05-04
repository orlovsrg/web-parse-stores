package org.itstep.service.parse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.itstep.data.parse.DataEquipment;
import org.itstep.model.ModelEquipment;
import org.itstep.model.SelectorKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoxtrotStoreService implements StoreService {

    private final Logger log = LoggerFactory.getLogger(FoxtrotStoreService.class);

    private String startPage;
    private String urlParsingTypePage;
    private WebDriver driver;
    private String whatParse;
    private int storeId;
    private final String variablePagination = "?page=";

    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final FormattingIncomingData formattingIncomingData;

    public FoxtrotStoreService(DataEquipment dataEquipment, FormattingIncomingData formattingIncomingData) {
        this.dataEquipment = dataEquipment;
        this.formattingIncomingData = formattingIncomingData;
    }

    public String getWhatParse() {
        return whatParse;
    }

    @Override
    public void parse(String nameStore) {
        whatParse = "Pre processing...";

        try {
            startPage = dataEquipment.storeUrl(nameStore);
            storeId = dataEquipment.storeId(nameStore);
            WebDriverManager.chromedriver().setup();

            List<SelectorKey> selectorKeyList = dataEquipment.getSelectorKey(nameStore);
            for (SelectorKey selectorKey : selectorKeyList) {
                log.info("SelectorKey = {}", selectorKey);
            }
            int countSelectorKey = selectorKeyList.size();
            log.info("countSelectorKey = {}", countSelectorKey);

            for (int i = 0; i < countSelectorKey; i++) {
                String selectorKeyProductType = selectorKeyList.get(i).getProductType();
                String selectorKeySelectorKey = selectorKeyList.get(i).getSelectorKey();
                driver = new ChromeDriver();
                driver.get(startPage);
                WebElement elementLink = driver.findElement(By.cssSelector("a[href*='" + selectorKeySelectorKey + "']"));
                urlParsingTypePage = elementLink.getAttribute("href");
                parsProduct(urlParsingTypePage, selectorKeyProductType);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parsProduct(String urlParsingTypePage, String productType) throws InterruptedException {
        whatParse = "Scanning " + productType;

        ModelEquipment modelEquipment = new ModelEquipment();
        driver.get(urlParsingTypePage);
        Thread.sleep(2000);
        WebElement countPage = driver.findElement(By.cssSelector("div[class='listing__pagination']"));
        List<WebElement> elementsCount = countPage.findElements(By.cssSelector("li[data-page]"));


        // Page counter variable
//        int count = elementsCount.stream()
//                .map(e -> e.getAttribute("data-page"))
//                .map(e -> Integer.parseInt(e))
//                .max(Integer::compare)
//                .get();

        // Test with a limit of 2 page. There is here should be variable "count".
        for (int i = 1; i <= 2; i++) {

            driver.get(urlParsingTypePage + variablePagination + i);

            List<WebElement> elementList = driver.findElements(By.cssSelector("div[class='card js-card isTracked']"));

            elementList.forEach(e -> {

                String title;
                int price;
                String url;
                String imgUrl;
                int storeId = this.storeId;

                title = e.findElement(By.cssSelector("div[class='card__body']"))
                        .findElement(By.cssSelector("a[class='card__title']"))
                        .getAttribute("title");

                price = formattingIncomingData.formattingPrice(e.findElement(By.cssSelector("div[class='card__body']"))
                        .findElement(By.cssSelector("div[class='card-price']"))
                        .getText());

                url = e.findElement(By.cssSelector("div[class='card__body']"))
                        .findElement(By.cssSelector("a[class='card__title']"))
                        .getAttribute("href");

                imgUrl = e.findElement(By.cssSelector("div[class='card__image']"))
                        .findElement(By.cssSelector("img[class='lazy']"))
                        .getAttribute("src");

                dataEquipment.save(productType, new ModelEquipment(title, price, url, imgUrl, storeId));
            });
        }

        driver.quit();
    }

}
