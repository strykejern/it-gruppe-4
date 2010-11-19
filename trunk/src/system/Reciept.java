/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

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
        boolean delivery = order.isToBeDelivered();
        int deliveryPrice = Properties.getDeliveryPrice();
        int maxTot = Properties.getFreeDeliveryLimit();
        double total = 0;

        String print = "Reciept for: \n"
                      + order.getCustomer().getId()
                      + "\n\nDish                     SAmount x Price    Total\n\n";
        Double localMva;

        for (DishOrder dish : order.getDishOrder()) {
            print += dish.getDish().forReciept(dish.getAmount());
            total += (double)( dish.getDish().getPrice() * dish.getAmount());//
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
        print += "\nTotal inkl. mva: " +
                toDes.format(total) + "\n";
        
        print += "Mva av totalen: " +
                toDes.format(localMva) + "\n";
        print += "\n   Takk for en hyggelig handel \n\n";

        
        return print;
    }
}


