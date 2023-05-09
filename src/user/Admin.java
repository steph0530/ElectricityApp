package user;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.*;

import bill.Bill;
import utils.HashPwd;
import exceptions.UserNotFoundException;
import exceptions.UserAlreadyExistsException;

public class Admin extends User implements displayBill{
	public Admin(){
		super();
	}
	
	public Boolean generateBill(String email, int month, double usage) throws UserNotFoundException, IOException{
		// user not found exception
		if(User.getUserList().containsKey(email)) {
			Bill bill = new Bill(usage, month, LocalDate.now(), User.getUserList().get(email).getUUID());
			
			if(Bill.getBillList().containsKey(email)) Bill.getBillList().get(email).add(bill);
			else {
				List<Bill> list = new ArrayList<>();
				list.add(bill);
				Bill.getBillList().put(email, list);
			}
			serializeBill(bill);
			return true;
		}else {
			throw new UserNotFoundException();
		}
			
	}
	@Override
	public List<Map<String, String>> displayBillMonth(int month) {
		// {{email, amount}}
		List<Map<String, String>> result = new ArrayList<>();
		Map<String, List<Bill>> bills = Bill.getBillList();
		
		for(Entry<String, List<Bill>> entry: bills.entrySet()) {
			for(Bill bill: entry.getValue()) {
				if(bill.getMonth() == month) {
					Map<String, String> temp = new HashMap<>();
					temp.put("email", entry.getKey());
					String str="";
					temp.put("amount", str+bill.getAmount());
					result.add(temp);
				}
			}
		}
		return result;
	}
	@Override
	public Map<String, List<Bill>> displayBillAll() {
		return Bill.getBillList();
	}

	
	public Boolean addUser(String name, String email, String password, Address address) throws UserAlreadyExistsException, IOException{
		if(!User.getUserList().containsKey(email)) {
			User newCustomer = new User(name, address, password);
	 		User.getUserList().put(email, newCustomer);
	 		serializeUser(newCustomer);
	 		return true;
		}else
			throw new UserAlreadyExistsException();
	}
	
	public void setHighRate(double rate) {
		Bill.setHighRate(rate);
	}
	
	public void setMidRate(double rate) {
		Bill.setMidRate(rate);
	}

	public void setLowRate(double rate) {
		Bill.setLowRate(rate);
	}
	
	public List<Map.Entry<String, User>> getCustomers(){
		return User.getUserList().entrySet().stream().filter(user-> user.getValue().getIsAdmin()==false).collect(Collectors.toList());
	}
	
	private static void serializeUser(User user) throws IOException {
		FileOutputStream fos = new FileOutputStream("Users.dat", true);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(user);
		System.out.println("user serialized");
		
	}
	
	private static void serializeBill(Bill bill) throws IOException {
		FileOutputStream fos = new FileOutputStream("Bills.dat", true);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(bill);
		System.out.println("bill serialized");
	}
}
