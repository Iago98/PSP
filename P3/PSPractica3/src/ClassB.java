
public class ClassB implements Runnable {

	ClassA object = new ClassA();

	public ClassB(ClassA object) {

		this.object = object;

	}

	@Override
	public void run() {
		object.run();
	}

}
