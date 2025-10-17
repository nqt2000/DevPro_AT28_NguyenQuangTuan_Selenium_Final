package actions;

import org.openqa.selenium.WebDriver;

public class BaseTest {
    private WebDriver driver;
    //init driver

    public WebDriver getBrowser(){
        return driver;
    }
}
