package pom_scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import utilities.CommonUtils;
import utilities.ConfigProperties;


public class POMScript {
	
	WebDriver driver;
	WebDriverWait wait;
	
	Logger log = Logger.getLogger(POMScript.class);
	ConfigProperties prop = new ConfigProperties();
	
	@FindBy(xpath="//li/a[contains(text(), 'Vacations')]")
	public WebElement vacationsTab;
	
	@FindBy(xpath="//div/button[contains(@ng-click,'flight')]")
	public WebElement flightTab;
	
	@FindBy(xpath="//div/button[contains(@ng-click,'hotel')]")
	public WebElement hotelTab;
	
	@FindBy(xpath="//div/button[contains(@ng-click,'car')]")
	public WebElement carTab;
	
	@FindBy(xpath="//div/input[contains(@id,'farefinder-package-origin-location')]")
	public WebElement flyFromTextBox;
	
	@FindBy(xpath="//div/input[contains(@id,'farefinder-package-destination-location')]")
	public WebElement flyToTextBox;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[contains(@title,'SFO')]")
	public WebElement SFO;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[contains(@title,'LAX')]")
	public WebElement LAX;
	
	@FindBy(xpath="//div/input[contains(@id,'package-startdate-input')]")
	public WebElement pickupStartDateTextBox;
	
	@FindBy(xpath="//div/input[contains(@id,'package-enddate-input')]")
	public WebElement pickupEndDateTextBox;
	
	@FindBy(xpath="//div/select[contains(@id,'pickuptime-input')]")
	public WebElement pickupTimeDropDown;
	
	@FindBy(xpath="//div/select[contains(@id,'pickuptime-input')]/option[@label='Evening']")
	public WebElement pickupTimeEvening;
	
	@FindBy(xpath="//div/button[contains(@id,'package-search-button')]")
	public WebElement dealButton;
	
	@FindBy(xpath = "//div[@class='container']/section/article[contains(@class,'hotel listing')]")
	public WebElement resultsContainer;
	
	public POMScript(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 3000);

        //This initElements method will create all WebElements

    }
	
	public void makeADeal(String origin, String destination) throws InterruptedException {
		
		vacationsTab.click();
		hotelTab.click();
		flightTab.click();
		carTab.click();
		
		flyFromTextBox.sendKeys(origin);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='dropdown-menu']/li/a")));
		driver.findElement(By.xpath("//ul[@class='dropdown-menu']/li/a[contains(@title,'" + origin + "')]")).click();
		flyToTextBox.sendKeys(destination);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='dropdown-menu']/li/a")));
		driver.findElement(By.xpath("//ul[@class='dropdown-menu']/li/a[contains(@title,'" + destination + "')]")).click();
		System.out.println(CommonUtils.getNextDate());
		pickupStartDateTextBox.clear();
		pickupStartDateTextBox.sendKeys(CommonUtils.getNextDate());
		
		pickupEndDateTextBox.clear();
		pickupEndDateTextBox.sendKeys(CommonUtils.getDateAfter3Weeks());
		
		pickupTimeDropDown.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(pickupTimeEvening));
		pickupTimeEvening.click();
		
		dealButton.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(resultsContainer));
		Assert.assertTrue(resultsContainer.isDisplayed());
	}
	
}
