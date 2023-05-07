package exceptions;

public class UserNotFoundException extends Exception{
	public String toString() {
		return "User doesn't exist.";
	}
}
