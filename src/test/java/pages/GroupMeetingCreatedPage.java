package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupMeetingCreatedPage extends BaseHelper {

    WebDriver driver;
    public GroupMeetingCreatedPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void insertEmailToAddInviteesField (String email)
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"d-sharingView\"]/div/section[2]/div[1]/div/div[1]/input")));
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"d-sharingView\"]/div/section[2]/div[1]/div/div[1]/input"));
        emailField.sendKeys(email);
        emailField.sendKeys(Keys.SPACE);
    }

    public void clickOnSendInvitationsButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("d-shareViaEmailButton")));
        WebElement sendButton = driver.findElement(By.className("d-shareViaEmailButton"));
        sendButton.click();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-participationFlashMessages")));
        WebElement successNag = driver.findElement(By.id("d-participationFlashMessages"));
        wdWait.until(ExpectedConditions.invisibilityOf(successNag));
    }


    public void clickOnSendYourVotesButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("/html/body/div[2]/div/div/div/section/div/div/div/div/div[2]/div/div[3]/section/div/div/div[3]/button")));
        WebElement sendYourVoteButton = driver.findElement
                (By.xpath("/html/body/div[2]/div/div/div/section/div/div/div/div/div[2]/div/div[3]/section/div/div/div[3]/button"));
        js.executeScript("arguments[0].scrollIntoView();",sendYourVoteButton);
        sendYourVoteButton.click();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("d-toggleSubscriptionToUpdates")));
        WebElement unsubscribeFromUpdatesButton = driver.findElement(By.className("d-toggleSubscriptionToUpdates"));
        String textFromTheButton = unsubscribeFromUpdatesButton.getText().toLowerCase();
        wdWait.until(ExpectedConditions.textToBePresentInElement(unsubscribeFromUpdatesButton, "Unsubscribe from updates"));
    }

    public WebElement getEditMeetingButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated
                (By.id("d-adminBarView")));
        WebElement adminBar = driver.findElement(By.id("d-adminBarView"));
        WebElement editButton = adminBar.findElement
                (By.className("d-edit"));
 //       js.executeScript("arguments[0].scrollIntoView();",editButton);
        return editButton;
    }

    public WebElement getParticipants()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-pollView")));
        WebElement wholeTable = driver.findElement(By.id("d-pollView"));
        js.executeScript("arguments[0].scrollIntoView();",wholeTable);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("d-participants")));
        WebElement listOfParticipants = wholeTable.findElement(By.className("d-participants"));
        return listOfParticipants;
    }

    public void scrollParticipationTableIntoView ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d-pollView")));
        WebElement wholeTable = driver.findElement(By.id("d-pollView"));
        js.executeScript("arguments[0].scrollIntoView();",wholeTable);
    }


}
