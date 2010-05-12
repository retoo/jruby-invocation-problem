package example;

public abstract class Job implements Runnable {

	@Override
	public void run() {
		DbSession session = new DbSession();
		doJob(session);
	}

	protected abstract void doJob(DbSession session);

}
