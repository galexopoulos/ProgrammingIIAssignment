package main.java.erp.dmst.aueb.gr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JUnitReporrtingHR {

	@Test
	public void test() {
		ReportingHR r1 = new ReportingHR(1200,"alexis","perakis");
		ReportingHR r2 = new ReportingHR(1200,"nikos","papadopoulos");
		ReportingHR r6 = new ReportingHR(1150,"alexandros","sideris");
		ReportingHR r7 = new ReportingHR(2000,"niki","pavlopoulou");
		
		double result = ReportingHR.averageWage();
		System.out.println(result); 
		double actual = 1387.5; 
		System.out.println(actual); 
		assertEquals(result, actual, result); //xriazete na sigkrinei 3 variables
											 // opote evala 2 fores to result	
	}
}
