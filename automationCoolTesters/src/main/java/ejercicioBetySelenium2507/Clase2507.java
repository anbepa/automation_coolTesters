package ejercicioBetySelenium2507;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Clase2507 {

	WebDriver driver;
	String userI;
	String passwordI;

	@BeforeTest
	public void startWebDriver() {
		String url;
		String rutaChrome;

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("incognito");
//		options.addArguments("disable-infobars");

//		Elimina este mensaje : Chrome is being controlled by automated test software" 
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		url = "https://opensource-demo.orangehrmlive.com/";
		rutaChrome = "chromedriver\\chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", rutaChrome);

		driver = new ChromeDriver(options);
		driver.get(url);
		// tiempo de espera
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 2)
	public void login() {
		WebElement user = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement buttonOk = driver.findElement(By.id("btnLogin"));

		userI = "Admin";
		passwordI = "admin123";

		user.clear();
		user.sendKeys(userI);

		password.clear();
		password.sendKeys(passwordI);

		buttonOk.submit(); // o se puede usar click
		Reporter.log("Usuario ingreso", true);

		mainPage();

	}

	public void mainPage() {
		WebElement welcome = driver.findElement(By.id("welcome"));
		WebElement logout = driver.findElement(By.xpath("//a[text()= \"Logout\"]"));

		welcome.click();
		Reporter.log("Se presionó Welcome", true);
		logout.click();
		Reporter.log("Se presionó Logout", true);
	}

	@Test(priority = 1)
	public void loginFail() {

		WebElement user = driver.findElement(By.id("txtUsername"));
		WebElement password = driver.findElement(By.id("txtPassword"));
		WebElement buttonOk = driver.findElement(By.id("btnLogin"));

		user.clear();
		user.sendKeys(userI);

		password.clear();
		password.sendKeys("admin12");

		buttonOk.submit(); // o se puede usar click

		Reporter.log("Caso que falla login", true);

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
		Reporter.log("Cerró la sesión", true);
	}

	/*
	 * Primer ejercicio
	 * 
	 * 1. Entrar al sistema de HR (https://opensource-demo.orangehrmlive.com/) 2.
	 * Acceder correctamente al sistema (usuario:Admin password:admin123 ) 3.
	 * Validar que existen datos en la grafica del tab de Dashboard. 5. salir del
	 * sistema.
	 */
	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * String rutaChrome; rutaChrome = "chromedriver\\chromedriver.exe";
	 * 
	 * String url; url = "https://opensource-demo.orangehrmlive.com/"; rutaChrome =
	 * "chromedriver\\chromedriver.exe";
	 * 
	 * System.setProperty("webdriver.chrome.driver",rutaChrome);
	 * 
	 * WebDriver driver;
	 * 
	 * driver = new ChromeDriver(); driver.manage().window().maximize();
	 * driver.get(url);
	 * 
	 * //localizar un elemento WebElement user =
	 * driver.findElement(By.id("txtUsername")); WebElement password =
	 * driver.findElement(By.id("txtPassword")); WebElement buttonOk =
	 * driver.findElement(By.id("btnLogin"));
	 * 
	 * user.clear(); user.sendKeys("Admin");
	 * 
	 * password.clear(); password.sendKeys("admin123");
	 * 
	 * buttonOk.click(); // o se puede usar submit
	 * 
	 * 
	 * 
	 * }
	 */

}
