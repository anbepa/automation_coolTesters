package ejercicio01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Ejemplo01 {

	WebDriver driver;
	String url = "https://opensource-demo.orangehrmlive.com/";
	String user = "Admin";
	String pass= "admin123";
	
	

	@BeforeTest
	private void startWebDriver() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(option);
		driver.get(url);
		user= "Admin";
		pass= "admin123 ";

	}
	
	
	@Test(priority = 1)
	private void loginFail(String user,String pass) {
    
		 //localizar elementos para el Login 
		driver.findElement(By.xpath("//input[contains(@id,'txtUsername')]")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys("admin1231");
		driver.findElement(By.id("btnLogin")).click();

	}
	
	@Test(priority = 2)
	private void login(String user,String pass) {
    
		 //localizar elementos para el Login 
		driver.findElement(By.xpath("//input[contains(@id,'txtUsername')]")).clear();
		driver.findElement(By.cssSelector("input[id='txtPassword']")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'txtUsername')]")).sendKeys(user);
		driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys(pass);
		driver.findElement(By.id("btnLogin")).click();
		Reporter.log("El usuario ingresado es "+user,true);

	}
	
	@AfterTest
	private void closeBrowser() {
      //  driver.close();
	}
	


}
