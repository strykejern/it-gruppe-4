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
        return "Id: " + this.id
                + " Name: " + this.firstName + " " + this.lastName
                + " Tel: " + this.phoneNumber
                + " Address: " + this.address;
    }
}
