package system;

/**
 *
 * @author Lars
 */
public class Dish {

    public int nr;		//number in menu
    public String name;		//name of dish
    public String contents;	//contents of dish
    public int price;           //price of dish

    //creates new Dish-object
    public Dish(int nrIn, String nameIn, String contentsIn, int priceIn) {
        this.nr = nrIn;
        this.name = nameIn;
        this.contents = contentsIn;
        this.price = priceIn;
    }
}