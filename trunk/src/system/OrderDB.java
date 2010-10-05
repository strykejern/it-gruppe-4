package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public static void initializeDB(String userName, String password, String databaseLocation)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{

        if (dbConnection != null) {
            dbConnection.close();
        }
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        dbConnection = DriverManager.getConnection(
                databaseLocation, userName, password);
    }

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

    public static Object[] getCustomerByName(String name) {
        String firstName = null;
        String lastName = null;

        String[] temp = name.split(" ");

        if (temp.length > 1){
            lastName = temp[temp.length-1];
            firstName = "";
            for (int i = temp.length-2; i >= 0; --i){
                firstName += temp[i] + " ";
            }
            firstName.trim();
        }
        else{
            lastName = name;
        }

        if (firstName != null){
            String query = "SELECT * FROM customer " +
                    "WHERE first_name='" + firstName +
                    "' AND last_name='" + lastName + "'";
        }

        return null;
    }
    // Lars

    /* Method for adding new dish to DB.
     * number in menu = dishIn.nr
     * name of dish = dishIn.name
     * contents of dish = dishIn.contents
     */
    void newDish(Dish dishIn) {
    }

    /*returns specified dish from MenuDB
     *
     */
    public Dish getDish(int menuNr) {
        Dish fromDB = null;
        return fromDB;
    }

    /*removes specified dish from menu DB
     *
     */
    void deleteDish(int menuNr) {
    }

    // Audun

    // Haavard
    
}
