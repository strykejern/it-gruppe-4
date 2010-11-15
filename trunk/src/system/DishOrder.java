package system;

import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Sets the ID, amount and comments of dishes in an order.
 * 
 * @author H�vard
 */
public class DishOrder {

    public int dishOrderId;
    public int dishID;
    public int amount;
    public String comments;
    public Dish dish;
    private boolean viewedByChef = false;

    /**
     *
     * @param dishID - nr of the dish in menu
     * @param amount - amount of this dish ordered
     * @param comments - comments for this dish
     */
    public DishOrder(int dishOrderId, int dishID, int amount, String comments) {
        this.dishOrderId = dishOrderId;
        this.dishID = dishID;
        this.amount = amount;
        this.comments = comments;

        try {
            dish = OrderDB.getDish(dishID);
        } catch (SQLException e) {
            // If we fail to retrieve the dish set it to unknown
            dish = new Dish(-1, "N/A", -1, "N/A");
        }
    }

    /**
     *
     * @param dish - Dish-object
     * @param amount - amount of that object in this order
     * @param comments - comments about dish
     */
    public DishOrder(Dish dish, int amount, String comments) {
        this.dishID = dish.nr;
        this.amount = amount;
        this.comments = comments;
        this.dish = dish;
    }

    /**
     * sets boolean to define toStrings for specific viewer
     */
    public void setViewedByChef(){
        this.viewedByChef=true;
    }

    /**
     *
     * @return the ID of this DishOrders dish
     */
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

    /** Builds a String with Dishorder-info formatted for viewer
     *
     * @return DishOrder info, setup either for chef or waiter
     */
    @Override
    public String toString() {
        String retStr="";
        String id=Integer.toString(dish.nr);
        String spaces="";
        String name=dish.name;
        String priceSpaces=" ";
        String amount2=Integer.toString(amount);
        DecimalFormat toDes = new DecimalFormat("0.00");

        if(id.length()<3){
            for(int i=0;i<(3-id.length()); i++){
                spaces+=" ";
            }
        }

        if(name.length()<16){
            for(int i=0;i<(16-name.length()); i++){
                priceSpaces+=" ";
            }
        }

        if(amount2.length()<3){
            for(int i=0;i<(3-name.length()); i++){
                spaces+=" ";
            }
        }

        if(!viewedByChef){
            retStr = spaces + id + " "
            + dish.name + priceSpaces
            + toDes.format(dish.price) + "kr  x "
            + amount2 + " \n";
            if(comments!=null && !comments.equals("")){
                retStr += "*comment*";}
        }
    
        if(viewedByChef){
            retStr = spaces + id + " "
            + dish.name + priceSpaces
            + "x" + amount2 + " \n";
            if(comments!=null && !comments.equals("")){
                retStr += "*comment*";}
        }

        return retStr;
    }
    
    public String getComment(){
        return this.comments;
    }

    @Override
    public DishOrder clone(){
        return new DishOrder(dishOrderId, dishID, amount, comments);
    }
}
