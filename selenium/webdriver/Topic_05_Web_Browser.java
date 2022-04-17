package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();		
	}

	@Test
	public void TC_01_Method() {
		// We have 2 ways to interact with a web page: WebBrowser & WebElement
		
		//Close browser
		//Over 1 tab: only close the current tab, not close browser
		//Only 1 tab: close 1 tab means that close the browser
		driver.close();
		
		//Close browser no matter how many tabs are opening
		driver.quit();
		
		//Open 1 URL
		driver.get("https://www.facebook.com/");
		
		//Find 1 element
		WebElement element = driver.findElement(By.className(""));
		element.click(); //store the string into a variable if we want to user more than 1 time
		
		//Find over 1 elements (list elements)
		driver.findElements(By.xpath(""));
		
		//Get the current URL
		driver.getCurrentUrl();
		
		//Get source code of current page, use for verifing relatively (e.g: go with cointains method)
		driver.getPageSource();
		
		//Get the title of the current page
		driver.getTitle();
		
		//***************LEARN IN HANDLE WINDOWS/ TAB****************
		//To handle windows tab, get the ID of current tab/window
		driver.getWindowHandle();
		
		//To handle windows tabs, get the ID of all tabs/windows
		driver.getWindowHandles();
		
		//Manage() returns to Option interface
		//Cookie: store login session/ web browse data/...
		driver.manage().deleteAllCookies();
		
		//***************LEARN IN BUIID FRAMEWORK-LOG****************
		driver.manage().logs();
		
		//***************LEARN IN WAIT****************
		//Wait for finding element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//Wait for loading page successfully
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//***************LEARN IN JAVAVASCRIPT EXECUTOR****************
		//Wait for executing a javascript code successfully in an asynchronous script
		//asynchronous script vs synchronous script (java) => expected
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//***************HANDLE WINDOWS****************
		//Set full screen 
		driver.manage().window().fullscreen();
		
		//Set maximum size of the window
		driver.manage().window().maximize();
		
		//Set position of the window
		//Not use regularly: test GUI/ Responsive (Font/ Size/ Color/ Position/ Location/...)
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().getPosition();
		
		//Set Dimension of the window
		//Use for testing responsive
		driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.manage().window().getSize();
		
		//Back to previous page
		driver.navigate().back();
		
		//Go to the forward page
		driver.navigate().forward();
		
		//Refresh page
		driver.navigate().refresh();
		
		//Go to a specific page, tracking history better get() method
		driver.navigate().to("https://www.facebook.com/");
		
		//***************LEARN IN ALERT****************
		//Alert
		driver.switchTo().alert();
		
		//***************LEARN IN FRAME/IFRAME****************
		//Frame/ Iframe
		driver.switchTo().frame(0);
		
		//***************LEARN IN WINDOWS/ TAB****************
		//Windows/ Tab
		driver.switchTo().window("");	
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

