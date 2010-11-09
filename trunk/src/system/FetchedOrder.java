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
    private String timeStamp = "";

    private ArrayList<DishOrder> dishes;

    public FetchedOrder(int orderId, int customerId, String deliveryAddress,
            View view) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.viewedBy = view;
        //this.timeStamp; TODO: get correct variable from DB
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

    @Override
    public String toString(){
        String FetchedOrderPrint = "";
        if(viewedBy == View.CHEF){
            FetchedOrderPrint+=this.orderId + " ordered at " + timeStamp ;
            }
        if(viewedBy == View.DRIVER){
            FetchedOrderPrint+= "Order: " + this.orderId + " to " +
                    this.customerId + " at " + this.deliveryAddress;


        }
        return FetchedOrderPrint;
    }
}
