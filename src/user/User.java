package user;

import java.util.*;
import java.security.*;
import utils.HashPwd;

import bill.Bill;

public class User{
	private String name;
	private Address address;
	private String password;
	private String uuid;
	private Boolean isAdmin;
	private Boolean isAuthentictaed;
	private Bill bill;
	
	// choosing email as key
	private static Map<String, User> userList = new HashMap<String, User>() {{
		User temp= new User("admin", null, "123456");
		
		temp.uuid = null;
		temp.isAdmin = true;
		temp.isAuthentictaed = false;
		temp.bill = null;
		temp.name = "admin";
		
		put("admin@admin.com", temp);
	}};
	
	public User(){};
	
	public String getUUID() {
		return this.uuid;
	}
	
	protected User(String name, Address address, String password){
		this.name = name;
		this.address = address;
		this.password = password;
		this.uuid = HashPwd.generateUUID();
		this.isAdmin = false;
		this.isAuthentictaed = false;
		this.bill = null;
		
	}
	
	public static Map<String, User> getUserList(){
		return userList;
	}
	
	public String authenticate(String email, String password) {		
		if(password.equals(userList.get(email).password)) {
			System.out.println("logged in!");
			return HashPwd.getToken();
		}else {
			System.out.println("Wrong credentials");
			System.exit(0);
		}
		return "";
	}
	
	public void logout(String email) {
		this.isAuthentictaed = false;
	}
	
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.uuid+" "+this.address;
	}
}
