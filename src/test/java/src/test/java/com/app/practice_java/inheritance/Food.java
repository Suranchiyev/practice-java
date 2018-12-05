package src.test.java.com.app.practice_java.inheritance;

public class Food {
  private int quantity;
  
  public Food(int quantity) {
	  this.quantity = quantity;
  }
  
  public int getQuantity() {
	  return this.quantity;
  }
  
  public void consumed() {
	  System.out.println("Food eaten" + getQuantity());
  }
}

class FoodFactory {
	public static Food getFood(String animalName) {
		switch(animalName) {
		  case "zebra" : return new Hay(100);
		  case "rabbit" : return new Pellets(5);
		  case "goat" : return new Pellets(30);
		  case "polar bear" : return new Fish(10);
		  default : return new Food(10);
		}
	}
}
