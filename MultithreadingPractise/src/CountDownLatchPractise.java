import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchPractise {
	public static void main(String args[]) throws InterruptedException {

		CountDownLatch cdl = new CountDownLatch(10);

		TPCounter vari = new TPCounter(0);

		FirstIncrementer f = new FirstIncrementer(vari, cdl);
		SecondIncrementer s = new SecondIncrementer(vari, cdl);

		Thread t1 = new Thread(f);
		Thread t2 = new Thread(s);
		ExecutorService es = Executors.newFixedThreadPool(4);
		es.submit(t1);
		es.submit(t2);
		es.awaitTermination(20000, TimeUnit.MILLISECONDS);
		System.out.println("The final value of vari is " + vari.getCount());
	}

}

class TPCounter {
	int count;

	TPCounter(int n) {
		count = n;
	}

	void increment() {
		count += 1;
	}

	int getCount() {
		return count;
	}
}

class FirstIncrementer implements Runnable {
	TPCounter n;
	CountDownLatch cd;

	FirstIncrementer(TPCounter n, CountDownLatch cs) {
		this.n = n;
		cd = cs;
	}

	public void run() {
	

		for (int i = 0; i < 10; i++) {
			n.increment();
			System.out.println(" First incrementer increments " + n.getCount());
			cd.countDown();
			try {
				System.out.println("Thread first sleeping ");
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		System.out.println("First thread releases latch");
	}

}

class SecondIncrementer implements Runnable {
	TPCounter n;
	CountDownLatch cs;

	SecondIncrementer(TPCounter n, CountDownLatch cs) {
		this.n = n;
		this.cs = cs;
	}

	public void run() {
	
		try {
			cs.await();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			
			n.increment();
			System.out.println(" Second incrementer increments " + n.getCount());
		}
	}

}
