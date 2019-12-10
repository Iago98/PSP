import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	AtomicInteger a = new AtomicInteger();// Al usar un atomic integer se prohibe la modificacion del mismo a la vez por lo que es valor siempre es real.
	
	private int count = 0;

    public int getCountInteger() {
        return count ;
    }

   
    public synchronized void incrementCountInteger() {//si utilizamos el synchronized en el incrementCounInteger los hilos no accederan a la vez por lo que el valor siempre será el real, este caso es igual que utilizar el atomicInteger
        ++count;
    }

	public AtomicInteger getA() {
		return a;
	}

	public void incrementCount() {
		a.getAndIncrement();
	}

}
