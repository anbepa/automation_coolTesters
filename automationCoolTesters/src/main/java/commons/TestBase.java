package commons;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class TestBase {
	protected WebDriver driver;
	WebDriverWait wait;
	
	public WebDriver startWebDriver(String url) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--incognito");
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return driver;
	}
	
	/*
	 * @Descripcion Metodo para infresar el texto
	 * @Autor Sergio
	 * @Date 01/08/2020
	 * @Paramentros WebElement, Texto
	 * */
	public void ingresarTexto(WebElement objecto, String texto) {
		try {	verificarElemento(objecto);
				scrollToElement(objecto);
				objecto.clear();
				objecto.sendKeys(texto);
				Reporter.log("El texto se ingreso correctamente :" + texto , true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @Descripcion Metodo que revisa que un elemento exista
	 * @Autor Sergio
	 * @Date 01/08/2020
	 * @Paramentros WebElement
	 * */
	public void verificarElemento(WebElement objecto) {
		try {
			wait.until(ExpectedConditions.visibilityOf(objecto));
			wait.until(ExpectedConditions.elementToBeClickable(objecto));
			Reporter.log("El Elemento existe:", true);
		} catch (Exception e) {
			Reporter.log("El Elemento no existe:", true);
			e.printStackTrace();
		}
	}//
	
	/*
	 * @Descripcion Metodo para hacer scroll a un webelement
	 * @Autor Sergio
	 * @Date 01/08/2020
	 * @Paramentros WebElement
	 * */
	public void scrollToElement(WebElement objecto) {
		try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", objecto);
				Reporter.log("Scroll al elemento :", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
