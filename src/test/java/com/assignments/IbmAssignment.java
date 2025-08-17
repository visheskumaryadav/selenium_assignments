package com.assignments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class IbmAssignment {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver wd = new ChromeDriver(chromeOptions);
        By hybridCloudParentLocator= By.cssSelector("[data-autoid*=nav1]");
        wd.manage().window().maximize();
        wd=new FirefoxDriver();
        wd.get("https://www.ibm.com/in-en");

        WebElement hybridCloudElement =wd.findElement(hybridCloudParentLocator);
        hybridCloudElement.click();
        WebDriverWait wait=new WebDriverWait(wd,Duration.ofSeconds(5));

//         By infraLocator= By.xpath("//*[@data-autoid='c4d--masthead__l0-nav--nav1']//c4d-megamenu-category-heading[@slot='heading' and @title='Overview']");
         Thread.sleep(5000);
//         driver.findElement(infraLocator).click();

        By ITInfrastructureMenuLocator = By.xpath("//c4d-megamenu-category-heading[@title=\"IT infrastructure\"]");
        WebElement ITInfrastructureMenu = wait
                .until(ExpectedConditions.visibilityOfElementLocated(ITInfrastructureMenuLocator));
        ITInfrastructureMenu.click();



    }
}
