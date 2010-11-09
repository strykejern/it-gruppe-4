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
    public DecimalFormat Format = new DecimalFormat("00");

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

    /**
     * @return String with Dish parameters
     */
    @Override
    public String toString() {
        DecimalFormat toDes=new DecimalFormat("0.00");
        String spaces="";
        if(name.length()<16){
            for(int i=0;i<(16-name.length()); i++){
                spaces+=" ";
            }
        }
        String streng = Format.format(nr) + " " + name + spaces +  toDes.format(price);
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
