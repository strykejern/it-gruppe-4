package system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
            print = super.toString() + " for " + this.getCustomer().firstName 
                    + " " + this.getCustomer().lastName + " (tlf: "
                    + this.getCustomer().phoneNumber + ")";
            return print;
        } catch (SQLException ex) {
            Logger.getLogger(WaiterFetchedOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
}
