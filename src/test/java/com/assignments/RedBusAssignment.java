package com.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RedBusAssignment {
    private static WebDriverWait wait;
    static By destinationInputLocator= By.xpath("//div[contains(@class,'srcDestWrapper')]");
    static By srcInputTextBoxLocator=By.cssSelector("#srcDest");
    static By searchBusesBtnLocator=By.xpath("//button[contains(@class,'primaryButton')]");
    static By primoLabelLocator=By.xpath("//div[contains(@aria-label,\"Primo Bus\")]");
    static By departureTimeSectionLocator=By.xpath("//div[@data-autoid='departureTime']");
    static By departureTimeLocator=By.xpath(".//div[contains(text(),'Evening')]");
    static By rowLocator=By.xpath("//li[contains(@class,'tupleWrapper')]");
    static By endListLocator=By.xpath("//span[contains(text(),\"End of list\")]");
    static By searchCategoryLocator=By.xpath("//div[contains(@class,'searchCategory')]");
    static By searchResultCitiesLocator=By.xpath(".//div[contains(@class,'listHeader')]");


    static WebDriver launchBrowser(){
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.redbus.in/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        return driver;
    }

    static void selectSourceLocation(WebDriver driver){
        List<WebElement> destinationInputWebElementsList=driver.findElements(destinationInputLocator);
        WebElement sourceInputElement=destinationInputWebElementsList.get(0);
        sourceInputElement.click();
        WebElement srcInputTextBoxElement=wait.until(ExpectedConditions.elementToBeClickable(srcInputTextBoxLocator));
        srcInputTextBoxElement.sendKeys("mumbai");

        List<WebElement>searchCategoryList=wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator,2));
        WebElement searchResultCategory=searchCategoryList.get(0);
        List<WebElement> searchResultCitiesList=searchResultCategory.findElements(searchResultCitiesLocator);
        for(WebElement e:searchResultCitiesList){
            if(e.getText().equalsIgnoreCase("Mumbai")){
                e.click();
                break;
            }
        }
    }
    static void selectDestinationLocation(WebDriver driver){
        //No need to add the locator for destination field as the moment source field is selected the cursor moves to destination
        WebElement destinationInputElement=driver.switchTo().activeElement();
        destinationInputElement.sendKeys("pune");
        /*
        we need the below expected condition because initially we have 2 categories by default and we are waiting for 3rd category which
        appears on the DOM only when use enters the city once 3rd category appears it satisfies the expectedConditions and we move forward
         */
        List<WebElement> searchResultCategoryList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchCategoryLocator, 2));
        WebElement searchResultCategory = searchResultCategoryList.get(0);
        List<WebElement> searchResultCitiesList = searchResultCategory.findElements(searchResultCitiesLocator);
        for(WebElement e:searchResultCitiesList){
            if(e.getText().equalsIgnoreCase("Pune")){
                e.click();
                break;
            }
        }
    }
    static void clickSearchBusesBtn(WebDriver driver){
        driver.findElement(searchBusesBtnLocator).click();
    }
    static void selectTheFilters(WebDriver driver){
        WebElement primoLabelElement=wait.until(ExpectedConditions.elementToBeClickable(primoLabelLocator));
        primoLabelElement.click();
        WebElement departureTimeSectionElement=driver.findElement(departureTimeSectionLocator);
        //we need to click because it is covering the nested element
        departureTimeSectionElement.click();
        // we needed this because for some seconds child element was not visible on the screen
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(departureTimeSectionElement,departureTimeLocator));
        departureTimeSectionElement.findElement(departureTimeLocator).click();
    }

    /*
    In this method, are going to read the rows of the buses and once we reached the initial row's count-3 (if total rows in 1st pagination is 10)
    then on the 7th row we will scroll the page using Actions class until we are able to see "endListLocator" is tells about end of the page
    once we reached to end of the page then endListElements.size() will be 1 and we will break the while loop and with this we will get all the buses lists
    Now we only have to recalculate all the buses
     */
    static void printingAllBuses(WebDriver driver){
        Actions actions=new Actions(driver);
        List<WebElement>rowElements=null;
        while(true){

            rowElements=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowLocator));
            List<WebElement>endListElements=driver.findElements(endListLocator);

            if(!endListElements.isEmpty()){
                break;
            }
            actions.scrollToElement(rowElements.get(rowElements.size()-3)).build().perform();

        }
        List<WebElement> rowList = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowLocator));
        System.out.println(rowList.size());
        rowList.forEach(e-> System.out.println(e.findElement(By.xpath(".//div[contains(@class,'travelsName')]")).getText()));

    }
    public static void main(String[] args) {
        WebDriver driver=launchBrowser();
        selectSourceLocation(driver);
        selectDestinationLocation(driver);
        clickSearchBusesBtn(driver);
        selectTheFilters(driver);
        printingAllBuses(driver);
        driver.quit();
    }

}
