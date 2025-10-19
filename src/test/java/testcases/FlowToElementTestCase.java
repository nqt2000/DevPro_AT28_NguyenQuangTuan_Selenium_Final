package testcases;

import actions.HomePageAction;
import actions.common.BaseTest;
import actions.elements.MenuLeftAction;
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
        Thread.sleep(10000);
    }
}
