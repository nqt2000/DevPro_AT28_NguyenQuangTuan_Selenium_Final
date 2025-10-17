package others;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumReader {
    public static void main(String[] args) throws InterruptedException {
        // Khởi tạo driver
        WebDriver driver = new ChromeDriver();
        // Download driver
        WebDriverManager.chromedriver().setup();
        driver.get("https://selectorshub.com/xpath-practice-page/");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Bước 1: Enter Email
        WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter email']"));
        email.sendKeys("devpro@gmail.com");
        Thread.sleep(5000);

        // Bước 2: Enter password
        WebElement password = driver.findElement(By.xpath("//input[@placeholder=\"Enter Password\"]"));
        password.sendKeys("devpro");
        Thread.sleep(3000);

        // Bước 3: Enter company
        WebElement company = driver.findElement(By.xpath("//input[@placeholder='\"Enter your company\"']"));
        company.sendKeys("Ominext");
        Thread.sleep(3000);

        // Bước 4: Enter number
        WebElement number = driver.findElement(By.xpath("//input[@placeholder='\"Enter your mobile number\"']"));
        number.sendKeys("0333444555");
        Thread.sleep(3000);

        // Bước 6: click submit
        WebElement buttonSubmit = driver.findElement(By.xpath("//button[@value='Submit']"));
        buttonSubmit.click();
        Thread.sleep(5000);
        driver.close();
    }
}
