import java.util.Random;

public class MyTask implements Runnable {

	private Counter counter;
	Random rand = new Random();
	int ranNumb = rand.nextInt(101);

	public MyTask(Counter counter) {
		this.counter = counter;
	}

	@Override
	public synchronized void run() {

		System.out.println("soy el hilo: " + Thread.currentThread().getName());
		try {
			Thread.sleep(ranNumb);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
synchronized(this) {
		counter.incrementCount();
}
	}

}
