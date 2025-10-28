package actions.elements;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.element.AlertsInterface;

public class AlertsPageAction extends BasePage {
    private WebDriver driver;

    public AlertsPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAlertButton() {
        clickToElement(driver, AlertsInterface.ALERT_BUTTON);
    }

    public void acceptSimpleAlert() {
        acceptAlert(driver);
    }
}
