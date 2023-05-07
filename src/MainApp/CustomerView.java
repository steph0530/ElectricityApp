package MainApp;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bill.Bill;
import exceptions.UserNotFoundException;
import user.*;
import utils.Constants;
import utils.Messages;

public class CustomerView {

	public static void main(String[] args) {
	}
	public static void showCustomerView(String email) throws UserNotFoundException {
		Customer user = new Customer(email);
		Scanner in = new Scanner(System.in);
		
		int chose = 0;
		
		while(true) {
			for(int i=0; i<Constants.customerOptions.length; i++) {
				System.out.println(i+1+" : "+Constants.customerOptions[i]);
			}
			
			chose = Integer.parseInt(in.nextLine());
			
			if(chose == 1) {
				System.out.println("Select a month: ");
				int month = Integer.parseInt(in.nextLine());
				
				List<Map<String, String>> result = user.displayBillMonth(month);
				if(result.size()>0) {
					System.out.println(result.get(0).entrySet());
					Messages.askNextStep();
				}
				else {
					Messages.emptyResultMessage();
					Messages.failedAskNextStep();
				}
			}
			else if(chose == 2) {
				System.out.println("Here are your bills since this year: ");
				Map<String, List<Bill>> bills = user.displayBillAll();
				if(!bills.containsKey(email) || bills.get(email).size() == 0) {
					Messages.emptyResultMessage();
					Messages.failedAskNextStep();
				}
				for(Bill bill:bills.get(email)) {
					System.out.println((bill));
					Messages.askNextStep();
				}
			}
			else if(chose == 3) {
				user.logout(email);
				Messages.logoutMessage();
			}
			else if(chose == 4) {
				App.main(null);
			}
			else {
				System.out.println("Please enter 1 - 4");
				Messages.failedAskNextStep();
			}
		}
	}

}
