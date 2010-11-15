/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.SQLException;
import java.text.DecimalFormat;



/**
 * Creating printable reciept
 * @author Lars
 */



public class Reciept {
    static DecimalFormat toDes = new DecimalFormat("0.00");
    static double mva = Properties.mva;
    static boolean delivery;
    static int deliveryPrice = Properties.deliveryPrice;
    static int maxTot = Properties.maxTot;
    static double total;

    /**
     *
     * @param order the order one creates a reciept for
     * @return string with reciept
     */
    public static String toString(Order order) {
        String print = "Reciept for: " 
                + order.customer
                + "\nDish \t\tAmount "
                + "  Price \n";
        Dish dish2;
        Double localMva;

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
            if (total >= maxTot) {
                print += "\t\t\t 0.0";
            } else {
                total += deliveryPrice;
                print += "\t\t\t" + deliveryPrice;
            }
        }

        localMva = (total-total/(1+mva));
        print += "\nTotal: \t\t       " + toDes.format(total) + "\n";
        
        print += "Mva: \t\t\t "  + toDes.format(localMva) + "\n";
        return print;
    }
}


