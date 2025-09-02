package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodepenAutomation {

    static WebDriver launchBrowser(){
        WebDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://codepen.io/rolandixor/pen/mdwZReq");
        return driver;
    }

    static void approach1(String css, WebDriver driver){
        By cssSectionLocator= By.cssSelector("#box-css div.CodeMirror");
        WebElement cssSectionElement=driver.findElement(cssSectionLocator);
        By textAreaLocator=By.tagName("textarea");
        WebElement textAreaElement=cssSectionElement.findElement(textAreaLocator);
        Actions actions=new Actions(driver);
        actions.click(textAreaElement).perform();
        textAreaElement.sendKeys(Keys.chord(Keys.CONTROL,"a")+Keys.DELETE);
        textAreaElement.sendKeys(css);

    }

    public static void main(String[] args) {
        String css= """
                div {
                   background-color: red;
                   border-radius: 100%;
                   height: 50px;
                   left: calc(50% - 50px);
                   position: absolute;
                   right: calc(50% - 50px);
                   width: 50px;
                   animation: bounce 1s ease-in-out infinite;
                   animation-fill-mode: both;
                   animation-direction: alternate;
                  }
                  span {
                   border-radius: 100%;
                   bottom: 32.5%;
                   left: calc(50% - 50px);
                   right: calc(50% - 50px);
                   position: absolute;
                   content: '';
                   background-color: black;
                   filter: blur(3px);
                   width: 50px;
                   height: 5px;
                   animation: shadow 1s ease-in-out infinite;
                   animation-fill-mode: both;
                   animation-direction: alternate;
                   z-index: -1;
                  }
                  @keyframes bounce {from {
                                      top: 25%;
                                      transform: scaleX(79.5%) scaleY(65%);
                                      }
                                      to {
                                      top: 55%;
                                      transform: scale(100%);
                                      }
                                     }
                                     @keyframes shadow {
                                      from {
                                      opacity: 0;
                                      transform: scale(0);
                                      }
                                      to {
                                      opacity: .5;
                                      transform: scale(100%);
                                      }
                                     }
                
                """;
        approach1(css,launchBrowser());

    }

}
