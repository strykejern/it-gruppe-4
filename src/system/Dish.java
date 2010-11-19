package system;

import java.text.DecimalFormat;

/**
 * Dish class for building dish-objects
 * @author Lars
 */
public class Dish {
    private int dishId;           //price of dish
    private String name;
    private String description;
    private int price;

    protected Dish(Dish dish){
        if (dish.dishId < 0){
            throw new IllegalArgumentException("Illegal dish ID");
        }
        if (dish.price < 0){
            throw new IllegalArgumentException("Illegal dish price");
        }

        this.dishId = dish.dishId;
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
        this.dishId = nr;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish(String dishId, String name, String price, String comment)
            throws IllegalArgumentException{
        try {
            this.dishId = Integer.parseInt(dishId);
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
        String id = Integer.toString(getDishId());
        String spaces="";
        String numSpace="";
        if(getName().length()<30){
            for(int i=0;i<(30-getName().length()); i++){
                spaces+=" ";
            }
        }
        if(id.length()<3){
            for(int i = 0;i<(3-id.length());i++){
               numSpace+=" ";
            }
        }
        return numSpace + id + " " + name + spaces + toDes.format(price) + " info: "
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
        if(getName().length()<30){
            for(int i=0;i<(30-getName().length()); i++){
                spaces+=" ";
            }
        }
        int total = amount * this.getPrice();

        return this.getName() + spaces + amount + " x " + this.getPrice() + " = " +
                toDes.format(total) + "\n";
    }

    /**
     * @return the dishId
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }
}
