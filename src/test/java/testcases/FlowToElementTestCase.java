package testcases;

import actions.HomePageAction;
import actions.common.BaseTest;
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

    @Test
    public void flowToElement() throws InterruptedException {
        HomePageAction homePageAction = new HomePageAction(driver);
        homePageAction.clickOnMenu("Elements");
        MenuLeftAction menuLeftAction = new MenuLeftAction(driver);
        menuLeftAction.clickOnMenuLeft("Text Box");
        Thread.sleep(3000);
        TextboxPageAction  textboxPageAction = new TextboxPageAction(driver);

        // Input
        String name = "Nguyen Van A";
        String email = "user@example.com";
        String currentAddr = "12 Nguyen Trai, HN";
        String permanentAddr = "34 Le Loi, HCM";

        textboxPageAction.inputTextBoxes(name,email,currentAddr,permanentAddr);
        textboxPageAction.clickSubmitButton();
        textboxPageAction.verifyOutput(name,email,currentAddr,permanentAddr);
    }


}
