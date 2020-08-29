package PCProblem;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
	
	
	private IngredientLine line;
	
	private final static List<Ingredient> ingredientList;
	
	private SecureRandom secureRandom = new SecureRandom();
	
	
	static {
		
		ingredientList = new ArrayList<>();
		ingredientList.add(new Fruit("Mango", "Yellow", "Sweet"));
		ingredientList.add(new Vegetable("Tomato", "Red", "acidic"));
		ingredientList.add(new Fruit("Banana", "Yellow", "Mildly Sweet"));
		ingredientList.add(new Vegetable("Capsicum", "Green", "fiery"));
		ingredientList.add(new Fruit("Pineapple", "Yellow", "Sweet"));
		ingredientList.add(new Vegetable("Onion", "White", "oniony"));
		ingredientList.add(new Fruit("Apple", "Red", "Sweet"));
		ingredientList.add(new Vegetable("Mushroom", "brown", "mushroomy"));
		ingredientList.add(new Fruit("Papaya", "Orange", "Sweet"));
		ingredientList.add(new Vegetable("Carrots", "Orange", "Mild"));
		ingredientList.add(new Fruit("Watermelon", "Red", "Sweet"));
		ingredientList.add(new Vegetable("Cabbage", "Green", "watery"));
		ingredientList.add(new Fruit("Walnut", "Brown", "Nutty"));
		ingredientList.add(new Vegetable("Garlic", "White", "garlicy"));
	}
	

	private final static int len = ingredientList.size();
	
	private static int fruitC = 0;
	private static int vegC = 0;
	
	public Producer(IngredientLine line) {
		this.line = line;
		
	}
	
	@Override
	public void run() {
		
		
		for(int i = 0 ; i< 10; i ++) {
			
			try {
				
				Thread.currentThread().sleep(secureRandom.nextInt(3000));
				
				Ingredient t = ingredientList.get(secureRandom.nextInt(len));
				line.blockingPut(t);
				if(t instanceof Fruit) {
					System.out.println("Producer produces" + " " + ((Fruit)t).getName());
					
					fruitC++;
				}
				else if (t instanceof Vegetable) {}
				System.out.println("Producer produces" + " " + ((Vegetable)t).getName());
				
				vegC++;
				
			}catch(Exception e) {
				
				
			}
		}
		
		System.out.println("Producer done producing!" + " Produced " + fruitC + " fruits " + vegC + " veggies ");
	}
	
	
	
	

}
