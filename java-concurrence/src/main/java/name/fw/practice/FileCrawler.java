package name.fw.practice;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class FileCrawler {

	private static final int N_CONSUMERS = 10;
	private static final int BOUND = 10;

	public static void startIndexing(File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingDeque<File>(BOUND);
		FileFilter filter = new FileFilter() {

			@Override public boolean accept(File pathname) {
				return true;
			}
		};
		for (File root : roots)
			new Thread(new DiskCrawler(queue, filter, root)).start();
		for (int i = 0; i < N_CONSUMERS; i++)
			new Thread(new Indexer(queue)).start();
	}
}

class DiskCrawler implements Runnable {

	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;

	public DiskCrawler(BlockingQueue<File> queue, FileFilter filter, File root) {
		this.fileQueue = queue;
		this.fileFilter = filter;
		this.root = root;
	}

	@Override public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory())
					crawl(entry);
				else if (!alreadyIndexed(entry))
					fileQueue.put(entry);
			}
		}
	}

	private boolean alreadyIndexed(File entry) {
		return false;
	}
}

class Indexer implements Runnable {

	private final BlockingQueue<File> queue;

	public Indexer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override public void run() {
		while (true) {
			try {
				indexFile(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void indexFile(File take) {}
}
