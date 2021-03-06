package org.itstep.service.parse;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.itstep.data.parse.DataEquipment;
import org.itstep.model.LinkProductType;
import org.itstep.model.ModelEquipment;
import org.itstep.service.analysis.AnalysisService;
import org.itstep.valodator.FormattingIncomingData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private static String whatParse;
    private int storeId;
    private final String pathVariable = "?page=";
    private String selectorKeyProductType;
    private String urlParsingPageByType;
    private WebDriver driver;

    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final FormattingIncomingData formattingIncomingData;
    @Autowired
    private final AnalysisService analysisService;

    public FoxtrotStoreService(DataEquipment dataEquipment, FormattingIncomingData formattingIncomingData, AnalysisService analysisService) {
        this.dataEquipment = dataEquipment;
        this.formattingIncomingData = formattingIncomingData;
        this.analysisService = analysisService;
    }

    public static String getWhatParse() {
        return whatParse;
    }

    @Override
    public void startProcess(String nameStore) {
        whatParse = "Pre processing...";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        try {
            storeId = dataEquipment.storeId(nameStore);

            List<LinkProductType> linkProductTypeList = dataEquipment.getSelectorKey(nameStore);

            for (LinkProductType linkProductType : linkProductTypeList) {
                log.info("Links: {}", linkProductType);
            }

            int countSelectorKey = linkProductTypeList.size();

            for (int i = 0; i < countSelectorKey; i++) {
                selectorKeyProductType = linkProductTypeList.get(i).getProductType();
                urlParsingPageByType = linkProductTypeList.get(i).getLinkProduct();
                log.info("selectorKeyProductType | {}", selectorKeyProductType);
                log.info("urlParsingPageByType | {}", urlParsingPageByType);
                pars(urlParsingPageByType, selectorKeyProductType);
            }
                driver.quit();
        } catch (Exception ex) {
            log.error("Error: {}", ex);
        }
    }

    @Override
    public void pars(String urlParsingTypePage, String productType) {

        whatParse = "Scanning " + productType;

        try {


            driver.get(urlParsingTypePage);
            driver.manage().window().maximize();
            Thread.sleep(2000);
            WebElement countPage = driver.findElement(By.cssSelector("div[class='listing__pagination']"));
            List<WebElement> elementsCount = countPage.findElements(By.cssSelector("li[data-page]"));


//             Page counter variable
            int count = elementsCount.stream()
                    .map(e -> e.getAttribute("data-page"))
                    .map(e -> Integer.parseInt(e))
                    .max(Integer::compare)
                    .get();

            // Test with a limit of 2 page. There is here should be variable "count".
            for (int i = 1; i <= count; i++) {

                driver.get(urlParsingTypePage + pathVariable + i);
                Thread.sleep(2000);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                for (int j = 0; j < 10; j++) {
                    jse.executeScript("window.scrollBy(0,1000)", "");
                    Thread.sleep(700);
                }
                Thread.sleep(2000);
                List<WebElement> elementList = driver.findElements(By.cssSelector("div[class='card js-card isTracked']"));
                try {

                    elementList.forEach(e -> {

                        String title;
                        int price;
                        String url;
                        String imgUrl;
                        int storeId = this.storeId;

                        title = formattingIncomingData.formattingTitle(e.findElement(By.cssSelector("div[class='card__body']"))
                                .findElement(By.cssSelector("a[class='card__title']"))
                                .getAttribute("title"));

                        price = formattingIncomingData.formattingPrice(e.findElement(By.cssSelector("div[class='card__body']"))
                                .findElement(By.cssSelector("div[class='card-price']"))
                                .getText());

                        url = e.findElement(By.cssSelector("div[class='card__body']"))
                                .findElement(By.cssSelector("a[class='card__title']"))
                                .getAttribute("href");

                        imgUrl = e.findElement(By.cssSelector("div[class='card__image']"))
                                .findElement(By.cssSelector("img[class='lazy']"))
                                .getAttribute("src");

                        analysisService.checkProduct(productType,  new ModelEquipment(title, price, url, imgUrl, storeId));
//                        dataEquipment.save(productType, new ModelEquipment(title, price, url, imgUrl, storeId));
                    });
                } catch (Exception ex) {
                    log.error("Error: {}", ex);
                }
            }

        } catch (Exception ex) {
            log.error("Error: {}", ex);
        }
    }

}
