package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndNavigateToURL() {
        ConfigReader.readProperties(Constants.CONFIG_READER_PATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //method is initializing all the objects available in the method
        initializePageObjects();
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static void selectFromDropdown(By dropdownLocator, String optionText) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select sel = new Select(dropdown);
        sel.selectByVisibleText(optionText);
    }

    public static void selectFromDropdown(String optionValue, By dropdownLocator) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select sel = new Select(dropdown);
        sel.selectByVisibleText(optionValue);
    }

    public static void selectFromDropdown(By dropdownLocator, int optionIndex) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select sel = new Select(dropdown);
        sel.selectByIndex(optionIndex);
    }

    public static byte[] takeScreenShot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot,
                    new File(Constants.SCREENSHOT_FILEPATH + " " + fileName
                            + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot wasn't taken due to error");
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}