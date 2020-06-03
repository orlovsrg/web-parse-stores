package org.itstep.testPars;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.itstep.model.ModelEquipment;
import org.itstep.valodator.FormattingIncomingData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ManagerDb {

    public static void main(String[] args) throws InterruptedException {
        FormattingIncomingData formattingIncomingData = new FormattingIncomingData();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://comfy.ua/smartfon/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        for (int j = 0; j < 10; j++) {
            jse.executeScript("window.scrollBy(0,1000)", "");
            Thread.sleep(700);
        }
        Thread.sleep(3000);
        List<WebElement> elementList = driver.findElements(By.cssSelector("div[data-gtm-location='catalog']"));
        System.out.println(elementList.size());
        List<WebElement> listPages = driver.findElements(By.cssSelector("li[class='pager__number']"));


        for (WebElement e : elementList) {
            try {


                String title;
                int price;
                String url;
                String imgUrl;

                title = formattingIncomingData.formattingTitle(e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                        .getAttribute("title"));
                String s = e.findElement(By.cssSelector("span[class='price-value']")).getText();
                System.out.println("s= " + s);
//                price = formattingIncomingData.formattingPrice(e.findElement(By.cssSelector("div[class='price-box__content price-box__content_special']"))
//                        .findElement(By.cssSelector("span[class='price-value']")).getText());

                url = e.findElement(By.cssSelector("a[class='product-item__name-link js-gtm-product-title']"))
                        .getAttribute("href");

                imgUrl = e.findElement(By.cssSelector("li[class='product-item-gallery__item slick-slide slick-current slick-active']>img"))
                        .getAttribute("src");

//                ModelEquipment modelEquipment = new ModelEquipment(title, price, url, imgUrl, 2);
//                System.out.println(modelEquipment);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


//        Random random = new Random();
//        List<Integer> lidt =  new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            lidt.add(random.nextInt(3));
//        }
//
//        System.out.println(lidt);

//        String title = "Ноутбук Lenovo IdeaPad S145-15IWL (81MV0151RA) Black";

//        if (title.lastIndexOf(")") > 0 && title.lastIndexOf(")") != title.length() - 1) {
//            String part = title.substring(title.lastIndexOf("("), title.lastIndexOf(")") + 1);
//            title = title.replace(part, "");
//            title = title + " " + part;
//            title = title.replaceAll(" {2,}", " ").trim();
//            System.out.println(title.length());
//            System.out.println(title);
//        }


    }
}
