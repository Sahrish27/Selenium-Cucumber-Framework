package Pages;

import SeleniumUtils.utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class loginPage{
    private WebDriver driver;
    @FindBy(xpath = "//*[@class=\"panel header\"]//span[contains(.,\"Default welcome msg!\")]")
    WebElement dashboardHeaderLocator;

    @FindBy(xpath = "//*[@class=\"panel header\"]//a[contains(.,\"Sign In\")]")
    WebElement signinFormLinkLocator;

    @FindBy(xpath = "//h1[contains(.,\"Customer Login\")]")
    WebElement customerLoginLocator;

    @FindBy(name = "login[username]")
    WebElement emailLocator;

    @FindBy(name = "login[password]")
    WebElement passwordLocator;
    @FindBy(xpath = "//button[@class=\"action login primary\"]")
    WebElement loginButtonLocator;
    @FindBy(xpath = "//*[@class=\"logged-in\"]")
    WebElement loggedinLocator;




    public loginPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void mainDashboardScreen() {
        Assert.assertTrue(signinFormLinkLocator.isDisplayed(),"Invalid Navigation");
    }

    public void clickSigninLink() {
       signinFormLinkLocator.click();

    }

    public void enterCredentialsAndContinue(String arg0, String arg1) throws InterruptedException {
        utility.wait2Seconds();
        utility.waitForElementToBePresent(emailLocator).sendKeys(arg0);
        utility.waitForElementToBePresent(passwordLocator).sendKeys(arg1);
        loginButtonLocator.click();
        utility.wait2Seconds();
    }

    public void signinUserDashboard(String arg0) {
        String actualUsername;
        utility.waitForElementToBePresent(loggedinLocator);
        arg0 = "Welcome, "+arg0+"!";
        Assert.assertEquals(loggedinLocator.getText(),arg0,"Login failed");
    }


}
