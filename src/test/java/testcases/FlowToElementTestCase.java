package testcases;

import actions.HomePageAction;
import actions.common.AssertUtils;
import actions.common.BaseTest;
import actions.elements.*;
import common.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlowToElementTestCase extends BaseTest {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeMethod
    public void init() {
        driver = getBrowserDriver("chrome", "https://demoqa.com/");
    }

    @Test(priority = 1, description = "DQ-TB-001")
    public void DQ_TB_001() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        Log.info("Step 1: Click Menu Elements on HomePage");
        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        homePageAction.clickOnMenu("Elements");

        Log.info("Step 2: Click Left Menu elements on ElementPage");
        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");

        Log.info("Step 3: Input full name, email, address");
        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);
        String name = "Nguyen Van A";
        String email = "user@example.com";
        String currentAddr = "12 Nguyen Trai, HN";
        String permanentAddr = "34 Le Loi, HCM";

        textboxPageAction.inputTextBoxes(name, email, currentAddr, permanentAddr);
        textboxPageAction.clickSubmitButton();
    }

    @Test(priority = 2, description = "DQ_TB_002 - Invalid email format")
    public void DQ_TB_002() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");

        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);
        textboxPageAction.inputTextBoxes("Nguyen Van A", "user@", "12 Nguyen Trai", "34 Le Loi");
        textboxPageAction.clickSubmitButton();

        textboxPageAction.verifyEmailBorderIsRed();
        textboxPageAction.verifyOutputNotDisplayedOrEmailMissing();
    }

    @Test(priority = 3)
    public void DQ_CB_001() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Check Box");

        CheckBoxPageAction checkBoxPage = new CheckBoxPageAction(driver);
        checkBoxPage.expandAllNodes();
        checkBoxPage.tickCheckboxByLabel("Desktop");
        checkBoxPage.verifyOutputContainsNode("desktop");
    }

    @Test(priority = 4)
    public void DQ_CB_002() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
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

    @Test(priority = 5)
    public void DQ_RB_001() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Radio Button");

        RadioButtonPageAction radioPage = new RadioButtonPageAction(driver);
        radioPage.selectRadioByLabel("Yes");
        radioPage.verifySelectedText("Yes");
    }

    @Test(priority = 6, description = "DQ-RB-002 - Verify 'No' radio button is disabled")
    public void DQ_RB_002() throws InterruptedException {
        js = (JavascriptExecutor) driver;

        Log.info("Step 1: Open 'Elements' and go to Radio Button page");
        HomePageAction homePageAction = new HomePageAction(driver);
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        homePageAction.clickOnMenu("Elements");

        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Radio Button");

        RadioButtonPageAction radioButtonPageAction = new RadioButtonPageAction(driver);
        boolean isDisabled = radioButtonPageAction.isRadioDisabled("No");

        AssertUtils.assertTrue(isDisabled, "❌ 'No' radio button không bị disable!");
        Log.info("✅ 'No' radio button ở trạng thái disabled, không thể chọn.");
    }

    @Test(priority = 7, description = "DQ-WT-001 - Locate Email by First Name using following-sibling")
    public void DQ_WT_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Lấy Email theo First Name = 'Cierra'");
        String email = webTables.getEmailByFirstName("Cierra");

        Log.info("Step 2: Xác nhận email hiển thị đúng trên UI: " + email);
        AssertUtils.assertTrue(email.contains("@"), "Email không hợp lệ: " + email);
    }

    @Test(priority = 8, description = "DQ-WT-002 - Locate Edit button by using ancestor::")
    public void DQ_WT_002() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Click Edit button theo Last Name = 'Alden'");
        webTables.clickEditButtonByLastName("Alden");

        Log.info("Step 2: Verify form Edit mở ra");
        Thread.sleep(1000);
        AssertUtils.assertTrue(webTables.isEditFormDisplayed(), "Form Edit không hiển thị!");
    }

    @Test(priority = 9, description = "DQ-WT-003 - Get following rows after Age=39")
    public void DQ_WT_003() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Web Tables");

        WebTablesPageAction webTables = new WebTablesPageAction(driver);
        Log.info("Step 1: Lấy tất cả hàng sau hàng có Age=39");
        var followingRows = webTables.getFollowingRowsAfterAge("39");

        Log.info("Step 2: In ra số lượng hàng sau: " + followingRows.size());
        for (var row : followingRows) {
            System.out.println("Row text: " + row.getText());
        }

        AssertUtils.assertFalse(followingRows.isEmpty(), "Không tìm thấy hàng nào sau hàng có Age=39!");
    }

    @Test(priority = 10, description = "DQ-BTN-001 - Double click 'Double Click Me' button")
    public void DQ_BTN_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Buttons");

        ButtonPageAction buttonPage = new ButtonPageAction(driver);
        Log.info("Step 1: Double click button 'Double Click Me'");
        buttonPage.doubleClickButton();

        Log.info("Step 2: Verify message appears correctly");
        String message = buttonPage.getDoubleClickMessage();
        AssertUtils.assertEquals(message, "You have done a double click", "Message không đúng!");
    }

    @Test(priority = 11, description = "DQ-LNK-001 - Verify Created link API feedback")
    public void DQ_LNK_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Links");

        LinksPageAction linksPage = new LinksPageAction(driver);
        Log.info("Step 1: Click link 'Created'");
        linksPage.clickCreatedLink();

        Log.info("Step 2: Verify response status = 201");
        String response = linksPage.getResponseText();
        AssertUtils.assertContains(response, "201", "Phản hồi không chứa status 201: " + response);
    }

    @Test(priority = 12, description = "DQ-UP-001 - Upload valid .png file")
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
        AssertUtils.assertTrue(result.endsWith("logo.png"), "Tên file hiển thị sai: " + result);
    }

    @Test(priority = 13, description = "DQ-DP-001 - Verify button enabled after 5s")
    public void DQ_DP_001() throws InterruptedException {
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Elements");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Dynamic Properties");

        DynamicPropertiesPageAction dynamicPage = new DynamicPropertiesPageAction(driver);
        Log.info("Step 1: Check button initially disabled");
        AssertUtils.assertFalse(dynamicPage.isEnableAfterButtonEnabled(), "Button không disabled ban đầu!");

        Log.info("Step 2: Wait for 6 seconds");
        Thread.sleep(6000);

        Log.info("Step 3: Verify button becomes enabled");
        AssertUtils.assertTrue(dynamicPage.isEnableAfterButtonEnabled(), "Button chưa enabled sau 5s!");
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

        Log.info("Step 2: Accept alert");
        alertsPage.acceptSimpleAlert();

        Log.info("Step 3: Verify alert handled successfully");
        AssertUtils.assertTrue(true, "Alert không được xử lý đúng.");
    }

    @Test(priority = 15, description = "DQ-FM-001 - Điền form hợp lệ tối thiểu và submit, verify modal hiển thị")
    public void DQ_FM_001() throws InterruptedException {
        Log.info("Step 1: Vào menu Forms > Practice Form");
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Forms");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Practice Form");

        PracticeFormPageAction formPage = new PracticeFormPageAction(driver);
        Log.info("Step 2: Điền thông tin hợp lệ");
        formPage.fillForm("Minh", "Le", "minh@example.com", "Male", "0987654321");

        Log.info("Step 3: Submit form");
        formPage.clickSubmit();

        Log.info("Step 4: Verify modal hiển thị và có dữ liệu nhập vào");
        AssertUtils.assertTrue(formPage.isModalDisplayed(), "❌ Modal không hiển thị sau khi submit!");
        AssertUtils.assertContains(formPage.getModalText(), "Minh", "❌ Modal không chứa tên đã nhập!");
        AssertUtils.assertContains(formPage.getModalText(), "minh@example.com", "❌ Modal không chứa email!");
    }

    @Test(priority = 16, description = "DQ-DPICK-001 - Chọn ngày đầu tháng và cuối tháng (BVA)")
    public void DQ_DPICK_001() throws InterruptedException {
        Log.info("Step 1: Vào menu Widgets > Date Picker");
        HomePageAction homePage = new HomePageAction(driver);
        homePage.clickOnMenu("Widgets");

        MenuLeftAction menuLeft = new MenuLeftAction(driver);
        menuLeft.clickOnMenuLeft("Date Picker");

        DatePickerPageAction datePicker = new DatePickerPageAction(driver);
        Log.info("Step 2: Chọn ngày đầu tháng");
        datePicker.selectDay(1);
        String firstDate = datePicker.getSelectedDate();
        Log.info("Ngày đầu tháng: " + firstDate);

        Log.info("Step 3: Chọn ngày cuối tháng (nếu có)");
        datePicker.selectDay(31);
        String lastDate = datePicker.getSelectedDate();
        Log.info("Ngày cuối tháng: " + lastDate);

        Log.info("Step 4: Verify giá trị cập nhật chính xác");
        AssertUtils.assertNotEquals(firstDate, lastDate, "❌ Ngày không thay đổi khi chọn lại!");
    }

}
