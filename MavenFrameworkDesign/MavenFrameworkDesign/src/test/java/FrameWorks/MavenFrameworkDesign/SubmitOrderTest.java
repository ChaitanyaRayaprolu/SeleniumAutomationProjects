package FrameWorks.MavenFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorks.TestComponents.BaseTest;
import frameworks.pageobjects.CartPage;
import frameworks.pageobjects.CheckoutPage;
import frameworks.pageobjects.ConfirmationPage;
import frameworks.pageobjects.LandingPage;
import frameworks.pageobjects.OrderPage;
import frameworks.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrderTest(HashMap <String,String> input) throws IOException, InterruptedException
	{		
		
		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));		
		List<WebElement> products = productCatalogue.getProductList();		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();		
		Boolean match = cartPage.verifyProductInCart(input.get("productName"));			
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		
		
		}
	
	@Test(dependsOnMethods={"submitOrderTest"})
	
	public void ordeHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApp("rahulshettytest1@gmail.com", "Thisistest@1");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\FrameWorks\\data\\ProductsOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};		
		
	}	
	
	
	/*		HashMap<String,String> map1 = new HashMap<String, String>();
	map1.put("email","rahulshettytest1@gmail.com");
	map1.put("password","Thisistest@1");
	map1.put("productName", "ZARA COAT 3");
	
	HashMap<String,String> map2 = new HashMap<String, String>();
	map2.put("email","anshika@gmail.com");
	map2.put("password","Iamking@000");
	map2.put("productName", "ADIDAS ORIGINAL");
*/		
	
/*	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"rahulshettytest1@gmail.com","Thisistest@1","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}
*/
}
