/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;
import system.*;
import static java.lang.System.out;
import java.sql.SQLException;
/**
 *
 * @author Lars
 */
public class reciepttest {
    public static void main (String[]args) throws SQLException{
        try {
            OrderDB.initializeDB(
                    DBInfo.username, DBInfo.password,
                    "jdbc:mysql://mysql.stud.ntnu.no/andereie_itgrupp");
            out.println("Successful db connection!");
            }
        catch (Exception e) {
            out.println("Connection failed: " + e.toString());
        }
    Order order=new Order();
    DishOrder order1 = new DishOrder(1, 2, "nada" );
    DishOrder order2 = new DishOrder(3, 3, "nada2" );
    Customer cust = new Customer(3, "Lars", "Larsen", 9178, "Jarleveien", 
            7089, "");
    order.setCustomer(cust);
    order.addDish(order1);
    order.addDish(order2);

    System.out.print(Reciept.toReciept(order));

   }
}
