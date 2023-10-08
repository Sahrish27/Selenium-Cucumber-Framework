package Pages;

import SeleniumUtils.utility;
import com.fasterxml.jackson.core.io.UTF32Reader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

public class orderPage {
    String orderNo;
    @FindBy(id="search")
    WebElement searchBoxLocator;
    @FindBy(xpath = "//ol[@class=\"products list items product-items\"]/li")
    WebElement searchResultLocator;
    @FindBy(xpath = "//*[@class=\"swatch-option color\"]")
    WebElement colorSelectionLocator;
    @FindBy(id = "qty")
    WebElement quantityLocator;
    @FindBy(xpath = "//button[@class=\"action primary tocart\"]")
    WebElement cartButtonLocator;
    @FindBy(xpath = "//a[@class=\"action showcart\"]")
    WebElement miniCartLocator;
    @FindBy(id = "top-cart-btn-checkout")
    WebElement checkoutLocator;
    @FindBy(xpath = "//*[@class=\"new-address-popup\"]/button")
    WebElement newAddressLocator;
    @FindBy(name = "firstname")
    WebElement firstNameLocator;
    @FindBy(name = "lastname")
    WebElement lastNameLocator;
    @FindBy(name = "street[0]")
    WebElement streetLocator;
    @FindBy(name = "city")
    WebElement cityLoctaor;
    @FindBy(name ="region")
    WebElement regionLocator;
    @FindBy(name="country_id")
    WebElement countryLocator;
    @FindBy(name="postcode")
    WebElement postCodeLocator;
    @FindBy(name="telephone")
    WebElement phoneLocator;
    @FindBy(xpath = "//input[@id=\"shipping-save-in-address-book\"]")
    WebElement saveToAddressBookLocator;
    @FindBy(xpath = "//button[@class=\"action primary action-save-address\"]")
    WebElement saveAddressLocator;
    @FindBy(xpath = "//span[contains(.,\"Next\")]")
    WebElement continueLocator;
    @FindBy(xpath = "//span[contains(.,\"Place Order\")]")
    WebElement placedOrderLocator;
    @FindBy(xpath = "//*[@class=\"counter-number\"]")
    WebElement counterLocator;
    @FindBy(name = "billing-address-same-as-shipping")
    WebElement billAddressShipAddressLocator;
    @FindBy(xpath = "//*[contains(text(),\"Thank you for your purchase!\")]")
    WebElement orderConfirmMessage;
    @FindBy(xpath = "//*[@class=\"order-number\"]")
    WebElement orderNoLocator;
    Select select;
    String counter;




    public orderPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void searchProduct(String arg0) throws InterruptedException {
        String expectedUrl = "https://magento.softwaretestingboard.com/"+utility.rplaceSpaceFromUrl(arg0)+".html";
        utility.waitForElementToBePresent(searchBoxLocator).sendKeys(arg0, Keys.RETURN);
        utility.pageLoad();
        utility.scrollDown();
        Assert.assertTrue(utility.partialLinkText(arg0).isDisplayed(),"No Result Found");
        utility.partialLinkText(arg0).click();
        utility.pageLoad();
        Assert.assertTrue(expectedUrl.equalsIgnoreCase(utility.getCurrentURL()), "Incorrect Navigation");
        utility.wait2Seconds();


    }

    public void selectSize(String arg0) {
        utility.waitForElementToBePresent(utility.xPathAriaLabel(arg0)).click();
    }


    public void selectColor(String arg1) {
        utility.waitForElementToBePresent(utility.xPathAriaLabel(arg1)).click();
       /* arialabel = colorSelectionLocator.getAttribute("aria-label");
        if (arialabel == "arg1"){
            colorSelectionLocator.click();
        }
    }*/
    }
    public void enterQuantity(String arg2) {
        counter=arg2;
        utility.waitForElementToBePresent(quantityLocator).clear();
        quantityLocator.sendKeys(arg2);
    }
    public void addToCart() throws InterruptedException {
        cartButtonLocator.click();
        utility.wait3Seconds();
    //    Assert.assertTrue(int(counterLocator.getText())>0,"not correct");


       
    }

    public void proceedToCheckOut() throws InterruptedException {
        miniCartLocator.isEnabled();
        miniCartLocator.click();
        utility.waitForElementToBeClickable(checkoutLocator).click();
        utility.pageLoad();

   //     Assert.assertEquals(utility.getCurrentURL(),"https://magento.softwaretestingboard.com/checkout/#shipping","Invalid navigation");
        
    }

    public void enterShippingDetails(String firstname, String lastname, String street, String city, String regionId, String country, String postcod , String telephone) throws InterruptedException {
     utility.pageLoad();
     utility.waitForElementToBePresent(newAddressLocator).click();
     utility.waitForElementToBePresent(firstNameLocator).clear();
     firstNameLocator.sendKeys(firstname);
     utility.waitForElementToBePresent(lastNameLocator).clear();
     lastNameLocator.sendKeys(lastname);
     utility.waitForElementToBePresent(streetLocator).sendKeys(street);
     utility.waitForElementToBePresent(cityLoctaor).sendKeys(city);
     utility.scrollIntoView(countryLocator);
     select = new Select(countryLocator);
     countryLocator.click();
     select.selectByVisibleText(country);
     utility.waitForElementToBePresent(regionLocator).sendKeys(regionId);
     utility.waitForElementToBePresent(postCodeLocator).sendKeys(postcod);
     utility.waitForElementToBePresent(phoneLocator).sendKeys(telephone);
     saveToAddressBookLocator.click();
     utility.waitForElementToBeClickable(saveAddressLocator).click();
   // utility.scrollIntoView(continueLocator);
        // utility.wait2Seconds();
    utility.waitForElementToBeClickable(continueLocator).click();



    }
    public void orderConfirm(){
        utility.pageLoad();
        utility.waitForElementToBePresent(billAddressShipAddressLocator).click();
        utility.waitForElementToBeClickable(placedOrderLocator).click();
    }
    public String orderDone() {
        utility.pageLoad();
        Assert.assertTrue(utility.waitForElementToBePresent(orderConfirmMessage).isDisplayed(),"Order failed");
        orderNo = orderNoLocator.getText();
       return orderNo;
    }
}
