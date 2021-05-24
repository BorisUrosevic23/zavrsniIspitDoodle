package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupPollSettingOnCreationPage extends BaseHelper {

    WebDriver driver;
    public GroupPollSettingOnCreationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnContinueButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"d-wizardSettingsNavigationView\"]/div/div/div[2]/button[2]")));
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"d-wizardSettingsNavigationView\"]/div/div/div[2]/button[2]"));
        continueButton.click();
    }

}
