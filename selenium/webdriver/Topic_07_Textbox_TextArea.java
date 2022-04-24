package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, loginUrl, userID, password, customerName, genderValue;
	String dateOfBirthInput, dateOfBirthOutput, city, state, pinNumber, phoneNumber;
	String addressInput, addressOutput;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Casting driver to jsExecutor (bz it is an interface) to remove type = "date"
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");

		Topic_03_Xpath_Part_II a = new Topic_03_Xpath_Part_II();
		emailAddress = "david" + a.generateRandomNumber() + "@hotmail.com";
		loginUrl = driver.getCurrentUrl();
		customerName = "John Terry";
		genderValue = "male";
		dateOfBirthInput = "08/24/1980";
		dateOfBirthOutput = "1980-08-24";
		addressInput = "123 Pham Van Dong\nward2\nBinh Thanh";
		addressOutput = "123 Pham Van Dong ward2 Binh Thanh";
		city = "Ho Chi Minh";
		state = "Vietnam";
		pinNumber = "135798";
		phoneNumber = "090123456";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		WebElement emailIDTextbox = driver.findElement(By.cssSelector("input[name='emailid']"));
		emailIDTextbox.clear();
		emailIDTextbox.sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input[name='btnLogin']")).click();

		// Verify
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		// Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("td[style='color: green']")).getText(),
				"Manger Id : " + userID);
	}

	@Test
	public void TC_03_Create_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input
		driver.findElement(By.name("name")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		
		//This code is used for removing "date" type
		WebElement dateOfBirthTextbox = driver.findElement(By.name("dob"));
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dateOfBirthTextbox);
		
		dateOfBirthTextbox.sendKeys(dateOfBirthInput);
		driver.findElement(By.name("addr")).sendKeys(addressInput);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		//Output
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following::td")).getText(), genderValue);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following::td")).getText(), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following::td")).getText(), emailAddress);
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
}
