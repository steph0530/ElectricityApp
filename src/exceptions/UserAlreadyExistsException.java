package exceptions;

public class UserAlreadyExistsException extends Exception{
	public String toString() {
		return "User alreay exist.";
	}
}
