package javaBasic;

import org.testng.annotations.Test;

public class Topic_03_Java_Function {

	//No parameter
	public void clickToElement() {
		
	}
	
	//1 parameter
	public void clickToElement(String elementName) {
		System.out.println(elementName);
	}
	
	//2 parameters
	public void clickToElement(String elementName, String locatorName) {
		
	}
	
	@Test //Test Method/ testcase/ testscript
	public void TC_01_Login() {
		clickToElement("Textbox");
	}
}
