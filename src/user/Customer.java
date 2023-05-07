package user;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import bill.Bill;

public class Customer extends User implements displayBill{
	private String email;
	
	public Customer(String email){
		super();
	};
	

	@Override
	public List<Map<String, String>> displayBillMonth(int month) {
		String email = this.email;
		
		List<Map<String, String>> result = new ArrayList<>();
		
		Map<String, List<Bill>> temp = Bill.getBillList();
		Bill b = (Bill) temp.get(email).stream().filter(bill-> bill.getMonth()==month);
		
		Map<String, String> tempMp = new HashMap<String, String>();
		String str="";
		
		tempMp.put("email", email);
		tempMp.put("amount", str+b.getAmount());
		result.add(tempMp);
		
		return result;
	}

	@Override
	public Map<String, List<Bill>> displayBillAll() {
		Map<String, List<Bill>> result = new HashMap<>();
		result.put(this.email, Bill.getBillList().get(email));
		return result;
	}
	
	
}
