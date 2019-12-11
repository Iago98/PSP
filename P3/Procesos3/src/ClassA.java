
public class ClassA {

	private static final int TIEMPO = 1000;

	public synchronized void enterAndWait() {

		System.out.println("El hilo que me está ejecutando " + Thread.currentThread().getName());
		try {
			Thread.sleep(TIEMPO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Estoy acabando");

	}

}
