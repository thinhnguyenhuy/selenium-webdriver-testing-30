package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// wait for element

    }

    @Test
    public void Register_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Thinh");
        driver.findElement(By.id("txtEmail")).sendKeys("Thinh@123@123");
        driver.findElement(By.id("txtCEmail")).sendKeys("Thinh@123@123");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Thinh");
        driver.findElement(By.id("txtEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Thinh12@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }


    @Test
    public void Register_04_Input_Password_less_than_6() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Thinh");
        driver.findElement(By.id("txtEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }

    @Test
    public void Register_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Thinh");
        driver.findElement(By.id("txtEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123457890");
        driver.findElement(By.id("txtPhone")).sendKeys("0987654321");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void Register_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Thinh");
        driver.findElement(By.id("txtEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Thinh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456789");
        driver.findElement(By.id("txtPhone")).sendKeys("09876543");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }

}