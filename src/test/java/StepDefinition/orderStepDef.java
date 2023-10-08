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

    @And("the user searches for a product by name")
    public void userSearchesForProduct() throws InterruptedException, IOException {
        order.searchProduct(excelConfig.readFromCell(fileToRead,"product",1,"productName"));

    }

    @And("selects the desired product variant")
    public void userSelectsSizeColorAndQuantityForSearchedProduct() throws IOException {
        order.selectSize(excelConfig.readFromCell(fileToRead,"product",1, "productSize"));
        order.selectColor(excelConfig.readFromCell(fileToRead,"product",1, "productColor"));
        order.enterQuantity(excelConfig.readFromCell(fileToRead,"product",1, "productQuantity"));
    }

    @And("adds the product to the cart")
    public void userAddsSelectedProductToCart() throws InterruptedException {
        order.addToCart();
    }

    @And("proceeds to checkout")
    public void userProceedsToCheckout() throws InterruptedException {
        order.proceedToCheckOut();
    }
    @And("provides shipping details")
    public void providesShippingDetails() throws IOException, InterruptedException {
        order.enterShippingDetails(excelConfig.readFromCell(fileToRead,"shipping",1,"firstname"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"lastname"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"street"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"city"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"regionId"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"country"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"postcode"),
                excelConfig.readFromCell(fileToRead,"shipping",1,"telephone"));
    }

    @And("confirms the order")
    public void confirmsTheOrder() {
        order.orderConfirm();
    }

    @Then("the order is placed successfully and order id is generated")
    public void theOrderIsPlacedSuccessfullyAndOrderIdIsGenerated() throws IOException {
        excelConfig.writeDataToExcel(fileToRead,"order",0,0, order.orderDone());
    }



}
