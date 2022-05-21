package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Radio_Button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		//bz popup is showed, not learned yet, temporarily using sleep command to click close pop-up manual
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		//Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("test12@hotmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		//some compututes that run too fast, "find..." command will run before color of "enabled button" is shown
		//then we need to sleep in a fews seconds to sync
		sleepInSecond(1);
	
		//Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		//Verify background-color is red
		String loginButtonBackgroundColor = driver.findElement(loginButtonBy).getCssValue("background-color");
		
		//Browser show color format is "rgb" but Java return "rgba" => need to convert to hexa to compare
		String loginButtonBackgroundHexa = Color.fromString(loginButtonBackgroundColor).asHex().toUpperCase();
		
		Assert.assertEquals(loginButtonBackgroundHexa, "#C92127");

	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Check box
		driver.findElement(By.xpath("//input[@value='Heart Attack']")).click();
		driver.findElement(By.xpath("//input[@value='Liver Disease']")).click();
		driver.findElement(By.xpath("//input[@value='Emphysema']")).click();
		sleepInSecond(3);
		
		//Radio button
		//Some custom element will not be showed (size 1x1 pixel), cannot click bz it is covered by other tags
		driver.findElement(By.xpath("//input[@value='I have a strict diet']")).click();
		driver.findElement(By.xpath("//input[@value='\"I don't drink\"]")).click();
		driver.findElement(By.xpath("//input[@value='3-4 cups/day']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Heart Attack']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Liver Disease']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Emphysema']")).isSelected());
				
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a strict diet']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='\"I don't drink\"]")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='3-4 cups/day']")).isSelected());
		
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

