package FrameWorks.MavenFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameWorks.TestComponents.BaseTest;
import FrameWorks.TestComponents.Retry;
import frameworks.pageobjects.CartPage;
import frameworks.pageobjects.CheckoutPage;
import frameworks.pageobjects.ConfirmationPage;
import frameworks.pageobjects.LandingPage;
import frameworks.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{		
		
		landingPage.loginApp("rahulshettytest1@gmail.com", "Thistest@1");		
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
		
		}

}
