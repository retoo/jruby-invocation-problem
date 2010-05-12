package example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BackgroundJob extends Thread {

	private static final Job SHUTDOWN_JOB = new Job() {
		@Override
		protected void doJob(DbSession session) {}
	};

	private final BlockingQueue<Job> jobQueue;

	public BackgroundJob() {
		this.jobQueue = new ArrayBlockingQueue<Job>(100);
	}

	@Override
	public void run() {
		System.out.println("started");
		while (true) {
			try {
				Job job = jobQueue.take();

				if (job == SHUTDOWN_JOB) {
					System.out.println("shutting down");
					return;
				}

				Thread t = new Thread(job);
				t.start();
				t.join();
			} catch (InterruptedException e) {
				this.interrupt();
				return;
			}
		}
	}

	public void register(AbstractJobFactory factory) {
		Job job = factory.createJob();
		this.jobQueue.add(job);
	}

	public void shutdown() {
		this.jobQueue.add(SHUTDOWN_JOB);
	}
}
