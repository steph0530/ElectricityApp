package user;
import java.util.*;

import bill.Bill;

public interface displayBill {
	public List<Map<String, String>> displayBillMonth(int month);
	public Map<String, List<Bill>> displayBillAll();
}
//// standard: names