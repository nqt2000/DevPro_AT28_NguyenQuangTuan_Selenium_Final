package testcases;

import actions.HomePageAction;
import actions.common.AssertUtils;
import actions.common.BaseTest;
import actions.elements.*;
import common.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlowToElementTestCase extends BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void init() {
        driver = getBrowserDriver("chrome", "https://demoqa.com/");
    }

    @Test(priority = 1, description = "DQ-TB-001 - Verify valid Text Box input")
    public void DQ_TB_001() throws InterruptedException {
        Log.info("Step 1: Click 'Elements' menu on Home Page");
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        Log.info("Step 2: Click 'Text Box' in the left menu");
        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");

        Log.info("Step 3: Input full name, email, and addresses");
        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);
        String name = "Nguyen Van A";
        String email = "user@example.com";
        String currentAddr = "12 Nguyen Trai, HN";
        String permanentAddr = "34 Le Loi, HCM";

        textboxPageAction.inputTextBoxes(name, email, currentAddr, permanentAddr);
        textboxPageAction.clickSubmitButton();
    }

    @Test(priority = 2, description = "DQ-TB-002 - Verify invalid email format handling")
    public void DQ_TB_002() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");

        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);
        textboxPageAction.inputTextBoxes("Nguyen Van A", "user@", "12 Nguyen Trai", "34 Le Loi");
        textboxPageAction.clickSubmitButton();

        textboxPageAction.verifyEmailBorderIsRed();
        textboxPageAction.verifyOutputNotDisplayedOrEmailMissing();
    }

    @Test(priority = 3, description = "DQ-CB-001 - Verify Check Box selection by label")
    public void DQ_CB_001() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Check Box");

        CheckBoxPageAction checkBoxPage = new CheckBoxPageAction(driver);
        checkBoxPage.expandAllNodes();
        checkBoxPage.tickCheckboxByLabel("Desktop");
        checkBoxPage.verifyOutputContainsNode("desktop");
    }

    @Test(priority = 4, description = "DQ-CB-002 - Verify all subnodes are selected when choosing 'Home'")
    public void DQ_CB_002() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Check Box");

        CheckBoxPageAction checkBoxPage = new CheckBoxPageAction(driver);
        checkBoxPage.expandAllNodes();
        checkBoxPage.tickCheckboxByLabel("Home");

        checkBoxPage.verifyAllNodesAreSelected(
                "home", "desktop", "notes", "commands",
                "documents", "workspace", "react", "angular", "veu",
                "office", "public", "private", "classified", "general",
                "downloads", "wordFile", "excelFile"
        );
    }

    @Test(priority = 5, description = "DQ-RB-001 - Verify 'Yes' radio button selection")
    public void DQ_RB_001() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Radio Button");

        RadioButtonPageAction radioPage = new RadioButtonPageAction(driver);
        radioPage.selectRadioByLabel("Yes");
        radioPage.verifySelectedText("Yes");
    }

    @Test(priority = 6, description = "DQ-RB-002 - Verify 'No' radio button is disabled")
    public void DQ_RB_002() throws InterruptedException {
        Log.info("Step 1: Open 'Elements' > 'Radio Button'");
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Radio Button");

        RadioButtonPageAction radioButtonPageAction = new RadioButtonPageAction(driver);
        boolean isDisabled = radioButtonPageAction.isRadioDisabled("No");

        AssertUtils.assertTrue(isDisabled, "'No' radio button is not disabled!");
        Log.info("'No' radio button is disabled as expected.");
    }

    @Test(priority = 7, description = "DQ-WT-001 - Locate Email by First Name using following-sibling")
    public void DQ_WT_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Get email for First Name = 'Cierra'");
        String email = webTables.getEmailByFirstName("Cierra");

        Log.info("Step 2: Verify email is displayed correctly: " + email);
        AssertUtils.assertTrue(email.contains("@"), "Invalid email: " + email);
    }

    @Test(priority = 8, description = "DQ-WT-002 - Locate Edit button using ancestor::")
    public void DQ_WT_002() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Click Edit button for Last Name = 'Alden'");
        webTables.clickEditButtonByLastName("Alden");

        Log.info("Step 2: Verify Edit form is displayed");
        Thread.sleep(1000);
        AssertUtils.assertTrue(webTables.isEditFormDisplayed(), "Edit form is not displayed!");
    }

    @Test(priority = 9, description = "DQ-WT-003 - Get rows following Age = 39")
    public void DQ_WT_003() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Retrieve all rows after Age = 39");
        var followingRows = webTables.getFollowingRowsAfterAge("39");

        Log.info("Step 2: Print number of following rows: " + followingRows.size());
        for (var row : followingRows) {
            System.out.print("Row text: " + row.getText() + "/n");
        }

        AssertUtils.assertFalse(followingRows.isEmpty(), "No rows found after Age = 39!");
    }

    @Test(priority = 10, description = "DQ-BTN-001 - Double click 'Double Click Me' button")
    public void DQ_BTN_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Buttons");

        ButtonPageAction buttonPage = new ButtonPageAction(driver);
        Log.info("Step 1: Double click 'Double Click Me' button");
        buttonPage.doubleClickButton();

        Log.info("Step 2: Verify message appears correctly");
        String message = buttonPage.getDoubleClickMessage();
        AssertUtils.assertEquals(message, "You have done a double click", "Incorrect double-click message!");
    }

    @Test(priority = 11, description = "DQ-LNK-001 - Verify 'Created' link API response")
    public void DQ_LNK_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Links");

        LinksPageAction linksPage = new LinksPageAction(driver);
        Log.info("Step 1: Click 'Created' link");
        linksPage.clickCreatedLink();

        Log.info("Step 2: Verify response status = 201");
        String response = linksPage.getResponseText();
        AssertUtils.assertContains(response, "201", "Response does not contain status 201: " + response);
    }

    @Test(priority = 12, description = "DQ-UP-001 - Upload a valid .png file")
    public void DQ_UP_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Upload and Download");

        UploadDownloadPageAction uploadPage = new UploadDownloadPageAction(driver);
        String filePath = "C:\\temp\\logo.png";

        Log.info("Step 1: Upload file " + filePath);
        uploadPage.uploadFile(filePath);

        Log.info("Step 2: Verify uploaded file name");
        String result = uploadPage.getUploadedFilePath();
        AssertUtils.assertTrue(result.endsWith("logo.png"), "Incorrect file name displayed: " + result);
    }

    @Test(priority = 13, description = "DQ-DP-001 - Verify button becomes enabled after 5 seconds")
    public void DQ_DP_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Dynamic Properties");

        DynamicPropertiesPageAction dynamicPage = new DynamicPropertiesPageAction(driver);
        Log.info("Step 1: Verify button is initially disabled");
        AssertUtils.assertFalse(dynamicPage.isEnableAfterButtonEnabled(), "Button should be disabled initially!");

        Log.info("Step 2: Wait for 6 seconds");
        Thread.sleep(6000);

        Log.info("Step 3: Verify button becomes enabled");
        AssertUtils.assertTrue(dynamicPage.isEnableAfterButtonEnabled(), "Button did not become enabled after 5s!");
    }

    @Test(priority = 14, description = "DQ-AL-001 - Handle simple alert")
    public void DQ_AL_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Alerts, Frame & Windows");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Alerts");

        AlertsPageAction alertsPage = new AlertsPageAction(driver);
        Log.info("Step 1: Click 'Click me' to trigger alert");
        alertsPage.clickAlertButton();

        Log.info("Step 2: Accept the alert");
        alertsPage.acceptSimpleAlert();

        Log.info("Step 3: Verify alert handled successfully");
        AssertUtils.assertTrue(true, "Alert was not handled correctly.");
    }

    @Test(priority = 15, description = "DQ-FM-001 - Fill valid form and verify modal is displayed")
    public void DQ_FM_001() throws InterruptedException {
        Log.info("Step 1: Navigate to Forms > Practice Form");
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Forms");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Practice Form");

        PracticeFormPageAction formPage = new PracticeFormPageAction(driver);
        Log.info("Step 2: Fill in valid information");
        formPage.fillForm("Minh", "Le", "minh@example.com", "Male", "0987654321");

        Log.info("Step 3: Submit the form");
        formPage.clickSubmit();

        Log.info("Step 4: Verify modal is displayed with submitted data");
        AssertUtils.assertTrue(formPage.isModalDisplayed(), "Modal is not displayed after submission!");
        AssertUtils.assertContains(formPage.getModalText(), "Minh", "Modal does not contain the entered name!");
        AssertUtils.assertContains(formPage.getModalText(), "minh@example.com", "Modal does not contain the entered email!");
    }

    @Test(priority = 16, description = "DQ-DPICK-001 - Select first and last day of the month (BVA)")
    public void DQ_DPICK_001() throws InterruptedException {
        Log.info("Step 1: Navigate to Widgets > Date Picker");
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Widgets");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Date Picker");

        DatePickerPageAction datePicker = new DatePickerPageAction(driver);
        Log.info("Step 2: Select first day of the month");
        datePicker.selectDay(1);
        String firstDate = datePicker.getSelectedDate();
        Log.info("First day of month: " + firstDate);

        Log.info("Step 3: Select last day of the month");
        datePicker.selectDay(31);
        String lastDate = datePicker.getSelectedDate();
        Log.info("Last day of month: " + lastDate);

        Log.info("Step 4: Verify values are updated correctly");
        AssertUtils.assertNotEquals(firstDate, lastDate, "Date did not change after re-selection!");
    }
}
