import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		CyclicBarrier cb = new CyclicBarrier(3, new CycBarPost());
		ExecutorService es = Executors.newFixedThreadPool(3);

		new Thread(new CycBarTh(cb, "One")).start();
		new Thread(new CycBarTh(cb, "Two")).start();
		new Thread(new CycBarTh(cb, "Three")).start();

	}

}

class CycBarTh implements Runnable {

	CyclicBarrier cb;
	String name;

	CycBarTh(CyclicBarrier cb, String name) {
		this.cb = cb;
		this.name = name;

	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name);

		try {
			cb.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class CycBarPost implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("All the threads have ran!");
	}

}
