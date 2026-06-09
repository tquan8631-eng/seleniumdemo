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

    @Test
    public void loginDung() throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void loginSai() throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("saiuser");
        driver.findElement(By.name("password")).sendKeys("saipass");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        String error = driver.findElement(By.className("oxd-alert-content-text")).getText();
        Assert.assertTrue(error.contains("ABCXYZ"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
