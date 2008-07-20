/**
 * 
 */
package wecker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Yousry Abdallah
 * 
 */
public class TimeRun implements Runnable {

	static private TimeRun timeRun;

	boolean enabled = false;

	long startTime;

	long intervall;

	List<TimeListener> timeListeners;

	private void inform() {
		for (TimeListener timeListener : timeListeners) {
			timeListener.timeStatus();
		}
	}

	private void wakeUp() {
		for (TimeListener timeListener : timeListeners) {
			timeListener.timeisUp();
		}
	}

	public void addListener(TimeListener timeListener) {
		timeListeners.add(timeListener);
	}

	public TimeRun() {
		super();
		TimeRun.timeRun = this;
		timeListeners = new ArrayList<TimeListener>();

	}

	public void startNewIntervall() {
		startTime = Calendar.getInstance().getTimeInMillis();
		enabled = true;
	}

	public long getIntervall() {
		return intervall;
	}

	synchronized public void setIntervall(long intervall) {
		this.intervall = intervall;
	}

	synchronized public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		Calendar cal;

		while (!Thread.interrupted()) {

			cal = Calendar.getInstance();

			if (enabled)
				inform();

			if (cal.getTimeInMillis() - startTime > intervall && enabled) {
				wakeUp();
				enabled = false;

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}

		}
	}

	public static TimeRun getTimeRun() {
		return timeRun;
	}

}
