package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class YatraAssignment {
    static WebDriver launchBrowser(String url){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications"); //To disable notification
        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    static int lowestFairOfCurrentMonth(WebElement currentMonthElement,WebDriverWait wait){
        //we can use not operator while defining xpath
        By ticketPricesLocator = By.xpath(".//div[contains(@class,'react-datepicker__day') and not(contains(@class, 'outside-month'))]//span[contains(@class,'custom-day-content')]");
        //We can also locate the nestedElements using wait mechanism
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(currentMonthElement, ticketPricesLocator));
        List<WebElement> currentMonthTicketPrices=currentMonthElement.findElements(ticketPricesLocator);



        int lowest=Integer.MAX_VALUE;
        String lowestPriceDateElement=null;
        for(WebElement element:currentMonthTicketPrices){
            int price=Integer.parseInt(element.getText().replace(",", "").replace("₹", ""));
            if(price<lowest){
                lowest=price;
                //we can use ".." for parent of current element
                lowestPriceDateElement = element.findElement(By.xpath("..")).getText().split("\n")[0];
            }
        }
        return lowest;
    }
    static int lowestFairOfNextMonth(WebElement nextMonthElement,WebDriverWait wait){
        // Wait for nested elements inside the current month element
        By ticketPricesLocator = By.xpath(".//div[contains(@class,'react-datepicker__day') and not(contains(@class, 'outside-month'))]//span[contains(@class,'custom-day-content')]");
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(nextMonthElement, ticketPricesLocator));

        List<WebElement> nextMonthTicketPrices=nextMonthElement.findElements(ticketPricesLocator);
        int lowest=Integer.MAX_VALUE;
        String lowestPriceDateElement=null;
        for(WebElement element:nextMonthTicketPrices){
            int price=Integer.parseInt(element.getText().replace(",", "").replace("₹", ""));
            if(price<lowest){
                lowest=price;
                lowestPriceDateElement = element.findElement(By.xpath("..")).getText().split("\n")[0];
            }
        }
        return lowest;
    }
    static int lowestFareInBothMonths(String url){
        WebDriver driver=launchBrowser(url);
        By modalCrossBtnLocator= By.xpath("(//img[@alt='cross' and @loading='lazy'])[1]");
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement crossBtnWebElement=wait.until(ExpectedConditions.elementToBeClickable(modalCrossBtnLocator));
        crossBtnWebElement.click();
        //clicking the departure date
        By departureDateLocator=By.xpath("//div[contains(@aria-label,\"Departure Date inputbox\")]");
        WebElement departureDateElement=wait.until(ExpectedConditions.elementToBeClickable(departureDateLocator));
        departureDateElement.click();

        By monthLocators=By.xpath("//div[@class=\"react-datepicker__month\"]");
        List<WebElement> monthsElementList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(monthLocators));
        int currentMonthPrice=0;
        int nextMonthPrice=0;
        if(!monthsElementList.isEmpty()){
            currentMonthPrice=lowestFairOfCurrentMonth(monthsElementList.get(0),wait);
            nextMonthPrice=lowestFairOfNextMonth(monthsElementList.get(1),wait);
        }
        return Math.min(currentMonthPrice, nextMonthPrice);


    }

    public static void main(String[] args) throws InterruptedException {
        int lowestFare=lowestFareInBothMonths("https://www.yatra.com/");
        System.out.println("LOWEST:"+lowestFare);

    }


}


