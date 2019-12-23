import java.util.Calendar;


public class weekThread  implements Runnable{
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
