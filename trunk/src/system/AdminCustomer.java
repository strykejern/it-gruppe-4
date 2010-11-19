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
        return "Name: " + this.getFirstName() + " " + this.getLastName()
                + " Tel: " + this.getPhoneNumber();
    }
}
