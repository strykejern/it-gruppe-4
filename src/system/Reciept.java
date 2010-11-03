/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.SQLException;

/**
 * Creating printable reciept
 * @author Lars
 */
public class Reciept {

    static double mva = 0.25; //TODO: create other class with statics
    static boolean delivery;
    static int deliveryPrice = 50;
    static int maxtot = 500;
    static double total;

    /**
     *
     * @param order the order one creates a reciept for
     * @return string with reciept
     */
    public String toReciept(Order order) {

        String print = "Reciept for: " + order.customer + "\nDish \t\t Amount "
                + "\t\t Price \n";
        Dish dish2;
        for (DishOrder dish : order.dishOrder) {
            try {
                dish2 = OrderDB.getDish(dish.dishID);
                print += dish2.forReciept(dish.amount);
                total = (double) dish2.price * dish.amount;//
            } catch (SQLException e) {
            }
        }
        if (delivery) {
            print += "Delivery: ";
            if (total >= maxtot) {
                print += "\t\t\t 0.0";
            } else {
                total += deliveryPrice;
                print += "\t\t\t" + deliveryPrice;
            }
        } 
        print += "Total: \t\t\t" + total + "\n";
        
        print += "Mva: \t\t\t"  + total/(1+mva) + "\n";
        return print;
    }
}
//

