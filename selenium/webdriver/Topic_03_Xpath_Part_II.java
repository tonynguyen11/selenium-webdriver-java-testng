package webdriver;

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

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Register_01_Empty_Data() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Click vào nút đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Kiểm tra message hiển thị lỗi ở các field bắt buộc
		driver.findElement(By.id("txtFirstname-error")).getText();
		
//		//Kiểm tra 1 điều kiện trả về là đúng
//		Assert.assertTrue(true);
//		//Kiểm tra 1 điều kiện trả về là sai
//		Assert.assertFalse(true);
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan123");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan123");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1234");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");
		
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	
	}
	
	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1234");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");
		
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void Register_04_Password_Less_Than_6_Chracters() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan1");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan1");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");
				
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void Register_05_Incorrect_Password() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan11");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan12");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234567");
				
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void Register_06_Incorrect_Phone() {
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan12");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan12");
		driver.findElement(By.id("txtPhone")).sendKeys("0901234");
				
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Toan Nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Toan@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Toan12");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Toan12");
		driver.findElement(By.id("txtPhone")).sendKeys("1901234");
				
		//Click vô nút đăng ký
		driver.findElement(By.xpath("//button[@type ='submit']")).click();
		
		//Kiểm tra 1 điều kiện trả về bằng vs dk mong muốn
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

