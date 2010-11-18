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


/**Constructor for building a receipt-object
 *
 * @author Lars
 */
public class Reciept {

    /**
     *
     * @param order
     * @return
     */
    public static String toString(Order order) {
        DecimalFormat toDes = new DecimalFormat("0.00");
        double mva = Properties.getMva();
        boolean delivery = order.takeAway;
        int deliveryPrice = Properties.getDeliveryPrice();
        int maxTot = Properties.getFreeDeliveryLimit();
        double total = 0;

        String print = "Reciept for: \n"
                      + order.customer.id
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


