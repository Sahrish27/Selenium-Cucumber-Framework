package StepDefinition;

import Pages.loginPage;
import Pages.orderPage;
import SeleniumUtils.driverManager;
import SeleniumUtils.excelConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class orderStepDef {
    driverManager manager;
    WebDriver driver;
    orderPage order;
    loginStepDef logStep;
    static String fileToRead ="src/test/resources/data/testData.xlsx";


    public orderStepDef(){

        manager = new driverManager();
        driver  = manager.initializeDriver();
        order = new orderPage(driver);
        logStep=new loginStepDef();

    }
    @Given("User is logged in")
    public void userIsLoggedIn() throws IOException, InterruptedException {
        logStep.userIsAtDashboardScreen();
        logStep.userNavigatesToSigninScreen();
        logStep.userEntersEmailAndPassword();
        logStep.userIsRoutedToDashboardPageAsLoggedInUser();
    }

    @And("User searches for product {string}")
    public void userSearchesForProduct(String arg0) throws InterruptedException {
        order.searchProduct(arg0);

    }

    @And("User selects size {string} , color {string} and quantity {string} for searched product")
    public void userSelectsSizeColorAndQuantityForSearchedProduct(String arg0, String arg1, String arg2) {
        order.selectSize(arg0);
        order.selectColor(arg1);
        order.enterQuantity(arg2);
    }

    @And("User adds selected product to cart")
    public void userAddsSelectedProductToCart() throws InterruptedException {
        order.addToCart();
    }

    @When("User proceeds to checkout")
    public void userProceedsToCheckout() throws InterruptedException {
        order.proceedToCheckOut();
    }

    @Then("The order is placed successfully and Order id is generated")
    public void theOrderIsPlacedSuccessfullyAndOrderIdIsGenerated() throws IOException, InterruptedException {
        order.enterShippingDetails(excelConfig.readFromCell(fileToRead,"shipping",1,"firstname"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"lastname"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"street"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"city"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"regionId"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"country"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"postcode"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"telephone"));
        excelConfig.writeDataToExcel(fileToRead,"order",0,0, order.orderDone());
    }
}
