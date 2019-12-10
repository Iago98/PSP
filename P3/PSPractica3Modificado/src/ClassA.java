import java.util.ArrayList;

public class ClassA implements Runnable {

	private static final int TIEMPO = 1000;
	private static int counter=10;
	public  boolean var=true;
	private ArrayList<Long> threadIds = new ArrayList<Long>();
	
	public boolean isVar() {
		return var;
	}

	public void setVar(boolean var) {
		this.var = var;
	}

	public ClassA() {
		this.counter=0;
		
	}
	
	public ArrayList<Long> getThreadIds() {
		return threadIds;
	}

	public synchronized void enterAndWait() {
		
		this.var=false;
		--counter;
		threadIds.add((Thread.currentThread().getId()));
		
		System.out.println("El hilo que me está ejecutando " + Thread.currentThread().getName());
		try {
			
			wait(TIEMPO);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Estoy acabando");
		this.notifyAll();
		this.var=true;
		

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
