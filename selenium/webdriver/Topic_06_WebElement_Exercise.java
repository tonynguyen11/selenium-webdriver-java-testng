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

public class Topic_06_WebElement_Exercise {
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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		System.out.println(emailTextbox);

		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}

		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));

		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 radio is displayed");
		} else {
			System.out.println("Age under 18 radio is not displayed");
		}

		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));

		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
		
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

		if (name5Text.isDisplayed()) {
			System.out.println("Name 5 Text is displayed");
		} else {
			System.out.println("Name 5 Text is not displayed");
		}

	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//ENABLED
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		//In fact, we use:
		//Assert.assertTrue(emailTextbox.isEnabled);
		//to verify it is enabled
		if(emailTextbox.isEnabled()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email texbox is disabled");
		}
		
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if(educationTextarea.isEnabled()) {
			System.out.println("Education textbox is enabled");
		} else {
			System.out.println("Education textbox is disabled");
		}
		
		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		if(ageUnder18.isEnabled()) {
			System.out.println("Age Under 18 is enabled");
		} else {
			System.out.println("Age Under 18 is disabled");
		}
		
		WebElement jobRole1 = driver.findElement(By.cssSelector("#job1"));
		if(jobRole1.isEnabled()) {
			System.out.println("Job role 1 is enabled");
		} else {
			System.out.println("Job role 1 is disabled");
		}
		
		WebElement jobRole2 = driver.findElement(By.cssSelector("#job2"));
		if(jobRole2.isEnabled()) {
			System.out.println("Job role 2 is enabled");
		} else {
			System.out.println("Job role 2 is disabled");
		}
		
		WebElement interestDevelopment = driver.findElement(By.cssSelector("#development"));
		if(interestDevelopment.isEnabled()) {
			System.out.println("Interest Development is enabled");
		} else {
			System.out.println("Interest Development is disabled");
		}
		
		WebElement slider01 = driver.findElement(By.cssSelector("#slider-1"));
		if(slider01.isEnabled()) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is disabled");
		}
		
		//DISABLED
		WebElement disablePassword = driver.findElement(By.cssSelector("#disable_password"));
		//In fact, we use:
				//Assert.assertFalse(disablePassword.isEnabled);
				//to verify it is disabled
		if(disablePassword.isEnabled()) {
			System.out.println("Disable Password is enabled");
		} else {
			System.out.println("Disable Password is disabled");
		}
		
		WebElement disableRadioButton = driver.findElement(By.xpath("//label[text()='Radio button is disabled']"));
		if(disableRadioButton.isEnabled()) {
			System.out.println("Disable Radio Button is enabled");
		} else {
			System.out.println("Disable Radio Button is disabled");
		}
		
		WebElement biography = driver.findElement(By.cssSelector("#bio"));
		if(biography.isEnabled()) {
			System.out.println("Biography is enabled");
		} else {
			System.out.println("Biography is disabled");
		}
		
		WebElement slider02 = driver.findElement(By.cssSelector("#slider-2"));
		if(slider02.isEnabled()) {
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02 is disabled");
		}
		
		WebElement jobRole3 = driver.findElement(By.cssSelector("#job3"));
		if(jobRole3.isEnabled()) {
			System.out.println("Job Role 3 is enabled");
		} else {
			System.out.println("Job Role 3 is disabled");
		}
		
		WebElement disableCheckbox = driver.findElement(By.cssSelector("#check-disbaled"));
		if(disableCheckbox.isEnabled()) {
			System.out.println("Disable Checkbox is enabled");
		} else {
			System.out.println("Disable Checkbox is disabled");
		}
		
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		
		ageUnder18Radio.click();
		javaCheckbox.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());
		
		javaCheckbox.click();
		Assert.assertFalse(javaCheckbox.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 is selected");
			
		} else {
			System.out.println("Age under 18 is deselected");
		}
		
		if (javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
			
		} else {
			System.out.println("Java Checkbox is deselected");
		}
	}

	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation2@gmail.com");
		WebElement usernameTextbox = driver.findElement(By.cssSelector("input#new_username"));
		usernameTextbox.click();
		usernameTextbox.clear();
		usernameTextbox.sendKeys("automation2fc");
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		WebElement signupButton = driver.findElement(By.cssSelector("button#create-account"));
		
		//Lowcase
		passwordTextbox.sendKeys("auto");
		//bz hint password label will appear after 1 - 2s
		//we insert a sleep code to hold a program in 1 - 2s
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//UpperCase
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed'and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//UpperCase
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//Number
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed'and text()='One number']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//Special
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("@#%^");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//8 Chars
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		
		//Combine
		//clear data before sendKey
		passwordTextbox.clear();
		passwordTextbox.sendKeys("1234Tu^!");
		WebElement receiveEmailCheckbox = driver.findElement(By.cssSelector("input#marketing_newsletter")); 
		receiveEmailCheckbox.click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		Assert.assertTrue(receiveEmailCheckbox.isSelected());
		Assert.assertTrue(signupButton.isEnabled());
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
