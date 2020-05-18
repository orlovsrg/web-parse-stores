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
public class ComfyStoreService implements StoreService {

    private static final Logger log = LoggerFactory.getLogger(ComfyStoreService.class);

    private static String whatParse;
    private int storeId;
    private final String pathVariable = "?p=";
    private String productType;
    private String urlParsingPageByType;
    private WebDriver driver;

    @Autowired
    private final DataEquipment dataEquipment;
    @Autowired
    private final FormattingIncomingData formattingIncomingData;
    @Autowired
    private final AnalysisService analysisService;

    public ComfyStoreService(DataEquipment dataEquipment, FormattingIncomingData formattingIncomingData, AnalysisService analysisService) {
        this.dataEquipment = dataEquipment;
        this.formattingIncomingData = formattingIncomingData;
        this.analysisService = analysisService;
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
                productType = linkProductTypeList.get(i).getProductType();
                urlParsingPageByType = linkProductTypeList.get(i).getLinkProduct();
                log.info("selectorKeyProductType | {}", productType);
                log.info("urlParsingPageByType | {}", urlParsingPageByType);
                pars(urlParsingPageByType, productType);
            }
            driver.quit();
        } catch (Exception e) {
            log.error("error");
        }
    }

    @Override
    public void pars(String urlParsingTypePage, String productType) {

        whatParse = "Scanning " + productType;

        //This driver create here for parsing single product



        boolean hasNextPage = true;
        int countCurrentPage = 1;

        while (hasNextPage) {
            try {
                driver.get(urlParsingTypePage + pathVariable + countCurrentPage);
                Thread.sleep(5000);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                for (int j = 0; j < 8; j++) {
                    jse.executeScript("window.scrollBy(0,1000)", "");
                    Thread.sleep(700);
                }
                Thread.sleep(3000);
                List<WebElement> elementList = driver.findElements(By.cssSelector("div[data-gtm-location='catalog']"));
                List<WebElement> listPages = driver.findElements(By.cssSelector("li[class='pager__number']"));
//                int countPages = listPages
//                        .stream()
//                        .map(e -> e.getText().replaceAll("\\D", ""))
//                        .filter(e -> e.length() > 0)
//                        .mapToInt(Integer::parseInt)
//                        .max()
//                        .getAsInt();

                try {
                    elementList.forEach(e -> {
                        String title;
                        int price;
                        String url;
                        String imgUrl;

                        title = formattingIncomingData.formattingTitle(e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                                .getAttribute("title"));

                        price = formattingIncomingData.formattingPrice(e.findElement(By.cssSelector("span[class='price-value']"))
                                .getText());

                        url = e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                                .getAttribute("href");

                        imgUrl = e.findElement(By.cssSelector("li[class='product-item-gallery__item slick-slide slick-current slick-active']>img"))
                                .getAttribute("src");

                        ModelEquipment modelEquipment = new ModelEquipment(title, price, url, imgUrl, storeId);
                        log.info("Product: {}", modelEquipment);

                        analysisService.checkProduct(productType, modelEquipment);


                    });
                } catch (Exception ex) {
                    log.error("Error: {}", ex.toString());
                }

//                log.info("current page: {} max page: {}", countCurrentPage, countPages);
                log.info("current page: {}", countCurrentPage);
                if (countCurrentPage == 10) {
                    hasNextPage = false;
                } else {
                    countCurrentPage++;
                }
            } catch (Exception ex) {
                log.error("Error: {}", ex);
            }
        }
    }
}
