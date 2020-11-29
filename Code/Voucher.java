class Voucher{
	private int id;
	private boolean valid;
	
	public Voucher(int id, boolean valid) {
		this.id = id;
		this.valid = valid;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}