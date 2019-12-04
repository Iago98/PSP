public class ClassB implements Runnable {

	ClassA object = new ClassA();

	public ClassB(ClassA object) {

		this.object = object;

	}

	@Override
	public void run() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		object.run();
		notifyAll();
	}

}

