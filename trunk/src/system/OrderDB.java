package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

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
        final String query = "SELECT * FROM menu";

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();

        Menu m = new Menu();
        while (result.next()){
            int id          = result.getInt("id");
            String name     = result.getString("name");
            int price       = result.getInt("price");
            String comment  = result.getString("comment");

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
    
    // Lars

   /**
    * Method for adding new dish to DB
    * @param dishIn - Dishobject
    * @throws SQLException
    */
    public void newDish(Dish dishIn) throws SQLException{
        String query = "INSERT INTO menu(name,price,description) VALUES('" +
                dishIn.name + "','" + dishIn.price + "','" +
                dishIn.contents + "')";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
    }

    /**
     *
     * @param dishNr - specified dish from menu
     * @return
     * @throws SQLException
     */
    public static Dish getDish(int dishNr) throws SQLException{
        String query = "GET FROM menu WHERE dish_id='" + dishNr +"'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();
        int id          = result.getInt("id");
        String name     = result.getString("name");
        int price       = result.getInt("price");
        String comment  = result.getString("comment");

        Dish fromDB = new Dish(id, name, price, comment);
        return fromDB;
    }

    /**
     * removes specified dish from menu DB
     *@param query : querystring for deleting element in DB
     */
    public void deleteDish(int menuNr) throws SQLException {

        String query = "DELETE FROM menu WHERE dish_id='" + menuNr +"'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);

    }
    /**
     * @param orderNr - id for reciept
     * @return
     * @throws SQLException
     */

    public static Reciept buildReciept(int orderNr) throws SQLException{
        String query = "GET FROM order WHERE order_id='" + orderNr +"'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        Reciept reciept = new Reciept();
        ResultSet result = stat.getResultSet();
        reciept.nr          = result.getInt("id");
        reciept.orders   = result.getString("dishes");
        reciept.customer =result.getInt("costumer_id");
        reciept.delivery =result.getInt("delivery");
        return reciept;
    }

    // Audun

    // Haavard
    
}
