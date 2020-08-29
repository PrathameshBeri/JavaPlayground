package PCProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PCPTest {

	
	public static void main(String args[]) throws InterruptedException {
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		SynchronizedIngredientLine line = new SynchronizedIngredientLine();
		
		System.out.println(" Lets start cooking !");
		executor.execute(new Producer(line));
		executor.execute(new Consumer(line));
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
	}
	
}
