package actions.elements;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.element.LinksInterface;

public class LinksPageAction extends BasePage {
    private WebDriver driver;

    public LinksPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreatedLink() {
        clickToElement(driver, LinksInterface.CREATED_LINK);
    }

    public String getResponseText() {
        return getTextElement(driver, LinksInterface.LINK_RESPONSE);
    }
}
