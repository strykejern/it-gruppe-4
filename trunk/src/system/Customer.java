package system;

/**
 * An object for holding the customer information.
 * @author Anders
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String address;
    private int id;
    private int phoneNumber;

    protected Customer(Customer customer){
        this.id = customer.id;
        this.firstName = customer.firstName;
        this.lastName = customer.lastName;
        this.address = customer.address;
        this.phoneNumber = customer.phoneNumber;
    }

    public Customer(int id, String firstName, String lastName, int phoneNumber,
                String address){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, String phoneNumber,
                String address) throws IllegalArgumentException {
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
    /**
     * Checks to see if the information provided is longer than 2 characters and not null.
     * @throws IllegalArgumentException
     */
    public void validate() throws IllegalArgumentException {
        if (getFirstName() == null || getFirstName().length() < 2)
                throw new IllegalArgumentException("Invalid first name");
        if (getLastName() == null || getLastName().length() < 2)
                throw new IllegalArgumentException("Invalid last name");
        if (getAddress() == null || getAddress().length() < 2)
                throw new IllegalArgumentException("Invalid address");
        if (getPhoneNumber() < 10000000)
                throw new IllegalArgumentException("Ivalid phonenumber");
    }
    /**
     * Overrides the toString method and returns the contents of the object in
     * a formatted String.
     * @return
     */
    @Override
    public String toString(){
        String spaces1=" ";
        String spaces2=" ";
        int nameLength=getFirstName().length()+getLastName().length();
        if(nameLength<30){
            for(int i =0;i<25-nameLength;i++){
                spaces1+=" ";
            }

        }
        return getFirstName() + " " + getLastName() + spaces1 +
                "Num: (" + getPhoneNumber() + ")     Addr: (" + getAddress() + ")";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }
}
