package actions.elements;

import actions.common.BasePage;
import interfaces.element.WebTablesInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTablesPageAction extends BasePage {
    private final WebDriver driver;

    public WebTablesPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmailByFirstName(String firstName) {
        String emailXpath = String.format(WebTablesInterface.EMAIL_BY_FIRST_NAME, firstName);
        waitForElementVisible(driver, emailXpath);
        return getTextElement(driver, emailXpath);
    }

    public void clickEditButtonByLastName(String lastName) {
        String editXpath = String.format(WebTablesInterface.EDIT_BUTTON_BY_LAST_NAME, lastName);
        waitForElementClickable(driver, editXpath);
        clickToElement(driver, editXpath);
    }

    public List<WebElement> getFollowingRowsAfterAge(String age) {
        String rowsXpath = String.format(WebTablesInterface.FOLLOWING_ROWS_AFTER_AGE, age);
        waitForElementVisible(driver, rowsXpath);
        return getListWebElements(driver, rowsXpath);
    }

    public boolean isEditFormDisplayed() {
        return isElementDisplayed(driver, WebTablesInterface.EDIT_FORM_TITLE);
    }
}
