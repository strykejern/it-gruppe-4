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

    public static ArrayList<Customer> searchCustomer(String firstName,
            String lastName, String phone, String address)
            throws SQLException{
        
        int phoneNumber = -1;
        try {
            phoneNumber = Integer.parseInt(phone);
        }
        catch (Exception e) {

        }
        String query = "SELECT * FROM customer WHERE ";

        if (firstName != null && firstName.length() > 1){
            query += "first_name LIKE '" + firstName + "%' ";
        }
        if (lastName != null && lastName.length() > 1){
            if (query.length() > 30) query += "OR ";

            query += "last_name LIKE '" + lastName + "%' ";
        }
        if (phoneNumber > 0){
            if (query.length() > 30) query += "OR ";

            query += "phone_number LIKE " + phoneNumber + " ";
        }
        if (address != null && address.length() > 1){
            if (query.length() > 30) query += "OR ";

            query += "address LIKE '" + address + "%'";
        }

        return getCustomerSimplifier(query);
    }
    
    public static Object getCustomerByName(String firstName, String lastName)
            throws SQLException {
        String query = "SELECT * FROM customer "
                + "WHERE first_name='" + firstName
                + "' AND last_name='" + lastName + "'";

        return getCustomerSimplifier(query);
    }

    public static ArrayList<Customer> getCustomersByName(String lastName)
            throws SQLException {
        String query = "SELECT * FROM customer "
                + "WHERE last_name='" + lastName + "'";

        return getCustomerSimplifier(query);
    }

    public static Customer getCustomerById(int customerId) throws SQLException{
        String query = "SELECT * FROM customer WHERE customer_id=" + customerId;

        return getCustomerSimplifier(query).get(0);
    }

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
        if (customers.size() == 0) {
            throw new SQLException("No rows fetched");
        }

        return customers;
    }

    public static void editCustomer(Customer updated, int originalId) throws SQLException{
        String query = "UPDATE customer SET " +
                "first_name='" + updated.firstName + "', " +
                "last_name='" + updated.lastName + "', " +
                "phone_number='" + updated.phoneNumber + "', " +
                "address='" + updated.address + "' " +
                "WHERE customer_id=" + originalId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }
    
    public static void createOrder(Order order) throws SQLException{
    	String query = "INSERT INTO orders (customer_id, made," +
    			" delivery, delivery_address, time) VALUES " +
                        "(" + order.getCustomer().id +
    			", 0, " + (order.takeAway ? 1 : 0) +
    			", '" + 
                        (order.deliveryAddress != null ? order.deliveryAddress
                        : "") + "', NOW())";
    	
    	Statement stat = dbConnection.createStatement();
    	stat.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet keys = stat.getGeneratedKeys();

        if (!keys.next()) throw new SQLException("Failed to retrieve key");
        int orderId = keys.getInt(1);
    	
        for (DishOrder dish : order.dishOrder){
            addDishOrder(dish, orderId);
        }
    }

    private static void addDishOrder(DishOrder dish, int orderId)
            throws SQLException {
        String query = "INSERT INTO dish_orders (order_id, dish_id, amount, "
                + "comment) " + "VALUES (" + orderId + ", " + dish.dishID + ", "
                + dish.amount + ", '" + (dish.comments != null &&
                dish.comments.length() > 1 ? dish.comments : "") +
                "')";

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static void newCustomer(Customer customer) throws SQLException{
        String query = "INSERT INTO customer " +
                "(first_name, last_name, phone_number," +
                " address) VALUES " +
                "('" + customer.firstName + "','" + customer.lastName + "'," +
                customer.phoneNumber + ",'" + customer.address + "')";

        Statement stat = dbConnection.createStatement();
        stat.executeUpdate(query);
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException{
        String query = "SELECT * FROM customer";

        return getCustomerSimplifier(query);
    }

    public enum CustomerOrder {
        ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS
    }

    public static ArrayList<Customer> getAllCustomersOrdered(CustomerOrder orderBy) throws SQLException{
        String query = "SELECT * FROM customer ORDER BY ";

        if (orderBy == CustomerOrder.ID) query += "customer_id";
        else if (orderBy == CustomerOrder.FIRST_NAME) query += "first_name";
        else if (orderBy == CustomerOrder.LAST_NAME) query += "last_name";
        else if (orderBy == CustomerOrder.PHONE_NUMBER) query += "phone_number";
        else if (orderBy == CustomerOrder.ADDRESS) query += "address";

        query += " DESC";

        return getCustomerSimplifier(query);
    }

    public static void deleteCustomer(Customer deleting) throws SQLException{
        String query = "DELETE FROM customer WHERE customer_id=" + deleting.id;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);

        deleteCustomersOrders(deleting.id);
    }

    private static void deleteCustomersOrders(int customerId) throws SQLException{
        String query = "SELECT order_id FROM order WHERE customer_id=" + customerId;

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        while (result.next()){
            deleteOrder(result.getInt("order_id"));
        }
    }

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


            FetchedOrder order = new FetchedOrder(orderId, customerId, 
                    deliveryAddress, FetchedOrder.View.CHEF, time);

            order.setDishes(getDishOrders(orderId));

            orders.add(order);
        }

        return orders;
    }

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

    public static void setOrderAsMade(int orderId) throws SQLException{
        String query = "UPDATE orders SET made=1 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static void undoSetOrderAsMade(int orderId) throws SQLException{
        String query = "UPDATE orders SET made=0 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static void setOrderAsDone(int orderId) throws SQLException{
        String query = "UPDATE orders SET done=1 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static void undoSetOrderAsDone(int orderId) throws SQLException{
        String query = "UPDATE orders SET done=0 WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static void updateDish(Dish updated, int originalId) throws SQLException{
        String query = "UPDATE menu SET " +
                "name='" + updated.name + "', " +
                "price=" + updated.price + ", " +
                "description='" + updated.description + "' " +
                "WHERE dish_id=" + originalId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

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

    public static void deleteDish(Dish removing) throws SQLException {
        String query = "DELETE FROM menu WHERE dish_id=" + removing.nr;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    public static ArrayList<FetchedOrder> getAdminOrders (int count, String time, boolean before) throws SQLException{
        String query = "SELECT customer.*, orders.* FROM customer, orders WHERE " +
                "time" + (before ? "<" : ">") + "'" + time + "' " +
                "AND orders.customer_id=customer.customer_id ORDER BY order_id DESC ";

        if (count > 0) query += "LIMIT 0, " + count;

        return getOrdersSimplifier(query, true);
    }

    public static ArrayList<FetchedOrder> getDriverOrders() throws SQLException {
        String query = "SELECT * FROM orders, customer WHERE done=0 AND made=1 AND delivery=1 AND orders.customer_id=customer.customer_id";

        return getOrdersSimplifier(query, true);
    }

    public static FetchedOrder getOrderById(int orderId) throws SQLException{
        String query = "SELECT * FROM orders, customer WHERE order_id=" + orderId + " AND orders.customer_id=customer.customer_id";

        return getOrdersSimplifier(query, true).get(0);
    }

    public static ArrayList<FetchedOrder> getOrdersSimplifier(String query, boolean withCustomer) throws SQLException{
        ArrayList<FetchedOrder> orders = new ArrayList<FetchedOrder>();

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();
        while (result.next()){
            int orderId             = result.getInt("order_id");
            int customerId          = result.getInt("customer_id");
            String deliveryAddress  = result.getString("delivery_address");
            String time             = result.getString("time").substring(0, 19);

            FetchedOrder order;
            if (withCustomer) {
                int id          = result.getInt("customer_id");
                String fName    = result.getString("first_name");
                String lName    = result.getString("last_name");
                int phone       = result.getInt("phone_number");
                String address  = result.getString("address");

                Customer customer = new Customer(id, fName, lName, phone, address);

                order = new FetchedOrder(orderId, customer, deliveryAddress, FetchedOrder.View.CHEF, time);
            }
            else {
                order = new FetchedOrder(orderId, customerId,
                        deliveryAddress, FetchedOrder.View.CHEF, time);
            }

            order.setDishes(getDishOrders(orderId));

            orders.add(order);
        }

        return orders;
    }

    public static ArrayList<FetchedOrder> getLatestOrders() throws SQLException {
        String query = "SELECT * FROM orders, customer WHERE made=0 AND orders.customer_id=customer.customer_id";

        return getOrdersSimplifier(query, true);
    }

    public static void deleteOrder(FetchedOrder deleting) throws SQLException{
        deleteOrder(deleting.getId());
    }

    private static void deleteOrder(int orderId) throws SQLException{
        String query = "DELETE FROM orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);

        deleteDishOrders(orderId);
    }

    private static void deleteDishOrders(int orderId) throws SQLException{
        String query = "DELETE FROM dish_orders WHERE order_id=" + orderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

    private static void deleteDishOrder(int dishOrderId) throws SQLException{
        String query = "DELETE FROM dish_orders WHERE dish_order_id=" + dishOrderId;

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
    }

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

    public static Object[] loadProperties() throws SQLException{
        String query = "SELECT * FROM properties ORDER BY properties_id DESC LIMIT 1";

        Statement stat = dbConnection.createStatement();

        stat.execute(query);

        ResultSet result = stat.getResultSet();

        if (result.next()){
            Object[] ret = new Object[3];
            ret[0] = result.getBigDecimal("mva");
            ret[1] = result.getInt("delivery_price");
            ret[2] = result.getInt("free_delivery_limit");

            return ret;
        }
        else {
            throw new SQLException("No rows fetched");
        }
    }

    public static void setProperties(double mva, int deliveryPrice, int freeDeliveryLimit) throws SQLException {
        String query = "INSERT INTO properties (mva, delivery_price, free_delivery_limit) VALUES (" + mva + ", " + deliveryPrice + ", " + freeDeliveryLimit + ")";

        Statement stat = dbConnection.createStatement();

        stat.executeUpdate(query);
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
     */
    public static Dish getDish(int dishNr) throws SQLException {
        String query = "SELECT * FROM `menu` WHERE dish_id=" + dishNr;
        Statement stat = dbConnection.createStatement();
        stat.executeQuery(query);
        ResultSet result = stat.getResultSet();
        if (result.next()) {
            int id = result.getInt("dish_id");
            String name = result.getString("name");
            int price = result.getInt("price");
            String comment = result.getString("description");

            Dish fromDB = new Dish(id, name, price, comment);

            return fromDB;
        } else {
            throw new SQLException("No Dish with that id");
        }

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

            int dishOrderId = result.getInt("dish_order_id");
            int id = result.getInt("order_id");
            int dish = result.getInt("dish_id");
            int amount = result.getInt("amount");
            String comment = result.getString("comment");

            order.dishOrder.add(new DishOrder(dishOrderId, id, amount, comment));
        }

        return order;
    }

    /**Checks if order is made by chef
     *
     * @param orderId Id of order being checked
     * @return true or false
     * @throws SQLException when no order with that id
     */
    public static boolean checkMade(int orderId) throws SQLException {
        boolean made = false;

        String query = "SELECT made FROM orders WHERE order_id='" + orderId + "'";
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

    // Audun

    // Haavard
    
}
