package user;

import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.*;
import utils.HashPwd;

import bill.Bill;

public class User implements Serializable{
	private String name;
	private Address address;
	transient private String password;
	private String uuid;
	private Boolean isAdmin;
	private Boolean isAuthentictaed;
	
	// choosing email as key: questionable. better use uuid.
	private static Map<String, User> userList = new HashMap<String, User>() {{
		User temp= new User("admin", null, "123456");
		
		temp.uuid = null;
		temp.isAdmin = true;
		temp.isAuthentictaed = false;
		temp.name = "admin";
		
		put("admin@admin.com", temp);
	}};
	
	public User(){};

	protected User(String name, Address address, String password){
		this.name = name;
		this.address = address;
		this.password = password;
		this.uuid = HashPwd.generateUUID();
		this.isAdmin = false;
		this.isAuthentictaed = false;
		
	}

	public String authenticate(String email, String password) {		
		if(password.equals(userList.get(email).password)) {
			System.out.println("logged in!");
			userList.get(email).isAuthentictaed = true;
			return HashPwd.getToken();
		}else {
			System.out.println("Wrong credentials");
			System.exit(0);
		}
		return "";
	}
	
	public static Map<String, User> getUserList(){
		return userList;
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
	
	public String getUUID() {
		return this.uuid;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String toString() {
		return this.name+" "+this.uuid+" "+this.address;
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		System.out.println("write obj invoked");
		oos.defaultWriteObject();
		oos.writeObject(getName());
		oos.writeObject(getUUID());
		oos.writeBoolean(getIsAdmin());
		oos.writeObject(getAddress());
		
	}
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		System.out.println("read obj invoked");
		ois.defaultReadObject();
		setName((String)ois.readObject());
		
	}

}
