public class Main {

	private final static int HILOS = 7;
	private final static int COUNTER = 10;

	public static void main(String[] args) {

		ClassA ClassA = new ClassA(COUNTER);
		ClassB[] ClassB = new ClassB[HILOS];
		Thread[] threads = new Thread[HILOS];

		for (int i = 0; i < HILOS; i++) {
			ClassB[i] = new ClassB(ClassA);
		}
		for (int i = 0; i < HILOS; i++) {
			if (i == HILOS - 1) {
				ClassB[i].setNext(ClassB[0]);
			} else {
				ClassB[i].setNext(ClassB[i+ 1]);
			}
		}

		for (int i = 0; i < HILOS; i++) {
			threads[i] = new Thread(ClassB[i]);
			threads[i].start();
		}

		synchronized (ClassB[0]) {
			ClassB[0].notify();
		}
	}

}