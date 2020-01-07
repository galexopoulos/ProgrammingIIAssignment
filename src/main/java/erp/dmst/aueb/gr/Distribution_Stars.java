package main.java.erp.dmst.aueb.gr;

public class Distribution_Stars {
	
	public static int [] getfreqAges() {
		int [] agesFreq = {0,0,0,0,0};
		try {	
			for( ReportingHR i : ReportingHR.hr) {
				if(i.getAge() < 25) {
					agesFreq[0]++;
				}else if(i.getAge() < 35) {
					agesFreq[1]++;
				}else if(i.getAge() < 45) {
					agesFreq[2]++;
				}else if(i.getAge() < 55) {
					agesFreq[3]++;
				}else {
					agesFreq[4]++;
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error.");
		}
		return agesFreq;
	}
	
	public static void getDistributionOfAges() {
		System.out.println("Ages Distribution: ");
		int[] freqs =  getfreqAges();
		for(int i = 0; i < 5; i++) {
			if (i == 0) {
				System.out.print("18 - 24: " );
				for(int stars =0 ; stars < freqs[0] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 1) {
				System.out.print("25 - 34: " );
				for(int stars =0 ; stars < freqs[1] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 2) {
				System.out.print("35 - 44: " );
				for(int stars =0 ; stars < freqs[2] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 3) {
				System.out.print("45 - 54: " );
				for(int stars =0 ; stars < freqs[3] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 4) {
				System.out.print("55 - 65: " );
				for(int stars =0 ; stars < freqs[4] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
	}
	
	//------------------------------------------------------------------------
	
	public static int [] getfreqDaysOfStay() {
		int [] daysFreq = {0,0,0,0,0,0};
		try {	
			for( ReportingClients i : ReportingClients.coustomerBase) {
				if(i.getCheckout() - i.getCheckin() <= 3) {
					daysFreq [0]++;
				}else if(i.getCheckout() - i.getCheckin() <= 6) {
					daysFreq [1]++;
				}else if(i.getCheckout() - i.getCheckin() <= 9) {
					daysFreq [2]++;
				}else if(i.getCheckout() - i.getCheckin() <= 12) {
					daysFreq [3]++;
				}else if(i.getCheckout() - i.getCheckin() <= 15){
					daysFreq [4]++;
				}else {
					daysFreq [5]++;
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error.");
		}
		return daysFreq;
	}
	
	public static void getDistributionOfDays() {
		System.out.println("Days Distribution: ");
		int[] freqs =  getfreqDaysOfStay();
		for(int i = 0; i < 6; i++) {
			if (i == 0) {
				System.out.print("1 - 3: " );
				for(int stars =0 ; stars < freqs[0] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 1) {
				System.out.print("4 - 6: " );
				for(int stars =0 ; stars < freqs[1] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 2) {
				System.out.print("7 - 9: " );
				for(int stars =0 ; stars < freqs[2] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 3) {
				System.out.print("10 - 12: " );
				for(int stars =0 ; stars < freqs[3] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 4) {
				System.out.print("13 - 15: " );
				for(int stars =0 ; stars < freqs[4] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else { System.out.print("15 - : " );
				for(int stars =0 ; stars < freqs[5] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
	}
	
	//------------------------------------------------------------------------
	
	public static int [] getfreqOfWages() {
		int [] wagesFreq = {0,0,0,0,0,0,0};
		try {	
			for( ReportingHR i : ReportingHR.hr) {
				if(i.getWage() <= 1000) {
					wagesFreq[0]++;
				}else if(i.getWage() <= 1400) {
					wagesFreq[1]++;
				}else if(i.getWage() <= 1800) {
					wagesFreq[2]++;
				}else if(i.getWage() <= 2200) {
					wagesFreq[3]++;
				}else if(i.getWage() <= 2600){
					wagesFreq[4]++;
				}else if(i.getWage() <= 3000){
					wagesFreq[5]++;
				}else {
					wagesFreq[6]++;
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error.");
		}
		return wagesFreq;
	}
	
	public static void getDistributionOfWages() {
		System.out.println("Wages Distribution: ");
		int[] freqs =  getfreqOfWages();
		for(int i = 0; i < 7; i++) {
			if (i == 0) {
				System.out.print("600 - 1000: " );
				for(int stars =0 ; stars < freqs[0] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 1) {
				System.out.print("1001 - 1400: " );
				for(int stars =0 ; stars < freqs[1] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 2) {
				System.out.print("1401 - 1800: " );
				for(int stars =0 ; stars < freqs[2] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 3) {
				System.out.print("1801 - 2200: " );
				for(int stars =0 ; stars < freqs[3] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 4) {
				System.out.print("2201 - 2600: " );
				for(int stars =0 ; stars < freqs[4] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if(i == 5){ 
				System.out.print("2601 - 3000: " );
				for(int stars =0 ; stars < freqs[5] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else System.out.print("3000 - :");
				for(int stars =0 ; stars < freqs[6] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
		}
	}
	
	//------------------------------------------------------------------------
	
	public static int [] getfreqOfSat() {
		int [] satFreq = {0,0,0,0,0,0};
		try {	
			for( ReportingClients i : ReportingClients.coustomerBase) {
				if(i.getSatisfuction() == 0 ) {
					satFreq [0]++;
				}else if(i.getSatisfuction() == 1) {
					satFreq [1]++;
				}else if(i.getSatisfuction() == 2) {
					satFreq [2]++;
				}else if(i.getSatisfuction() == 3) {
					satFreq [3]++;
				}else if(i.getSatisfuction() == 4){
					satFreq [4]++;
				}else {
					satFreq [5]++;
				}
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Error.");
		}
		return satFreq;
	}
	
	public static void getDistributionOfSatisfaction() {
		System.out.println("Satisfaction Distribution: ");
		int[] freqs =  getfreqOfSat();
		for(int i = 0; i < 6; i++) {
			if (i == 0) {
				System.out.print("0: " );
				for(int stars =0 ; stars < freqs[0] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 1) {
				System.out.print("1: " );
				for(int stars =0 ; stars < freqs[1] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 2) {
				System.out.print("2: " );
				for(int stars =0 ; stars < freqs[2] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 3) {
				System.out.print("3: " );
				for(int stars =0 ; stars < freqs[3] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else if (i == 4) {
				System.out.print("4: " );
				for(int stars =0 ; stars < freqs[4] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}else { System.out.print("5: " );
				for(int stars =0 ; stars < freqs[5] / 2 ; stars++ ) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
	}
}
