package system;
/*
 * Bestillingsklasse
 * Håvard
 */

public class Order {
	
	boolean sameAddress = false;
	String	deliveryAddress = "";
	String	comment = "";
	boolean dishIsFinished = false;
	boolean takeAway = false;
	
	/*
	 * Overloaded constructors taking in different parameters
	 * Labeled as comments because of errors
	 */
	
	//Takes the customer's name and the number of the dish
	public Order(String lastName, String firstName, int nr, boolean sameAddress, boolean takeAway, String comment){
		setDeliveryAddress();
		//nr	= Dish.nr;
		//lastName	= Customer.lastName;
		//firstName = Customer.firstName;
	}
	
	//Takes the customer's phone number and the number of the dish
	public Order(int phone, int nr, boolean sameAddress, boolean takeAway, String comment){
		setDeliveryAddress();
		//nr	= Dish.nr;
		//phone = Customer.phone;
	}
	
	//Takes the customer's address and the number of the dish
	public Order(String address, int nr, boolean takeAway, String comment){
		//nr	= Dish.nr;
		//address = Customer.address;
	}
	
	//Takes the customer's name and the name of the dish
	public Order(String lastName, String firstName, String dishName, boolean sameAddress, boolean takeAway, String comment){
		setDeliveryAddress();
		//dishName	= Dish.name;
		//lastName	= Customer.lastName;
		//firstName = Customer.firstName;
	}
	
	//Takes the customer's phone number and the number of the dish
	public Order(int phone, String dishName, boolean sameAddress, boolean takeAway, String comment){
		setDeliveryAddress();
		//dishName	= Dish.name;
		//phone = Customer.phone;
	}
	
	//Takes the customer's address and the number of the dish
	public Order(String address, String dishName, boolean takeAway, String comment){
		//dishName	= Dish.name;
		//address = Customer.address;
	}
	
	
	public boolean setCustomer(){
		return false;
	}
	
	public boolean addDish(){
		return false;
	}
	
	
	public void setDeliveryAddress(){
		if(sameAddress){
	//		deliveryAddress = Customer.address;
		}
	}
}
