/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

/**
 *
 * @author Lars
 */
public class Properties {
    static double mva;
    static int deliveryPrice = 50;
    static int maxTot = 500;

    public Properties(double mva, int deliveryPrice, int maxtot){
        Properties.mva = mva;
        Properties.deliveryPrice = deliveryPrice;
        Properties.maxTot = maxtot;

    }

    public void setMva(double newMva){
        Properties.mva=newMva;
    }

    public void setDeliveryPrice(int newDeliveryPrice){
        Properties.deliveryPrice = newDeliveryPrice;
    }

    public void setMaxTot(int newMaxTot){
        Properties.maxTot = newMaxTot;
    }

    public void storeProperties(){
        //call method setProperties in OrderDB
    }
}
