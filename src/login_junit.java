import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class login_junit {

	@Test
	void test() {
	 new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
	 int output=Employee.login(0,"Petreasismydaddy");
	 assertEquals(0,output);
	}
	@Test
	void test1() {
		 new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		 int output=Employee.login(0, "Petsreasismydaddy");
		 assertEquals(-1,output);
	}
}
