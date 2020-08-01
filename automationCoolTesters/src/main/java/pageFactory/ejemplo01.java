package pageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ejemplo01 {
	
	String url = "https://opensource-demo.orangehrmlive.com";
	String user="Admin";
	String password = "admin123";
	WebDriver driver;
	WebDriverWait wait ;
	//elementos
	@FindBy(id="txtUsername")
	WebElement txt_usuario;
	@FindBy(name="txtPassword")
	WebElement txt_contraseña;
	@FindBy(id="btnLogin")
	WebElement btn_login;
	@FindBy(id="welcome")
	WebElement btn_welcome;
	@FindBy(xpath="//a[text()='Logout']")
	WebElement link_logout;
	@FindBy(xpath="//div[@id='mainMenu']")
	WebElement tbl_menu;
	
	@BeforeTest
	public void startWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		PageFactory.initElements(driver, this);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		//wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(7))
				.ignoring(NoSuchElementException.class);
		
	}
	
	@Test(priority=1)
	public void loginFail() {
		txt_usuario.sendKeys("Admin");
		txt_contraseña.sendKeys("admin12");
		btn_login.submit();
		
		
		if(driver.getPageSource().contains("Dashboard")) {
			Reporter.log("el login  fue exitoso",true);
		}
		else {
			Assert.assertTrue(false);

		}
	}
	
	@Test(priority=2)
	public void login() {
		txt_usuario.clear();
		txt_contraseña.clear();
		txt_usuario.sendKeys(user);
		Reporter.log("Usuario Ingresado: " + user , true);
		txt_contraseña.sendKeys(password);
		Reporter.log("Password Ingresado: " + password +"\n" , true);
		btn_login.click();
		wait.until(ExpectedConditions.visibilityOf(tbl_menu));
		Reporter.log("Click en Login\n", true);
		
		if(tbl_menu.isDisplayed()) {
			Reporter.log("el login  fue exitoso",true);
			Assert.assertTrue(true,"el login fue exitoso");
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		btn_welcome.click();
		link_logout.click();
//		driver.close();
	}

}
