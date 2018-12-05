package src.test.java.com.app.practice_java.inheritance;

public class ZooKeeper {
   public static void main(String[] args) {
    Food food = FoodFactory.getFood("goat");
    food.consumed();
}
}
