public class Main {

	private static final int HILOS = 3;

	public static void main(String[] args) {

		Thread[] threads = new Thread[HILOS];
		ClassA classA = new ClassA();

		for (int i = 0; i < HILOS; ++i) {
			threads[i] = new Thread(new ClassB(classA));
			threads[i].start();
			/*
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
		}

	}
}