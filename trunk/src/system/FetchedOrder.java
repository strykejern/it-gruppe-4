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

    private ArrayList<DishOrder> dishes;

    public FetchedOrder(int orderId, int customerId, String deliveryAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
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
        return orderId + "";
    }
}
