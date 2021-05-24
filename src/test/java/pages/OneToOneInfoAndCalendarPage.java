package pages;

import helpers.BaseHelper;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OneToOneInfoAndCalendarPage extends BaseHelper {

        WebDriver driver;
        public OneToOneInfoAndCalendarPage(WebDriver driver)
        {
            this.driver = driver;
            PageFactory.initElements(driver,this);
        }

        public void setMeetingTitle (String pollName)
        {
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-panel_title-area")));
            WebElement meetingTitleField = driver.findElement(By.className("meeting-panel_title-area"));
            meetingTitleField.click();
            meetingTitleField.clear();
            meetingTitleField.sendKeys(pollName);
        }

        public String setPollNameAsDateTime()
        {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        return formattedDate;
        }

        private WebElement descriptionField()
        {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-details_description-area")));
        WebElement descriptionField = driver.findElement(By.className("meeting-details_description-area"));
        return descriptionField;
        }

        private WebElement locationField()
        {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-details_location-area")));
        WebElement locationField = driver.findElement(By.className("meeting-details_location-area"));
        return locationField;
        }

        public void setDescription (String description)
        {
                wdWait.until(ExpectedConditions.elementToBeClickable(By.className("meeting-panel_details-button")));
                WebElement addMoreDetailsButton = driver.findElement(By.className("meeting-panel_details-button"));
                addMoreDetailsButton.click();
                wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-details_description-area")));
                WebElement descriptionField = driver.findElement(By.className("meeting-details_description-area"));
                descriptionField.click();
                descriptionField.sendKeys(description);
        }

        public void setLocation (String location)
        {
            if(descriptionField().isDisplayed()){
                wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-details_location-area")));
                WebElement locationField = driver.findElement(By.className("meeting-details_location-area"));
                locationField.click();
                locationField.sendKeys(location);
            } else {
                wdWait.until(ExpectedConditions.elementToBeClickable(By.className("meeting-panel_details-button")));
                WebElement addMoreDetailsButton = driver.findElement(By.className("meeting-panel_details-button"));
                addMoreDetailsButton.click();
                wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("meeting-details_location-areaa")));
                WebElement locationField = driver.findElement(By.className("meeting-details_location-area"));
                locationField.click();
                locationField.sendKeys(location);
            }
        }

        public void selectATimeslotOnCalendar() throws InterruptedException, AWTException {
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("rbc-time-column")));
            List <WebElement> calendarWizard = driver.findElements(By.className("rbc-time-column"));
            System.out.println(calendarWizard.size());
            List <WebElement> singleDay = calendarWizard.get(3).findElements(By.className("rbc-timeslot-group"));
            WebElement around11am = singleDay.get(11);
            js.executeScript("arguments[0].scrollIntoView()", around11am);
            Point location = around11am.getLocation();
            Robot robot = new Robot();
            robot.mouseMove(location.getX() + 15, location.getY() + 89);
            js.executeScript("arguments[0].click()", around11am);
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("rbc-day-hover-interaction")));
            List <WebElement> hoverKurac = driver.findElements(By.className("rbc-day-hover-interaction"));
            hoverKurac.get(2).click();
            Thread.sleep(2000);
            js.executeScript("arguments[0].click()", around11am);

//            around11am.click();

        }

    public void selectATimeslotOnCalendarTwo() throws AWTException {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("rbc-time-column")));
        List <WebElement> calendarWizard = driver.findElements(By.className("rbc-time-column"));
        System.out.println(calendarWizard.size());
        List <WebElement> singleDay = calendarWizard.get(3).findElements(By.className("rbc-timeslot-group"));
        WebElement around11am = singleDay.get(11);
        js.executeScript("arguments[0].scrollIntoView()", around11am);
        js.executeScript("arguments[0].click()", around11am);
    }

        public void clickOnNextWeek ()
        {
            wdWait.until(ExpectedConditions.presenceOfElementLocated
                    (By.className("calendar_toolbar-btn--forward"))); // calendar_toolbar-btn
            WebElement nextWeekButton = driver.findElement
                    (By.className("calendar_toolbar-btn--forward"));
            nextWeekButton.click();
        }

        public void clickOnContinue () {
            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("calendar-page-footer_wrapper")));
            WebElement futer = driver.findElement(By.cssSelector(".calendar-page-footer_wrapper"));
            By continueButan = By.cssSelector("button");
            wdWait.until(ExpectedConditions.elementToBeClickable(continueButan)); // calendar_toolbar-btn
            WebElement continueButton = futer.findElement(continueButan);
//            if (wdWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Button--whiteWithBorder"))).isDisplayed()) {
//                WebElement modalButton = driver.findElement((By.cssSelector(".Button--whiteWithBorder")));
//                modalButton.click();
//            }
//            else
            continueButton.click();
        }

}
