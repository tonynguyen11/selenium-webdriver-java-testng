package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, fullName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Automation2";
		lastName = "Practive";
		emailAddress = "test" + generateRandomNumber() + "@yahoo.com";
		password = "123456";
		fullName = firstName + " " + lastName;

	}

	@Test
	public void TC_01_Register_Empty_Data() {
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Click vào nút đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Kiểm tra message hiển thị lỗi ở các field bắt buộc
		driver.findElement(By.id("txtFirstname-error")).getText();

//		//Kiểm tra 1 điều kiện trả về là đúng
//		Assert.assertTrue(true);
//		//Kiểm tra 1 điều kiện trả về là sai
//		Assert.assertFalse(true);
		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan123");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan123");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1234");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {

		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1234");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Register_Password_Less_Than_6_Chracters() {
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Register_Incorrect_Password() {
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan11");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan12");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_Register_Incorrect_Phone() {
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan12");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan12");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// Xóa dữ liệu tại ô phone
		driver.findElement(By.id("txtPhone")).clear();

		// Nhập dữ liệu mới
		driver.findElement(By.id("txtPhone")).sendKeys("1901234");

		// Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@Test
	public void TC_07_Login_Empty_Email_and_Password() {
		// Mở app
		driver.get("http://live.techpanda.org/");

		// Click vào link My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Ndùng hàm clear() để xóa data, phòng trường hợp nó có sẵn data
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();

		// Nhấp vào button login
		driver.findElement(By.id("send2")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_08_Login_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.name("login[username]")).sendKeys("Toan@123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_09_Login_Password_Less_Than_6_Characters() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.name("login[username]")).sendKeys("Toan123@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1234");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_10_Login_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.name("login[username]")).sendKeys("toantoan@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("1234567");

		driver.findElement(By.id("send2")).click();

		// Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void TC_11_Create_An_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("email")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// Absolute comparison
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"Thank you for registering with Main Website Store.");
		
		//Relative comparison
		String contactInforText = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/"
				+ "parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();	
	}
	
	@Test
	public void TC_12_Login_Valid_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			
		driver.findElement(By.name("login[username]")).sendKeys(emailAddress);
		driver.findElement(By.name("login[password]")).sendKeys(password);
		
		driver.findElement(By.name("send")).click();

		String contactInforText = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/"
				+ "parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAddress));	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}
}
