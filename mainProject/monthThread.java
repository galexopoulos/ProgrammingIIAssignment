import java.util.Calendar;

public class monthThread implements Runnable{
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