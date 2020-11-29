public class FinancialInstitute 
{
    private String name;
    public FinancialInstitute(String name) 
    {
        this.name = name;
    }

    public double verfiyPayementMethod(int cardNo, int CVV, String expDate, String nameOnCard)
    {
        return AccountSystem.verifyPayment(cardNo, CVV, expDate, nameOnCard);
    }

    // Removes money from user's account based on "amount"
    public void chargeUser(int cardNo, int CVV, String expDate, String nameOnCard, int amount)
    {
        // implement when balance is added to card
    }

    // Returns money to user's account based on "amount"
    public void refundUser(int cardNo, int CVV, String expDate, String nameOnCard, int amount)
    {
        // implement when balance is added to card
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