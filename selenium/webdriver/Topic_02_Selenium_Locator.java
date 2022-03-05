package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	//Khai báo 1 biến driver đại diện cho Selenium Driver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//Set geckodriver: giao tiếp giữa browser và code
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		//Bật trình duyệt Firefox lên
		driver = new FirefoxDriver();
		
		//Set thời gian đi tìm element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Bật cửa sổ windows to lên
		driver.manage().window().maximize();
		
		//Mở app lên
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
//		Element structure: tag name +  attribute name +  attribute value
		
//		<input type="text" class="inputtext _55r1 _6luy" name="email" id="email" 
//		data-testid="royal_email" placeholder="Email address or phone number" autofocus="1" 
//		aria-label="Email address or phone number" spellcheck="false" data-ms-editor="true">
		
//		Selenium (version <= 3) has 8 types of locators:
		
//		1. Id
		driver.findElement(By.id("email")).sendKeys("toan@gmail.com");
	
//		2. Class
//		<img class="fb_logo _8ilh img" 
//		src="https://static.xx.fbcdn.net/rsrc.php/y8/r/dF5SId3UHWd.svg" alt="Facebook">
		driver.findElement(By.className("fb_logo")).isDisplayed();
		
		//driver đại diện cho thư viện của Selenium
		//Tìm element để tương tác lên
	
//		3. Name
		driver.findElements(By.name("email"));
		
//		4. Tag name: tìm xem có bao nhiêu element cùng loại ở trên page HTML
		driver.findElement(By.tagName("a"));
	
//		5. Link Text:truyền cả text vào
		driver.findElement(By.linkText("Tiếng Việt"));
		
//		6. Partial Link Text: 1 phần của text, độ chính xác k cao
		driver.findElement(By.partialLinkText("Tiếng"));
		driver.findElement(By.partialLinkText("Việt"));
		driver.findElement(By.partialLinkText("Tiến"));
		driver.findElement(By.partialLinkText("iệt"));
		
//		7. Css: 6 thằng trên k dùng nhiều do css và xpath đã cover hết
		
		//cover id
		driver.findElements(By.cssSelector("input[id = 'email']"));
		driver.findElements(By.cssSelector("input#email"));
		driver.findElements(By.cssSelector("#email"));
		
		//cover class:
		driver.findElements(By.cssSelector("img.fb_logo"));
		driver.findElements(By.cssSelector("img[class = 'fb_logo_8ilh img']"));
		driver.findElements(By.cssSelector(".fb_logo"));
		
		//cover name:
		driver.findElements(By.cssSelector("input[name = 'email']"));
		
		//cover tag name
		driver.findElements(By.cssSelector("a"));
		
		//cover link lext. css k làm việc với text, dùng thuộc tính khac của thẻ a để thao tác
		driver.findElements(By.cssSelector("a[title = 'Vietnamese']"));
		driver.findElements(By.cssSelector("a[onclick *= 'vi_VN']"));
		
		//cover partial link text. nhưng thực tế dùng xpath là chính
		driver.findElements(By.cssSelector("a[title *= 'Vietnam']"));
		
//		8. Xpath: cover tất cả 7 cái trên. cú pháp: // + tag name + [@ +attribute]
		driver.findElement(By.xpath("//input[@id = 'email']"));
		driver.findElement(By.xpath("//img[@class = 'fb_logo _8ilh img']"));
		driver.findElement(By.xpath("//img[contains(@class, 'fb_logo')]"));
		driver.findElement(By.xpath("//img[starts-with(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//img[starts-with(@class,'fb_logo')]"));
		driver.findElement(By.xpath("//input[@name = 'email']"));
		driver.findElement(By.xpath("//a"));
		driver.findElement(By.xpath("//a[text() = 'Tiếng Việt']"));
		driver.findElement(By.xpath("//a[contains(text(), 'Tiếng Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(), 'Việt')]"));
		driver.findElement(By.xpath("//a[contains(text(), 'Tiếng')]"));
			
	}

	@Test
	public void TC_02_() {
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

