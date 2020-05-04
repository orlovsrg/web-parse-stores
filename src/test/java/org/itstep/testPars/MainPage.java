package org.itstep.testPars;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;


public class MainPage {
    private static final Logger log = LoggerFactory.getLogger(MainPage.class);
    private static String comfyStartPage = "https://comfy.ua/";


    public static void main(String[] args) throws IOException {
        String page = Paths.get("home.html").toString();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try (FileWriter writer = new FileWriter(page)) {

            driver.get(comfyStartPage);
            Thread.sleep(2000);
//            writer.write(driver.getPageSource());
//            writer.flush();

            String element = driver.findElement(By.cssSelector("a[href*='smartfon']")).getAttribute("href");

            log.info("link of smartphone {}", element);
            driver.get("https://www.foxtrot.com.ua/ru/shop/mobilnye_telefony_smartfon.html");
            Thread.sleep(10000);
            writer.write(driver.getPageSource());
            writer.flush();
//            WebElement element = driver.findElement(By.cssSelector("a[href*='mobilnye_telefony_smartfon']"));

//            foxtrotSmartphones = element.getAttribute("href");
//            String paginationVariable = foxtrotSmartphones + "?page=";

//            driver.get(foxtrotSmartphones);
//            Thread.sleep(3000);
//            WebElement countPage = driver.findElement(By.cssSelector("div[class='listing__pagination']"));
//            List<WebElement> elementsLi = countPage.findElements(By.cssSelector("li[data-page]"));

//            log.info("Count elements li = {}", elementsLi.size());

//            int count = elementsLi.stream()
//                    .map(e -> e.getAttribute("data-page"))
//                    .map(e -> Integer.parseInt(e))
//                    .max(Integer::compare)
//                    .get();
//
//            System.out.println("count = " + count);

//            for (int i = 1; i <= count; i++) {
//                String paginationVariable = foxtrotSmartphones + "?page=" + i;
//                driver.get(paginationVariable);
//                Thread.sleep(5000);
//                List<WebElement> elementList = driver.findElements(By.cssSelector("div[class='card js-card isTracked']"));
//                log.info("Count phone {}", elementList.size());
//                elementList.forEach(e -> {
//                    String title;
//                    String price;
//                    String url;
//                    String imgUrl;
//
//                    title = e.findElement(By.cssSelector("div[class='card__body']"))
//                            .findElement(By.cssSelector("a[class='card__title']"))
//                            .getAttribute("title");
//
//                    price = e.findElement(By.cssSelector("div[class='card__body']"))
//                            .findElement(By.cssSelector("div[class='card-price']"))
//                            .getText();
//
//                    url = e.findElement(By.cssSelector("div[class='card__body']"))
//                            .findElement(By.cssSelector("a[class='card__title']"))
//                            .getAttribute("href");
//
//                    imgUrl = e.findElement(By.cssSelector("div[class='card__image']"))
//                            .findElement(By.cssSelector("img[class='lazy']"))
//                            .getAttribute("src");
//                    try {
//                        writer.write("title= " + title + " | " + "price= " + price + " | " + "url= " + url + " | " + "urlImg =" + imgUrl);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                });
//            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
