
class RegisteredUser extends User {
    private String email;
    private String password;
    private String cardNo;
    private int cvv;
    private String expDate;
    private String nameOnCard;

    public RegisteredUser(String name, String city, String id, String email, String password, String cardNo, int cvv,
            String expDate, String nameOnCard) {
        super(name, city, id);

        this.setEmail(email);
        this.setPassword(password);
        this.setCardNo(cardNo);
        this.setCvv(cvv);
        this.setExpDate(expDate);
        this.setNameOnCard(nameOnCard);
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}