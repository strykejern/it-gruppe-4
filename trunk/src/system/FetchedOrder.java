package system;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anders
 */
public class FetchedOrder {
    public enum View{CHEF, DRIVER};

    private int orderId;
    private int customerId;
    private String deliveryAddress;
    public View viewedBy;
    private String timeStamp = "";

    private Customer customer;

    private ArrayList<DishOrder> dishes;

    protected FetchedOrder(FetchedOrder orig){
        this.orderId = orig.orderId;
        this.customerId = orig.customer.id;
        this.customer = orig.customer;
        this.deliveryAddress = orig.deliveryAddress;
        this.viewedBy = orig.viewedBy;
        this.timeStamp = orig.timeStamp;
        if(viewedBy == View.DRIVER){
            if(deliveryAddress==null){
                String getAddress = ""; //TODO create query
            }
        }
    }

    public FetchedOrder(int orderId, int customerId, String deliveryAddress,
            View view, String time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        this.timeStamp = time; 
        if(viewedBy == View.DRIVER){
            if(deliveryAddress==null){
                String getAddress = ""; //TODO create query
            }
        }
    }

    public FetchedOrder(int orderId, Customer customer, String deliveryAddress,
            View view, String time) {
        this.orderId = orderId;
        this.customerId = customer.id;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        this.timeStamp = time;
        if(viewedBy == View.DRIVER){
            if(deliveryAddress==null){
                String getAddress = ""; //TODO create query
            }
        }
    }

    public void setDishes(ArrayList<DishOrder> dishes) {
        this.dishes = dishes;
         if(this.viewedBy==View.CHEF){
            for(int i = 0;i<this.dishes.size();i++)
                this.dishes.get(i).setViewedByChef();
        }
    }

    public ArrayList<DishOrder> getDishes() {
        return dishes;
    }

    public boolean equals(FetchedOrder other){
        return other.orderId == orderId;
    }

    public int getId(){
        return this.orderId;
    }

    public Customer getCustomer() throws SQLException{
        if (customer == null) customer = OrderDB.getCustomerById(customerId);
        return customer;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public boolean getDelivery() throws SQLException{
        return OrderDB.getOrderDelivery(orderId);
    }

    public boolean hasCustomAddress(){
        return !(deliveryAddress == null || deliveryAddress.length() < 2);
    }

    public String getDeliveryAddress() throws SQLException {
        if (hasCustomAddress()){
            return deliveryAddress;
        }
        else {
            return getCustomer().address;
        }
    }

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
