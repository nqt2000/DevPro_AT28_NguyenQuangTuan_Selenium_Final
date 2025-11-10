package actions;

import actions.common.BasePage;
import interfaces.CommonInterface;
import org.openqa.selenium.WebDriver;

public class HomePageAction extends BasePage {
    WebDriver driver;

    public HomePageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnMenu(String menu) throws InterruptedException {
        scrollIntoView(driver, CommonInterface.HOME_PAGE_MENU, menu);
        highLightElement(driver, CommonInterface.HOME_PAGE_MENU, menu);
        Thread.sleep(2000);
        clickToElement(driver, CommonInterface.HOME_PAGE_MENU, menu);
    }

}
