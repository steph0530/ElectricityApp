package bill;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class Bill implements Serializable{
	private double amount;
	private int month;
	private String customerID;
	private LocalDate date;

	private static double highRate = 1.5;
	private static double midRate = 0.8;
	private static double lowRate = 0.5;
	
	// email, {bill}
	private static Map<String, List<Bill>> billList = new HashMap<>();
	
	public Bill(double unit, int month, LocalDate date, String id){
		this.amount = getAmount(unit);
		this.month = month;
		this.date = date;
		this.customerID = id;
	}
	
	public static void setHighRate(double rate) {
		highRate = rate;
	}
	
	public static void setMidRate(double rate) {
		midRate = rate;
	}
	
	public static void setLowRate(double rate) {
		lowRate = rate;
	}
	
	@Override
	public String toString() {
		return this.month+": "+this.amount+ ","+this.date;
	}
	
	public static Map<String, List<Bill>> getBillList(){
		return billList;
	}
	
	public String getUUID() {
		return this.customerID;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public double getAmount() {return this.amount;};
	public double getAmount(double unit) {
		double amount = 0;
		while(unit > 0) {
			if(unit > 200) {
				amount+= (unit-200)*1.5;
				unit = 200;
			}else if(unit >= 101 && unit<=200) {
				amount+= unit*0.8;
				unit = 0;
			}
			else {
				amount+= unit*0.5;
				unit =0;
			}

		}
		return amount;
	}
	private void writeObject(ObjectOutputStream oos) throws IOException {
		System.out.println("bill writeobj invoked");
		oos.defaultWriteObject();
		oos.writeDouble(getAmount());
		oos.writeObject(getUUID());
		oos.writeObject(this.getDate());
		oos.writeInt(getMonth());
	}
	
}
