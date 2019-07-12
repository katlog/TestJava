package name.fw.thread.synchronizor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @moudle: TestExchanger
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月30日 下午5:42:16 
 * 	Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换。
 *        它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。这两个线程通过exchange方法交换数据，
 *        如果第一个线程先执行exchange方法，它会一直等待第二个线程也执行exchange，
 *        当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 */
@SuppressWarnings("javadoc")
public class TestExchanger {

	final static Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

	public static void main(String[] args) {
		new Producer("Producer", exchanger).start();
		new Consumer("Consumer", exchanger).start();
	}

	static class Producer extends Thread {

		private Exchanger<List<String>> exchanger;

		public Producer(String threadName, Exchanger<List<String>> exchanger) {
			super(threadName);
			this.exchanger = exchanger;
		}

		@Override public void run() {
			List<String> products = new ArrayList<String>();
			for (int i = 0; i < 100; i++) {
				products.add("product " + i);
			}
			try {
				List<String> results = exchanger.exchange(products);
				System.out.println("get results from consumer");
				for (String s : results) {
					System.out.println(s);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Consumer extends Thread {

		private Exchanger<List<String>> exchanger;

		public Consumer(String threadName, Exchanger<List<String>> exchanger) {
			super(threadName);
			this.exchanger = exchanger;
		}

		@Override public void run() {
			List<String> products = new ArrayList<String>();
			for (int i = 0; i < 100; i++) {
				products.add("consumed " + i);
			}
			try {
				List<String> results = exchanger.exchange(products);
				System.out.println("get products from produces");
				for (String s : results) {
					System.out.println(s);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}