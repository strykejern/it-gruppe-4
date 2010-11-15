package system;

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
     * 
     * @return returns a menuobject (arrayList)
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

    /**
     *
     * @param nr of dish in menu
     * @return requested dish
     */
    public Dish getDish(int nr){
        for(Dish dish : menu){
            if(dish.nr == nr){
                return dish;
            }
        }
        return null;
    }

    /**
     *
     * @return String formated as a menu
     */
    @Override
    public String toString(){
        String output = "Nr\tName\t    Price\n";
        for(int i = 0; i<menu.size(); i++){
            output += menu.get(i);
        }
        return output;
    }



}


