package system;

import java.*;
import java.util.ArrayList;

/* Class for editing menu-DB 
 * 
 * @author Lars
 */
public class Menu {
    private ArrayList<Dish> menu;
    
    /**
     * constructor
     *
     */
    public Menu() {
         menu = new ArrayList<Dish>();
         

    }

    /**
     * adds dish to menu arraylist
     *
     * @param fromDB Dish-object from DB
     */
    public void addDish(Dish fromDB){

        menu.add(fromDB);
    }

    @Override
    public String toString(){
        String output = "Nr\tName\t\t\tprice\n";
        for(int i = 0; i<menu.size(); i++){
            output += menu.get(i);
        }
        return output;
    }



}


