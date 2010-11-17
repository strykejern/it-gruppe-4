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
        String print = "Reciept for: \n"
                + order.customer.firstName + " " + order.customer.lastName
                + "\n\nDish                 Amount         Price \n\n";
        Double localMva;

        for (DishOrder dish : order.dishOrder) {
            print += dish.dish.forReciept(dish.amount);
            total += (double)( dish.dish.price * dish.amount);//
        }

        if (delivery) {
            print += "Delivery: ";
            if (total >= maxTot) {
                print += "\t\t\t 0.00";
            } else {
                total += deliveryPrice;
                print += "\t\t\t" + deliveryPrice;
            }
        }

        localMva = total*(mva/100.0);
        print += "\nTotal inkl. mva:                       " +
                toDes.format(total) + "\n";
        
        print += "Mva:                                     " +
                toDes.format(localMva) + "\n";
        print += "\n   Takk for en hyggelig handel \n\n";

        
        return print;
    }
}


