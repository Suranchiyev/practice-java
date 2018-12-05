package src.test.java.com.app.practice_java.inheritance;

public class Hay extends Food {
    /**
     *   1. In case your parent class has constructor with arguments, you need to provide same constructor in child class
     */
	public Hay(int quantity) {
		// Call constructor from parent class 
		super(quantity);
	}
	
	@Override
	public void consumed() {
		System.out.println("Hay eaten: "+getQuantity());
	}
}
