package appcrautomation;

import java.time.Duration;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppCr {
	
	static String email = "chaitanya.rayaprolu@ivycomptech.com";
	static String password = "Chaitu#2222";

	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver-win64/chromedriver.exe");
		
		//ChromeOptions to disable the tab
		ChromeOptions exclude = new ChromeOptions(); 
		exclude.setExperimentalOption("useAutomationExtension", false);
		exclude.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		
		WebDriver driver = new ChromeDriver(exclude);
		driver.manage().window().maximize();
		
		driver.get("https://entain.service-now.com/esp?id=esp_pro_home");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginfmt")));
		
		driver.findElement(By.id("i0116")).sendKeys(email);
		driver.findElement(By.id("idSIButton9")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwd")));
		driver.findElement(By.id("i0118")).sendKeys(password);
		driver.findElement(By.id("idSIButton9")).click();
		
		//Open Change Request page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("favorites")));
		driver.findElement(By.id("favorites")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("favorite_card_c5218d190f218600de4085ece1050e44")));
		driver.findElement(By.id("favorite_card_c5218d190f218600de4085ece1050e44")).click();
		
		//Selecting type of the CR
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("select2-chosen-2")));
		driver.findElement(By.id("select2-chosen-2")).click();
		driver.findElement(By.id("s2id_autogen2_search")).sendKeys("Standard");		
		List<WebElement> options = driver.findElements(By.id("select2-results-2"));		
		for(WebElement option :options ) {			
			if(option.getText().equalsIgnoreCase("Standard")) {
				option.click();
				break;
			}
		}
		
		//Change Model
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s2id_sp_formfield_u_model")));
		driver.findElement(By.id("s2id_sp_formfield_u_model")).click();
		driver.findElement(By.id("s2id_autogen12_search")).sendKeys("CHMOD001735");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='select2-result-label']"))).click();
		driver.switchTo().alert().accept();
		
		//Coordinator
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s2id_sp_formfield_u_coordinator")));
		driver.findElement(By.id("s2id_sp_formfield_u_coordinator")).click();
		driver.findElement(By.id("s2id_autogen15_search")).sendKeys("Prathipati Sudha Maheswari");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li/div[@class='select2-result-label']")));
		driver.findElement(By.xpath("//ul/li/div[@class='select2-result-label']")).click();
		
		
		//Service
		driver.findElement(By.id("s2id_sp_formfield_u_business_service")).click();
		driver.findElement(By.id("s2id_autogen11_search")).sendKeys("Casino");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select2-result-label']"))).click();
		
		//Risk
		driver.findElement(By.id("s2id_sp_formfield_risk")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-search")));
		driver.findElement(By.id("s2id_autogen5_search")).sendKeys("Low");
		driver.findElement(By.id("s2id_autogen5_search")).sendKeys(Keys.ENTER);
		
		//Capacity Planning
		driver.findElement(By.id("s2id_sp_formfield_u_capacity_planning_required")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-search")));
		driver.findElement(By.id("s2id_autogen19_search")).click();
		driver.findElement(By.xpath("//ul[@class='select2-results'][2]")).click();		
		
	}

}
