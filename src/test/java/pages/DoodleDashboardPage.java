package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DoodleDashboardPage extends BaseHelper {

    WebDriver driver;
    public DoodleDashboardPage (WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void goToCreationFlow()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("UserMenu")));
        driver.navigate().to("https://doodle-test.com/meeting/organize");
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("MeetingTypeSelector")));
    }

    public void waitForEverythingToLoadOnDashboard()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c")));
        WebElement meetingGrid = driver.findElement(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated((By.className("Link__6a9634bbc9ec3d1397af4935d816f020"))));
        List <WebElement> singleMeeting = meetingGrid.findElements(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"));
        wdWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"),4));
    }

    public void clickUserAvatarMenuButton()
    {
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("UserMenu")));
        WebElement userAvatarMenu = driver.findElement(By.className("UserMenu"));
        userAvatarMenu.click();
    }

    public WebElement logOutButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("Menu-list")));
        WebElement menuList = driver.findElement(By.className("Menu-list"));
       //  WebElement menuList = returnElementAttValue("class","Menu-list");
        WebElement logOutButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/header/section/div/nav/ul[2]/li[1]/div/div/ul/li[4]/span"));
        return logOutButton;
    }


    public void clickCreateButton()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("CreatePollMenu")));
        WebElement createButton = driver.findElement(By.className("CreatePollMenu"));
        createButton.click();
    }

    public void clickOnCreateGroupMeeting()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='navigation-create-button']")));
        WebElement createButton = driver.findElement(By.cssSelector("[data-testid='navigation-create-button']"));
        createButton.click();
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='create-group-poll-button']")));
        WebElement groupPollCreate = driver.findElement(By.cssSelector("[data-testid='create-group-poll-button']"));
        groupPollCreate.click();
//        List<WebElement> menu = createButton.findElements(By.className("Menu-item"));
//        menu.get(0).click();
    }

    public void clickOnCreateOneToOneMeeting() {

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='navigation-create-button']")));
        WebElement createButton = driver.findElement(By.cssSelector("[data-testid='navigation-create-button']"));
        createButton.click();
        wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='create-one-on-one-button']")));
        WebElement groupPollCreate = driver.findElement(By.cssSelector("[data-testid='create-one-on-one-button']"));
        groupPollCreate.click();
    }

    public void archiveFirstOneToOneMeeting()
    {
        WebElement desiredMeeting = getFirstOneToOneMeeting();
        WebElement kebabMenu = desiredMeeting.findElement(By.className("moreButtonToggle__ee91f1804acef195561322f06d1371cb"));
        kebabMenu.click();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("kebabMenuList__b540ce5e80123367715d691f8924faf1")));
        WebElement dropdownMenu = driver.findElement(By.className("kebabMenuList__b540ce5e80123367715d691f8924faf1"));
        List <WebElement> dropdownOptions = dropdownMenu.findElements(By.tagName("li"));
            for (WebElement li : dropdownOptions) {
                if (li.getText().contains("Archive")) {
                    li.click();
                }
            }
    }

    public WebElement getFirstMeeting()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c")));
        WebElement meetingGrid = driver.findElement(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated((By.className("Link__6a9634bbc9ec3d1397af4935d816f020"))));
        List <WebElement> singleMeeting = meetingGrid.findElements(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"));
        WebElement desiredMeeting = singleMeeting.get(0);
        return desiredMeeting;
    }

    public WebElement getFirstOneToOneMeeting(){
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c")));
        WebElement meetingGrid = driver.findElement(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated((By.className("Link__6a9634bbc9ec3d1397af4935d816f020"))));
        List <WebElement> singleMeeting = meetingGrid.findElements(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"));
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        for (WebElement desiredMeeting : singleMeeting)
            for (int i=0;i <= singleMeeting.size();i++){
                WebElement desiredMeeting = singleMeeting.get(i);
                String desiredMeetingName = desiredMeeting.getText().toLowerCase();
                    if (desiredMeetingName.contains("1-on-1")){
                        js.executeScript("arguments[0].scrollIntoView();",desiredMeeting);
                        return desiredMeeting;
            }
        }
        return null;
    }

    public String getNameOfTheFirstOneToOneMeeting()
    {
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c")));
            WebElement meetingGrid = driver.findElement(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c"));
            wdWait.until(ExpectedConditions.presenceOfElementLocated((By.className("Link__6a9634bbc9ec3d1397af4935d816f020"))));
            List <WebElement> singleMeeting = meetingGrid.findElements(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"));
            String nameOfFirstMeeting = "";
            for (int i=0;i <= singleMeeting.size();i++){
                WebElement desiredMeeting = singleMeeting.get(i);
                String desiredMeetingName = desiredMeeting.getText().toLowerCase();
                if (desiredMeetingName.contains("1-on-1")){
                    nameOfFirstMeeting = desiredMeeting.getText().toLowerCase();
                    break;
                }
            }
            return nameOfFirstMeeting;
    }

    public String getNameOfTheFirstMeeting()
    {
        WebElement desiredMeeting = getFirstMeeting();
        return  desiredMeeting.getText().toLowerCase();
    }

    public int numberOfMeetingsOnDashboard()
    {
        waitForEverythingToLoadOnDashboard();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c")));
        WebElement meetingGrid = driver.findElement(By.className("GridContainer__3eb0ac30a1983e09c4ea69c29e40dd9c"));
        wdWait.until(ExpectedConditions.presenceOfElementLocated((By.className("Link__6a9634bbc9ec3d1397af4935d816f020"))));
        List <WebElement> singleMeeting = meetingGrid.findElements(By.className("Link__6a9634bbc9ec3d1397af4935d816f020"));
        return singleMeeting.size();
    }

    public void deleteBETAmeeting()
    {
        WebElement desiredMeeting = getFirstOneToOneMeeting();
        WebElement kebabMenuButton = desiredMeeting.findElement(By.className("moreButtonToggle__ee91f1804acef195561322f06d1371cb"));
        kebabMenuButton.click();
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("kebabMenuList__b540ce5e80123367715d691f8924faf1")));
        WebElement dropdownMenu = driver.findElement(By.className("kebabMenuList__b540ce5e80123367715d691f8924faf1"));
        List <WebElement> dropdownOptions = dropdownMenu.findElements(By.tagName("li"));
        if (desiredMeeting.getText().contains("BETA")) {
            for (WebElement li : dropdownOptions) {
                if (li.getText().contains("Delete")) {
                    li.click();
                }
            }
        }
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("react-aria-modal-dialog")));
        WebElement deleteModal = driver.findElement((By.id("react-aria-modal-dialog")));
        WebElement deleteButtonModal = deleteModal.findElement(By.cssSelector(".StandardModal-primaryButton"));
        deleteButtonModal.click();
        wdWait.until(ExpectedConditions.invisibilityOf(desiredMeeting));
    }

}
