package user;

public class Address {
	private String aptNumber;
	private String street;
	private String city;
	private String country;
	
	public Address(String apt, String street, String city, String country) {
		this.aptNumber = apt;
		this.street = street;
		this.city = city;
		this.country = country;
	}
	
	@Override 
	public String toString() {
		return this.aptNumber+","+this.street+","+this.city+","+this.country;
	}

	
}
