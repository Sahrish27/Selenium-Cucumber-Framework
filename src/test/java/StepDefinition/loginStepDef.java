package StepDefinition;

import Pages.loginPage;
import SeleniumUtils.driverManager;
import SeleniumUtils.excelConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class loginStepDef{
    loginPage login;
    driverManager manager;
    WebDriver driver;
    static String fileToRead ="src/test/resources/data/testData.xlsx";


    public loginStepDef(){

        manager = new driverManager();
        driver  = manager.initializeDriver();
        login = new loginPage(driver);

    }
    @Given("User is at dashboard screen")
    public void userIsAtDashboardScreen() {
        login.mainDashboardScreen();

    }

    @When("User navigates to signin screen")
    public void userNavigatesToSigninScreen() {
        login.clickSigninLink();
    }

    @And("User enters email and password")
    public void userEntersEmailAndPassword() throws InterruptedException, IOException {
        login.enterCredentialsAndContinue(excelConfig.readFromCell(fileToRead, "Login",1,"email"),
                excelConfig.readFromCell(fileToRead,"Login",1,"password"));
    }

    @Then("User is routed to Dashboard page as signin user")
    public void userIsRoutedToDashboardPageAsLoggedInUser() throws IOException {
        login.signinUserDashboard(excelConfig.readFromCell(fileToRead,"Login",1,"username"));
    }
}
