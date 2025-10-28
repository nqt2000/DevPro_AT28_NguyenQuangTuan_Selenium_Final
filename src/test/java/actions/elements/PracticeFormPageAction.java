package actions.elements;

import org.openqa.selenium.WebDriver;
import actions.common.BasePage;
import interfaces.element.PracticeFormInterface;

public class PracticeFormPageAction extends BasePage {
    private WebDriver driver;

    public PracticeFormPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String firstName, String lastName, String email, String gender, String mobile) {
        enterTextToElement(driver, PracticeFormInterface.FIRST_NAME, firstName);
        enterTextToElement(driver, PracticeFormInterface.LAST_NAME, lastName);
        enterTextToElement(driver, PracticeFormInterface.EMAIL, email);

        if (gender.equalsIgnoreCase("Male")) {
            clickToElement(driver, PracticeFormInterface.GENDER_MALE);
        }
        enterTextToElement(driver, PracticeFormInterface.MOBILE, mobile);
    }

    public void clickSubmit() {
        scrollToElement(driver, PracticeFormInterface.SUBMIT_BUTTON);
        clickToElement(driver, PracticeFormInterface.SUBMIT_BUTTON);
    }

    public boolean isModalDisplayed() {
        return isDisplayElement(driver, PracticeFormInterface.MODAL_CONTENT);
    }

    public String getModalText() {
        return getTextElement(driver, PracticeFormInterface.MODAL_TEXT);
    }
}
