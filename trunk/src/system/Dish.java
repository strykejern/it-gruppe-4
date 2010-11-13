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
    

    /**
     * Constructor for creating dish-object
     *
     * @param nrIn - nr in menu
     * @param nameIn - name of dish
     * @param contentsIn - contents of dish
     * @param priceIn - price of dish
     */
    public Dish(int nrIn, String nameIn, int priceIn, String contentsIn) {
        this.nr = nrIn;
        this.name = nameIn;
        this.description = contentsIn;
        this.price = priceIn;
    }

    public Dish(String dishId, String name, String price, String comment) throws IllegalArgumentException{
        try {
            nr = Integer.parseInt(dishId);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid dish-ID");
        }

        if (name == null || name.length() < 2) throw new IllegalArgumentException("Invalid dish-name");
        this.name = name;

        try {
            this.price = Integer.parseInt(price);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price for the dish");
        }

        description = comment;
    }

    /**
     *
     * @return String with Dish parameters
     */
    @Override
    public String toString() {
        DecimalFormat toDes=new DecimalFormat("0.00");
        String id = Integer.toString(nr);
        String spaces="";
        String numSpace="";
        if(name.length()<16){
            for(int i=0;i<(16-name.length()); i++){
                spaces+=" ";
            }
        }
        if(id.length()<3){
            for(int i = 0;i<(3-id.length());i++){
               numSpace+=" ";
            }
        }
        String streng = numSpace + id + " " + name + spaces +  toDes.format(price) + "\n";
        return streng;
    }

    /**
     *
     * @param amount
     * @return String with name, amount and price
     */
    public String forReciept(int amount) {
        DecimalFormat toDes=new DecimalFormat("0.00");
        String print;
        String spaces="";
        if(name.length()<16){
            for(int i=0;i<(16-name.length()); i++){
                spaces+=" ";
            }
        }
        int total = amount * this.price;
        print = this.name + spaces + "\t" + amount + "     " + 
                toDes.format(total) + "\n";
        
        return print;
    }
}
