package others;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/text-box");

        // Cach 1
        WebElement fullname = driver.findElement(By.xpath("//*[@placeholder=\"Full Name\"]"));
        fullname.sendKeys("Nguyen Van An");
        Thread.sleep(2000);

        // Cach 2
        WebElement fullname2 = driver.findElement(By.id("userName"));
        fullname2.clear();
        fullname2.sendKeys("Tran Quang Binh");
        Thread.sleep(2000);

        // Cach 3
        WebElement fullname4 = driver.findElement(By.cssSelector("#userName"));
        fullname4.clear();
        fullname4.sendKeys("Nguyen Tran Nhat Minh");
        Thread.sleep(2000);

        // Cach 4
        WebElement fullname3 = driver.findElement(By.xpath("//input[@class=' mr-sm-2 form-control']"));
        fullname3.clear();
        fullname3.sendKeys("Dao Vinh Mai");
        Thread.sleep(2000);

        // Email cach 1
        WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("mailtest1@gmail.com");
        Thread.sleep(2000);

        // Email cach 2
        WebElement email2 = driver.findElement(By.cssSelector("input[placeholder='name@example.com']"));
        email2.clear();
        email2.sendKeys("mailtest02@gmail.com");
        Thread.sleep(2000);


        // Submit cach 1
        WebElement buttonsubmit = driver.findElement(By.cssSelector("#submit"));
        buttonsubmit.click();
        Thread.sleep(2000);


        WebElement currentaddress = driver.findElement(By.xpath("//*[@placeholder=\"Current Address\"]"));
        currentaddress.sendKeys("Khuat Duy Tien, Trung Hoa, Cau Giay, Ha Noi");
        Thread.sleep(2000);

        WebElement permanenetaddress = driver.findElement(By.xpath("//*[@id=\"permanentAddress\"]"));
        permanenetaddress.sendKeys("Nguyen Chi Thanh, Dong Da, Ha Noi");
        Thread.sleep(2000);



        // Submit cach 2
        WebElement buttonsubmit2 = driver.findElement(By.xpath("//textarea[@id='currentAddress']/parent::div/following::button[@id='submit']"));
        buttonsubmit2.click();
        Thread.sleep(2000);

        driver.close();
    }
}