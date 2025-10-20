package actions.elements;

import actions.common.BasePage;
import interfaces.element.RadioButtonInterface;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RadioButtonPageAction extends BasePage {
    private final WebDriver driver;

    public RadioButtonPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRadioByLabel(String label) throws InterruptedException {
        String xpath = String.format(RadioButtonInterface.RADIO_BUTTON_BY_LABEL, label);
        Thread.sleep(2000);
        clickToElement(driver, xpath);
        Thread.sleep(2000);
    }

    public void verifySelectedText(String expectedText) {
        waitForElementIsVisible(driver, RadioButtonInterface.OUTPUT_TEXT);
        String actualText = getTextElement(driver, RadioButtonInterface.OUTPUT_TEXT);
        System.out.println("Output hiển thị: " + actualText);
        Assert.assertEquals(actualText.trim(), expectedText.trim(),
                "❌ Kết quả hiển thị không đúng!");
    }
}