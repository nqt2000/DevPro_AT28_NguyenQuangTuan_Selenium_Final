package testcases;

import actions.HomePageAction;
import actions.common.BaseTest;
import actions.elements.CheckBoxPageAction;
import actions.elements.MenuLeftAction;
import actions.elements.TextboxPageAction;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlowToElementTestCase extends BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void init() {
        driver = getBrowserDriver("chrome", "https://demoqa.com/");
    }

    @Test(priority = 1, description = "DQ-TB-001")
    public void DQ_TB_001() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");
        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");
        Thread.sleep(3000);
        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);

        String name = "Nguyen Van A";
        String email = "user@example.com";
        String currentAddr = "12 Nguyen Trai, HN";
        String permanentAddr = "34 Le Loi, HCM";

        textboxPageAction.inputTextBoxes(name, email, currentAddr, permanentAddr);
        textboxPageAction.clickSubmitButton();
        textboxPageAction.verifyOutput(name, email, currentAddr, permanentAddr);
    }

    @Test(priority = 2, description = "DQ_TB_002")
    public void DQ_TB_002() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");
        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");
        Thread.sleep(3000);
        TextboxPageAction textboxPageAction = new TextboxPageAction(driver);

        String name = "Nguyen Van A";
        String email = "user@";
        String currentAddr = "12 Nguyen Trai, HN";
        String permanentAddr = "34 Le Loi, HCM";

        textboxPageAction.inputTextBoxes(name, email, currentAddr, permanentAddr);
        textboxPageAction.clickSubmitButton();
        textboxPageAction.verifyEmailBorderIsRed();
        textboxPageAction.verifyOutputNotDisplayedOrEmailMissing();
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
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
}
