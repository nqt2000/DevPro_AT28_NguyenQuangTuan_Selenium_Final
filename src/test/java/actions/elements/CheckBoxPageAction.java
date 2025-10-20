package actions.elements;

import actions.common.BasePage;
import interfaces.element.CheckBoxPageInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CheckBoxPageAction extends BasePage {
    private WebDriver driver;

    public CheckBoxPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void expandAllNodes() throws InterruptedException {
        clickToElement(driver, CheckBoxPageInterface.EXPAND_ALL_BUTTON);
        Thread.sleep(2000);
    }

    public void tickCheckboxByLabel(String label) throws InterruptedException {
        String xpath = String.format(CheckBoxPageInterface.CHECKBOX_INPUT_BY_LABEL, label);
        clickToElement(driver, xpath);
        Thread.sleep(2000);
    }

    public void verifyOutputContainsNode(String expectedNode) throws InterruptedException {
        waitForElementIsVisible(driver, CheckBoxPageInterface.OUTPUT_SECTION);
        String output = getTextListFromOutput();
        System.out.println("Output: " + output);
        Assert.assertTrue(output.contains(expectedNode.toLowerCase()),
                "❌ Output không chứa node mong đợi: " + expectedNode);
        Thread.sleep(2000);
    }

    public void verifyAllNodesAreSelected(String... expectedNodes) throws InterruptedException {
        waitForElementIsVisible(driver, CheckBoxPageInterface.OUTPUT_SECTION);
        String output = getTextListFromOutput();
        System.out.println("Output: " + output);

        for (String node : expectedNodes) {
            Assert.assertTrue(output.contains(node.toLowerCase()),
                    "❌ Output không chứa node con: " + node);
        }
        Thread.sleep(2000);
    }

    private String getTextListFromOutput() {
        List<WebElement> items = driver.findElements(By.xpath(CheckBoxPageInterface.OUTPUT_TEXT));
        StringBuilder sb = new StringBuilder();
        for (WebElement item : items) {
            sb.append(item.getText().toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }
}
