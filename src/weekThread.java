import java.util.Calendar;

/**
 * The thread that does automatically the actions need to be done at the start of every week (Monday).
 * *** WON'T be used at the presentation of the project because of the difficulties at checking.
 * @author Georgios Sideris
 */
public class weekThread  implements Runnable{
	
	/**
	 * run method of the thread, 
	 * calls the methods that contain the actions need to be done at the start of every week
	 * after every execution of these actions sleeps till the next Monday (at 00:00)
	 */
	public void run() {
		while (true) {
			try{
				Hr_surface.toBeDoneEveryWeek();
				long wait = millisToNextMonday();
				Thread.sleep(wait);
			}catch (InterruptedException e) {
				System.out.println("Something wrong hapenned.");
				return;
			}
		}
	}
	
	/**
	 * calculates the time till 00:00 of the next Monday
	 * @return the time at milliseconds
	 */
	public long millisToNextMonday() {
				long sleepTime = 0;
				//method for current monday in shift
				Calendar nextMonday = Calendar.getInstance();
				int weekday = nextMonday.get(Calendar.DAY_OF_WEEK);
				if (weekday != Calendar.MONDAY) {
					int days = (Calendar.SATURDAY - weekday + 2) % 7;
					nextMonday.add(Calendar.DAY_OF_YEAR, days);
				} else {
					nextMonday.add(Calendar.DAY_OF_YEAR, 7);
				}
				nextMonday.set(Calendar.HOUR_OF_DAY, 00);
				nextMonday.set(Calendar.MINUTE, 00);
				nextMonday.set(Calendar.SECOND, 00);
				nextMonday.set(Calendar.MILLISECOND, 00);
				sleepTime = nextMonday.getTimeInMillis() - System.currentTimeMillis();//to be checked
				return sleepTime;
	}
}
