package actions.elements;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import interfaces.element.DynamicPropertiesInterface;

public class DynamicPropertiesPageAction extends BasePage {
    private WebDriver driver;

    public DynamicPropertiesPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEnableAfterButtonEnabled() {
        WebElement button = getElement(driver, DynamicPropertiesInterface.ENABLE_AFTER_BUTTON);
        return button.isEnabled();
    }
}
