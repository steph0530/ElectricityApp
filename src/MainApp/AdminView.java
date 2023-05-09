package MainApp;

import user.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

import bill.Bill;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import utils.Constants;
import utils.Messages;

public class AdminView {

	public static void main(String[] args) throws UserNotFoundException, IOException {
		Admin user = new Admin();
		System.out.println("You are on admin page:) Please choose an action: ");
		Scanner in = new Scanner(System.in);
		
		int chose = 0;
		
		while(true) {
			for(int i=0; i<Constants.adminOptions.length; i++) 
				System.out.println(i+1+" : "+Constants.adminOptions[i]);
			
			chose = Integer.parseInt(in.nextLine());
			if(chose == 1) {
				List<Entry<String, User>> customers = user.getCustomers();
				
				for(Entry<String, User> customer: customers) {
					System.out.print("email: "+customer.getKey()+" ");
					System.out.println(customer.getValue());
				}
				
				Messages.askNextStep();
			}
			
			else if(chose == 2) {
				System.out.println("Customer's name: ");
				String name = in.nextLine();
				
				System.out.println("Customer's email: ");
				String email = in.nextLine();
				
				System.out.println("Customer's password: ");
				String password = in.nextLine();
				
				System.out.println("Customer's address: (format: aptNumber/ Street/ City/ Country) with new line for each.");
				String apt = in.nextLine();
				String street = in.nextLine();
				String city = in.nextLine();
				String country = in.nextLine();
				
				try {
					if(user.addUser(name,email,password,new Address(apt,street,city,country))) 
						Messages.askNextStep();
				} catch (UserAlreadyExistsException e) {
					e.printStackTrace();
					Messages.failedAskNextStep();
				}
				
			}else if(chose == 3) {
				System.out.println("Genrate bill for customer: ");
				String email = in.nextLine();
				System.out.println("Month: ");
				int month = Integer.parseInt(in.nextLine());
				System.out.println("Usage: ");
				double usage = Double.parseDouble(in.nextLine());
				
				try {
					if(user.generateBill(email, month, usage)) 
						Messages.askNextStep();
				} catch (UserNotFoundException e) {
					e.printStackTrace();
					Messages.failedAskNextStep();
				}
				
			}else if(chose == 4) {
				System.out.println("Please input month: ");
				int month = Integer.parseInt(in.nextLine());
				List<Map<String, String>> bills = user.displayBillMonth(month);
				
				System.out.println("Bills for month" + month + ": ");
				for(Map<String, String> entry: bills) {
					System.out.print("Customer: "+entry.get("email"));
					System.out.print("Amount: "+entry.get("amount"));
				}
				
				Messages.askNextStep();
				
			}else if(chose == 5) {
				System.out.println("Display all bills:");
				
				Map<String, List<Bill>> bills = user.displayBillAll();
				for(Entry<String, List<Bill>> bill:bills.entrySet()) {
					System.out.println("user: "+bill.getKey());
					for(Bill b: bill.getValue()) {
						System.out.println(b);
					}
				}
				
				Messages.askNextStep();
				
			}else if(chose == 6) {
				System.out.println("Configure rate for (1) high rate, (2) mid rate, (3) low rate: ");
				int rateType = Integer.parseInt(in.nextLine());
				System.out.println("Please enter rate: ");
				double newRate = Double.parseDouble(in.nextLine());
				if(rateType == 1) 
					user.setHighRate(newRate);
				else if(rateType == 2) 
					user.setMidRate(newRate);
				else if(rateType == 3)
					user.setLowRate(newRate);
				
				Messages.askNextStep();
				
			}else if(chose == 7) {
				user.logout("admin@admin.com");
				Messages.logoutMessage();
			}
			else if(chose == 8) {
				App.main(null);
			}
			else {
				System.out.println("Please enter 1 - 7");
				Messages.failedAskNextStep();
			}
		}

	}

}
