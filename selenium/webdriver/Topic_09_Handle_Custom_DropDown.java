package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Custom_DropDown {
	WebDriver driver;
	
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemCustomDropdownByXpath("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");
		
		selectItemCustomDropdownByXpath("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
		
	}

	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemCustomDropdownByXpath("//div[@class='ui fluid selection dropdown']", "//span[@class='text']", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");
		
		selectItemCustomDropdownByXpath("//div[@class='ui fluid selection dropdown']", "//span[@class='text']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemCustomDropdownByCss("li.dropdown-toggle", "ul.dropdown-menu>li>a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemCustomDropdownByCss("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
		selectItemCustomDropdownByCss("li.dropdown-toggle", "ul.dropdown-menu>li>a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}
	
	@Test
	public void TC_04_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemCustomDropdownByCss("input.search", "span.text", "Algeria");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
		
		selectItemCustomDropdownByCss("input.search", "span.text", "Belize");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belize");
		
		selectItemCustomDropdownByCss("input.search", "span.text", "Aland Islands");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Aland Islands");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectItemCustomDropdownByXpath(String parentLocator, String chilLocator, String expectedItemText) {
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(chilLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.xpath(chilLocator));
		
		for (WebElement item : allDropdownItems) {
			String actualItemText = item.getText();
			if (actualItemText.equals(expectedItemText)) {
				
				//scrollIntoView(true) => scroll into the above bound
				//scrollIntoView(false) => scroll into the bottom bound
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				
				item.click();
				item.isSelected();
				break;
			}
		}
	}
	
	public void selectItemCustomDropdownByCss(String parentLocator, String chilLocator, String expectedItemText) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(chilLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(chilLocator));
		
		for (WebElement item : allDropdownItems) {
			String actualItemText = item.getText();
			if (actualItemText.equals(expectedItemText)) {
				
				//scrollIntoView(true) => scroll into the above bound
				//scrollIntoView(false) => scroll into the bottom bound
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				
				item.click();
				item.isSelected();
				break;
			}
		}
	}

	public void enterItemInCustomDropdownByCss(String editableLocator, String chilLocator, String expectedItemText) {
		driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedItemText);
		sleepInSecond(1);
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(chilLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(chilLocator));
		
		for (WebElement item : allDropdownItems) {
			String actualItemText = item.getText();
			if (actualItemText.equals(expectedItemText)) {
				
				//scrollIntoView(true) => scroll into the above bound
				//scrollIntoView(false) => scroll into the bottom bound
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				
				item.click();
				item.isSelected();
				break;
			}
		}
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

