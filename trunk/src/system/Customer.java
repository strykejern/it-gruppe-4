package system;

/**
 *
 * @author Anders
 */
public class Customer {
    public String firstName;
    public String lastName;
    public String address;
    public String comment;
    public int postalCode;
    public int phoneNumber;

    public Customer(String firstName, String lastName, int phoneNumber, String address, int postalCode, String comment){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.comment = comment;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }
}
