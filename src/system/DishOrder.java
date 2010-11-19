package system;

import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Sets the ID, amount and comments of dishes in an order.
 * 
 * @author Havard
 */
public class DishOrder {
    private int dishOrderId;
    private int dishID;
    private int amount;
    private String comment;
    private Dish dish;
    private boolean viewedByChef = false;

    /*
     * Creates a DishOrder object with the following parameters.
     * 
     * @param dishOrderID - nr assigned to the DishOrder in the database
     * @param dishID - nr of the dish in menu
     * @param amount - amount of this dish ordered
     * @param comments - comments for this dish
     */
    public DishOrder(int dishOrderId, int dishID, int amount, String comments) {
        this.dishOrderId = dishOrderId;
        this.dishID = dishID;
        this.amount = amount;
        this.comment = comments;

        try {
            dish = OrderDB.getDish(dishID);
        } catch (SQLException e) {
            // If we fail to retrieve the dish set it to unknown
            dish = new Dish(-1, "N/A", -1, "N/A");
        }
    }

    /* Creates a DishOrder object without the dishOrderID
     * 
     * @param dish - Dish-object
     * @param amount - amount of that object in this order
     * @param comments - comments about dish
     */
    public DishOrder(Dish dish, int amount, String comments) {
        this.dishID = dish.getDishId();
        this.amount = amount;
        this.comment = comments;
        this.dish = dish;
    }

    /*
     * sets boolean to define toStrings for specific viewer
     */
    public void setViewedByChef(){
        this.viewedByChef=true;
    }

    /* NOT USED
     * @return the ID of this DishOrders dish
     */
    public int getDishID() {
        return dishID;
    }

    /*NOT USED*/
    public int getAmount() {
        return amount;
    }

    /*NOT USED*/
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /*NOT USED*/
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /* Builds a String with DishOrder-info formatted for viewer
     *
     * @return DishOrder info, setup either for chef or waiter
     */
    @Override
    public String toString() {
        String retStr="";
        String id=Integer.toString(getDish().getDishId());
        String spaces="";
        String name=getDish().getName();
        String priceSpaces=" ";
        String amount2=Integer.toString(getAmount());
        DecimalFormat toDes = new DecimalFormat("0.00");

        if(id.length()<3){
            for(int i=0;i<(3-id.length()); i++){
                spaces+=" ";
            }
        }

        if(name.length()<25){
            for(int i=0;i<(25-name.length()); i++){
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
            + getDish().getName() + priceSpaces
            + toDes.format(getDish().getPrice()) + "kr  x "
            + amount2 + " \n";
            if(getComment()!=null && !comment.equals("")){
                retStr += "*comment*";}
        }
    
        if(viewedByChef){
            retStr = spaces + id + " "
            + getDish().getName() + priceSpaces
            + "x" + amount2 + " \n";
            if(getComment()!=null && !comment.equals("")){
                retStr += "*comment*";}
        }

        return retStr;
    }

    @Override
    public DishOrder clone(){
        return new DishOrder(
                getDishOrderId(), getDishID(), getAmount(), getComment());
    }

    /**
     * @return the dishOrderId
     */
    public int getDishOrderId() {
        return dishOrderId;
    }

    /**
     * @param dishOrderId the dishOrderId to set
     */
    public void setDishOrderId(int dishOrderId) {
        this.dishOrderId = dishOrderId;
    }

    /**
     * @return the dish
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * @param dish the dish to set
     */
    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
