public class FinancialInstitute 
{
    private String name;
    private AccountSystem model;
    
    public FinancialInstitute(String name, AccountSystem model) 
    {
        this.name = name;
        this.model = model;
    }

    public void verfiyPayementMethod(String cardNo, int CVV, String expDate, String nameOnCard) throws Exception
    {
        if(model.verifyPayment(cardNo, CVV, expDate, nameOnCard) == -1) {
        	throw new Exception();
        }
        		
    }

    // Removes money from user's account based on "amount"
    public void chargeUser(String cardNo, int CVV, String expDate, String nameOnCard, double amount) throws Exception
    {
    	
    	 if(!model.transaction(cardNo, CVV, expDate, nameOnCard, -amount)) {
         	throw new Exception();
         }
    }

    // Returns money to user's account based on "amount"
    public void refundUser(String cardNo, int CVV, String expDate, String nameOnCard, double amount) throws Exception
    {
    	 if(!model.transaction(cardNo, CVV, expDate, nameOnCard, amount)) {
         	throw new Exception();
         }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}