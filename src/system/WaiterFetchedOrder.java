package system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Essentially a FetchedOrder object, but overrides the toString method to accomodate
 * a Waiter view.
 * @author Anders
 */
public class WaiterFetchedOrder extends FetchedOrder{
    public WaiterFetchedOrder(FetchedOrder original){
        super(original);
    }

    @Override
    public String toString(){
        try {
            String print;
            print = super.toString() + " for " + this.getCustomer().getFirstName()
                    + " " + this.getCustomer().getLastName() + " (tlf: "
                    + this.getCustomer().getPhoneNumber() + ")";
            return print;
        } catch (SQLException ex) {
            Logger.getLogger(WaiterFetchedOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
}
