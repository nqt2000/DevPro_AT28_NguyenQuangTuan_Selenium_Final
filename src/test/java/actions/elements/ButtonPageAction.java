package actions.elements;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.element.ButtonsInterface;

public class ButtonPageAction extends BasePage {
    private WebDriver driver;

    public ButtonPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void doubleClickButton() {
        doubleClickOnElement(driver, ButtonsInterface.DOUBLE_CLICK_BUTTON);
    }

    public String getDoubleClickMessage() {
        return getTextElement(driver, ButtonsInterface.DOUBLE_CLICK_MESSAGE);
    }
}
