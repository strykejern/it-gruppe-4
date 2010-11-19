package system;

import java.util.ArrayList;

/**
 * This class contains the methods that the WaiterForm uses to create an order.
 * 
 * @author Havard
 */
public class Order {
    private String deliveryAddress;
    private boolean delivery;
    private Customer customer;
    private ArrayList<DishOrder> dishOrder;
    private String reciept;

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

    /*Is called if the order is to be delivered*/
    public void setToBeDelivered(){
        setToBeDelivered(true);
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

    /**
     * @return the deliveryAddress
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @return the takeAway
     */
    public boolean isToBeDelivered() {
        return delivery;
    }

    /**
     * @param takeAway the takeAway to set
     */
    public void setToBeDelivered(boolean takeAway) {
        this.delivery = takeAway;
    }

    /**
     * @return the dishOrder
     */
    public ArrayList<DishOrder> getDishOrder() {
        return dishOrder;
    }

    /**
     * @param dishOrder the dishOrder to set
     */
    public void setDishes(ArrayList<DishOrder> dishOrder) {
        this.dishOrder = dishOrder;
    }

    /**
     * @return the reciept
     */
    public String getReciept() {
        return reciept;
    }
}
