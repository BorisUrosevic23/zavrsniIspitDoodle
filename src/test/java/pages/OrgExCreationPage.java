package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrgExCreationPage extends BaseHelper {

    WebDriver driver;
    public OrgExCreationPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void selectGroupMeetingType()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.id("MeetingTypeSelector-GROUP")));
        WebElement groupMeetingTypeSelector = driver.findElement(By.id("MeetingTypeSelector-GROUP"));
        groupMeetingTypeSelector.click();
    }

    public void modalChecker ()
    {
        List<WebElement> createMeetingModal = driver.findElements(By.id("react-aria-modal-dialog"));
        if (createMeetingModal.size()>0){
            wdWait.until(ExpectedConditions.elementToBeClickable(By.className("StandardModal-primaryButton")));
            WebElement continueButton = driver.findElement(By.cssSelector(".StandardModal-primaryButton"));
            continueButton.click();
        }
    }

}
