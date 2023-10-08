package SeleniumUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class utility{
    private static final int timeoutSeconds = 40;
    static WebDriverWait wait;
    static driverManager manager = new driverManager();
    static WebDriver driver  = manager.initializeDriver();

    public static void wait1Seconds() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void wait2Seconds() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void wait3Seconds() throws InterruptedException {
        Thread.sleep(3000);
    }

    public static void wait5Seconds() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static void wait10Seconds() throws InterruptedException {
        Thread.sleep(10000);
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver, timeoutSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static WebElement waitForElementToBePresent(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new RuntimeException("Element not found or not visible within " + timeoutSeconds + " seconds.");
        }
    }


    public static void checkElementIsPresent(WebElement Locator) {
        if (Locator.isDisplayed()) {
            System.out.println(Locator.getAttribute("content-desc") + " is on screen");
        } else System.out.println("Element not exist on screen");
    }

    public static void clickBack() {

        driver.navigate().back();
    }

    // Method to capture a screenshot
    public static String captureScreenshot(String methodName) {
        String screenshotDirectory = "./Test-Reports/screenshot"; // Update with the actual directory path
        String screenshotPath = null;

        try {
            // Take a screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Define the path for the screenshot
            screenshotPath = screenshotDirectory + File.separator + methodName + ".png";

            // Copy the screenshot to the specified path
            FileUtils.copyFile(source, new File(screenshotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }


    public static void pageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            System.out.println("Page has loaded");
        }
    }
    public static void scrollDown() {
        // Create a JavaScriptExecutor object
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down the page by a fixed amount (e.g., 500 pixels)
        js.executeScript("window.scrollBy(0, 500);");
    }

    public static void scrollIntoView(WebElement element) {
        // Create a JavaScriptExecutor object
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll the page to bring the specified element into view
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static WebElement partialLinkText(String s){
        return driver.findElement(By.partialLinkText(s));
    }
    public static String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public static String rplaceSpaceFromUrl(String arg0) {
        return (arg0.replace(" ","-"));
    }

    public static WebElement xPathAriaLabel(String s){
        return driver.findElement(By.xpath("//*[@aria-label=\""+s+"\"]"));
    }

}