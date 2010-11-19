package system;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class retrieves an order and customer information from the database. It
 * is used by the chef and the driver classes.
 * @author Anders
 */
public class FetchedOrder {
    public enum View{CHEF, DRIVER};

    private boolean made=false;
    private boolean done=false;
    private int orderId;
    private int customerId;
    private String deliveryAddress;
    private View viewedBy;
    private String timeStamp = "";

    private Customer customer;

    private ArrayList<DishOrder> dishes;

    protected FetchedOrder(FetchedOrder orig){
        this.orderId = orig.orderId;
        this.customerId = orig.customer.getId();
        this.customer = orig.customer;
        this.deliveryAddress = orig.deliveryAddress;
        this.viewedBy = orig.viewedBy;
        this.timeStamp = orig.timeStamp;
        this.made = orig.made;
        this.done = orig.done;
    }

    public FetchedOrder(int orderId, int customerId, String deliveryAddress,
            View view, String time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        this.timeStamp = time;
    }

    public FetchedOrder(int orderId, Customer customer, String deliveryAddress,
            View view, String time) {
        this.orderId = orderId;
        this.customerId = customer.getId();
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        this.timeStamp = time;
    }
    /**
     * Recieves an ArrayList with DishOrders, then puts it in the dishes variable.
     * If the object is to be viewed by the chef, the enum View is set to CHEF for
     * every DishOrder in the ArrayList.
     * @param dishes
     */
    public void setDishes(ArrayList<DishOrder> dishes) {
        this.dishes = dishes;
         if(this.viewedBy==View.CHEF){
            for(int i = 0;i<this.dishes.size();i++)
                this.dishes.get(i).setViewedByChef();
        }
    }
    /**
     * Returns ArrayList dishes.
     * @return
     */
    public ArrayList<DishOrder> getDishes() {
        return dishes;
    }

    /**
     * checks to see if a FetchedOrder.orderId equals this FetchedOrder.orderId.
     * @param other
     * @return
     */
    public boolean equals(FetchedOrder other) {
        return other.orderId == orderId;
    }
    /**
     * Return orderId.
     * @return
     */
    public int getId(){
        return this.orderId;
    }
    /**
     * Retrieves a Customer object from the database using the customerId,
     * if the customer variable is empty.
     * @return
     * @throws SQLException
     */
    public Customer getCustomer() throws SQLException{
        if (customer == null) customer = OrderDB.getCustomerById(customerId);
        return customer;
    }
    /**
     * Gets the timeStamp.
     * @return
     */
    public String getTimeStamp(){
        return timeStamp;
    }

    public boolean getDelivery() throws SQLException{
        return OrderDB.getOrderDelivery(orderId);
    }
    /**
     * checks to see if the deliveryaddress is custom i.e. Not the customers home
     * address.
     * @return
     */
    public boolean hasCustomAddress(){
        return !(deliveryAddress == null || deliveryAddress.length() < 2);
    }
    /**
     * Gets the delivery address for the customer.
     * @return
     * @throws SQLException
     */
    public String getDeliveryAddress() throws SQLException {
        if (hasCustomAddress()){
            return deliveryAddress;
        }
        else {
            return getCustomer().getAddress();
        }
    }
    /**
     * Retrieves the reciept from the database using orderID.
     * @return
     * @throws SQLException
     */
    public String getReciept() throws SQLException{
        return OrderDB.getReciept(orderId);
    }

    /**
     * update order if chef has completed order
     */
    public void isMade() throws SQLException{
        if(OrderDB.checkMade(this.orderId)){
           this.made = true;
        }
    }

    /**
     * update order if customer has recieved order
     */
    public void isDone() throws SQLException{
        if(OrderDB.checkDone(this.orderId)){
            this.done=true;
        }
        
    }
    /**
     * Checks to se wether View is set to CHEF or to DRIVER and then returns a
     * String-value appropriate for the two different users.
     * @return
     */
    @Override
    public String toString(){
        String FetchedOrderPrint = "";
        String spaces=" ";
        if(this.orderId<99){
            spaces+=" ";
        }
        if(this.orderId<10){
            spaces+=" ";
        }

        if(viewedBy == View.CHEF){
            FetchedOrderPrint+=spaces + this.orderId + " ordered at " +
                    timeStamp ;
            }
        if(viewedBy == View.DRIVER){
            FetchedOrderPrint+= "Order:"+ spaces + this.orderId + " to " +
                    this.customerId + " at " + this.deliveryAddress;
        }
        

        return FetchedOrderPrint;
    }
}
