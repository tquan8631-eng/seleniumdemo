package com.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Test(priority = 1)
    public void loginDung() throws Exception {

        Thread.sleep(3000);

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.tagName("button")).click();

        Thread.sleep(3000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"));
    }

    @Test(priority = 2)
    public void loginSai() throws Exception {

        Thread.sleep(3000);

        driver.findElement(By.name("username")).sendKeys("saiuser");
        driver.findElement(By.name("password")).sendKeys("saipass");

        driver.findElement(By.tagName("button")).click();

        Thread.sleep(5000);

        String pageSource = driver.getPageSource();

        // Cố tình FAIL
        Assert.assertTrue(pageSource.contains("Invalid credentials"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}