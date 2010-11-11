package system;

import java.util.ArrayList;

/*
 * Bestillingsklasse
 * H�vard
 */
public class Order {

    boolean sameAddress = false;
    String deliveryAddress = null;
    String comment = "";
    boolean dishIsFinished = false;
    boolean takeAway = false;
    Customer customer = null;
    ArrayList<DishOrder> dishOrder;

    public Order() {
        dishOrder = new ArrayList<DishOrder>();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addDish(DishOrder dish) {
        dishOrder.add(dish);
    }

    public void setDishes(ArrayList<DishOrder> dishes) {
        dishOrder = dishes;
    }

    public void setToBeDelivered(){
        takeAway = true;
    }

    public void setDeliveryAddress(String address) {
        deliveryAddress = address;
    }

    public void createOrder() throws Exception {
        if (customer == null) {
            throw new Exception("Customer is not set");
        }
        if (dishOrder.isEmpty()) {
            throw new Exception("No dish is added");
        }
        // TODO: OrderDB.createOrder()
    }
}
