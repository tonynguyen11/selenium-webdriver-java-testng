package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea_Exercise_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String userName, password, firstName, lastName;
	WebElement firstNameTextboxOutput, lastNameTextboxOutput;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		userName = "Admin";
		password = "admin123";
		firstName = "David";
		lastName = "Beckham";

		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@Test
	public void TC_01_Login() {
		WebElement userNameTextbox = driver.findElement(By.id("txtUsername"));
		userNameTextbox.click();
		userNameTextbox.sendKeys(userName);
		
		WebElement passwordTextbox = driver.findElement(By.id("txtPassword"));
		passwordTextbox.click();
		passwordTextbox.sendKeys(password);
		
		driver.findElement(By.id("btnLogin")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Dashboard']")).getText(), "Dashboard");
	}

	@Test
	public void TC_02_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		WebElement firstNameTextboxInput = driver.findElement(By.id("firstName"));
		firstNameTextboxInput.clear();
		firstNameTextboxInput.click();
		firstNameTextboxInput.sendKeys(firstName);

		WebElement lastNameTextboxInput = driver.findElement(By.id("lastName"));
		lastNameTextboxInput.clear();
		lastNameTextboxInput.click();
		lastNameTextboxInput.sendKeys(lastName);
		
		String EmployeeIdInput = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		driver.findElement(By.id("btnSave")).click();
		
		firstNameTextboxOutput = driver.findElement(By.id("personal_txtEmpFirstName"));
		Assert.assertEquals(firstNameTextboxOutput.getAttribute("value"), firstName);
		
		lastNameTextboxOutput = driver.findElement(By.id("personal_txtEmpLastName"));
		Assert.assertEquals(lastNameTextboxOutput.getAttribute("value"), lastName);
		
		WebElement EmployeeIdOutput = driver.findElement(By.id("personal_txtEmployeeId"));
		Assert.assertEquals(EmployeeIdOutput.getAttribute("value"), EmployeeIdInput);
		
		Assert.assertFalse(firstNameTextboxOutput.isEnabled());
		Assert.assertFalse(lastNameTextboxOutput.isEnabled());
		Assert.assertFalse(EmployeeIdOutput.isEnabled());
	}

	@Test
	public void TC_03_Edit_Employee() {
		driver.findElement(By.id("btnSave")).click();
		firstName = "Peter";
		lastName = "Crouch";
		
		firstNameTextboxOutput = driver.findElement(By.id("personal_txtEmpFirstName"));
		firstNameTextboxOutput.clear();
		firstNameTextboxOutput.sendKeys(firstName);
		Assert.assertTrue(firstNameTextboxOutput.isEnabled());
		
		lastNameTextboxOutput = driver.findElement(By.id("personal_txtEmpLastName"));
		lastNameTextboxOutput.clear();
		lastNameTextboxOutput.sendKeys(lastName);
		Assert.assertTrue(lastNameTextboxOutput.isEnabled());
		
		driver.findElement(By.id("btnSave")).click();
		
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), lastName);
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

