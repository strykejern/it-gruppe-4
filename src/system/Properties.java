/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for admin
 * @author Lars
 */
public class Properties {
    private static Connection dbConnection;
    public static double mva;
    public static int deliveryPrice;
    public static int maxTot;
    private static String password;


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

    private Properties(){

    }

    public void setMva(double newMva){
        Properties.mva=newMva;
    }

    public void setDeliveryPrice(int newDeliveryPrice){
        Properties.deliveryPrice = newDeliveryPrice;
    }

    public void setMaxTot(int newMaxTot){
        Properties.maxTot = newMaxTot;
    }

    private void setPassw(String passw){
        Properties.password = passw;
    }

    public void storeProperties(){
        //TODO: SQL query storing info in DB
        //TODO: Table in DB for storing info
    }
}
