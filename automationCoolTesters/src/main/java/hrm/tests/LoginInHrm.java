package hrm.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.CommonsMethods;
import login.hrm.Login;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

public class LoginInHrm extends CommonsMethods{


	String url, txt_user, txt_password;
	WebDriver driver;
	
	@BeforeTest
	public void readJSON() throws FileNotFoundException, IOException, ParseException {

		JSONObject getText = readJSON("json\\hrm\\credentials.json");
		url =  getText.get("url").toString();
		txt_user =  getText.get("user").toString();
		txt_password =  getText.get("password").toString();
	}

	@Parameters({ "Browser" })
	@Test
	public void loginHRM(String browser) throws Exception {

		driver = CommonsMethods.StartDriver(url, browser);
		
		Login login = PageFactory.initElements(driver, Login.class);
		login.loginHRM(txt_user, txt_password);
	
	}


	
	@AfterMethod(alwaysRun = true)
    public void closeBrowser(){
		if(driver!=null) {
		driver.quit();
		}
        
    }








}//end class



