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
     *
     * @author Anders
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
     *
     * @author Anders
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

    /**
     * Search for customers, given the parameters (empty parameters are not used)
     *
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     * @param phone Customer's phonenumber
     * @param address Customer's address
     * @return An arraylist with all the customers found
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<Customer> searchCustomer
            (String firstName, String lastName, String phone, String address)
            throws SQLException{
        
        int phoneNumber = -1;
        try {
            phoneNumber = Integer.parseInt(phone);
        }
        catch (Exception e) {
            // If phoneNumber cannot be parsed, ignore it
        }
        String query = "SELECT * FROM customer WHERE ";

        /* Go through all the parameters and apply constraints to those wich are
         * not null or empty strings
         */
        if (firstName != null && firstName.length() > 1){
            query += "first_name LIKE '" + firstName + "%' ";
        }
        if (lastName != null && lastName.length() > 1){
            if (query.length() > 30){
                query += "OR ";                                                 // If there already is a constraint, add "OR"
            }

            query += "last_name LIKE '" + lastName + "%' ";
        }
        if (phoneNumber > 0){
            if (query.length() > 30){
                query += "OR ";                                                 // If there already is a constraint, add "OR"
            }

            query += "phone_number=" + phoneNumber + " ";
        }
        if (address != null && address.length() > 1){
            if (query.length() > 30){
                query += "OR ";                                                 // If there already is a constraint, add "OR"
            }

            query += "address LIKE '" + address + "%'";
        }

        return getCustomerSimplifier(query);
    }

    /**
     * Get a single customer by his ID
     *
     * @param customerId
     * @return
     * @throws SQLException
     *
     * @author Anders
     */
    public static Customer getCustomerById(int customerId) throws SQLException{
        String query = "SELECT * FROM customer WHERE customer_id=" + customerId;

        return getCustomerSimplifier(query).get(0);
    }

    /*
     * A simplifier for all the methods wich gets customers from the database.
     * It gets all the customers returned from the given query
     */
    private static ArrayList<Customer> getCustomerSimplifier(String query)
            throws SQLException{
        ArrayList<Customer> customers = new ArrayList<Customer>();

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);

        ResultSet result = stat.getResultSet();

        while (result.next()) {
            // TODO: Create customer object to return
            int id          = result.getInt("customer_id");
            String fName    = result.getString("first_name");
            String lName    = result.getString("last_name");
            int phone       = result.getInt("phone_number");
            String address  = result.getString("address");

            customers.add(new Customer(id, fName, lName, phone, address));

        }
        if (customers.isEmpty()) {
            throw new SQLException("No rows fetched");
        }

        return customers;
    }

    /**
     * Edits the customer with the originalId to what the customer "updated" is
     *
     * @param updated The customer containing information to what the customer is to be edited to
     * @param originalId The id of the customer to be edited
     * @throws SQLException
     *
     * @author Anders
     */
    public static void editCustomer(Customer updated, int originalId)
            throws SQLException{
        String query = "UPDATE customer SET " +
                "first_name='" + updated.firstName + "', " +
                "last_name='" + updated.lastName + "', " +
                "phone_number='" + updated.phoneNumber + "', " +
                "address='" + updated.address + "' " +
                "WHERE customer_id=" + originalId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }
    
    /**
     * Inserts the order into the database
     *
     * @param order Order to be inserted
     * @throws SQLException
     *
     * @author Anders
     */
    public static void createOrder(Order order) throws SQLException{
        String address = (order.deliveryAddress != null ?
                        order.deliveryAddress : "");

    	String query = "INSERT INTO orders (customer_id," +
    			" delivery, delivery_address, time, reciept) VALUES " +
                        "(" + order.getCustomer().id +                          // Customer id
    			", " + (order.takeAway ? 1 : 0) +                       // Order going to be delivered?
    			", '" + address + "'" +                                 // Delivery address
                        ", NOW()" +                                             // Current date and time
                        ", '" + order.reciept + "')";                           // The reciept
    	
    	Statement stat = dbConnection.createStatement();
    	stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet keys = stat.getGeneratedKeys();

        if (!keys.next()) throw new SQLException("Failed to retrieve key");
        int orderId = keys.getInt(1);
    	
        for (DishOrder dish : order.dishOrder){
            addDishOrder(dish, orderId);
        }
    }

    /*
     * Helper to add a DishOrder to the database, with relation to the orderId
     */
    private static void addDishOrder(DishOrder dish, int orderId)
            throws SQLException {
        String comment = (dish.comments != null && dish.comments.length() > 1 ? 
                        dish.comments : "");

        String query = "INSERT INTO dish_orders "
                + "(order_id, dish_id, amount, comment) VALUES (" +
                orderId + ", " +
                dish.dishID + ", " +
                dish.amount + ", '" +
                comment + "')";

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Creates the specified customer in the database
     *
     * @param customer The customer to be created
     * @return The id of the customer that was inserted
     * @throws SQLException
     *
     * @author Anders
     */
    public static int newCustomer(Customer customer) throws SQLException{
        String query = "INSERT INTO customer " +
                "(first_name, last_name, phone_number, address) VALUES ('" + 
                customer.firstName + "','" +
                customer.lastName + "'," +
                customer.phoneNumber + ",'" +
                customer.address + "')";

        Statement stat = dbConnection.createStatement();
        stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet keys = stat.getGeneratedKeys();

        if (!keys.next()) throw new SQLException("Failed to retrieve key");
        return keys.getInt(1);
    }

    /**
     * Enum specifying what orders should be ordered by
     */
    public enum CustomerOrder {
        ID,
        FIRST_NAME,
        LAST_NAME,
        PHONE_NUMBER,
        ADDRESS
    }

    /**
     * Gets a list of all the customers in the database, order by the specified argument
     *
     * @param orderBy What the customers are to be ordered by
     * @return An arraylist of the customers
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<Customer> getAllCustomersOrdered(CustomerOrder orderBy)
            throws SQLException{
        String query = "SELECT * FROM customer ORDER BY ";

        if      (orderBy == CustomerOrder.ID)           query += "customer_id";
        else if (orderBy == CustomerOrder.FIRST_NAME)   query += "first_name";
        else if (orderBy == CustomerOrder.LAST_NAME)    query += "last_name";
        else if (orderBy == CustomerOrder.PHONE_NUMBER) query += "phone_number";
        else if (orderBy == CustomerOrder.ADDRESS)      query += "address";

        query += " DESC";

        return getCustomerSimplifier(query);
    }

    /**
     * Deletes the specified customer from the database (from the customers id)
     *
     * @param deleting The customer to be deleted
     * @throws SQLException
     *
     * @author Anders
     */
    public static void deleteCustomer(Customer deleting) throws SQLException{
        deleteCustomersOrders(deleting.id);

        String query = "DELETE FROM customer WHERE customer_id=" + deleting.id;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /*
     * Helper that deletes all the orders of a customer
     */
    private static void deleteCustomersOrders(int customerId) throws SQLException{
        String query = "SELECT order_id, customer_id FROM orders "
                    + "WHERE customer_id=" + customerId;

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        while (result.next()){
            deleteOrder(result.getInt("order_id"));
        }
    }

    /**
     * Returns all the orders available to the chef
     *
     * @return All the chef's orders
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<FetchedOrder> getCooksOrders() throws SQLException {
        String query = "SELECT * FROM orders WHERE made=0 ORDER BY order_id ASC";

        ArrayList<FetchedOrder> orders = new ArrayList<FetchedOrder>();

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);

        ResultSet result = stat.getResultSet();
        while (result.next()) {
            int orderId             = result.getInt("order_id");
            int customerId          = result.getInt("customer_id");
            String deliveryAddress  = result.getString("delivery_address");
            String time             = result.getString("time");


            FetchedOrder order = new FetchedOrder(orderId,
                                                customerId,
                                                deliveryAddress,
                                                FetchedOrder.View.CHEF,
                                                time);

            order.setDishes(getDishOrders(orderId));

            orders.add(order);
        }

        return orders;
    }

    /**
     * Gets all the DishOrders connected to the specified orderId
     *
     * @param orderId The id of the order that the DishOrders are connected to
     * @return An arraylist with the dishorder
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<DishOrder> getDishOrders(int orderId)
            throws SQLException{
        String query = "SELECT * FROM dish_orders WHERE order_id=" + orderId;

        ArrayList<DishOrder> dishes = new ArrayList<DishOrder>();

        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);

        ResultSet result = stat.getResultSet();
        while (result.next()) {

            int dishOrderId = result.getInt("dish_order_id");
            int dishId      = result.getInt("dish_id");
            int amount      = result.getInt("amount");
            String comment  = result.getString("comment");

            dishes.add(new DishOrder(dishOrderId, dishId, amount, comment));
        }

        return dishes;
    }

    /**
     * Sets an order as made in the database
     *
     * @param orderId The ID of the order to be edited
     * @throws SQLException
     *
     * @author Anders
     */
    public static void setOrderAsMade(int orderId) throws SQLException{
        String query = "UPDATE orders SET made=1 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Sets an order as not made in the database
     *
     * @param orderId The ID of the order to be edited
     * @throws SQLException
     *
     * @author Anders
     */
    public static void undoSetOrderAsMade(int orderId) throws SQLException{
        String query = "UPDATE orders SET made=0 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Sets an order as done in the database
     *
     * @param orderId The ID of the order to be edited
     * @throws SQLException
     *
     * @author Anders
     */
    public static void setOrderAsDone(int orderId) throws SQLException{
        String query = "UPDATE orders SET done=1 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Sets and order to be not done in the database
     *
     * @param orderId The ID of the order to be edited
     * @throws SQLException
     */
    public static void undoSetOrderAsDone(int orderId) throws SQLException{
        String query = "UPDATE orders SET done=0 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Updates the specific dish in the database
     *
     * @param updated What the dish is to be updated to
     * @param originalId The id of the dish to be edited
     * @throws SQLException
     */
    public static void updateDish(Dish updated, int originalId) throws SQLException{
        String query = "UPDATE menu SET " +
                "name='"        + updated.name + "', " +
                "price="        + updated.price + ", " +
                "description='" + updated.description + "' " +
                "WHERE dish_id=" + originalId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Check if the order is to be delivered
     *
     * @param orderId The ID of the order to check
     * @return wheter or not the order should be delivered
     * @throws SQLException
     *
     * @author Anders
     */
    public static boolean getOrderDelivery(int orderId) throws SQLException {
        String query = "SELECT delivery FROM orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        if (result.next()){
            return result.getBoolean("delivery");
        }
        else {
            throw new SQLException("Order not found");
        }
    }

    /**
     * Deletes the specified dish from the database (based on the dish's ID)
     *
     * @param removing The dish to be deleted
     * @throws SQLException
     *
     * @author Anders
     */
    public static void deleteDish(Dish removing) throws SQLException {
        String query = "DELETE FROM menu WHERE dish_id=" + removing.nr;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Gets the orders relevant to the admin
     *
     * @param count How many orders to get
     * @param time The time constraint to the list
     * @param before Wheter to search before or after the specified time
     * @return An arraylist with all the relevant orders
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<FetchedOrder> getAdminOrders
            (int count, String time, boolean before) throws SQLException{
        String query = "SELECT * FROM customer, orders WHERE " +
                "time" + (before ? "<" : ">") + "'" + time + "' " +             // Before or after the specified datetime
                "AND orders.customer_id=customer.customer_id " +                // Relation constraint
                "ORDER BY order_id DESC ";                                      // Order descending by order id to get newest first

        if (count > 0){
            query += "LIMIT 0, " + count;                                       // Limit to the number specified if larger than 0
        }

        return getOrdersSimplifier(query, true);
    }

    /**
     * Gets the orders relevant to the driver
     *
     * @return An arraylist of the relevant orders
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<FetchedOrder> getDriverOrders() throws SQLException {
        String query = "SELECT * FROM orders, customer "
                + "WHERE done=0 AND made=1 AND delivery=1 "                     // Constraints to get just the driver's orders
                + "AND orders.customer_id=customer.customer_id";                // Relation constraint

        return getOrdersSimplifier(query, true);
    }

    /**
     * Gets a specific order based on the ID
     *
     * @param orderId The ID of the order to be fetched
     * @return The order
     * @throws SQLException
     *
     * @author Anders
     */
    public static FetchedOrder getOrderById(int orderId) throws SQLException{
        String query = "SELECT * FROM orders, customer "
                + "WHERE order_id=" + orderId + " "
                + "AND orders.customer_id=customer.customer_id";

        return getOrdersSimplifier(query, true).get(0);                         // We can only get one row, so return the first element
    }

    /*
     * Simplifier to get Orders from the database. Gets orders based on the query
     * specified and includes the customer if withCustomer is true.
     */
    private static ArrayList<FetchedOrder> getOrdersSimplifier
            (String query, boolean withCustomer) throws SQLException{
        ArrayList<FetchedOrder> orders = new ArrayList<FetchedOrder>();

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        while (result.next()){
            int orderId             = result.getInt("order_id");
            int customerId          = result.getInt("customer_id");
            String deliveryAddress  = result.getString("delivery_address");
            String time             = result.getString("time").substring(0, 19);// Substring to avoid artifacts

            FetchedOrder order;
            if (withCustomer) {                                                 // If customer is to be included
                int id          = result.getInt("customer_id");
                String fName    = result.getString("first_name");
                String lName    = result.getString("last_name");
                int phone       = result.getInt("phone_number");
                String address  = result.getString("address");

                Customer customer = new Customer(id, fName, lName, phone, address);

                order = new FetchedOrder
                        (orderId, customer, deliveryAddress,
                        FetchedOrder.View.CHEF, time);
            }
            else {
                order = new FetchedOrder(orderId, customerId,
                        deliveryAddress, FetchedOrder.View.CHEF, time);
            }

            order.setDishes(getDishOrders(orderId));                            // Add all DishOrders to the order

            orders.add(order);                                                  // Add order to arraylist to be returned
        }

        return orders;
    }

    /**
     * Gets the latest order in the database, that are not made and not done
     *
     * @return The orders
     * @throws SQLException
     *
     * @author Anders
     */
    public static ArrayList<FetchedOrder> getLatestOrders() throws SQLException{
        String query = "SELECT * FROM orders, customer "
                + "WHERE made=0 AND done=0 "                                    // Constraints to get only latest orders not finished
                + "AND orders.customer_id=customer.customer_id";                // Relation constraints

        return getOrdersSimplifier(query, true);
    }

    /**
     * Deletes the specified order from the database (Based on the ID)
     *
     * @param deleting The order to be deleted
     * @throws SQLException
     *
     * @author Anders
     */
    public static void deleteOrder(FetchedOrder deleting) throws SQLException{
        deleteOrder(deleting.getId());
    }

    /*
     * Helper that deletes the order based on the ID specified
     */
    private static void deleteOrder(int orderId) throws SQLException{
        String query = "DELETE FROM orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);

        deleteDishOrders(orderId);                                              // Be sure to delete all the DishOrders connected to this order
    }

    /*
     * Helper that deletes DishOrders based on the ID of the order it is connected
     * to
     */
    private static void deleteDishOrders(int orderId) throws SQLException{
        String query = "DELETE FROM dish_orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    /**
     * Gets the reciept of the specified order
     *
     * @param orderId The ID of the order to get the reciept for
     * @return A String with the reciept
     * @throws SQLException
     *
     * @author Anders
     */
    public static String getReciept(int orderId) throws SQLException{
        String query = "SELECT reciept FROM orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        if (result.next()){
            String reciept = result.getString("reciept");
            if (reciept != null && reciept.length() > 1) return reciept;
        }
        throw new SQLException("The order does not have a reciept yet");
    }

    /**
     * Loads the properties from the database into an Object array. The array
     * has 3 elements, the first one is a BigDecimal, and the two others are
     * ints
     *
     * @return The properties in an Object array
     * @throws SQLException
     *
     * @author Anders
     */
    public static Object[] loadProperties() throws SQLException{
        String query = "SELECT * FROM properties " +
                       "ORDER BY properties_id DESC LIMIT 1";                   // Order by newest first and limit results to 1

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();

        if (result.next()){
            Object[] ret = new Object[3];                                       // Load into "Object" array to enable different datatypes
            ret[0] = result.getBigDecimal("mva");
            ret[1] = result.getInt("delivery_price");
            ret[2] = result.getInt("free_delivery_limit");

            return ret;
        }
        else {
            throw new SQLException("No rows fetched");
        }
    }

    /**
     * Saves new properties to the database
     *
     * @param mva The MVA
     * @param deliveryPrice The delivery price
     * @param freeDeliveryLimit The limit at which the delivery should be free
     * @throws SQLException
     *
     * @author Anders
     */
    public static void setProperties
            (double mva, int deliveryPrice, int freeDeliveryLimit)
            throws SQLException {
        String query = "INSERT INTO properties "
                + "(mva, delivery_price, free_delivery_limit, time) VALUES (" + 
                mva + ", " +                                                    // MVA
                deliveryPrice + ", " +                                          // Price to have order delivered
                freeDeliveryLimit + ", NOW())";                                 // The limit to where delivery is free and the current datetime

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

   /**
    * Method for adding new dish to DB
    * @param dishIn - Dishobject
    * @throws SQLException
    *
    * @author Lars
    */
    public static void newDish(Dish dishIn) throws SQLException{
        String query = "INSERT INTO menu "
                + "VALUES (" +
                dishIn.nr +", '" +
                dishIn.name + "'," +
                dishIn.price + ",'" +
                dishIn.description + "');";
        Statement stat = dbConnection.createStatement();
        stat.executeUpdate(query);
    }

    /**
     *
     * @param dishNr - specified dish from menu
     * @return
     * @throws SQLException
     *
     * @author Lars
     */
    public static Dish getDish(int dishNr) throws SQLException {
        String query = "SELECT * FROM `menu` WHERE dish_id=" + dishNr;
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();
        if (result.next()) {
            int id          = result.getInt("dish_id");
            String name     = result.getString("name");
            int price       = result.getInt("price");
            String comment  = result.getString("description");

            Dish fromDB = new Dish(id, name, price, comment);

            return fromDB;
        } else {
            throw new SQLException("No Dish with that id");
        }

    }

    /**Checks if order is made by chef
     *
     * @param orderId Id of order being checked
     * @return true or false
     * @throws SQLException when no order with that id
     *
     * @author Lars
     */
    public static boolean checkMade(int orderId) throws SQLException {
        boolean made = false;

        String query = "SELECT made FROM orders " +
                       "WHERE order_id='" + orderId + "'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();

        if (result.next()) {
            if (result.getInt("made") == 1) {
                made = true;
            }
            return made;
        } else {
            throw new SQLException("No order with that id");
        }

    }

    /**Checks if order is delivered to customer
     *
     * @param orderId id of the order being checked
     * @return true/false
     * @throws SQLException when no order with that id
     *
     * @author Lars
     */
    public static boolean checkDone(int orderId) throws SQLException {
        boolean made = false;

        String query = "SELECT done FROM orders WHERE order_id='" + orderId + "'";
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();

        if (result.next()) {
            if (result.getInt("done") == 1) {
                made = true;
            }
            return made;
        } else {
            throw new SQLException("No order with that id");
        }

    }
}
