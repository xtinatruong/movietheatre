import java.io.*;

class Voucher{
	private String id;
	private double value;
	
	public Voucher() {
		id = "";
		value = 0;
	}
	public Voucher(String id, double value) {
		this.id = id;
		this.setValue(value);;
	}
	
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public void printReceipt(String email, String name) {
		try {
			FileWriter writer = new FileWriter(email + ".txt");
			writer.write("Hello " + name + ", here is your voucher.");
			writer.write("\nVoucher ID: " + id);
			writer.write("\nValue: " + value);
			writer.close();
		 
		 } catch (IOException e) {
			 e.printStackTrace();
		}
	}

}