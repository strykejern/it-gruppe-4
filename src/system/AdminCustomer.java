package system;

/**
 *
 * @author Anders
 */
public class AdminCustomer extends Customer {

    public AdminCustomer(Customer customer){
        super(customer);
    }

    @Override
    public String toString(){
        return "Your text here";
    }
}
