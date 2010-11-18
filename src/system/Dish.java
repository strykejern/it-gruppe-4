package system;

import java.text.DecimalFormat;

/**
 * Dish class for building dish-objects
 * @author Lars
 */
public class Dish {


    public int nr;		//number in menu
    public String name;		//name of dish
    public String description;	//contents of dish
    public int price;           //price of dish

    protected Dish(Dish dish){
        if (dish.nr < 0){
            throw new IllegalArgumentException("Illegal dish ID");
        }

        this.nr = dish.nr;
        this.name = dish.name;
        this.description = dish.description;
        this.price = dish.price;
    }

    /**
     * Constructor for creating dish-object
     *
     * @param nrIn - nr in menu
     * @param nameIn - name of dish
     * @param contentsIn - contents of dish
     * @param priceIn - price of dish
     */
    public Dish(int nr, String name, int price, String description) {
        this.nr = nr;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish(String dishId, String name, String price, String comment)
            throws IllegalArgumentException{
        try {
            nr = Integer.parseInt(dishId);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid dish-ID");
        }

        if (name == null || name.length() < 2)
            throw new IllegalArgumentException("Invalid dish-name");
        this.name = name;

        try {
            this.price = Integer.parseInt(price);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price for the dish");
        }

        description = comment;
    }

    /** Method builds a String with dish parameters and formats it to fit well
     * in a list of several dishes
     *
     * @return String with Dish parameters
     */
    @Override
    public String toString() {
        DecimalFormat toDes=new DecimalFormat("0.00");
        String id = Integer.toString(nr);
        String spaces="";
        String numSpace="";
        if(name.length()<30){
            for(int i=0;i<(30-name.length()); i++){
                spaces+=" ";
            }
        }
        if(id.length()<3){
            for(int i = 0;i<(3-id.length());i++){
               numSpace+=" ";
            }
        }
        return numSpace + id + " " + name + spaces + toDes.format(price) + "\n"
                + this.description;
    }

    /** Returns a String of parameters in Dish-object. Formated for a print
     * of a reciept
     *
     * @param amount
     * @return String with name, amount and price
     */
    public String forReciept(int amount) {
        DecimalFormat toDes=new DecimalFormat("0.00");
        String spaces=" ";
        if(name.length()<30){
            for(int i=0;i<(30-name.length()); i++){
                spaces+=" ";
            }
        }
        int total = amount * this.price;

        return this.name + spaces + amount + " x " + this.price + " = " +
                toDes.format(total) + "\n";
    }
}
