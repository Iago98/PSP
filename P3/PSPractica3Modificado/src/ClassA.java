import java.util.ArrayList;
import java.util.Set;

public class ClassA implements Runnable {

	private static final int TIEMPO = 1000;
	private int counter;
	private ArrayList<Long> threadIds = new ArrayList<Long>();
	
	public ClassA() {
		this.counter=0;
		
	}
	
	public ArrayList<Long> getThreadIds() {
		return threadIds;
	}

	public synchronized void enterAndWait() {

		
		threadIds.add((Thread.currentThread().getId()));
		--counter;
		System.out.println("El hilo que me está ejecutando " + Thread.currentThread().getName());
		try {
			wait(TIEMPO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Estoy acabando");

	}
	public boolean isFinished() {
		boolean bool=false;
		if(counter==0) {
			bool=true;
		}
		
		return bool;
		
	}
	
	

	@Override
	public void run() {
		
		this.enterAndWait();
	}

}
