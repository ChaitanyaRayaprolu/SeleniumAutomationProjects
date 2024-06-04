import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Introduction1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.get("file:///C:/Users/Chaitanya.Rayaprolu/Downloads/ndcasinocommon-release/base_common/feedtestsuite/Game_Launch_Simulator/StandAloneGameSimulator.html");
		
		WebElement environment = driver.findElement(By.id("environment"));
		Select dropDown = new Select(environment);
		dropDown.selectByVisibleText("QA51 (USAudit 1)");
		
		WebElement frontend = driver.findElement(By.id("frontend"));
		Select dropDown2 = new Select (frontend);
		dropDown2.selectByVisibleText("PLAYMGM(mj)");
		
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys("casinotestgf4");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.name("Log In")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gameVariantId")));
		
		driver.findElement(By.id("gameVariantId")).sendKeys("spearheadshamrocksurprise");
		
		driver.findElement(By.id("gameLaunch")).click();
		
		Set<String> launchWindows = driver.getWindowHandles();
		
		Iterator <String> it = launchWindows.iterator();
		
		String simulatorWindow = it.next();
		String gameWindow = it.next();
		
		driver.switchTo().window(gameWindow);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[class='vendorGame']")));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("iframe[class='vendorGame']")).sendKeys(Keys.SPACE);
		

		}


}
