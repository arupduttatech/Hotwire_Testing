package test_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import pom_scripts.POMScript;
import utilities.ConfigProperties;
import utilities.ReportListener;

@Listeners(ReportListener.class)
public class HotWireScript {
	
	WebDriver driver;
	POMScript dataPOM;
	Logger log = Logger.getLogger(HotWireScript.class);
	ConfigProperties prop = new ConfigProperties();
	WebDriverWait wait;
	
	@BeforeMethod(alwaysRun = true)
	public void setupBrowser(){
		
		System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath"));
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));	
		dataPOM = new POMScript(driver);
		wait = new WebDriverWait(driver, 3000);
	}
	
	//test to verify the full success journey
	@Test
	public void testSuccessJourney() throws InterruptedException {
		
		dataPOM.makeADeal("SFO", "LAX");
	}
	
	@AfterMethod(alwaysRun = true)
	public void quitBrowser(){
		driver.close();
	}	
}