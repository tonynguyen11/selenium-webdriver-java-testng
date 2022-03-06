package javaBasic;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		//Data types: primitive type & reference type
		
		//Declaring a variable
		int age;
		
		//Initializing a variable
		age = 10;
		
		//Initializing a variable at declaring
		int salary = 1000;
		
		//I. Primitive types:
		
			//1. Boolean: 1 bit (true /false)
			boolean studentSex = true;
			
			//2. Byte: 1 byte.
			byte bEmployee = 100;
			
			//3. Short: 2 bytes.
			short sEmployee = 50;
			
			//4. Int: 4 bytes
			int iEmployee = 500;
					
			//5. Long: 8 bytes
			long population = 10000000000L;
			
			//6. Float: 4 bytes
			float grade = 7.5F;
			
			//7. Double: 8 bytes
			double hight = 1.64;
			
			//8. Char: 2 byte
			char c = 'A';
		
		//II. Reference types:
			
			//1. String: number, text, special character
			String studentName = "Peter";
			String companeName = "Vin Group";
			
			String a = "Nam";
			System.out.println(a);
			
			String b = a;
			System.out.println(a);
			System.out.println(b);
			
			b = "Dung";
			a = b;
			System.out.println(a);
			System.out.println(b);
			
			
			
			//2. Array:
////			int[] studentNumbers = {15, 4, 2, -3};
////			String[] studentNames = {"Nam", "Hoang", "Le"};
////			
//			//3. Class 
//			WebDriver driver = new ChromeDriver();
//			
//			//4. Interface
//			Actions action = new Actions(driver);
//			
//			//5. Collection: List (ArrayList / LinkedList / Set / Quene
//			ArrayList<String> StudentCountry = new ArrayList<String>();
			
			//6. Object
			Object phone;
			
		
	}
}
