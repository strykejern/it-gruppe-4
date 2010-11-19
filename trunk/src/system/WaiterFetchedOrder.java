package system;

import java.sql.SQLException;

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
            throw new IllegalArgumentException("Failed to connect to database");
        }
    }
}
