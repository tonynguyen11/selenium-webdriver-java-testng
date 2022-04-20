package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;

	@Test
	public void TC_01() {
		//3 assert methods we use regularly:
		//1 - AssertEqual: check if actual data is equal with the expected data.
		
		//Verify absulutely (2 things are equal)
		Assert.assertEquals(driver.findElement(By.id("search"))
				.getAttribute("placeholder"), "Search entire store here... ");
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email"))
				.getText(), "This is a required field.");
		
		//Verify relatively
		String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefitText.contains("Faster checkout"));
		Assert.assertTrue(benefitText.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benefitText.contains("View and track orders and more"));
		
		//2 - AssertTrue: check if the expected data is correct.
		//Email textbox is displayed
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		//3 - AssertFalse: check if the expected data is incorrect.
		//Email textbox is undisplayed
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
	}
}
