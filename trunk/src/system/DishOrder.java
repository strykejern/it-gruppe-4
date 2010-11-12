package system;

import java.sql.SQLException;
import java.text.DecimalFormat;

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
    private boolean viewedByChef = false;

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

    public void setViewedByChef(){
        this.viewedByChef=true;
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
        String retStr="";
        String id=Integer.toString(dish.nr);
        String spaces="";
        String name=dish.name;
        String priceSpaces=" ";
        String amount2=Integer.toString(amount);
        String amountSpaces=" ";
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
            + amount2 + " \n";
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
        return new DishOrder(dishID, amount, comments);
    }
}
