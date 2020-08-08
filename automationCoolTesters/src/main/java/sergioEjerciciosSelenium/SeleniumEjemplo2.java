package sergioEjerciciosSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.TestBase;


/*
 * 1. Entrar al sistema de HR (https://opensource-demo.orangehrmlive.com/) 
 * 2. Acceder correctamente al sistema (usuario:Admin password:admin123 ) 
 * 3. Validar que existen datos en la grafica del tab de Dashboard. 5. salir del sistema.
 *comentario TEst
 */
public class SeleniumEjemplo2 extends TestBase {
	String url = "https://opensource-demo.orangehrmlive.com";
	String user="Admin";
	String password = "admin123";
	
	WebDriverWait wait;
	
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
		
		driver = startWebDriver(url);
		PageFactory.initElements(driver, this);
		
		
	}
	
	@Test(priority=1)
	public void loginFail() {
		txt_usuario.sendKeys("Admin");
		txt_contraseña.sendKeys("admin12");
		btn_login.submit();
		

		//takeScreenShot("Test");
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(30))
//				.pollingEvery(Duration.ofSeconds(5))
//				.ignoring(NoSuchElementException.class);
//				
//		wait.until(ExpectedConditions.visibilityOf(tbl_menu));
	
//		wait.until(ExpectedConditions.visibilityOf(tbl_menu));
//		wait.until(ExpectedConditions.elementToBeClickable(tbl_menu));
		
		if(driver.getPageSource().contains("Dashboard")) {
			Reporter.log("El login fue exitoso", true);
		}else {
			Assert.assertTrue(false, "El login No fue exitoso");
		}
	}
	
	@Test(priority=2)
	public void login() {
//		txt_usuario.clear();
//		txt_contraseña.clear();
//		txt_usuario.sendKeys(user);
//		Reporter.log("Usuario Ingresado: " + user , true);
//		txt_contraseña.sendKeys(password);
//		Reporter.log("Password Ingresado: " + password +"\n" , true);
		ingresarTexto(txt_usuario,user);
		ingresarTexto(txt_contraseña,password);
		btn_login.click();
		Reporter.log("Click en Login\n", true);
		
		verificarElemento(tbl_menu);
	
		
		if(tbl_menu.isDisplayed()) {
			Reporter.log("El login fue exitoso", true);
		}
		
		List<WebElement> list_Menu = driver.findElements(By.xpath("//li[@class='main-menu-first-level-list-item']//b"));
		
//		for(WebElement object : list_Menu) {
//			
//		}
		String option="Buzz";
		System.out.println("La cantidad de elementos en la lista es : " + list_Menu.size());
		System.out.println("La opcion seleccionada es : "+ option);
		for(int i = 0; i<list_Menu.size(); i++) {
			
			System.out.println(list_Menu.get(i).getText());
			if(list_Menu.get(i).getText().equals(option)) {
				
				list_Menu.get(i).click();
				break;
				
			}
			if(i>list_Menu.size()) {
				Assert.assertTrue(false, "El elemento en la lista no existe");
			}
			
			
		}//endfor
		
		
//		if(driver.getPageSource().contains("Dashboard")) {
//			Reporter.log("El login fue exitoso", true);
//			Assert.assertTrue(true, "El login fue exitoso");
//		}
		
	}
	
	@AfterTest
	public void closeBrowser() {
		btn_welcome.click();
		link_logout.click();
//		driver.close();
	}
	

	
	
	
	

}
