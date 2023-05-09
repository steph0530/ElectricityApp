package MainApp;

import java.io.IOException;
import java.util.*;

import user.*;
import exceptions.UserNotFoundException;
// "front-end"
public class App {

	public static void main(String[] args) throws UserNotFoundException, IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your email: ");
		
		
		String email = in.nextLine();
		
			if(User.getUserList().containsKey(email)) {
				User user = User.getUserList().get(email);
				
				if(user.getIsAdmin()==true) {
					System.out.println("Hello admin! Please enter your password: ");
					String password = in.nextLine();
					String token = user.authenticate(email, password);
					AdminView.main(new String[]{email, token});
					
				}else {
					System.out.println("Hello "+user.getName()+ " Please enter your password:");
					//System.out.println("admin? " + user.getIsAdmin());
					String password = in.nextLine();
					String token = user.authenticate(email, password);
					CustomerView.showCustomerView(email);
				}
			}
		else {
			throw new UserNotFoundException();
		}

	}

}
