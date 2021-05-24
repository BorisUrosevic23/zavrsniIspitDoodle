package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OneToOneInvitationsPage extends BaseHelper {

    WebDriver driver;
    public OneToOneInvitationsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmail(String email)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email-7")));
        WebElement emailField = driver.findElement(By.id("email-7"));
        emailField.click();
        emailField.sendKeys(email);
    }

    public void enterMessage(String message)
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sharing-by-email_message")));
        WebElement messageField = driver.findElement(By.className("sharing-by-email_message"));
        messageField.sendKeys(message);
    }

    public void sendInvitations()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div/article/div[2]/div/div/button")));
        WebElement sendInvitationsButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div/div/article/div[2]/div/div/button"));
        sendInvitationsButton.click();
    }

    public String successMessage()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sharing-dialog_confirmation-title")));
        WebElement successBanner = driver.findElement(By.className("sharing-dialog_confirmation-title"));
        String successText = successBanner.getText().toLowerCase();
        return successText;
    }



}
