/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

import system.*;

/**
 *
 * @author Lars
 */
public class menutest {
    public static void main (String [] args){
        Menu meny = new Menu();
        meny.addDish(new Dish(1, "navn", "gift", 150));
        System.out.println("menu with one dish and 4 parameters \n"  + meny);
    }

}
