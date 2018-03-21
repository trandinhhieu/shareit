package model.bean;

public class User {
	private int uid;
	private String uname;
	private String ufullname;
	private int role;
	private String upass;
	private String email;
	private int active;
	private String avatar;
	
	public User() {
	}
	
	
	
	public User(int uid, String uname, String ufullname, int role, String upass, String email, int active,
			String avatar) {
		this.uid = uid;
		this.uname = uname;
		this.ufullname = ufullname;
		this.role = role;
		this.upass = upass;
		this.email = email;
		this.active = active;
		this.avatar = avatar;
	}
	
	
	public User(int uid, String ufullname, String upass, String email, String avatar) {
		this.uid = uid;
		this.ufullname = ufullname;
		this.upass = upass;
		this.email = email;
		this.avatar = avatar;
	}



	public User(int uid, String ufullname, String upass, String email) {
		this.uid = uid;
		this.ufullname = ufullname;
		this.upass = upass;
		this.email = email;
	}
	
	public User(String uname, String ufullname, int role, String upass, String email) {
		this.uname = uname;
		this.ufullname = ufullname;
		this.role = role;
		this.upass = upass;
		this.email = email;
	}

	public User(String uname, String upass) {
		this.uname = uname;
		this.upass = upass;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUfullname() {
		return ufullname;
	}

	public void setUfullname(String ufullname) {
		this.ufullname = ufullname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", ufullname=" + ufullname + ", role=" + role + ", upass="
				+ upass + ", email=" + email + ", active=" + active + ", avatar=" + avatar + "]";
	}

}
