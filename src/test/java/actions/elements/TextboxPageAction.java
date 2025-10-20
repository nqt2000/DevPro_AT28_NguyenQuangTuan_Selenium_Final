package actions.elements;

import actions.common.BasePage;
import interfaces.element.TextBoxPageInterface;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TextboxPageAction extends BasePage {
    WebDriver driver;

    public TextboxPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void inputTextBoxes(String fullName, String email, String currentAddress, String permanentAddress) throws InterruptedException {
        enterTextToElement(driver, TextBoxPageInterface.FULL_NAME_INPUT, fullName);
        Thread.sleep(2000);

        enterTextToElement(driver, TextBoxPageInterface.EMAIL_INPUT, email);
        Thread.sleep(2000);

        enterTextToElement(driver, TextBoxPageInterface.CURRENT_ADDRESS_TEXTAREA, currentAddress);
        Thread.sleep(2000);

        enterTextToElement(driver, TextBoxPageInterface.PERMANENT_ADDRESS_TEXTAREA, permanentAddress);
        Thread.sleep(2000);
    }

    public void clickSubmitButton() throws InterruptedException {
        scrollToElement(driver, TextBoxPageInterface.SUBMIT_BUTTON);
        clickToElement(driver, TextBoxPageInterface.SUBMIT_BUTTON);
        Thread.sleep(5000);
    }

    public void verifyOutput(String expectedName, String expectedEmail, String expectedCurrentAddr, String expectedPermanentAddr) {
        String actualName = getTextElement(driver, TextBoxPageInterface.OUTPUT_NAME).replace("Name:", "").trim();
        String actualEmail = getTextElement(driver, TextBoxPageInterface.OUTPUT_EMAIL).replace("Email:", "").trim();
        String actualCurrent = getTextElement(driver, TextBoxPageInterface.OUTPUT_CURRENT_ADDRESS).replace("Current Address :", "").trim();
        String actualPermanent = getTextElement(driver, TextBoxPageInterface.OUTPUT_PERMANENT_ADDRESS).replace("Permananet Address :", "").trim();

        Assert.assertEquals(actualName, expectedName, "❌ Name không đúng!");
        Assert.assertTrue(actualEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"), "❌ Email không đúng định dạng!");
        Assert.assertEquals(actualEmail, expectedEmail, "❌ Email không đúng!");
        Assert.assertEquals(actualCurrent, expectedCurrentAddr, "❌ Current Address không đúng!");
        Assert.assertEquals(actualPermanent, expectedPermanentAddr, "❌ Permanent Address không đúng!");
    }
}
