package system;

/**
 * Dish class for building dish-objects
 * @author Lars
 */
public class Dish {

    public int nr;		//number in menu
    public String name;		//name of dish
    public String contents;	//contents of dish
    public int price;           //price of dish

    /**
     * Constructor for creating dish-object
     *
     * @param nrIn - nr in menu
     * @param nameIn - name of dish
     * @param contentsIn - contents of dish
     * @param priceIn - price of dish
     */
    public Dish(int nrIn, String nameIn, String contentsIn, int priceIn) {
        this.nr = nrIn;
        this.name = nameIn;
        this.contents = contentsIn;
        this.price = priceIn;
    }

    /**
     * @return String with Dish parameters
     */
    @Override
    public String toString(){
        String streng = "id= " + nr + " navn= " + name + " innhold: " + contents
                + " pris: "+ price;
        return streng;
    }
}