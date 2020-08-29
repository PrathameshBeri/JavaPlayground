package PCProblem;

import java.util.concurrent.ArrayBlockingQueue;

public class SynchronizedIngredientLine implements IngredientLine {
	
	ArrayBlockingQueue<Ingredient> queue = new ArrayBlockingQueue(10);

	@Override
	public void blockingPut(Ingredient i) throws InterruptedException {
		queue.add(i);
	}

	@Override
	public Ingredient blockingGet() throws InterruptedException {
		// TODO Auto-generated method stub
		return queue.remove();
	}

}
