package system;

import java.util.ArrayList;

/**
 * This class contains the methods that the WaiterForm uses to create an order.
 * 
 * @author Havard
 */
public class Order {

    boolean sameAddress = false;
    String deliveryAddress = null;
    String comment = "";
    boolean dishIsFinished = false;
    boolean takeAway = false;
    Customer customer = null;
    ArrayList<DishOrder> dishOrder;
    String reciept;

    /* Initiates the arraylist of DishOrder objects */
    public Order() {
        dishOrder = new ArrayList<DishOrder>();
    }

    /*Sets the customer variable as the customer parameter*/
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*Gets the customer*/
    public Customer getCustomer() {
        return customer;
    }

    /*NOT USED*/
    public void addDish(DishOrder dish) {
        dishOrder.add(dish);
    }

    /*Sets the DishOrder list as the parameter*/
    public void setDishes(ArrayList<DishOrder> dishes) {
        dishOrder = dishes;
    }

    /*Is called if the order is to be delivered*/
    public void setToBeDelivered(){
        takeAway = true;
    }

    /*Is called if the customer wants to have the pizza delivered to an address that is different
     * from the one they are registered with.
     */
    public void setDeliveryAddress(String address) {
        deliveryAddress = address;
    }

    /*Sets the reciept*/
    public void setReciept(String reciept){
        this.reciept = reciept;
    }

    /*NOT USED*/
    public void createOrder() throws Exception {
        if (customer == null) {
            throw new Exception("Customer is not set");
        }
        if (dishOrder.isEmpty()) {
            throw new Exception("No dish is added");
        }
        
    }
}
