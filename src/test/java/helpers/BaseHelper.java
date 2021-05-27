package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseHelper
{

    protected static WebDriver driver = new ChromeDriver();

//    @BeforeClass
//    public static void setupClass() {
//        WebDriverManager.chromedriver().setup();
//    }
//
//    @Before
//    public void setupTest() {
//        driver = new ChromeDriver();
//    }

    protected  WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    protected  JavascriptExecutor js = (JavascriptExecutor) driver;

    public  WebElement returnElementAttValue (String attributeName, String attributeValue)
    {String selector = "[" + attributeName + "=" + attributeValue + "]";
    WebElement element = driver.findElement(By.cssSelector(selector));
    return element;
    }

}
