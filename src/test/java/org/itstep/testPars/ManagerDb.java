package org.itstep.testPars;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
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
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://www.foxtrot.com.ua/ru/shop/mobilnye_telefony_smartfon");
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        Thread.sleep(5000);
//        jse.executeScript("window.scrollBy(0,10050)", "");
//        Thread.sleep(5000);
//        List<WebElement> elements = driver.findElements(By.cssSelector("img[class='lazy']"));
//        elements.forEach(element -> System.out.println(element.getAttribute("src")));
//        driver.quit();

//        Random random = new Random();
//        List<Integer> lidt =  new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            lidt.add(random.nextInt(3));
//        }
//
//        System.out.println(lidt);

        String title = "Ноутбук Lenovo IdeaPad S145-15IWL (81MV0151RA) Black";

        if (title.lastIndexOf(")") > 0 && title.lastIndexOf(")") != title.length() - 1) {
            String part = title.substring(title.lastIndexOf("("), title.lastIndexOf(")") + 1);
            title = title.replace(part, "");
            title = title + " " + part;
            title = title.replaceAll(" {2,}", " ").trim();
            System.out.println(title.length());
            System.out.println(title);
        }


    }
}
