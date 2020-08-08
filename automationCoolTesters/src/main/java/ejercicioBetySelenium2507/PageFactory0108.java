package ejercicioBetySelenium2507;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class PageFactory0108 {

	WebDriver driver;
	String userI;
	String passwordI;
	WebDriverWait wait;

	@FindBy(id = "txtUsername")
	WebElement txt_usuario;

	@FindBy(name = "txtPassword")
	WebElement txt_contraseña;

	@FindBy(id = "btnLogin")
	WebElement btn_login;

	@FindBy(id = "welcome")
	WebElement btn_welcome;

	//@FindBy(xpath = "//a[text()='Logout']")
	@FindBy(css = "[href='/index.php/auth/logout']")
	WebElement link_logout;

	@FindBy(xpath = "//div[@id='mainMenu']")
	WebElement tbl_menu;
	
	@FindBy(css = "ul#mainMenuFirstLevelUnorderedList>li")
	List<WebElement> menuOptions;

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

		// Inicia elementos de PageFactory
		PageFactory.initElements(driver, this);

		driver.get(url);
		// tiempo de espera
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	
	/*
	 * @Descrption: Test case for successful Login
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters :None
	 */


	
	@Test(priority=1)
	public void login() {
		System.out.println("***Test case 1: Login successful***");
		userI = "Admin";
		passwordI = "admin123";

		txt_usuario.clear();
		txt_usuario.sendKeys(userI);

		txt_contraseña.clear();
		txt_contraseña.sendKeys(passwordI);
		btn_login.submit();

		Reporter.log("User is logged", true);

		logOut();

	}
	
	
	/*
	 * @Descrption: Test case for failure Login
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters :None
	 */

	@Test(priority=2)
	public void loginFail() {
		System.out.println("***Test case 2: Invalid credentials***");
		txt_usuario.clear();
		txt_usuario.sendKeys("Admin");
		txt_contraseña.sendKeys("admin12");
		btn_login.submit();
		logOut();
	}


	/*
	 * @Descrption: Test case to select an option from the MENU
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 05/08/2020
	 * 
	 * @Parameters :None
	 */

	@Test(priority=3)
	public void searchOption() {
		System.out.println("***Test case 3: Select an option from the menu***");
		userI = "Admin";
		passwordI = "admin123";

		txt_usuario.clear();
		txt_usuario.sendKeys(userI);

		txt_contraseña.clear();
		txt_contraseña.sendKeys(passwordI);
		btn_login.submit();

		Reporter.log("User is logged", true);

		//Looks for the option in the MENU 
		searchMenuoption("Recruitment");
	}

	/*
	 * @Descrption: Method used to search an option into the MENU
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 05/08/2020
	 * 
	 * @Parameters : option
	 */
	private void searchMenuoption(String option) {
		boolean found = false;
	
		System.out.println("Total of elements : " + menuOptions.size());
		
		//Search the option into the list  
		for (WebElement element: menuOptions)
		{
			if (element.getText().trim().equals(option.trim()))
			{
				found = true;
				System.out.println("The option " + option + " was found");
				element.click();
				break;
			}
		}
		//if the option does not exist then it sends a message
		Assert.assertTrue(found, "Option : " + option + " was NOT found");
	}	
	
	
	/*
	 * @Descrption: Method used to do the LOGOUT
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters :None
	 */

	
	public void logOut() {
		// Dos maneras de hacer que se encuentre un elemento
		// Por getPageSource o por menuDisplayed
//		if (driver.getPageSource().contains("Dashboard"))
//		{
//			Reporter.log("El login fue existoso", true );
//		}
//
//		else
//		{
//			Assert.assertTrue(false,"El login no fue existoso");
//		}

		try
		{
			if (tbl_menu.isDisplayed()) {
				btn_welcome.click();
				Reporter.log("Welcome menu was pressed", true);

				link_logout.click();
				Reporter.log("Logout option was pressed", true);
			}
		} 
		catch(Exception e)
		{
			Reporter.log("The logout was NOT successful", true);
			
		}
}
	
	
	/*
	 * @Descrption: Methods that quits and close all open windows
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters :None
	 */
	

	@AfterTest
	public void closeBrowser() {
		driver.quit();
		Reporter.log("End : Session was closed ", true);
	}

	/*
	 * @Descrption: Method that validates that an element exist in the page
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters : WebElement
	 */

	public void verificarElemento(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Reporter.log("The element exist");
		} catch (Exception e) {
			Reporter.log("The element does not exist");
			e.printStackTrace(); // muestra el nombre de la excepcion y la linea del código
		}
	}

	/*
	 * @Description: Method used to scroll into a page 
	 * 
	 * @Autor: Beatriz Arredondo
	 * 
	 * @Date : 01/08/2020
	 * 
	 * @Parameters : WebElement
	 */

	public void scrollElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Reporter.log("Element was scroll into View", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
