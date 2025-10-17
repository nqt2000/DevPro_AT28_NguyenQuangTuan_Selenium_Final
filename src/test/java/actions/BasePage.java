package actions;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private WebElement element;
    private List<WebElement> elements;
    private WebDriverWait wait;
    private Select select;

    // ============================
    // 1. Locator & Element handling
    // ============================

    // getXpath - Trả về đối tượng By theo xpath.
    public By getXpath(String xpath) {
        return By.xpath(xpath);
    }

    // getDynamicXpath - Tạo By động với tham số truyền vào.
    public By getDynamicXpath(String xpath, String... params) {
        return By.xpath(String.format(xpath, (Object[]) params));
    }

    // getElement - Lấy 1 phần tử WebElement theo locator.
    public WebElement getElement(WebDriver driver, String xpath) {
        element = driver.findElement(getXpath(xpath));
        return element;
    }

    // getElements - Lấy danh sách phần tử WebElement theo locator.
    public List<WebElement> getElements(WebDriver driver, String xpath) {
        elements = driver.findElements(getXpath(xpath));
        return elements;
    }

    // getElements (params) - Lấy danh sách phần tử theo xpath động.
    public List<WebElement> getElements(WebDriver driver, String xpath, String... params) {
        elements = driver.findElements(getDynamicXpath(xpath, params));
        return elements;
    }

    // getDynamicLocator - Sinh locator động dạng chuỗi.
    public By getDynamicLocator(String xpath, String... params) {
        return getDynamicXpath(xpath, params);
    }

    // getDynamicElement - Lấy WebElement từ locator động.
    public WebElement getDynamicElement(WebDriver driver, String xpath, String... params) {
        element = driver.findElement(getDynamicXpath(xpath, params));
        return element;
    }

    // ============================
    // 2. Click / Input actions
    // ============================

    // clickToElement - Click vào phần tử cố định.
    public void clickToElement(WebDriver driver, String xpath) {
        waitForElementClickable(driver, xpath);
        getElement(driver, xpath).click();
    }

    // clickToElement (params) - Click vào phần tử động.
    public void clickToElement(WebDriver driver, String xpath, String... params) {
        waitForElementClickable(driver, xpath, params);
        getDynamicElement(driver, xpath, params).click();
    }

    // enterTextToElement - Nhập text vào phần tử cố định.
    public void enterTextToElement(WebDriver driver, String xpath, String value) {
        waitForElementIsVisible(driver, xpath);
        WebElement el = getElement(driver, xpath);
        el.clear();
        el.sendKeys(value);
    }

    // enterTextToElement (params) - Nhập text vào phần tử động.
    public void enterTextToElement(WebDriver driver, String xpath, String value, String... params) {
        waitForElementIsVisible(driver, xpath, params);
        WebElement el = getDynamicElement(driver, xpath, params);
        el.clear();
        el.sendKeys(value);
    }

    // enterTextToElementUsingActions - Nhập text dùng Actions class.
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value) {
        Actions action = new Actions(driver);
        waitForElementIsVisible(driver, xpath);
        action.sendKeys(getElement(driver, xpath), value).perform();
    }

    // enterTextToElementUsingActions (params) - Nhập text động dùng Actions class.
    public void enterTextToElementUsingActions(WebDriver driver, String xpath, String value, String... params) {
        Actions action = new Actions(driver);
        waitForElementIsVisible(driver, xpath, params);
        action.sendKeys(getDynamicElement(driver, xpath, params), value).perform();
    }

    // clickToElementByJS - Click bằng JavascriptExecutor.
    public void clickToElementByJS(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // ============================
    // 3. Wait & Highlight
    // ============================

    // waitForElementIsVisible - Chờ phần tử hiển thị.
    public void waitForElementIsVisible(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getElement(driver, xpath)));
    }

    // waitForElementIsVisible (params) - Chờ phần tử hiển thị (động).
    public void waitForElementIsVisible(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getDynamicElement(driver, xpath, params)));
    }

    // waitForElementClickable - Chờ phần tử có thể click.
    public void waitForElementClickable(WebDriver driver, String xpath) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(getElement(driver, xpath)));
    }

    // waitForElementClickable (params) - Chờ phần tử có thể click (động).
    public void waitForElementClickable(WebDriver driver, String xpath, String... params) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(getDynamicElement(driver, xpath, params)));
    }

    // highLightElement - Highlight phần tử bằng JS.
    public void highLightElement(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", el);
    }

    // highLightElement (params) - Highlight phần tử động.
    public void highLightElement(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", el);
    }

    // sleepInSecond - Tạm dừng theo thời gian giây.
    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ============================
    // 4. Hover / Mouse actions
    // ============================

    // hoverToElement - Hover chuột lên phần tử.
    public void hoverToElement(WebDriver driver, String xpath) {
        new Actions(driver).moveToElement(getElement(driver, xpath)).perform();
    }

    // hoverToElement (params) - Hover chuột lên phần tử động.
    public void hoverToElement(WebDriver driver, String xpath, String... params) {
        new Actions(driver).moveToElement(getDynamicElement(driver, xpath, params)).perform();
    }

    // rightClickOnElement - Click chuột phải vào phần tử.
    public void rightClickOnElement(WebDriver driver, String xpath) {
        new Actions(driver).contextClick(getElement(driver, xpath)).perform();
    }

    // rightClickOnElement (params) - Click chuột phải vào phần tử động.
    public void rightClickOnElement(WebDriver driver, String xpath, String... params) {
        new Actions(driver).contextClick(getDynamicElement(driver, xpath, params)).perform();
    }

    // doubleClickOnElement - Double click vào phần tử.
    public void doubleClickOnElement(WebDriver driver, String xpath) {
        new Actions(driver).doubleClick(getElement(driver, xpath)).perform();
    }

    // doubleClickOnElement (params) - Double click vào phần tử động.
    public void doubleClickOnElement(WebDriver driver, String xpath, String... params) {
        new Actions(driver).doubleClick(getDynamicElement(driver, xpath, params)).perform();
    }

    // dragAndDropElement - Kéo và thả phần tử.
    public void dragAndDropElement(WebDriver driver, String sourceXpath, String targetXpath) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceXpath), getElement(driver, targetXpath)).perform();
    }

    // pressKeyToElement - Nhấn phím vào phần tử.
    public void pressKeyToElement(WebDriver driver, String xpath, Keys key) {
        getElement(driver, xpath).sendKeys(key);
    }

    // pressKeyToElement (params) - Nhấn phím vào phần tử động.
    public void pressKeyToElement(WebDriver driver, String xpath, Keys key, String... params) {
        getDynamicElement(driver, xpath, params).sendKeys(key);
    }

    // ============================
    // 5. Get element info
    // ============================

    // getTextElement - Lấy text của phần tử.
    public String getTextElement(WebDriver driver, String xpath) {
        return getElement(driver, xpath).getText();
    }

    // getTextElement (params) - Lấy text phần tử động.
    public String getTextElement(WebDriver driver, String xpath, String... params) {
        return getDynamicElement(driver, xpath, params).getText();
    }

    // getElementAttributeValue - Lấy giá trị attribute của phần tử.
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute) {
        return getElement(driver, xpath).getAttribute(attribute);
    }

    // getElementAttributeValue (params) - Lấy giá trị attribute phần tử động.
    public String getElementAttributeValue(WebDriver driver, String xpath, String attribute, String... params) {
        return getDynamicElement(driver, xpath, params).getAttribute(attribute);
    }

    // getListElementSize - Lấy số lượng phần tử trong danh sách.
    public int getListElementSize(WebDriver driver, String xpath) {
        return getElements(driver, xpath).size();
    }

    // getListElementSize (params) - Lấy số lượng phần tử (động).
    public int getListElementSize(WebDriver driver, String xpath, String... params) {
        return getElements(driver, xpath, params).size();
    }

    // ============================
    // 6. Display / Visibility checking
    // ============================

    // isDisplayElement - Kiểm tra phần tử có hiển thị không.
    public boolean isDisplayElement(WebDriver driver, String xpath) {
        return getElement(driver, xpath).isDisplayed();
    }

    // isDisplayElement (params) - Kiểm tra phần tử động có hiển thị không.
    public boolean isDisplayElement(WebDriver driver, String xpath, String... params) {
        return getDynamicElement(driver, xpath, params).isDisplayed();
    }

    // isDisplayElements - Kiểm tra danh sách phần tử có hiển thị không.
    public boolean isDisplayElements(WebDriver driver, String xpath) {
        return !getElements(driver, xpath).isEmpty();
    }

    // isDisplayElements (params) - Kiểm tra danh sách phần tử động hiển thị không.
    public boolean isDisplayElements(WebDriver driver, String xpath, String... params) {
        return !getElements(driver, xpath, params).isEmpty();
    }

    // ============================
    // 7. Page navigation
    // ============================

    // getPageUrl - Lấy URL hiện tại của trang.
    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    // getPageTitle - Lấy tiêu đề trang hiện tại.
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // getPageSourceCode - Lấy source HTML của trang.
    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    // getCurrentUrl - Lấy URL hiện tại.
    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    // backToPage - Quay lại trang trước đó.
    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    // forwardToPage - Đi tới trang kế tiếp.
    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    // refreshPage - Refresh lại trang hiện tại.
    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // ============================
    // 8. Alert handling
    // ============================

    // waitForAlertPresence - Chờ alert xuất hiện.
    public Alert waitForAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // acceptAlert - Chấp nhận alert.
    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    // cancelAlert - Hủy alert.
    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    // getTextAlert - Lấy nội dung text trong alert.
    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    // enterTextToAlert - Nhập text vào alert.
    public void enterTextToAlert(WebDriver driver, String value) {
        Alert alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
        alert.accept();
    }

    // ============================
    // 9. Window / Tab switching
    // ============================

    // switchWindowByID - Chuyển tab theo ID.
    public void switchWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    // switchWindowByTitle - Chuyển tab theo tiêu đề.
    public void switchWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String id : allWindows) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    // closeAllWindowsWithoutParent - Đóng tất cả tab trừ tab cha.
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

    // selectItemInDefaultDropdown - Chọn item trong dropdown mặc định.
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String itemText) {
        select = new Select(getElement(driver, xpath));
        select.selectByVisibleText(itemText);
    }

    // selectItemInDefaultDropdown (params) - Chọn item dropdown động.
    public void selectItemInDefaultDropdown(WebDriver driver, String xpath, String itemText, String... params) {
        select = new Select(getDynamicElement(driver, xpath, params));
        select.selectByVisibleText(itemText);
    }

    // getFirstSelectedTextItem - Lấy text item được chọn đầu tiên.
    public String getFirstSelectedTextItem(WebDriver driver, String xpath) {
        select = new Select(getElement(driver, xpath));
        return select.getFirstSelectedOption().getText();
    }

    // getFirstSelectedTextItem (params) - Lấy text item được chọn đầu tiên (động).
    public String getFirstSelectedTextItem(WebDriver driver, String xpath, String... params) {
        select = new Select(getDynamicElement(driver, xpath, params));
        return select.getFirstSelectedOption().getText();
    }

    // isDropdownMultiple - Kiểm tra dropdown có multiple không.
    public boolean isDropdownMultiple(WebDriver driver, String xpath) {
        select = new Select(getElement(driver, xpath));
        return select.isMultiple();
    }

    // isDropdownMultiple (params) - Kiểm tra dropdown động có multiple không.
    public boolean isDropdownMultiple(WebDriver driver, String xpath, String... params) {
        select = new Select(getDynamicElement(driver, xpath, params));
        return select.isMultiple();
    }

    // ============================
    // 11. Checkbox / Radio
    // ============================

    // checkToCheckboxOrRadio - Tích chọn checkbox/radio nếu chưa chọn.
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        if (!el.isSelected()) {
            el.click();
        }
    }

    // checkToCheckboxOrRadio (params) - Tích chọn checkbox/radio động.
    public void checkToCheckboxOrRadio(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        if (!el.isSelected()) {
            el.click();
        }
    }

    // unCheckToCheckbox - Bỏ chọn checkbox nếu đang chọn.
    public void unCheckToCheckbox(WebDriver driver, String xpath) {
        WebElement el = getElement(driver, xpath);
        if (el.isSelected()) {
            el.click();
        }
    }

    // unCheckToCheckbox (params) - Bỏ chọn checkbox động.
    public void unCheckToCheckbox(WebDriver driver, String xpath, String... params) {
        WebElement el = getDynamicElement(driver, xpath, params);
        if (el.isSelected()) {
            el.click();
        }
    }

    // ============================
    // 12. Wait / Frame / Timeouts
    // ============================

    // setImplicitTime - Thiết lập thời gian implicit wait.
    public void setImplicitTime(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    // switchToFrame - Chuyển vào iframe theo locator.
    public void switchToFrame(WebDriver driver, String xpath) {
        driver.switchTo().frame(getElement(driver, xpath));
    }

    // switchToDefaultContent - Thoát khỏi iframe về trang chính.
    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}
