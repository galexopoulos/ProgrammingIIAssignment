package src.main.java.gr.aueb.dmst.erp;

public enum Membership {

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


