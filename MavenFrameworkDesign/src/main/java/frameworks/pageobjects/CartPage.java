package frameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	
	//Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productInCart;
	
	@FindBy(css=".totalRow button")
	private WebElement checkout;
	
	public Boolean verifyProductInCart(String productName) 
	{
		Boolean match = productInCart.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}

	public CheckoutPage goToCheckout() 
	{
		checkout.click();
		return new CheckoutPage(driver);
	}

}
