package src.main.java.gr.aueb.dmst.erp;
import java.util.Calendar;

/**
 * The thread that does automatically the actions need to be done at the start of every month.
 * *** WON'T be used at the presentation of the project because of the difficulties at checking.
 * @author Georgios Sideris
 *
 */ 
public class monthThread implements Runnable{
	
	/**
	 * run method of the thread, 
	 * calls the methods that contain the actions need to be done at the start of every month
	 * after every execution of these actions sleeps till the 1st day of the next month
	 */
	public void run() {
		while (true) {
			try{
				Hr_surface.toBeDoneEveryMonth();
				long wait = millisToNextMonth();
				Thread.sleep(wait);
			}catch (InterruptedException e) {
				System.out.println("Something wrong hapenned.");
				return;
			}
		}
	}
	
	

	/**
	 * calculates the time till the 1st day of the next month
	 * @return the time at milliseconds
	 */
	public long millisToNextMonth() {
		long sleepTime = 0;
		Calendar nextMonth = Calendar.getInstance();
		if (nextMonth.get(Calendar.MONTH) != 11) {
			nextMonth.add(Calendar.MONTH,1);
		}else {
			nextMonth.set(Calendar.MONTH, 0);
			nextMonth.add(Calendar.YEAR, 1);
		}
		nextMonth.set(Calendar.DAY_OF_MONTH,1);
		nextMonth.set(Calendar.HOUR_OF_DAY, 00);
		nextMonth.set(Calendar.MINUTE, 00);
		nextMonth.set(Calendar.SECOND, 00);
		nextMonth.set(Calendar.MILLISECOND, 00);
		sleepTime = nextMonth.getTimeInMillis() - System.currentTimeMillis();//to be checked
		return sleepTime;
	}
}