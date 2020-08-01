package sergioEjerciciosSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*
 * 1. Entrar al sistema de HR (https://opensource-demo.orangehrmlive.com/) 
 * 2. Acceder correctamente al sistema (usuario:Admin password:admin123 ) 
 * 3. Validar que existen datos en la grafica del tab de Dashboard. 5. salir del sistema.
 */
public class SeleniumEjemplo1 {
	String url = "https://opensource-demo.orangehrmlive.com";
	String user="Admin";
	String password = "admin123";
	WebDriver driver;
	@FindBy(id="txtUsername")
	WebElement txt_usuario;
	@FindBy(name="txtPassword")
	WebElement txt_contraseña;
	@FindBy(name="btnLogin")
	WebElement btn_login;
	
	@BeforeTest
	public void startWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		PageFactory.initElements(driver, this);
		driver.get(url);
		
	}
	
	@Test(priority=1)
	public void loginFail() {
		txt_usuario.sendKeys("Admin");
		txt_contraseña.sendKeys("admin12");
		btn_login.submit();
	}
	
	@Test(priority=2)
	public void login() {
		txt_usuario.clear();
		txt_contraseña.clear();
		driver.findElement(By.id("txtUsername")).sendKeys(user);
		Reporter.log("Usuario Ingresado: " + user , true);
		txt_contraseña.sendKeys(password);
		Reporter.log("Password Ingresado: " + password +"\n" , true);
		btn_login.submit();
		Reporter.log("Click en Login\n", true);
	}
	
	@AfterTest
	public void closeBrowser() {
//		driver.close();
	}
	
	
	/*
	 * 1. Entrar al sistema de HR (https://opensource-demo.orangehrmlive.com/) 2.
	 * Acceder correctamente al sistema (usuario:Admin password:admin123 ) 3.
	 * Validar que existen datos en la grafica del tab de Dashboard. 5. salir del
	 * sistema.
	 */
//	public static void main(String[] args) {
//		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
//		//crear objecto chromedriver
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://www.google.com");
//		driver.navigate().to("https://opensource-demo.orangehrmlive.com");
//		//localizar el elemento de usuario para igresarlo
//		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
//		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
//		driver.findElement(By.name("btnLogin")).submit();
//		
//	}

}
