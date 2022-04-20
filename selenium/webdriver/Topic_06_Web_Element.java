package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Define_Element() {
		//To interact with web elements, we need to find them first
		//1. Find element
		//2. Which locator to find: name, class, id,...
		//3. Interact with/ check (verify) elements by methods
		
		driver.findElement(By.id("send2")).click();
		//When interacting over 1 time => use variable to store value
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("test@gmail.com");
		emailTextbox.isDisplayed();
	}

	@Test
	public void TC_02_Element_Method() {
		WebElement element = driver.findElement(By.id(""));
		
		//Delete data before sendKeys: Textbox/ TextArea/ Editable Dropdown
		element.clear();
		
		//Input data into field
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		//Find elements can be use in both WebBrowswer and WebElement:
		//But in actual, we dont write like bottom:
		driver.findElement(By.className("footer")).findElement(By.cssSelector("a[title='My Account']"));
		
		//get attribute of element
		driver.findElement(By.id("search")).getAttribute("placeholder");
		
		//not use variable
		Assert.assertEquals(driver.findElement(By.id("search"))
		.getAttribute("placeholder"), "Search entire store here...");
		
		//Use variable, assert is a class in testng library
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValue, "Search entire store here...");
		
		//Verify GUI: Font/ Color/ Size/ Pixel/...
		//in Firefox => check attributes of the elements at "Rule" tab
		element.getCssValue("background-color");
		element.getCssValue("font-size");
		
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//LEARN IN FRAMEWORK: ATTACH SCREENSHOT TO REPORT HTML
		element.getScreenshotAs(OutputType.FILE);
		
		element = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		element = driver.findElement(By.cssSelector("div[id='advice-validate-email-email']"));
		
		element = driver.findElement(By.cssSelector("div#advice-validate-email-email"));
		//get tag name => rarely to use
		String emailTextboxTagname = element.getTagName();
		
		//Output of this element will be the input of the other
		//Passing 1 variable into a String
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='advice-required-entry-pass']"));
		
		//Get text of current element/ text of sub element inside
		element.getText();
		
		//If we want an element is displayed (see the size: height - width)
		//If an element is undisplayed => isDisplayed() will return to false
		//Apply for:textbox/ textarea/ dropdown/ checkbox/ radio/ button/...
		element.isDisplayed();
		
		//If an element is enabled
		//If an element is disabled => isEnabled() will return to false
		element.isEnabled();
		
		//If an element is selected: checkbox/ radio/ dropdown list
		//Checkbox can be selected/ deselected
		//Radio cannot be deselected
		element.isSelected();
		
		//click an element: button/ link/ Radio/ icon/ Image/...
		element.click();
		
		//The same behavior as Enter button at Form
		//Use for tagName: form (sub elements inside)
		element.submit();
		
		//Slider: click on Slider then drag the mouse
		//Drag to the a coordinates (pixel unit)
		//Or drag to other element
		action.clickAndHold(element).moveToElement(element).perform();
		
		
		
		
		
		
		
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

