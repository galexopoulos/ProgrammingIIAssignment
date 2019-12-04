package main.java.erp.dmst.aueb.gr;
import java.util.Arrays;
import java.util.Scanner;

public class Goals {
	
	static double [] goals = new double [12]; 
	static double [][] years = new double [12][4];
	public static int ans;
	
	public static void setArray() {
	}
	
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===============================MENU======================================"
				+ "\n If you want to compare current year with the results from 3 years ago press: 0 "
				+ "\n If you want to compare current year with the results from 2 years ago press: 1 "
				+ "\n If you want to compare current year with the previous year press:            2 "
				+ "\n If you want to compare current year with current year's goals press:         3 "
				+ "\n If you want to exit press:                                                  -1 ");
		boolean flag = true;
		ans = 456789;
		
		do {
			try {
				ans = sc.nextInt();
				flag = false;
				if(ans == -1){
					break;
				}
			}catch(Exception e) {
				System.err.println("WRONG INPUT");
			}
		}while(flag);
		return ans;
	}
	
	public static void comparison(int ans, int month) { // ans einai to year pou thelei na sigrinei
	//	double [] year_3 = new double [12]; // 3 xronia prin
	//	double [] year_2 = new double [12]; // 2 xronia prin
	//	double [] year_1 = new double [12]; // 1 xrono prin
	//	double [] year_cur = new double [12]; // current year
	//	
	//	try {
	//		for (int i = 0; i <= 12;i++) {
	//			year_3[i] = years[i][0];
	//			year_2[i] = years[i][1];
	//			year_1[i] = years[i][2];
	//			year_cur[i] = years[i][3];
	//		}
	//	}catch(Exception e) {
	//		System.err.println("Error.");
	//	}
		try {
			if (ans == 0) {
				if (years[month][3] > years[month][ans]) {
					System.out.printf("Profits of current year are %f MORE than 3 years ago \n",years[month][3] - years[month][ans]);
				}else if (years[month][3] < years[month][ans]) {
					System.out.printf("Profits of current year are %f LESS than 3 years ago \n",years[month][ans] - years[month][3]);
				}else System.out.println("Profits of current year are equal to those 3 years ago" + years[month][ans]);
				
			}else if (ans == 1) {
				if (years[month][3] > years[month][ans]) {
					System.out.printf("Profits of current year are %f MORE than 2 years ago \n",years[month][3] - years[month][ans]);
				}else if (years[month][3] < years[month][ans]) {
					System.out.printf("Profits of current year are %f LESS than 2 years ago \n",years[month][ans] - years[month][3]);
				}else System.out.println("Profits of current year are equal to those 2 years ago" + years[month][ans]);
				
			}else if (ans == 2) {
				if (years[month][3] > years[month][ans]) {
					System.out.printf("Profits of current year are %f MORE than 1 year ago \n",years[month][3] - years[month][ans]);
				}else if (years[month][3] < years[month][ans]) {
					System.out.printf("Profits of current year are %f LESS than 1 year ago \n",years[month][ans] - years[month][3]);
				}else System.out.println("Profits of current year are equal to those 1 year ago" + years[month][ans]);
				
			}else {
				if (years[month][3] > goals[month]) {
					System.out.printf("Profits of current year are %f MORE than goal \n",years[month][3] - goals[month]);
				}else if (years[month][3] < goals[month]) {
					System.out.printf("Profits of current year are %f LESS goal \n",goals[month] - years[month][3]);
				}else System.out.println("Profits of current year are equal to goal" + years[month][ans]);
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("ERROR.");
		}catch(Exception e) {
			System.out.println("RunTimeError.");
		}
		
	}
	
	public static void loadgoals() { 
		goals[0] = 2000; //IANOUARIOS
		goals[1] = 2500; //FEVROUARIOS
		goals[2] = 2550; //MARTIOS
		goals[3] = 3500; //APRILIOS
		goals[4] = 4500; //MAIOS
		goals[5] = 7500; //IOUNIOS
		goals[6] = 9800; //IOULIOS
		goals[7] = 10000; //AVGOUSTOS
		goals[8] = 6500; //SEPTEMVRIOS
		goals[9] = 2500; //OKTOVRIOS
		goals[10] = 1000; //NOEMVRIOS
		goals[11] = 900; //DEKEMVRIOS
		
	}
}
