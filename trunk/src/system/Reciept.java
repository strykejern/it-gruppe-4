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
    static double mva = Properties.getMva();
    static boolean delivery;
    static int deliveryPrice = Properties.getDeliveryPrice();
    static int maxTot = Properties.getFreeDeliveryLimit();
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
        Double localMva;

        for (DishOrder dish : order.dishOrder) {
            print += dish.dish.forReciept(dish.amount);
            total += (double)( dish.dish.price * dish.amount);//
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

        localMva = total*(mva/100.0);
        print += "\nTotal: \t\t       " + toDes.format(total) + "\n";
        
        print += "Mva: \t\t\t "  + toDes.format(localMva) + "\n";
        return print;
    }
}


