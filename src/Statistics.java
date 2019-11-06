import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Statistics {

	private static double esodaKratieon = 0; 
	private static double eksoda = 0;
	private static double esodaEkdiloseon = 0;
	
	public void setEsoda(Double Esoda) {//from Booking class
		esodaKratieon += Booking.getChecks;	
											//ftiaxnoume antikimeno tipou markou gia na aproume ta esoda ekdiloseon
	}
	
	public void setEksoda(Double eksoda) {
	//pairnw kostos ergasias apo markou
	}
	
	public double [] eksodaEmployee(double [] misthoi) {
		//tha dinei pinaka me tous misthous
	}
	
	public static double getEsoda() {
		return esodaKratieon + esodaEkdiloseon ;
	}
	
	public double getEksoda() {
		//markou
	}
	
	public String getMenu() {
		return  "---MENU--- \n"+getDate()+
				"1.";
	}
	
	public String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
	public void Profit_losses() {
		if (getEsoda() > getEksoda()) {
			System.out.println("Hotel has profit: "+ (getEsoda()-getEksoda()));
		}else System.out.println("Hotel has losses: "+(getEsoda()- getEksoda()));
	}
}
