import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	AtomicInteger a = new AtomicInteger();

	public AtomicInteger getA() {
		return a;
	}

	public void incrementCount() {
		a.getAndIncrement();
	}

}
