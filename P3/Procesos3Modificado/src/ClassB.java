
public class ClassB implements Runnable {

	private ClassA classA;
	private ClassB classB;
	private boolean bool;

	public ClassB(ClassA a) {
		this.classA = a;
		this.bool = true;
	}

	public void setNext(ClassB setnext) {
		this.classB = setnext;
	}
	

	@Override
	public void run() {
		
	////while boolean counter finished enw, return false
	////wait
	//recibe notify

		while (bool) {
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {

				}
			}
			if (!classA.isfinished()) {
				this.classA.EnterAndWait();
			} else if (classA.isfinished()) {
				bool = false;
			}

			synchronized (classB) {
				classB.notify();
			}
		}

	}

}
