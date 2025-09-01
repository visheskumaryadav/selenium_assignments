package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
Printing the internet speed
 */
public class FastAutomationAssignment {

    static WebDriver launchBrowser(){

        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fast.com/");
        return driver;
    }

    static void printInternetSpeedApproach1(WebDriver driver){
        By speedValueLocator= By.cssSelector("#speed-value");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Boolean isFinalSpeedValueAppeared =wait.until(ExpectedConditions.attributeContains(speedValueLocator,"class","succeeded"));
        if(isFinalSpeedValueAppeared){
            String finalSpeedValue=driver.findElement(speedValueLocator).getText();
            System.out.println(finalSpeedValue);
        }
    }
    static void printInternetSpeedApproach2(WebDriver driver){
        By speedValueLocator= By.xpath("//div[contains(@class,'succeeded') and @id='speed-value']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement finalSpeedValue =wait.until(ExpectedConditions.visibilityOfElementLocated(speedValueLocator));
        System.out.println(finalSpeedValue.getText());
    }



    public static void main(String[] args) {
        printInternetSpeedApproach1(launchBrowser());
        printInternetSpeedApproach2(launchBrowser());
    }
}
