import java.io.Serializable;

public class Supplier implements Serializable{
	private String Fullname;
	private int phonenumber;
	private String mail;

	public Supplier(String Fullname, int phonenumber, String mail) {
		Fullname = this.Fullname;
		phonenumber = this.phonenumber;
		mail = this.Fullname;
	
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMail() {
		return mail;
	}
}