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
public class ComfyStoreService implements StoreService {
    private static final Logger log = LoggerFactory.getLogger(ComfyStoreService.class);

    private String urlParsingPageByType;
    private WebDriver driver;
    private static String whatParse;
    private int storeId;
    private String selectorKeyProductType;
    private final String pathVariable = "?p=";

    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final FormattingIncomingData formattingIncomingData;

    public ComfyStoreService(DataEquipment dataEquipment, FormattingIncomingData formattingIncomingData) {
        this.dataEquipment = dataEquipment;
        this.formattingIncomingData = formattingIncomingData;
    }

    @Override
    public void startProcess(String nameStore) {
        whatParse = "Pre processing...";

        try {
            storeId = dataEquipment.storeId(nameStore);
            WebDriverManager.chromedriver().setup();
            List<SelectorKey> selectorKeyList = dataEquipment.getSelectorKey(nameStore);

            for (SelectorKey selectorKey : selectorKeyList) {
                log.info("SelectorKey = {}", selectorKey);
            }

            int countSelectorKey = selectorKeyList.size();

            for (int i = 0; i < countSelectorKey; i++) {
                selectorKeyProductType = selectorKeyList.get(i).getProductType();
                urlParsingPageByType = selectorKeyList.get(i).getSelectorKey();
                log.info("selectorKeyProductType | {}", selectorKeyProductType);
                log.info("urlParsingPageByType | {}", urlParsingPageByType);
                pars(urlParsingPageByType, selectorKeyProductType);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pars(String urlParsingTypePage, String productType) throws InterruptedException {
        whatParse = "Scanning " + productType;
        driver = new ChromeDriver();


        boolean hasNextPage = true;
        int countCurrentPage = 1;

        while (hasNextPage) {


            driver.get(urlParsingTypePage);

            driver.get(urlParsingTypePage + pathVariable + countCurrentPage);
            Thread.sleep(10000);

            List<WebElement> elementList = driver.findElements(By.cssSelector("div[data-gtm-location='catalog']"));
            List<WebElement> listPages = driver.findElements(By.cssSelector("li[class='pager__number']"));
            int countPages = listPages
                    .stream()
                    .map(e -> e.getText().replaceAll("\\D", ""))
                    .filter(e -> e.length() > 0)
                    .mapToInt(Integer::parseInt)
                    .max()
                    .getAsInt();

            try {
                elementList.forEach(e -> {
                    String title;
                    int price;
                    String url;
                    String imgUrl;
                    int storeId = 2;

                    title = e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                            .getAttribute("title");

                    price = formattingIncomingData.formattingPrice(e.findElement(By.cssSelector("span[class='price-value']"))
                            .getText());

                    url = e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                            .getAttribute("href");

                    imgUrl = e.findElement(By.cssSelector("li[class='product-item-gallery__item slick-slide slick-current slick-active']>img"))
                            .getAttribute("src");

                    ModelEquipment modelEquipment = new ModelEquipment(title, price, url, imgUrl, storeId);
                    log.info("Product: {}" , modelEquipment);

                    dataEquipment.save(productType, modelEquipment);

                });
            } catch (Exception ex) {
                log.error("Error: {}", ex.toString());
            }

            log.info("current page: {} max page: {}", countCurrentPage, countPages);
            if (countCurrentPage > countPages) {
                hasNextPage = false;
            } else {
                countCurrentPage++;
            }
        }

    }
}
