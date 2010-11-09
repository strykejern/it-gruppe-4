package system;

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
    private String timeStamp = "00.00.00";

    private ArrayList<DishOrder> dishes;

    public FetchedOrder(int orderId, int customerId, String deliveryAddress,
            View view) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        if(viewedBy == View.DRIVER){
            if(deliveryAddress==null){
                String getAddress = ""; //TODO create query
            }
        }
    }

    public void setDishes(ArrayList<DishOrder> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<DishOrder> getDishes() {
        return dishes;
    }

    public boolean equals(FetchedOrder other){
        return other.orderId == orderId;
    }

    @Override
    public String toString(){
        String FetchedOrderPrint = "Order: ";
        if(viewedBy == View.CHEF){
            FetchedOrderPrint+=this.orderId + timeStamp ;
            }
        if(viewedBy == View.DRIVER){
            FetchedOrderPrint+= this.orderId + " to " +
                    this.customerId + " at " + this.deliveryAddress;


        }
        return FetchedOrderPrint;
    }
}
