package actions.elements;

import org.openqa.selenium.WebDriver;
import actions.common.BasePage;
import interfaces.element.DatePickerInterface;

public class DatePickerPageAction extends BasePage {
    private WebDriver driver;

    public DatePickerPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDay(int day) {
        clickToElement(driver, DatePickerInterface.DATE_INPUT);
        clickToElement(driver, String.format(DatePickerInterface.DATE_SELECT, day));
    }

    public String getSelectedDate() {
        return getElementAttributeValue(driver, DatePickerInterface.DATE_INPUT, "value");
    }
}
