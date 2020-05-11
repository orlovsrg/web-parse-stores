package org.itstep.testPars;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.itstep.model.ModelEquipment;
import org.itstep.valodator.FormattingIncomingData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
public class MainPage {
    private static final Logger log = LoggerFactory.getLogger(MainPage.class);
    private static String phoneLink = "https://comfy.ua/smartfon/";
    private static final String pathVariable = "?p=";


    static FormattingIncomingData formattingIncomingData = new FormattingIncomingData();

    public static void main(String[] args) throws IOException, InterruptedException {

        String page = Paths.get("home.html").toString();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try (FileWriter writer = new FileWriter(page)) {


            boolean hasNextPage = true;
            int countCurrentPage = 6;
            while (hasNextPage) {

                driver.get(phoneLink + pathVariable + countCurrentPage);
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

                        log.info("Model techno: {}", modelEquipment);
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

        } finally {
            driver.quit();
        }

    }
}
