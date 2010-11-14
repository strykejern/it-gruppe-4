package system;

/**
 *Extends Dish to give an additional toString for Dish-info in Admin menu
 * @author Anders
 */
public class AdminDish extends Dish {

    public AdminDish(Dish dish){
        super(dish);
    }

    /**
     * Shows Dish-details and definitions of info
     * @return Corectly formatted String of Dish-info
     */
    @Override
    public String toString(){
        return "Id: " + this.nr
                + " Name: " + this.name
                + " Price: " + this.price + " kr.";
    }
}
