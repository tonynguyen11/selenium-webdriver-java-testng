package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Default_Dropdown {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, date, month, year;
	String emailAddress, companyName, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Lucas";
		lastName = "Toni";
		date = "25";
		month = "July";
		year = "1980";
		emailAddress = "Lucas" + generateRandomNumber() + "@hotmail.net";
		companyName = "Toyota";
		password = "1234456";
	}

	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		
		//I - ACTION (INPUT DATA)
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Just only initialize variable when interating with element
		// Day
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		// Interact with the dropdown (*)
		// 1. select.selectByIndex(0); => not suggest
		// 2. select.selectByValue("item"); => not suggest
		// 3. select.selectByVisibleText("text here"); => use in reality

		// getOptions(): get all selected options in dropdown list => return to
		// List<WwbElement>

		select.selectByVisibleText("25");

		// getFirstSelectedOption(): get the context that we selected at (*) correctly
		// or incorrectly
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");

		// isMultiple(): verify that if the dropdown list allow sellecting multiple or
		// not, return to Boolean
		Assert.assertFalse(select.isMultiple());

		// Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("July");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "July");
		Assert.assertFalse(select.isMultiple());

		// Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		Assert.assertFalse(select.isMultiple());

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		//II - VERIFY DATA
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		driver.findElement(By.className("ico-account")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "July");
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1980");
		
		Assert.assertEquals(driver.findElement(By.name("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.name("Company")).getAttribute("value"), companyName);
		
	}

	@Test
	public void TC_02_Wheretobuy() {
		driver.get("https://rode.com/en/support/where-to-buy");
		select = new Select(driver.findElement(By.name("country")));
		Assert.assertFalse(select.isMultiple());
		
		//choose "Vietnam" value in the dropdown list
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}
}
