public class Main{
	private static final int HILOS = 1000;

	public static void main(String[] args) {

		Thread[] threads = new Thread[HILOS];
		Counter counter = new Counter();

		for (int i = 0; i < HILOS; ++i) {
			threads[i]= new Thread(new MyTask(counter));
			threads[i].start();
		}

		for (int i = 0; i < HILOS; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("cuenta= " + counter.getCount());
	}

}
