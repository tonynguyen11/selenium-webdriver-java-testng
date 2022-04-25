package javaBasic;

public class Test {
	int number = 1;
	
	public static void main(String[] args) {
		Test obj = new Test();
		obj.show();
		
	}
	
	void show() {
		number = 2;
		System.out.println(number);
	}

}
