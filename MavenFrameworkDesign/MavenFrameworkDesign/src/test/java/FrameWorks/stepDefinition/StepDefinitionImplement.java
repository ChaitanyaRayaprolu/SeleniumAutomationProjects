package FrameWorks.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FrameWorks.TestComponents.BaseTest;
import frameworks.pageobjects.CartPage;
import frameworks.pageobjects.CheckoutPage;
import frameworks.pageobjects.ConfirmationPage;
import frameworks.pageobjects.LandingPage;
import frameworks.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplement extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApp(username, password);
	}

	@When("^I add product (.+) into cart$")
	public void I_add_product_into_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_product_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
