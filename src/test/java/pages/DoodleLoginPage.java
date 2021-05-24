package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DoodleLoginPage extends BaseHelper {

    WebDriver driver;
    public DoodleLoginPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    private WebElement returnEmailField()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-tab-panel")));
        WebElement loginTabPanel = driver.findElement(By.id("login-tab-panel"));
        WebElement emailField = loginTabPanel.findElement(By.name("email"));
        return emailField;
    }

    private WebElement returnPasswordField()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-tab-panel")));
        WebElement loginTabPanel = driver.findElement(By.id("login-tab-panel"));
        WebElement passwordField = loginTabPanel.findElement(By.name("password"));
        return passwordField;
    }

    private WebElement returnLogInButton ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("Button--green")));
        WebElement logInButton = driver.findElement(By.className("Button--green"));
        return logInButton;
    }

    private void populateEmailField (String email)
    {
        WebElement emailField = returnEmailField();
        emailField.sendKeys(email);
    }

    private void populatePasswordField (String password)
    {
        WebElement passwordField = returnPasswordField();
        passwordField.sendKeys(password);
    }

    private void clickOnLoginButton()
    {
        WebElement logInButton = returnLogInButton();
        logInButton.click();
    }

    public void loginAttempt (String email, String password)
    {
        populateEmailField(email);
        populatePasswordField(password);
        clickOnLoginButton();
    }

    public String errorMessage ()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("LoginTab")));
        WebElement loginTab = driver.findElement(By.className("LoginTab"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"login-tab-panel\"]/form/div[2]/div[2]/span")));
        WebElement errorMessage = loginTab.findElement(By.xpath("//*[@id=\"login-tab-panel\"]/form/div[2]/div[2]/span"));
        String errorMessageText = errorMessage.getText().toLowerCase();
        return errorMessageText;
    }



}
