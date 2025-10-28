package actions.elements;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;
import interfaces.element.UploadDownloadInterface;

public class UploadDownloadPageAction extends BasePage {
    private WebDriver driver;

    public UploadDownloadPageAction(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String filePath) {
        getElement(driver, UploadDownloadInterface.UPLOAD_BUTTON).sendKeys(filePath);
    }

    public String getUploadedFilePath() {
        return getTextElement(driver, UploadDownloadInterface.UPLOADED_PATH);
    }
}
