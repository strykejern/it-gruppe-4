package testing;

import static java.lang.System.out;
import system.OrderDB;

/**
 *
 * @author Anders
 */
public class OrderDBTest {
    public static void main(String[] args){
        OrderDB database;
        try {
            database = new OrderDB(
                    DBInfo.username, DBInfo.password,
                    "jdbc:mysql://mysql.stud.ntnu.no/andereie_itgrupp");
            out.println("Successful db connection!");
            }
        catch (Exception e) {
            out.println("Connection failed: " + e.toString());
        }
    }
}
