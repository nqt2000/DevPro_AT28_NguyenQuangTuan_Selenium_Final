package actions.elements;

import actions.common.BasePage;
import interfaces.element.TextBoxPageInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public String verifyOutput(String expectedName, String expectedEmail, String expectedCurrentAddr, String expectedPermanentAddr) {
        waitForElementIsVisible(driver, TextBoxPageInterface.OUTPUT_SECTION);


        String actualName = getTextElement(driver, TextBoxPageInterface.OUTPUT_NAME).replace("Name:", "").trim();
        String actualEmail = getTextElement(driver, TextBoxPageInterface.OUTPUT_EMAIL).replace("Email:", "").trim();
        String actualCurrent = getTextElement(driver, TextBoxPageInterface.OUTPUT_CURRENT_ADDRESS).replace("Current Address :", "").trim();
        String actualPermanent = getTextElement(driver, TextBoxPageInterface.OUTPUT_PERMANENT_ADDRESS).replace("Permananet Address :", "").trim();

        Assert.assertEquals(actualName, expectedName, "Nguyen Van A");
        Assert.assertTrue(actualEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"), "❌ Email không đúng định dạng!");
        Assert.assertEquals(actualEmail, expectedEmail, "❌ Email không đúng!");
        Assert.assertEquals(actualCurrent, expectedCurrentAddr, "❌ Current Address không đúng!");
        Assert.assertEquals(actualPermanent, expectedPermanentAddr, "❌ Permanent Address không đúng!");
        return actualName;
    }

    public void verifyEmailBorderIsRed() {
        WebElement emailInput = driver.findElement(By.xpath((TextBoxPageInterface.EMAIL_INPUT)));
        scrollToElement(driver, TextBoxPageInterface.EMAIL_INPUT);
        String borderColor = emailInput.getCssValue("border-color");
        System.out.println("Email border color: " + borderColor);
        Assert.assertTrue(borderColor.contains("255, 0, 0"),
                "❌ Border email không có màu đỏ như mong đợi!");
    }

    public void verifyOutputNotDisplayedOrEmailMissing() {
        boolean isOutputVisible = isDisplayElement(driver, TextBoxPageInterface.OUTPUT_SECTION);

        if (!isOutputVisible) {
            System.out.println("✅ Output section không hiển thị — PASSED");
        } else {
            boolean emailLineVisible = isDisplayElement(driver, TextBoxPageInterface.OUTPUT_EMAIL);
            Assert.assertFalse(emailLineVisible, "❌ Output hiển thị dòng Email dù email không hợp lệ!");
        }
    }
}
