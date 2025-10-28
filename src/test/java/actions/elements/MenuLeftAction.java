package actions.elements;

import actions.common.BasePage;
import interfaces.CommonInterface;
import org.openqa.selenium.WebDriver;

public class MenuLeftAction extends BasePage {
    private final WebDriver driver;

    public MenuLeftAction(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnMenuLeft(String menu) {
        scrollIntoView(driver, CommonInterface.ELEMENT_MENU, menu);
        highLightElement(driver, CommonInterface.ELEMENT_MENU, menu);
        clickToElement(driver, CommonInterface.ELEMENT_MENU, menu);
    }
}
