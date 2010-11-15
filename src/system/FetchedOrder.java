package system;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anders
 */
public class FetchedOrder {
    private int orderId;
    private int customerId;
    private String deliveryAddress;
    public enum View{CHEF, DRIVER};
    public View viewedBy;
    private String timeStamp = "";

    private Customer customer;

    private ArrayList<DishOrder> dishes;

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
