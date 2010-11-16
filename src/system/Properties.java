/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Class for admin
 * @author Lars
 */
public class Properties {
    private static double mva;
    private static int deliveryPrice;
    private static int freeDeliveryLimit;

    /**
     * Preventing instantiation of class
     */
    private Properties(){
    }

    public static void loadProperties() throws SQLException{
        Object[] values = OrderDB.loadProperties();

        BigDecimal tmp = (BigDecimal)values[0];
        mva = tmp.doubleValue();
        deliveryPrice = (Integer)values[1];
        freeDeliveryLimit = (Integer)values[2];
    }

    public static int getDeliveryPrice() {
        return deliveryPrice;
    }

    public static int getFreeDeliveryLimit() {
        return freeDeliveryLimit;
    }

    public static double getMva() {
        return mva;
    }

    public static void storeProperties(String mva, String deliveryPrice, String freeDeliveryLimit) throws SQLException {
        double tmpMva;
        int tmpDeliveryPrice;
        int tmpFreeDeliveryLimit;

        try {
            tmpMva = Double.parseDouble(mva);
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Invalid MVA");
        }

        try {
            tmpDeliveryPrice = Integer.parseInt(deliveryPrice);
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Invalid delivery price");
        }

        try {
            tmpFreeDeliveryLimit = Integer.parseInt(freeDeliveryLimit);
        }
        catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("Invalid free delivery limit");
        }

        OrderDB.setProperties(tmpMva, tmpDeliveryPrice, tmpFreeDeliveryLimit);

        Properties.mva = tmpMva;
        Properties.deliveryPrice = tmpDeliveryPrice;
        Properties.freeDeliveryLimit = tmpFreeDeliveryLimit;
    }
}
