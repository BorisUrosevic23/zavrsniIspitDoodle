package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DoodleStartPage extends BaseHelper {

    @FindBy (className = "HeaderWidget-loginButton")
    WebElement loginButton;

    @FindBy (className = "HeaderWidget-signupButton")
    WebElement signUpButton;

    WebDriver driver;
    public DoodleStartPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

        void navigateToDoodleLoginPage(){
            driver.get("https://doodle-test.com/en/");
        }

        public void clickOnLoginButton()
        {
            navigateToDoodleLoginPage();
            wdWait.until(ExpectedConditions.elementToBeClickable
                    (By.className("HeaderWidget-loginButton")));
            loginButton.click();
        }

        public void clickOnSignUpButton()
        {
            navigateToDoodleLoginPage();
            wdWait.until(ExpectedConditions.elementToBeClickable
                    (By.className("HeaderWidget-signupButton")));
            signUpButton.click();
        }

        private WebElement clickOnLoginFromDoodleStartPage(){
            navigateToDoodleLoginPage();
            return loginButton;
        }

        private WebElement clickOnSignUpFromDoodleStartPage()
        {
            navigateToDoodleLoginPage();
            return signUpButton;
        }


    }
