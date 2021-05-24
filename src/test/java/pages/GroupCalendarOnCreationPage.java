package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GroupCalendarOnCreationPage extends BaseHelper {

    WebDriver driver;
    public GroupCalendarOnCreationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAround8AM ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-wizardWeekView")));
        WebElement calendarWizard = driver.findElement(By.id("d-wizardWeekView"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//*[@id=\"d-wizardWeekView\"]/div[2]/div/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[17]/td[2]")));
        WebElement myTime8AM = calendarWizard.findElement
                (By.xpath("//*[@id=\"d-wizardWeekView\"]/div[2]/div/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[17]/td[2]"));
        js.executeScript("arguments[0].scrollIntoView();",myTime8AM);
        myTime8AM.click();
    }

    public void clickAround10AM ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-wizardWeekView")));
        WebElement calendarWizard = driver.findElement(By.id("d-wizardWeekView"));
        WebElement myTime10AM = calendarWizard.findElement
                (By.xpath("//*[@id=\"d-wizardWeekView\"]/div[2]/div/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[21]/td[2]"));
        js.executeScript("arguments[0].scrollIntoView();",myTime10AM);
        myTime10AM.click();
        js.executeScript("window.scrollBy(0,-500)","");
    }

    public void clickOnNewWeek()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("d-nextWeek")));
        WebElement nextWeekButton = driver.findElement(By.className("d-nextWeek"));
        nextWeekButton.click();

    }

    public void clickOnContinue ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"d-wizardOptionsNavigationView\"]/div/div/div[2]/button[2]")));
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"d-wizardOptionsNavigationView\"]/div/div/div[2]/button[2]"));
        continueButton.click();
    }

}
