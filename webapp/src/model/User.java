package model;

/**
 * A simple bean to hold data
 */
public class User {
	private String userName, password, nickName, description, photo;//users "schema"
	
	
/*
 * constructor
 */
	public User(String name, String psswrd, String ncknm, String description, String photo) {
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
	
	public void printUser()
	{
		System.out.printf("username: %s\n", this.userName);
		System.out.printf("password: %s\n", this.password);
		System.out.printf("nickname: %s\n", this.nickName);
		System.out.printf("description: %s\n", this.description);
		System.out.printf("photo: %s\n", this.photo);
	}
}
