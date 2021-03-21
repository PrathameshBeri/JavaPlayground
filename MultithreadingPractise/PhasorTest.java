import java.util.concurrent.Phaser;

public class PhasorTest {
	public static void main(String args[]) {

		Phaser ph = new Phaser(1);
		int currPhase;

		System.out.println("Starting!");
		new Thread(new PhaserConsumer(ph, "A")).start();
		new Thread(new PhaserConsumer(ph, "B")).start();
		new Thread(new PhaserConsumer(ph, "C")).start();

		currPhase = ph.getPhase();
		ph.arriveAndAwaitAdvance();
		System.out.println("the phaser registered parties are " + ph.getRegisteredParties());
		System.out.println("Starting the threads! " + currPhase);

		currPhase = ph.getPhase();
		ph.arriveAndAwaitAdvance();
		System.out.println("Starting the threads! " + currPhase);

		currPhase = ph.getPhase();
		ph.arriveAndAwaitAdvance();
		System.out.println("Starting the threads! " + currPhase);

		System.out.println("the phaser registered parties are " + ph.getRegisteredParties());

		ph.arriveAndDeregister();

		System.out.println("the phaser registered parties are " + ph.getRegisteredParties());

		if (ph.isTerminated()) {
			System.out.println("The phaser is terminated");
		}

	}
}

class PhaserConsumer implements Runnable {
	Phaser ph;
	String name;

	PhaserConsumer(Phaser ph, String s) {
		this.ph = ph;
		name = s;
		ph.register();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " + name + " Starting phase 1");
		ph.arriveAndAwaitAdvance();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " Starting phase 2");
		ph.arriveAndAwaitAdvance();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread " + name + " Starting phase 3");
		ph.arriveAndDeregister();
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
