import java.util.concurrent.Semaphore;

class BankAccount {
	static final int MAX = 100; // 입출금 회수
	int balance;
	Semaphore sem;
	Semaphore wsem;
	Semaphore dsem;

	BankAccount() {
		sem = new Semaphore(1);// 초기값 = 1
		wsem = new Semaphore(1);// 초기값 = 1
		dsem = new Semaphore(1);// 초기값 = 1
	}

	void deposit(int amount) { // 입금
		try {
			sem.acquire(); // 진입 전: acquire()
		} catch (InterruptedException e) {
		}
		int temp = balance + amount;
		System.out.print("+");
		balance = temp;
		sem.release(); // 나온 후: release()
		wsem.release();
		try {
			dsem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void withdraw(int amount) { // 출금
		try {
			wsem.acquire();
//			sem.acquire(); // 진입 전: acquire()
		} catch (InterruptedException e) {
		}
		int temp = balance - amount;
		System.out.print("-");
		balance = temp;
//		sem.release(); // 나온 후: release()
		dsem.release();
	}

	int getBalance() {
		return balance;
	}

	public static void main(String[] args) throws InterruptedException {
		// 은행계좌를 만들고
		BankAccount b = new BankAccount();
		// 부모 쓰레드와
		Parent p = new Parent(b, MAX);
		// 자식 쓰레드를 만든 후
		Child c = new Child(b, MAX);
		// 각각 실행시킨다.
		p.start();
		c.start();
		p.join();// 부모와 자식 쓰레드가
		c.join();// 각각 종료하기를 기다린다.
		System.out.println();
		System.out.println("Final balance = " + b.getBalance());// 최종 잔액 출력
	}
}

class Parent extends Thread {
	BankAccount b;
	int count;

	Parent(BankAccount b, int count) {
		this.b = b;
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++)
			b.deposit(1);
	}
}

class Child extends Thread {
	BankAccount b;
	int count;

	Child(BankAccount b, int count) {
		this.b = b;
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++)
			b.withdraw(1);
	}
}
