package commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import listeners.ScreenshotListener;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class CommonsMethods extends TestListenerAdapter {
	static WebDriver driver;
	String filePath;
	private static String chromeDriverPath = "chromedriver\\chromedriver.exe";
	private static String geckoDriver = "geckodriver\\geckodriver.exe";
	private static String msedgeDriver = "msedgedriver\\msedgedriver.exe";
	
	public CommonsMethods(WebDriver driver) {
		CommonsMethods.driver=driver;
	}
	
	public CommonsMethods() {
		
	}
	/* @Descriptio WebDriver initialization   
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter URL, browserName
	 * */
	public static WebDriver StartDriver(String url, String browser) throws Exception {

		try {
			switch (browser) {

			case "chrome":
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--ignore-certificate-errors");
				option.addArguments("--allow-running-insecure-content");
				option.addArguments("--disable-extensions");
				option.addArguments("--start-maximized");
				option.addArguments("--incognito");
				option.addArguments("--whitelisted-ips");
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver(option);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(url);
				break;

			case "firefox":

				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				options.addArguments("--incognito");
				options.addArguments("--disable-web-security");
				options.addArguments("--whitelisted-ips");
				options.addArguments("--allow-running-insecure-content");
				System.setProperty("webdriver.gecko.driver", geckoDriver);
				driver = new FirefoxDriver(options);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(url);
				break;
			case "edge":
				EdgeOptions optionEdge = new EdgeOptions();
				optionEdge.addArguments("--ignore-certificate-errors");
				optionEdge.addArguments("--allow-running-insecure-content");
				optionEdge.addArguments("--disable-extensions");
				optionEdge.addArguments("--start-maximized");
				optionEdge.addArguments("--incognito");
				optionEdge.addArguments("--disable-web-security");
				optionEdge.addArguments("--whitelisted-ip");
				optionEdge.addArguments("--allow-running-insecure-content");
				System.setProperty("webdriver.edge.driver", msedgeDriver);
				driver = new EdgeDriver(optionEdge);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(url);
				break;

			default:
				System.out.println("chrome driver was initialize");
			}

		} catch (Exception e) {
			Assert.fail("WebDriver can't initialize\n");
			System.out.println("WebDriver can't initialize");
		}
		return driver;
	}
	
	/* @Description return WebDriver initialized  
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement, text
	 * */
	public static WebDriver getDriver() {
		return driver;
	}

	/* @Description Set Text 
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement, text
	 * */
	public void setText(WebElement webElement, String text) throws Exception {
		try {
			reviewElement(webElement);
			scrollToWebElement(webElement);
			webElement.sendKeys(text);
			Reporter.log("Text was entered: " + text, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* @Description scroll to web element
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement
	 * */
	public void scrollToWebElement(WebElement webElement) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			Reporter.log("Element was scroll into View",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* @Description verify element is present 
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement
	 * */
	public void reviewElement(WebElement webElement) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(webElement));	
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			Reporter.log("Element is present in the page", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* @Description click in webElement
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement
	 * */
	public void clickObject(WebElement webElement) throws Exception {
		try {
			reviewElement(webElement);
			scrollToWebElement(webElement);
			webElement.click();
			Reporter.log("Object was clicked.", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* @Description click in webElement
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter WebElement
	 * */
	public void selectElementByValue(List<WebElement> webElement, String language) throws Exception {
		try {

			reviewElement(webElement.get(0));
			for (int i = 0; i <= webElement.size(); i++) {

				if (i >= webElement.size()) {
					Assert.fail("Language: " + language + " is not the list\n");
					break;
				}

				if (webElement.get(i).getText().equals(language)) {
					scrollToWebElement(webElement.get(i));
					Reporter.log("Language selected: " + webElement.get(i).getText());
					clickObject(webElement.get(i));
					Assert.assertTrue(true);
					break;
				}

			}
			Reporter.log("Language selected: " + language, true);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	/* @Description take screenshot 
	 * @author Sergio.Ramones
	 * @Date 06/08/2020
	 * @parameter description
	 * */
	  public void takeScreenShot(String description) throws InterruptedException {
	        //get the driver
		 	  driver=getDriver();
	    	 Calendar calendar = Calendar.getInstance();
	         SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyyhhmmss");
	         Thread.sleep(1000);
	         File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	         //The below method will save the screen shot in d drive with test method name 
	            try {
	                FileUtils.copyFile(scrFile, new File(ScreenshotListener.filePath+ScreenshotListener.methodName+"_"+description+"_"+formater.format(calendar.getTime())+".png"));
	                Reporter.log("***Sceenshot taken save it --> "+ScreenshotListener.filePath+" ***",true);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	  
	  	/* @Description take screenshot 
		 * @author Sergio.Ramones
		 * @Date 06/08/2020
		 * @parameter jsonPath
		 * */ 
	  public JSONObject readJSON(String jsonPath) throws FileNotFoundException, ParseException {
		  JSONObject jsonObject = null;
		  try {	
		  JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
			Object obj = parser.parse(new FileReader(jsonPath));
		  jsonObject = (JSONObject) obj;
		  }catch (Exception e) {
			  Reporter.log("JSON file can't be readed: " + jsonPath, true);
		  }
		  return jsonObject;
	  }
}
