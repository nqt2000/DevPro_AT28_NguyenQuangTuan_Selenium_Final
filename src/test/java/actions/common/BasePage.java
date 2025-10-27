package actions.common;

import common.GlobalVariables;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BasePage {
    private WebElement element;
    private List<WebElement> elements;
    private WebDriverWait wait;
    private Select select;

    // ============================
    // 1. Locator & Element handling
    // ============================

    // 1. getXpath - Trả về đối tượng By theo xpath.
    public By getXpath(String xpath) {
        return By.xpath(xpath);
    }

    // 2. getDynamicXpath - Tạo By động với tham số truyền vào.
    public By getDynamicXpath(String xpath, String... params) {
        return By.xpath(String.format(xpath, (Object[]) params));
    }

    // 3. getElement - Lấy 1 phần tử WebElement theo locator.
    public WebElement getElement(WebDriver driver, String xpath) {
        element = driver.findElement(getXpath(xpath));
        return element;
    }

    // 4. getElements - Lấy danh sách phần tử WebElement theo locator.
    public List<WebElement> getElements(WebDriver driver, String xpath) {
        elements = driver.findElements(getXpath(xpath));
        return elements;
    }

    // 5. getElements (params) - Lấy danh sách phần tử theo xpath động.
    public List<WebElement> getElements(WebDriver driver, String xpath, String... params) {
        elements = driver.findElements(getDynamicXpath(xpath, params));
        return elements;
    }

    // 6. getDynamicLocator - Sinh locator động dạng chuỗi.
    public By getDynamicLocator(String xpath, String... params) {
        return getDynamicXpath(xpath, params);
    }

    // 7. getDynamicElement - Lấy WebElement từ locator động.
    public WebElement getDynamicElement(WebDriver driver, String xpath, String... params) {
        element = driver.findElement(getDynamicXpath(xpath, params));
        return element;
    }

    // ============================
    // 2. Click / Input actions
    // ============================

    // 8. clickToElement - Click vào phần tử cố định.
    public void clickToElement(WebDriver driver, String xpath) {
        waitForElementClickable(driver, xpath);
        getElement(driver, xpath).click();
    }

    // 9. clickToElement (params) - Click vào phần tử động.
    public void clickToElement(WebDriver driver, String xpath, String... params) {
        waitForElementClickable(driver, xpath, params);
        getDynamicElement(driver, xpath, params).click();
    }

    // 10. enterTextToElement - Nhập text vào phần tử cố định.
    public void enterTextToElement(WebDriver driver, String xpath, String value) {
        waitForElementIsVisible(driver, xpath);
        WebElement el = getElement(driver, xpath);
        el.clear();
        el.sendKeys(value);
    }

    // 11. enterTextToElement (params) - Nhập text vào phần tử động.
    public void enterTextToElement(WebDriver driver, String xpath, String value, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        WebElement el = getDynamicElement(driver, xpath, params);
        el.clear();
        el.sendKeys(value);
    }

    // 12. enterTextToElementUsingActions - Nhập text dùng Actions class.
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value) {
        Actions action = new Actions(driver);
        WebElement el = getElement(driver, xpath);
        waitForElementIsVisible(driver, xpath);
        el.clear();
        action.sendKeys(el, value).perform();
    }

    // 13. enterTextToElementUsingActions (params) - Nhập text động dùng Actions class.
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value, String... params) {
        Actions action = new Actions(driver);
        WebElement el = getDynamicElement(driver, xpath, params);
        waitForElementIsVisible(driver, xpath, params);
        el.clear();
        action.sendKeys(el, value).perform();
    }

    // 14. clickToElementByJS - Click bằng JavascriptExecutor.
    public void clickToElementByJS(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // 14.1. clickToElementByJS (params) - Click bằng JavascriptExecutor (động).
    public void clickToElementByJS(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // ============================
    // 3. Wait & Highlight
    // ============================

    // 15. waitForElementIsVisible - Chờ phần tử hiển thị.
    public void waitForElementIsVisible(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(getElement(driver, xpath)));
    }

    // 16. waitForElementIsVisible (params) - Chờ phần tử hiển thị (động).
    public void waitForElementIsVisible(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(getDynamicElement(driver, xpath, params)));
    }

    // 17. waitForElementClickable - Chờ phần tử có thể click.
    public void waitForElementClickable(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getElement(driver, xpath)));
    }

    // 18. waitForElementClickable (params) - Chờ phần tử có thể click (động).
    public void waitForElementClickable(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(getDynamicElement(driver, xpath, params)));
    }

    // 19. highLightElement - Highlight phần tử bằng JS.
    public void highLightElement(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", el);
    }

    // 20. highLightElement (params) - Highlight phần tử động.
    public void highLightElement(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", el);
    }

//    // 21. sleepInSecond - Tạm dừng theo thời gian giây.
//    public void sleepInSecond(long time) {
//        try {
//            Thread.sleep(time * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    // ============================
    // 4. Hover / Mouse actions
    // ============================

    // 22. hoverToElement - Hover chuột lên phần tử.
    public void hoverToElement(WebDriver driver, String xpath) {
        Actions action = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        action.moveToElement(getElement(driver, xpath)).perform();
    }

    // 23. hoverToElement (params) - Hover chuột lên phần tử động.
    public void hoverToElement(WebDriver driver, String xpath, String... params) {
        Actions action = new Actions(driver);
        waitForElementIsVisible(driver, xpath, params);
        action.moveToElement(getDynamicElement(driver, xpath, params)).perform();
    }

    // 24. rightClickOnElement - Click chuột phải vào phần tử.
    public void rightClickOnElement(WebDriver driver, String xpath) {
        new Actions(driver).contextClick(getElement(driver, xpath)).perform();
    }

    // 25. rightClickOnElement (params) - Click chuột phải vào phần tử động.
    public void rightClickOnElement(WebDriver driver, String xpath, String... params) {
        new Actions(driver).contextClick(getDynamicElement(driver, xpath, params)).perform();
    }

    // 26. doubleClickOnElement - Double click vào phần tử.
    public void doubleClickOnElement(WebDriver driver, String xpath) {
        new Actions(driver).doubleClick(getElement(driver, xpath)).perform();
    }

    // 27. doubleClickOnElement (params) - Double click vào phần tử động.
    public void doubleClickOnElement(WebDriver driver, String xpath, String... params) {
        new Actions(driver).doubleClick(getDynamicElement(driver, xpath, params)).perform();
    }

    // 28. dragAndDropElement - Kéo và thả phần tử.
    public void dragAndDropElement(WebDriver driver, String sourceXpath, String targetXpath) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();
    }

    // 29. pressKeyToElement - Nhấn phím vào phần tử.
    public void pressKeyToElement(WebDriver driver, String xpath, Keys key) {
        waitForElementIsVisible(driver, xpath);
        getElement(driver, xpath).sendKeys(key);
    }

    // 30. pressKeyToElement (params) - Nhấn phím vào phần tử động.
    public void pressKeyToElement(WebDriver driver, String xpath, Keys key, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        getDynamicElement(driver, xpath, params).sendKeys(key);
    }

    // ============================
    // 5. Get element info
    // ============================

    // 31. getTextElement - Lấy text của phần tử.
    public String getTextElement(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return getElement(driver, xpath).getText().trim();
    }

    // 32. getTextElement (params) - Lấy text phần tử động.
    public String getTextElement(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).getText().trim();
    }

    // 33. getElementAttributeValue - Lấy giá trị attribute của phần tử.
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute) {
        waitForElementIsVisible(driver, xpath);
        return getElement(driver, xpath).getAttribute(attribute);
    }

    // 34. getElementAttributeValue (params) - Lấy giá trị attribute phần tử động.
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).getAttribute(attribute);
    }

    // 35. getListElementSize - Lấy số lượng phần tử trong danh sách.
    public int getListElementSize(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return getElements(driver, xpath).size();
    }

    // 36. getListElementSize (params) - Lấy số lượng phần tử (động).
    public int getListElementSize(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getElements(driver, xpath, params).size();
    }

    // ============================
    // 6. Display / Visibility checking
    // ============================

    // 37. isDisplayElement - Kiểm tra phần tử có hiển thị không.
    public boolean isDisplayElement(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        try {
            return getElement(driver, xpath).isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    // 38. isDisplayElement (params) - Kiểm tra phần tử động có hiển thị không.
    public boolean isDisplayElement(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).isDisplayed();
    }

    // 39. isDisplayElements - Kiểm tra danh sách phần tử có hiển thị không.
    public boolean isDisplayElements(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return !getElements(driver, xpath).isEmpty();
    }

    // 40. isDisplayElements (params) - Kiểm tra danh sách phần tử động hiển thị không.
    public boolean isDisplayElements(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return !getElements(driver, xpath, params).isEmpty();
    }

    // ============================
    // 7. Page navigation
    // ============================

    // 41. getPageUrl - Lấy URL hiện tại của trang.
    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    // 42. getPageTitle - Lấy tiêu đề trang hiện tại.
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // 43. getPageSourceCode - Lấy source HTML của trang.
    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    // 44. getCurrentUrl - Lấy URL hiện tại.
    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    // 45. backToPage - Quay lại trang trước đó.
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    // 46. forwardToPage - Đi tới trang kế tiếp.
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    // 47. refreshPage - Refresh lại trang hiện tại.
    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // ============================
    // 8. Alert handling
    // ============================

    // 48. waitForAlertPresence - Chờ alert xuất hiện.
    public Alert waitForAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // 49. acceptAlert - Chấp nhận alert.
    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    // 50. cancelAlert - Hủy alert.
    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    // 51. getTextAlert - Lấy nội dung text trong alert.
    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    // 52. enterTextToAlert - Nhập text vào alert.
    public void enterTextToAlert(WebDriver driver, String value) {
        Alert alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
        alert.accept();
    }

    // ============================
    // 9. Window / Tab switching
    // ============================

    // 53. switchWindowByID - Chuyển tab theo ID.
    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    // 54. switchWindowByTitle - Chuyển tab theo tiêu đề.
    public void switchWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            if (Objects.equals(driver.getTitle(), title)) {
                break;
            }
        }
    }

    // 55. closeAllWindowsWithoutParent - Đóng tất cả tab trừ tab cha.
    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    // ============================
    // 10. Dropdown (Select tag)
    // ============================

    // 56. selectItemInDefaultDropdown - Chọn item trong dropdown mặc định.
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String itemText) {
        waitForElementIsVisible(driver, xpath);
        select = new Select(getElement(driver, xpath));
        select.selectByVisibleText(itemText);
    }

    // 57. selectItemInDefaultDropdown (params) - Chọn item dropdown động.
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String itemText, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        select = new Select(getDynamicElement(driver, xpath, params));
        select.selectByVisibleText(itemText);
    }

    // 58. getFirstSelectedTextItem - Lấy text item được chọn đầu tiên.
    public String getFirstSelectedTextItem(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        select = new Select(getElement(driver, xpath));
        return select.getFirstSelectedOption().getText();
    }

    // 59. getFirstSelectedTextItem (params) - Lấy text item được chọn đầu tiên (động).
    public String getFirstSelectedTextItem(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        select = new Select(getDynamicElement(driver, xpath, params));
        return select.getFirstSelectedOption().getText();
    }

    // 60. isDropdownMultiple - Kiểm tra dropdown có multiple không.
    public boolean isDropdownMultiple(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        select = new Select(getElement(driver, xpath));
        return select.isMultiple();
    }

    // 61. isDropdownMultiple (params) - Kiểm tra dropdown động có multiple không.
    public boolean isDropdownMultiple(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        select = new Select(getDynamicElement(driver, xpath, params));
        return select.isMultiple();
    }

    // ============================
    // 11. Checkbox / Radio
    // ============================

    // 62. checkToCheckboxOrRadio - Tích chọn checkbox/radio nếu chưa chọn.
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        element = getElement(driver, xpath);
        if (!element.isSelected()) {
            element.click();
        }
    }

    // 63. checkToCheckboxOrRadio (params) - Tích chọn checkbox/radio động.
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        element = getDynamicElement(driver, xpath, params);
        if (!element.isSelected()) {
            element.click();
        }
    }

    // 64. unCheckToCheckbox - Bỏ chọn checkbox nếu đang chọn.
    public void unCheckToCheckbox(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        element = getElement(driver, xpath);
        if (element.isSelected()) {
            element.click();
        }
    }

    // 65. unCheckToCheckbox (params) - Bỏ chọn checkbox động.
    public void unCheckToCheckbox(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        element = getDynamicElement(driver, xpath, params);
        if (element.isSelected()) {
            element.click();
        }
    }

    // 66. isElementSelected - Kiểm tra checkbox/radio có được chọn không.
    public boolean isElementSelected(WebDriver driver, String xpath) {
        waitForElementIsVisible(driver, xpath);
        return getElement(driver, xpath).isSelected();
    }

    // 67. isElementSelected (params) - Kiểm tra checkbox/radio động có được chọn không.
    public boolean isElementSelected(WebDriver driver, String xpath, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        return getDynamicElement(driver, xpath, params).isSelected();
    }

    // 68. scrollToElement - Cuộn đến phần tử (đầy đủ).
    public void scrollToElement(WebDriver driver, String xpath) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpath));
    }

    // 69. scrollIntoView - Cuộn đến phần tử (cố định).
    public void scrollIntoView(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", el);
    }

    // 70. scrollIntoView (params) - Cuộn đến phần tử (động).
    public void scrollIntoView(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", el);
    }

    // 71. switchToDefaultContent
}
