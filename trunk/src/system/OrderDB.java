package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private Connection dbConnection;

    public OrderDB(String userName, String password, String databaseLocation)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        dbConnection = DriverManager.getConnection(
                databaseLocation, userName, password);
    }

    /**
     * Close the database connection on exit
     *
     * If the database connection was successful,
     * close it when class is destroyed
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable{
        if (dbConnection != null){
            dbConnection.close();
        }
    }
    // Lars
		
		/* Method for adding new dish to DB.
		 * number in menu = dishIn.nr 
		 * name of dish = dishIn.name
		 * contents of dish = dishIn.contents 
		 */
	void newDish(Dish dishIn){
		
	}
	
		/*returns specified dish from MenuDB
		 * 
		 */
	public Dish getDish(int menuNr){
		Dish fromDB =null;
		return fromDB;
	}
		
		/*removes specified dish from menu DB
		 * 
		 */
	void deleteDish(int menuNr){
		
	}

    // Audun

    // Haavard
    
}
