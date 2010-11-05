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
     * returns a menuobject (arrayList)
     * @return
     */
    public ArrayList<Dish> getMenu(){
        return menu;

    }
    /**
     * adds dish to menu arraylist
     *
     * @param fromDB Dish-object from DB
     */
    public void addDish(Dish fromDB){

        menu.add(fromDB);
    }

    public Dish getDish(int nr){
        for(Dish dish : menu){
            if(dish.nr == nr){
                return dish;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String output = "Nr\tName\t    Price\n";
        for(int i = 0; i<menu.size(); i++){
            output += menu.get(i);
        }
        return output;
    }



}


