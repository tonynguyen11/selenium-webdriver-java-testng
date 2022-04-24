package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebBrowser_Exercise {
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
	public void TC_01_Page_Url() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), 
				"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), 
				"http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Page_Title() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), 
				"http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), 
				"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		sleepInSecond(1);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
	}
	
	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		sleepInSecond(1);
	
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account")); 

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//Because while waiting for opening a URL then code has already run
	//we may get error: element not found!
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

