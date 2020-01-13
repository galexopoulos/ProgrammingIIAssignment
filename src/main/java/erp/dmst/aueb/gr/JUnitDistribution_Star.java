package main.java.erp.dmst.aueb.gr;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class JUnitDistribution_Star {

	@Test
	public void Distribution_StarTest() {
		loadobjects();
		
		//Testarei to function get freqDaysOfStay 
		
		Distribution_Stars a = new Distribution_Stars();
		int[] result = a.getfreqDaysOfStay();
		int size = result.length;
		int[] actual = {5 , 6 , 1, 0 ,0 ,0};
		for(int i=0; i<size ; i++) {
			assertEquals( actual[i] , result[i]);
		}
		
		
		//Testarei to function get freqDaysOfWages 
	
		
		Distribution_Stars b = new Distribution_Stars();
		int[] result1 = b.getfreqOfWages();
		int size1 = result1.length;
		int[] actual1 = { 1,6 , 0 , 3, 0, 0, 0};
		for(int i=0; i<size1 ; i++) {
			assertEquals( actual1[i] , result1[i]);
		}

		//Testarei to function get freqDaysOfReasonsForTravel 
		
		Distribution_Stars c = new Distribution_Stars();
		int[] result2 = c.getfreqOfReasonForTravel();
		int size2 = result2.length;
		int[] actual2 = { 6,3 ,3 , 0,0 };
		for(int i=0; i<size2 ; i++) {
			assertEquals( actual2[i] , result2[i]);
		}

	}

	static  void loadobjects() {


		ReportingFinance f = new ReportingFinance(4500,5000,500); // orizoume tous logarismous tou mina
		ReportingFinance f1 = new ReportingFinance(4509,5232,443);
		ReportingFinance f2 = new ReportingFinance(5509,5222,433);
		ReportingFinance f3 = new ReportingFinance(7509,5232,443);
		ReportingFinance f4 = new ReportingFinance(4655,4553,434);

		ReportingClients c1 = new ReportingClients("alexis", "Perakis", 5, 230, 2, 5, 1, "abc@gmail.com", 2);
		ReportingClients c2 = new ReportingClients("Giorgos", "Alexopoulos", 5, 150, 1, 3, 2, "abc1@gmail.com", 2);
		ReportingClients c3 = new ReportingClients("Dimitris", "gianakopoulos", 5, 210, 3, 5, 0, "dpg7000@gmail.com", 2);
		ReportingClients c4 = new ReportingClients("Takis", "Tsoukalas", 4, 70, 1, 4, 4, "takaros@gmail.com", 2);
		ReportingClients c5 = new ReportingClients("Vaggelis", "Marinakis", 4, 90, 2, 5, 0, "Vagelas7@gmail.com", 2);
		ReportingClients c6 = new ReportingClients("Stefania", "Terzopoulou", 4, 85, 3, 6, 0, "stefterzo@gmail.com", 2);
		ReportingClients c7 = new ReportingClients("Christina", "Maragou", 3, 530, 3, 2, 3, "chrismar@gmail.com", 2);
		ReportingClients c8 = new ReportingClients("Elena", "kouts", 3, 400, 3, 2, 2, "kouts@gmail.com", 2);
		ReportingClients c9 = new ReportingClients("Katerina", "Patera", 3, 350, 3, 3, 3, "katpat@gmail.com", 2);
		ReportingClients c10 = new ReportingClients("Marina", "Pepelasi",3, 125, 2, 4, 3, "mar@gmail.com", 2);
		ReportingClients c11 = new ReportingClients("Aggelina", "Kafouni",5, 100, 1, 2, 3, "aggee@gmail.com", 2);
		ReportingClients c12 = new ReportingClients("Eleni", "Vasilopoulou",5, 550, 1, 5, 1, "elenivas@gmail.com", 2);
		ReportingClients c13 = new ReportingClients("Katerina", "Tzima",5, 630, 2, 6, 4, "kat@gmail.com", 2);
		ReportingClients c14 = new ReportingClients("Panagiotis", "Aggelopoulos",5, 540, 1, 3, 4, "Olympiacos7@gmail.com", 2);
		ReportingClients c15 = new ReportingClients("Giorgos", "Aggelopoulos", 5, 130, 3, 5, 1,"hrilikosMagas7@gmail.com", 2);
	}

}
