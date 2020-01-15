package src.main.java.gr.aueb.dmst.erp;

public enum Membership {
	/** This 'enum' class is used for the account customers in CustomerEntry class 
	 * and defines the discount percentage for every stage of the loyalty card.
	 * @author Georgios Alkis Alexopoulos.
	 */

	DIAMOND{
        @Override
        public double beneffitsPay() {
            return 0.2;
        }
       
    },
    GOLD{
        @Override
        public double beneffitsPay() {
            return 0.15;
        }
    },
    SILVER{
        @Override
        public double beneffitsPay() {
            return 0.1;
        }
        },
    BRONZE{
    	@Override
    	public double beneffitsPay() {
    		return 0.05 ; 
    	}
    
    }
    ;

    public abstract double beneffitsPay();
}


