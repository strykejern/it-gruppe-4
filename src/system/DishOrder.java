package system;

import java.sql.SQLException;

/**
 * Sets the ID, amount and comments of dishes in an order.
 * 
 * @author H�vard
 */
public class DishOrder {

    public int dishID;
    public int amount;
    public String comments;
    public Dish dish;

    public DishOrder(int dishID, int amount, String comments) {
        this.dishID = dishID;
        this.amount = amount;
        this.comments = comments;

        try {
            dish = OrderDB.getDish(dishID);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public DishOrder(Dish dish, int amount, String comments) {
        this.dishID = dish.nr;
        this.amount = amount;
        this.comments = comments;

        this.dish = dish;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        String retStr = dish.nr + "  "
                + dish.name + "  "
                + dish.price + "kr  x"
                + amount + " \n"
                + comments;

        return retStr;
    }
}
