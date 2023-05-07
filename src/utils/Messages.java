package utils;

import java.util.*;

/// in reality will be a UI component. 
/// is this extra? otherwise will be too much repetitive code...
public class Messages {
	public static void askNextStep() {
		Scanner in = new Scanner(System.in);
		System.out.println("Success! Back to main menu? yes/no");
		String back = in.nextLine();
		if(back.equals("no"))
			System.exit(0);
	}
	
	public static void failedAskNextStep() {
		Scanner in = new Scanner(System.in);
		System.out.println("Action couldn't be done. Back to main menu? yes/no");
		String back = in.nextLine();
		if(back.equals("no")) {
			System.out.println("bye");
			System.exit(0);
		}
			
	}
	
	public static void emptyResultMessage() {
		System.out.println("Empty result!");
	}
	
	public static void logoutMessage() {
		System.out.println("bye");
		System.exit(0);
	}
}
