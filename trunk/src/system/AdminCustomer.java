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
        return "Name: " + this.firstName + " " + this.lastName
                + " Tel: " + this.phoneNumber;
    }
}
