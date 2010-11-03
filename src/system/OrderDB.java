package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Class for handling of database queries
 *
 * @author Anders
 */
public class OrderDB {
    /** Todo: empty implementations of methods needed
     *        in the classes.
     *
     * PS: To avoid conflicts in simultaneous editing,
     *     create methods under your commented name
     */

    // Anders
    private static Connection dbConnection;

    /**
     * Preventing instantiation
     */
    private OrderDB(){
        
    }

    /**
     * Initializer that creates the database connection
     *
     * @param userName Database username
     * @param password Database password
     * @param databaseLocation The whole database url
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public static void initializeDB
    		(String userName, String password, String databaseLocation)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{

        if (dbConnection != null) {
            dbConnection.close();
        }
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        dbConnection = DriverManager.getConnection(
                databaseLocation, userName, password);
    }
    
    /**
     * Gets the menu from the database
     * 
     * @return Menu object with the loaded menu
     * @throws SQLException
     */
    public static Menu getMenu() throws SQLException{
        final String query = "SELECT * FROM menu ORDER BY dish_id ASC";

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();

        Menu m = new Menu();
        while (result.next()){
            int id          = result.getInt("dish_id");
            String name     = result.getString("name");
            int price       = result.getInt("price");
            String comment  = result.getString("description");

            m.addDish(new Dish(id, name, price, comment));
        }

        result.close();
        stat.close();

        return m;
    }
    
    public static Object getCustomerByName(String firstName, String lastName) 
    		throws SQLException, Exception {
    	String query = "SELECT * FROM customer " +
        	"WHERE first_name='" + firstName +
        	"' AND last_name='" + lastName + "'";
    	
    	Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        
        ResultSet result = stat.getResultSet();
        
        if (result.next()){
        	// TODO: Create customer object to return
        	String fName 	= result.getString("first_name");
        	String lName 	= result.getString("last_name");
        	int phone 		= result.getInt("phone_number");
        	String address 	= result.getString("address");
        	int postalCode 	= result.getInt("postal_code");
        	String comment 	= result.getString("comment");
        }
        else {
        	throw new SQLException ("No rows fetched");
        }
        
        // Throw exception on more than one row result
        if (result.next()){
        	throw new Exception ("Multiple customer matched your search");
        }
    	
    	return null;
    }
    
    public static Object getCustomerByName(String lastName) 
    		throws SQLException, Exception {
    	String query = "SELECT * FROM customer " +
        			"WHERE last_name='" + lastName + "'";
        
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        
        ResultSet result = stat.getResultSet();
        
        if (result.next()){
        	// TODO: Create customer object to return
        	String fName 	= result.getString("first_name");
        	String lName 	= result.getString("last_name");
        	int phone 		= result.getInt("phone_number");
        	String address 	= result.getString("address");
        	int postalCode 	= result.getInt("postal_code");
        	String comment 	= result.getString("comment");
        }
        else {
        	throw new SQLException ("No rows fetched");
        }
        
        // Throw exception on more than one row result
        if (result.next()){
        	throw new Exception ("Multiple customer matched your search");
        }

        return null;
    }
    
    public static void createOrder(int customerId, boolean made, 
    		boolean delivery, String deliveryAddress) throws SQLException{
    	String query = "INSERT INTO 'orders' ('customer_id', 'made'," +
    			" 'delivery', 'delivery_address') VALUES (" + customerId + 
    			", " + (made ? 1 : 0) + ", " + (delivery ? 1 : 0) + 
    			", '" + deliveryAddress + "')";
    	
    	Statement stat = dbConnection.createStatement();
    	stat.executeQuery(query);
    	
    	// TODO: add dishes into dish_orders
    }

    public static void addCustomer(Customer customer) throws SQLException{
        String query = "INSERT INTO 'customer' " +
                "('first_name','last_name','phone_number'," +
                "'address','postal_code','comment') VALUES " +
                "('" + customer.firstName + "','" + customer.lastName + "'," +
                customer.phoneNumber + ",'" + customer.address + "'," +
                customer.postalCode + ",'" + customer.comment + "')";

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException{
        String query = "SELECT * FROM customer";

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);

        ResultSet result = stat.getResultSet();

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        while (result.next()){
            int id           = result.getInt("customer_id");
            String firstName = result.getString("first_name");
            String lastName  = result.getString("last_name");
            int phoneNumber  = result.getInt("phone_number");
            String address   = result.getString("address");
            int postalCode   = result.getInt("postal_code");
            String comment   = result.getString("comment");

            customerList.add(
                    new Customer(
                    id, firstName, lastName, phoneNumber,
                    address, postalCode, comment));

        }

        return customerList;
    }
    
    // Lars

   /**
    * Method for adding new dish to DB
    * @param dishIn - Dishobject
    * @throws SQLException
    */
    public static void newDish(Dish dishIn) throws SQLException{
                /**INSERT INTO  `menu` (  `dish_id` ,  `name` ,  `price` ,
                 * `description` )
                VALUES ('4',  'navn',  '345',  'innhold');

                 **/

        String query = "INSERT INTO `menu` "
                + "VALUES (`" +
                dishIn.nr +"`,`" +
                dishIn.name + "`," +
                dishIn.price + ",`" +
                dishIn.description + "`);";
        Statement stat = dbConnection.createStatement();
        stat.executeUpdate(query);
    }

    /**
     *
     * @param dishNr - specified dish from menu
     * @return
     * @throws SQLException
     */
    public static Dish getDish(int dishNr) throws SQLException{
        String query = "SELECT * FROM `menu` WHERE dish_id=" + dishNr;
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();
        result.next();
        int id          = result.getInt("dish_id");
        String name     = result.getString("name");
        int price       = result.getInt("price");
        String comment  = result.getString("description");

        Dish fromDB = new Dish(id, name, price, comment);
        return fromDB;
    }

    /**
     * removes specified dish from menu DB
     *@param query : querystring for deleting element in DB
     */
    public static void deleteDish(int menuNr) throws SQLException {

        String query = "DELETE FROM menu WHERE dish_id=" + menuNr;
        Statement stat = dbConnection.createStatement();
        stat.executeUpdate(query);

    }
    /**
     * @param orderNr - id for reciept
     * @return
     * @throws SQLException
     */
    public static Order getOrder(int orderNr) throws SQLException {
        String query = "SELECT * FROM dish_orders WHERE order_id='" + orderNr + "'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        Order order = new Order();
        ResultSet result = stat.getResultSet();
        while (result.next()) {

            int id = result.getInt("order_id");
            int dish = result.getInt("dish_id");
            int amount = result.getInt("amount");
            String comment = result.getString("comment");

            order.dishOrder.add(new DishOrder(id, amount, comment));
        }

        return order;
    }

    // Audun

    // Haavard
    
}
