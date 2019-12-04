
public class ClassA implements Runnable {

	private static final int TIEMPO = 1000;

	public synchronized void enterAndWait() {

		System.out.println("El hilo que me está ejecutando " + Thread.currentThread().getName());
		try {
			wait(TIEMPO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Estoy acabando");

	}

	@Override
	public void run() {
		this.enterAndWait();
	}

}
