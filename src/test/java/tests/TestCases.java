package tests;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;

import java.awt.*;

public class TestCases extends BaseTest {

    @Test
    public void positiveloginTest() throws InterruptedException {
        DoodleStartPage startPage = new DoodleStartPage(driver);
        startPage.clickOnLoginButton();
        DoodleLoginPage loginPage = new DoodleLoginPage(driver);
        loginPage.loginAttempt("boris.urosevic.tamedia+15@doodle.com", "testPassword");
        DoodleDashboardPage dashboard = new DoodleDashboardPage(driver);
        dashboard.clickUserAvatarMenuButton();
        WebElement logOutButton = dashboard.logOutButton();
        String logOutButtonText = logOutButton.getText().toLowerCase();
        Assert.assertTrue("Log Out button not displayed", logOutButtonText.contains("log out"));
    }

    @Test
    public void negativeEmailLoginTest() throws InterruptedException {
        DoodleStartPage startPage = new DoodleStartPage(driver);
        startPage.clickOnLoginButton();
        DoodleLoginPage loginPage = new DoodleLoginPage(driver);
        loginPage.loginAttempt("boris.urosevicF.tamedia+15@doodle.com", "testPassword");
        String invalidEmail = loginPage.errorMessage().toLowerCase();
        Assert.assertTrue("Error message for invalid email did not appear", invalidEmail.contains("invalid email. try again, please."));

        Thread.sleep(4000); // left for visual confirmation
    }

    @Test
    public void CreateAGroupPollTest () throws InterruptedException {
        DoodleStartPage startPage = new DoodleStartPage(driver);
        startPage.clickOnLoginButton();

        DoodleLoginPage loginPage = new DoodleLoginPage(driver);
        loginPage.loginAttempt("boris.urosevic@doodle-test.com", "testPassword");

        DoodleDashboardPage dashboard = new DoodleDashboardPage(driver);
        dashboard.clickOnCreateGroupMeeting();

        GroupMeetingInfoPage meetingInfoPage = new GroupMeetingInfoPage(driver);

        String pollName = meetingInfoPage.setPollNameAsDateTime();
        Faker faker = new Faker();
        String description = faker.lorem().characters();
        String location = faker.lordOfTheRings().location();

        meetingInfoPage.setDescriptionOfPoll(description);
        meetingInfoPage.setNameOfPoll(pollName);
        meetingInfoPage.setLocationOfPollRawInput(location);
        meetingInfoPage.clickContinueButton();

        GroupCalendarOnCreationPage calendar = new GroupCalendarOnCreationPage(driver);
        calendar.clickOnNewWeek();
        calendar.clickAround8AM();
        calendar.clickAround10AM();
        calendar.clickOnContinue();

        GroupPollSettingOnCreationPage pollSettings = new GroupPollSettingOnCreationPage(driver);
        pollSettings.clickOnContinueButton();

        GroupMeetingCreatedPage meetingCreated = new GroupMeetingCreatedPage(driver);
        String invitee1 = "boris.urosevic+1@doodle-test.com";
        String invitee2 = "boris.urosevic+2@doodle-test.com";
        String invitee3 = "boris.urosevic+3@doodle-test.com";

        meetingCreated.insertEmailToAddInviteesField(invitee1);
        meetingCreated.insertEmailToAddInviteesField(invitee2);
        meetingCreated.insertEmailToAddInviteesField(invitee3);

        meetingCreated.clickOnSendInvitationsButton();
        meetingCreated.clickOnSendYourVotesButton();

        String textFromTable = meetingCreated.getParticipants().getText().toLowerCase();

        Assert.assertTrue("Edit meeting button is not displayed", meetingCreated.getEditMeetingButton().isDisplayed());

        meetingCreated.scrollParticipationTableIntoView();
        Assert.assertTrue("Invitee1 is not in the participation table", textFromTable.contains(invitee1.toLowerCase()));
        Assert.assertTrue("Invitee2 is not in the participation table", textFromTable.contains(invitee2.toLowerCase()));
        Assert.assertTrue("Invitee3 is not in the participation table", textFromTable.contains(invitee3.toLowerCase()));

//      thread sleep ostavljen zbog vizuelne konfirmacije
        Thread.sleep(5000);
    }

    @Test
    public void CreateAOneToOneMeeting() throws InterruptedException, AWTException {

        DoodleStartPage startPage = new DoodleStartPage(driver);
        startPage.clickOnLoginButton();

        DoodleLoginPage loginPage = new DoodleLoginPage(driver);
        loginPage.loginAttempt("boris.urosevic@doodle-test.com", "testPassword");

        DoodleDashboardPage dashboard = new DoodleDashboardPage(driver);
        dashboard.clickOnCreateOneToOneMeeting();

        OneToOneInfoAndCalendarPage infoAndCalendarPage = new OneToOneInfoAndCalendarPage(driver);
        infoAndCalendarPage.setMeetingTitle(infoAndCalendarPage.setPollNameAsDateTime());
        infoAndCalendarPage.setDescription("Lorem ipsum text 20202 @#$%");
        infoAndCalendarPage.setLocation("Belgrade");

        infoAndCalendarPage.clickOnNextWeek();
        infoAndCalendarPage.selectATimeslotOnCalendar();
        infoAndCalendarPage.clickOnContinue();

        OneToOneInvitationsPage invitationsPage = new OneToOneInvitationsPage(driver);
        invitationsPage.enterEmail("boris.urosevic+1@doodle-test.com");

        Faker faker = new Faker();
        String message = faker.lorem().sentence(7);
        invitationsPage.enterMessage(message);
        invitationsPage.sendInvitations();
        String successMessage = invitationsPage.successMessage();

        Assert.assertTrue("Invitation is not sent",successMessage.contains("invitation sent, yay!"));



        Thread.sleep(10000);


    }


    @Test
    public void deleteTheFirstOneToOneMeetingFromDashboard() throws InterruptedException {

        DoodleStartPage startPage = new DoodleStartPage(driver);
        startPage.clickOnLoginButton();

        DoodleLoginPage loginPage = new DoodleLoginPage(driver);
        loginPage.loginAttempt("boris.urosevic@doodle-test.com", "testPassword");

        DoodleDashboardPage dashboard = new DoodleDashboardPage(driver);
        dashboard.waitForEverythingToLoadOnDashboard();
        String nameOfTheFirstMeeting = dashboard.getNameOfTheFirstMeeting();
        String nameOfFirstMeetingBeforeArchive = dashboard.getNameOfTheFirstOneToOneMeeting();

        int listSizeBeforeArchive = dashboard.numberOfMeetingsOnDashboard();

//        System.out.println(nameOfTheFirstMeeting);
//        System.out.println(nameOfFirstMeetingBeforeArchive);
        dashboard.archiveFirstOneToOneMeeting();
        dashboard.waitForEverythingToLoadOnDashboard();
//        System.out.println(nameOfTheFirstMeeting);

        int listSizeAfterArchive = dashboard.numberOfMeetingsOnDashboard();

        Assert.assertTrue("Meeting was not archived", listSizeBeforeArchive > listSizeAfterArchive);

        Thread.sleep(5000); // left for visual confirmation
    }

}
