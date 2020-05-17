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

import java.util.List;
import java.util.Scanner;

public class ManagerDb {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.foxtrot.com.ua/ru/shop/mobilnye_telefony_smartfon");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Thread.sleep(5000);
        jse.executeScript("window.scrollBy(0,10050)", "");
        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.cssSelector("img[class='lazy']"));
        elements.forEach(element -> System.out.println(element.getAttribute("src")));
//        driver.quit();

    }
}
