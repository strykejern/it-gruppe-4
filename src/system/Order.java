package system;

import java.util.ArrayList;

/*
 * Bestillingsklasse
 * Håvard
 */

public class Order {
	
	boolean sameAddress = false;
	String	deliveryAddress = null;
	String	comment = "";
	boolean dishIsFinished = false;
	boolean takeAway = false;
	
	Object customer = null;
	ArrayList<Dish> dishes; 
	
	public Order(){
		dishes = new ArrayList<Dish>();
	}

	public void setCustomer(Object customer){ // TODO: Replace Object with Customer
		// TODO: Set customer variable
	}
	
	public void addDish(Dish dish){
		dishes.add(dish);
	}
	
	public void setDeliveryAddress(String address){
		deliveryAddress = address;
	}
	
	public void createOrder() throws Exception{
		if(customer == null){
			throw new Exception("Customer is not set");
		}
		if(dishes.isEmpty()){
			throw new Exception("No dish is added");
		}
			// TODO: OrderDB.createOrder()
	}
}
