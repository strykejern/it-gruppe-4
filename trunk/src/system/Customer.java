package system;

/**
 *
 * @author Anders
 */
public class Customer {
    public String firstName;
    public String lastName;
    public String address;
    public int id;
    public int phoneNumber;

    public Customer(int id, String firstName, String lastName, int phoneNumber, String address){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, String phoneNumber, String address) throws IllegalArgumentException { // TODO: postal code?
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        try {
            this.phoneNumber = Integer.parseInt(phoneNumber);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid phonenumber");
        }

        validate();
    }

    public void validate() throws IllegalArgumentException {
        if (firstName == null || firstName.length() < 2)    throw new IllegalArgumentException("Invalid first name");
        if (lastName == null || lastName.length() < 2)      throw new IllegalArgumentException("Invalid last name");
        if (address == null || address.length() < 2)        throw new IllegalArgumentException("Invalid address");
        if (phoneNumber < 10000000)                         throw new IllegalArgumentException("Ivalid phonenumber");
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}
