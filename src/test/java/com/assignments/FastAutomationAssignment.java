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
        By speedUnitLocator= By.cssSelector("#speed-units");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Boolean isFinalSpeedValueAppeared =wait.until(ExpectedConditions.attributeContains(speedValueLocator,"class","succeeded"));
        Boolean isFinalSpeedUnitAppeared =wait.until(ExpectedConditions.attributeContains(speedUnitLocator,"class","succeeded"));
        if(isFinalSpeedValueAppeared && isFinalSpeedUnitAppeared){
            String finalSpeedValue=driver.findElement(speedValueLocator).getText();
            String finalSpeedUnit=driver.findElement(speedUnitLocator).getText();
            System.out.println(finalSpeedValue+finalSpeedUnit);
        }
    }
    static void printInternetSpeedApproach2(WebDriver driver){
        By speedValueLocator= By.xpath("//div[contains(@class,'succeeded') and @id='speed-value']");
        By speedUnitLocator= By.xpath("//div[contains(@class,'succeeded') and @id='speed-units']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement finalSpeedValue =wait.until(ExpectedConditions.visibilityOfElementLocated(speedValueLocator));
        WebElement finalSpeedUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(speedUnitLocator));
        System.out.println(finalSpeedValue.getText()+finalSpeedUnit.getText());
    }
    static void printInternetSpeedRealTime(WebDriver driver){
        By speedValueLocator= By.id("speed-value");
        By speedUnitLocator= By.id("speed-units");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement finalSpeedValue = wait.until(ExpectedConditions.visibilityOfElementLocated(speedValueLocator));
        WebElement finalSpeedUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(speedUnitLocator));
        while(true) {
            System.out.println(finalSpeedValue.getText() + finalSpeedUnit.getText());
            if(finalSpeedValue.getAttribute("class").contains("succeeded")){
                break;
            }
        }
        System.out.println("DONE");
    }



    public static void main(String[] args) {
//        printInternetSpeedApproach1(launchBrowser());
//        printInternetSpeedApproach2(launchBrowser());
        printInternetSpeedRealTime(launchBrowser());
    }
}
