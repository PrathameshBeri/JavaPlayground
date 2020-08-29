package PCProblem;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
	
	private IngredientLine line;
	
	private SecureRandom random = new SecureRandom();
	
	private List<Fruit> fruitSalad = new ArrayList<>();
	
	private List<Vegetable> stirFry = new ArrayList<>();
	
	public Consumer(IngredientLine line) {
		this.line = line;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; i ++) {
			
				try {
					Thread.currentThread().sleep(random.nextInt(3000));
					Ingredient item = line.blockingGet();
					
					if(item instanceof Fruit) {
						fruitSalad.add((Fruit) item);
						
					}
					else if (item instanceof Vegetable) {
						stirFry.add((Vegetable) item);
					}
					
					System.out.println("Consumer consumes " + item.getName());
					
				}catch(Exception e) {
					
					
				}
			}
		System.out.println("Cooking is done!" + "total fruits " + fruitSalad.size() + " total veggies " + stirFry.size());
		fruitSalad.forEach(value -> System.out.println(value.getName()));
		
		System.out.println(" Stir fry contains");
		stirFry.forEach(value -> System.out.println(value.getName()));
		}
		
	}

