
import java.util.ArrayList;


public class ClassA {
	
	private ArrayList<Long> threadIdss = new ArrayList<Long>();
	private int counter;
	
	public int getCounter() {
		return counter;
	}

	public ClassA(int counter) {
		this.counter = counter;
	}
	public synchronized void countLess() {
		counter--;
	}

	public ArrayList<Long> getIds() {
		return this.threadIdss;
	}

	public boolean isfinished() {

		if (this.counter == 0) {
			return true;
		} else {
			return false;
		}
		
	}

	public synchronized void EnterAndWait() {
		System.out.println("El hilo que me está ejecutando es " + Thread.currentThread().getName());
		threadIdss.add(Thread.currentThread().getId());
		countLess();
		System.out.println("Estoy acabando");
	}

}