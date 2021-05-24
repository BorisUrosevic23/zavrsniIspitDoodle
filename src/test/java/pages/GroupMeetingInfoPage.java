package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupMeetingInfoPage extends BaseHelper {

    WebDriver driver;
    public GroupMeetingInfoPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void setNameOfPoll (String name)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-pollTitle")));
        WebElement pollTitleField = driver.findElement(By.id("d-pollTitle"));
        pollTitleField.sendKeys(name);
    }

    public void setDescriptionOfPoll (String description)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-pollDescription")));
        WebElement pollDescriptionField = driver.findElement(By.id("d-pollDescription"));
        pollDescriptionField.sendKeys(description);
    }

    public String setPollNameAsDateTime()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public void setLocationOfPollRawInput (String location)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-pollLocation")));
        WebElement pollLocationField = driver.findElement(By.id("d-pollLocation"));
        pollLocationField.sendKeys(location);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-locationSuggestions")));
        WebElement locationSuggestions = driver.findElement(By.id("d-locationSuggestions"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("d-rawInput")));
        WebElement justUseRawInput = driver.findElement(By.className("d-rawInput"));
        justUseRawInput.click();
    }

    public void clickContinueButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"d-wizardGeneralInformationNavigationView\"]/div/div/div[2]/button")));
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"d-wizardGeneralInformationNavigationView\"]/div/div/div[2]/button"));
        continueButton.click();
    }

}
