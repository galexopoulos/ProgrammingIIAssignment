package main.java.erp.dmst.aueb.gr;
public class testaki {

	public static void main(String[] args)  {
		loadobjects();
		//ShareHolders.getShareHoldersMenu();
		Reporting.Menu();
		//ShareHolders.getMenuflag();
		//try {
		//	ReportingFinance.getMenu();
		//} catch (Exception e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	}
		//Distribution_Stars.getDistributionOfDays();
		//System.out.println();
		//Distribution_Stars.getDistributionOfReasons();
		//System.out.println();
		//Distribution_Stars.getDistributionOfSatisfaction();
		//System.out.println();
		//Distribution_Stars.getDistributionOfWages();
	}
	
	static void loadobjects() {
		ReportingHR r1 = new ReportingHR(1200,"alexis","perakis");
		ReportingHR r2 = new ReportingHR(1200,"nikos","papadopoulos");
		ReportingHR r3 = new ReportingHR(1200,"giorgos","moatsos");
		ReportingHR r4 = new ReportingHR(1200,"babis","markou");
		ReportingHR r5 = new ReportingHR(1150,"dimitris","tsoutrelis");
		ReportingHR r6 = new ReportingHR(1150,"alexandros","sideris");
		ReportingHR r7 = new ReportingHR(2000,"niki","pavlopoulou");
		ReportingHR r8 = new ReportingHR(2000,"sofia","kafouni");
		ReportingHR r9 = new ReportingHR(2100,"stefania","terzopoulou");
		ReportingHR r10 = new ReportingHR(780,"katerina","patera");
		
		ReportingFinance f = new ReportingFinance(4500,5000,500); // orizoume tous logarismous tou mina
		ReportingFinance f1 = new ReportingFinance(4509,5232,443);
		ReportingFinance f2 = new ReportingFinance(5509,5222,433);
		ReportingFinance f3 = new ReportingFinance(7509,5232,443);
		ReportingFinance f4 = new ReportingFinance(4655,4553,434);
		
		ReportingClients c1 = new ReportingClients("alexis","perakis",5,340,0,17,19,2);
		ReportingClients c2 = new ReportingClients("nikos","antonopoulos",3,50,2,11,12,0);
		ReportingClients c3 = new ReportingClients("dimitris","dimitropoulos",5,540,2,7,11,2);
		ReportingClients c4 = new ReportingClients("notis","lapatas",5,240,2,23,27,1);
		ReportingClients c5 = new ReportingClients("christina","kardami",5,3300,2,20,29,1);
		ReportingClients c6 = new ReportingClients("kostantina","epithetopoulou",4,300,1,15,17,1);
		ReportingClients c7 = new ReportingClients("onomas","spetsiopoulos",4,290,0,17,19,2);
		ReportingClients c8 = new ReportingClients("dimitris","gianakopoulos",5,470,0,17,19,0);
		ReportingClients c9 = new ReportingClients("christina","katsaba",5,335,2,8,14,0);
		ReportingClients c10 = new ReportingClients("takis","tsoukalas",5,535,2,8,14,0);
		ReportingClients c11 = new ReportingClients("Vaggelis","Marinakis",5,475,2,8,14,0);
		ReportingClients c12 = new ReportingClients("Dimitris","Giannakopoulos",5,455,2,8,14,0);
		
		ShareHolders sh1 = new ShareHolders("alexis","alexis12345");
		ShareHolders sh2 = new ShareHolders("olympiakos","eisaistomialokatimagiko7");
		ShareHolders sh3 = new ShareHolders("username","passss12345");
		
	}

}
