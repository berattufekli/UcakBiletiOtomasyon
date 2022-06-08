package application;

public class adminler_tableview {
	int adminID;
	String username;
	String password;
	
	public adminler_tableview(int adminID, String username, String password) {
		this.adminID = adminID;
		this.username = username;
		this.password = password;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

