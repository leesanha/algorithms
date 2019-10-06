import java.util.concurrent.Semaphore;

public class Producer_Consumer {
	public static void main(String[] arg) {
		Buffer b = new Buffer(100);
		Producer p = new Producer(b, 1000);
		Consumer c = new Consumer(b, 1000);
		p.start();
		c.start();
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Number of items in the buf is " + b.count);
	}
}

class Buffer {
	int[] buf;
	int size, count, in, out;
	Semaphore mutex, full, empty;

	Buffer(int size) {
		buf = new int[size];
		this.size = size;
		count = in = out = 0;
		mutex = new Semaphore(1);
		full = new Semaphore(0);
		empty = new Semaphore(size);
	}

	void insert(int item) {
		try {
			empty.acquire();
			// while (count == size);
			mutex.acquire();
			count++;
			buf[in] = item;
			in = (in + 1) % size;
			mutex.release();
			full.release();
		} catch (InterruptedException e) {
		}
	}

	int remove() {
		try {
			full.acquire();
			// while (count == 0);
			mutex.acquire();
			count--;
			int item = buf[out];
			out = (out + 1) % size;
			mutex.release();
			return item;
		} catch (InterruptedException e) {
			return -1; // dummy
		}
	}
}

class Producer extends Thread {
	Buffer b;
	int n;

	Producer(Buffer b, int n) {
		this.b = b;
		this.n = n;
	}

	public void run() {
		for (int i = 0; i < n; i++)
			b.insert(i);
	}
}

class Consumer extends Thread {
	Buffer b;
	int n;

	Consumer(Buffer b, int n) {
		this.b = b;
		this.n = n;
	}

	public void run() {
		int item;
		for (int i = 0; i < n; i++)
			item = b.remove();
	}
}