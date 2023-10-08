package SeleniumUtils;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class driverManager {
    protected static WebDriver driver;


    public WebDriver initializeDriver() {
        if (driver == null) {
            String browser = "chrome";
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                System.setProperty("webdriver.chrome.driver", "D:/SeleniumCucumberFramwork/src/test/resources/driver/chromeDriver.exe");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
                driver.manage().window().maximize();

                driver.get("https://magento.softwaretestingboard.com/");
                utility.pageLoad();

            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                ;
            } else {
                throw new IllegalArgumentException("The Browser Type is Undefined");
            }
        }
        return driver;


    }



    @After
    public void tearDown(Scenario scenario) {
        // WebDriver driver;
        try {
            String screenshotName = scenario.getName().replaceAll("", "_");
            if (scenario.isFailed()) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "img/png", screenshotName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
