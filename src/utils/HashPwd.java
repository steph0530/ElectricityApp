package utils;
import java.security.*;
import java.util.Base64;
import java.util.UUID;


public class HashPwd {
	/*
	 to do: BCrypt
	public static String getHashPassword(String plainPwd) {
		
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashed;
	}
	*/
	public static String getToken() {
		SecureRandom secureRandom = new SecureRandom();
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();
		
		byte[] randomBytes = new byte[24];
		    secureRandom.nextBytes(randomBytes);
		    return base64Encoder.encodeToString(randomBytes);
	}
	
	public static Boolean verifyPassword(String UserhashedPwd, String hashedPwd) {
		/* to do: BCrypt
		if(BCrypt.checkpw(UserhashedPwd, hashedPwd)) return true;
		return false;*/
		return UserhashedPwd==hashedPwd;
	}
	
	public static String generateUUID() {
		SecureRandom secureRandom = new SecureRandom(); 
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();
		
		return UUID.randomUUID().toString().replace("-", "");
		
	}

}
