package model;

/**
 * A simple bean to hold data
 */
public class Users {
	private String userName, password, nickName, description, photo;//users "schema"
	
	
/*
 * ctor
 */
	public Users(String name, String psswrd, String ncknm, String description, String photo) {
		this.userName = name;
		this.password = psswrd;
		this.nickName = ncknm;
		this.description = description;
		this.photo=photo;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getnickName() {
		return nickName;
	}

	public String getDescription() {
		return description;
	}

	public String getPhoto() {
		return photo;
	}
	
	
}
