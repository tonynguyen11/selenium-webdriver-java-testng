package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
	// Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	//Hàm này sẽ áp dụng cho việc tìm element(findElement / findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	//Phóng to browser lên
		driver.manage().window().maximize();
		
	//Mở app lên
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		
	//Tìm (find) - số ít - trả về 1 cái
		
	// Thao tác trực tiếp, k khai báo biến - sử dụng 1 lần, k dùng lại element này
		driver.findElement(By.id("")).click();
	
	//Thao tác khai báo biến
		WebElement loginButton = driver.findElement(By.id(""));
	
	//Có thể dùng lại nhiều lần
		loginButton.click();
		loginButton.isDisplayed();
		
	//Tìm (find) - số nhiều - trả về >= 1 cái
		driver.findElements(By.id("")).size();
	
	//Lặp lại nhiểu lần
		List<WebElement> loginCheckboxes = driver.findElements(By.id(""));
	
	//Thao tác (Action): click / type / select / hover / ...
	
	//Kiểm tra (Verify / Assert): getText / getAttribute / getCss / ...
	
	//Thao tác vs email textbox

		
	//Thao tác vs password email
		
	//Thao tác Logic	
	}

	@Test
	public void TC_02_() {
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

