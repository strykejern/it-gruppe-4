/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapFrame.java
 *
 * Created on 17.nov.2010, 10:52:51
 */

package gui;

import java.io.IOException;
import org.w3c.dom.Document;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import system.FetchedOrder;
import system.OrderDB;

/**
 * This class creates the frame for the Driverwindow, and initializes all components. 
 * By default it sets the maplocation to the location specified in the dummyobject unSelected.
 * The region-variable sets the region for where the map will search for addresses.
 * @author Audun
 */
public class MapFrame extends javax.swing.JFrame implements GUIUpdater{
    private DefaultListModel model;
    private String latitude = null;
    private String longitude = null;
    private int undoId = -1;
    private String noOrderSelected = "No order has been selected.";
    private MainFrame parent;
    private UpdaterThread updater;
    private String region = "Trondheim";
    ArrayList<FetchedOrder> undeliveredList;
    FetchedOrder unSelected = new FetchedOrder(0, 0, "Trondheim",
                                    FetchedOrder.View.DRIVER, "");

    /** Creates new form MapFrame */
    public MapFrame(MainFrame parent) {
        this.parent = parent;

        initComponents();
        initMapSettings();
        init();
    }

    private void init(){
        updater = new UpdaterThread(this, 10 * 1000); // GUI updater with 10 second interval
        updater.start();
    }
    private void initMapSettings(){
        mapView.setDefaultProvider(DefaultProviders.OpenStreetMaps);
        mapView.setMiniMapVisible(false);
        mapView.setZoom(4);
        mapView.setAddressLocation(getCoords(unSelected));
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ordersToDeliverList = new javax.swing.JList();
        recieptButton = new javax.swing.JButton();
        deliveredButton = new javax.swing.JButton();
        mapView = new org.jdesktop.swingx.JXMapKit();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        ordersToDeliverList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ordersToDeliverList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ordersToDeliverListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ordersToDeliverList);

        recieptButton.setText("Reciept");
        recieptButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                recieptButtonMouseReleased(evt);
            }
        });

        deliveredButton.setText("Delivered");
        deliveredButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deliveredButtonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(mapView, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(recieptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deliveredButton, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mapView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(recieptButton)
                            .addComponent(deliveredButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ordersToDeliverListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ordersToDeliverListValueChanged
        try{
        mapView.setAddressLocation(getCoords(getSelectedOrder()));
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error: \n"+e.getMessage());
        }
    }//GEN-LAST:event_ordersToDeliverListValueChanged

    private void recieptButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recieptButtonMouseReleased
        JOptionPane.showMessageDialog(null, getSelectedOrderReciept());
    }//GEN-LAST:event_recieptButtonMouseReleased

    private void deliveredButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveredButtonKeyReleased
        orderDelivered(getSelectedOrder());
}//GEN-LAST:event_deliveredButtonKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    public void updateGUI(){
        setOrdersToDeliverModel();
    }
    /**
     * Retrieves a FetchedOrder ArrayList from OrderDB, then adds all the objects in a model, then finally sets the model to the orderToDeliverList.
     */
    private void setOrdersToDeliverModel() {

        try {
            undeliveredList = OrderDB.getDriverOrders();
        } catch (SQLException e) {
        }

        model = new DefaultListModel();

        for (FetchedOrder i : undeliveredList) {
            model.addElement(i);
        }

        ordersToDeliverList.setModel(model);
    }

    private String getSelectedOrderReciept() {
        if (ordersToDeliverList.getSelectedValue() != null) {
            return ordersToDeliverList.getSelectedValue().toString();
        }
        return noOrderSelected;
    }

    /**
     * Return the selected order from the ordersToDeliverList. If no order is selected, it will return a dummy-object.
     * @return
     */
    private FetchedOrder getSelectedOrder() {
        try {
            return (ordersToDeliverList.getSelectedValue() != null ? (FetchedOrder) ordersToDeliverList.getSelectedValue() : null);
            
        } catch (Exception e) {
            throw new IllegalArgumentException("No order is selected.");
        }
    }
    /**
     * Sets the FetchedOrder to delivered, then reloads the list containing the undelivered orders. if The FetchedOrder equals the dummyObject unSelected which does not exist in the
     * database, it will only refresh the orderToDeliverList.
     * @param o
     */
    private void orderDelivered(FetchedOrder o){
        if (o !=unSelected){
            try {
                OrderDB.setOrderAsDone(o.getId());

            }catch(SQLException e) {
            }
            undoId = o.getId();
        }
        setOrdersToDeliverModel();
    }
    /**
     * Undos a orderDelivered call, setting the object to undelivered, if undoId = -1, no object has been delivered as of this session, and the method will return without 
     * doing anything.
     * @param o
     */
    private void undoOrderDelivered(){
       if(undoId != -1){
          try {
              OrderDB.undoSetOrderAsDone(undoId);
          }catch(SQLException e) {
          }
          setOrdersToDeliverModel();
       }     
    }

    /**
     * Takes a FetchedOrder object, extracts the delivery address, then converts the delivery address with the help of the Yahoo-whereis service.
     * Returns a GeoPosition object containing the latitude and longitude of the position.
     * 
     * 
     * @param o
     * @return 
     */
    public GeoPosition getCoords(FetchedOrder o)throws IllegalArgumentException {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            String address = URLEncoder.encode(o.getDeliveryAddress(), "UTF-8");
            Document xml = builder.parse("http://where.yahooapis.com/geocode?q=" + address + "+" + region);
            latitude = getLat(xml);
            longitude = getLong(xml);
            return new GeoPosition(Double.parseDouble(latitude), Double.parseDouble(longitude));
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to find address or unable to connect to mapserver: " + e.getMessage());
        } catch (SAXException e){
            throw new IllegalArgumentException("Parsing error: " + e.getMessage());
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    /**
     * Extracts the item at position 0 in a Document object, in this case the longitude, and returns a String containing the value.
     * @param doc
     * @return
     */
    private String getLong(Document doc) {
        try {
            NodeList nodeList = doc.getElementsByTagName("longitude");
           
            return nodeList.item(0).getTextContent();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to retrieve longitude from Document.");
        }

    }
    /**
     * Extracts the item at position 0 in a Document object, in this case the latitude, and returns a String containing the value
     * @param doc
     * @return
     */
    private String getLat(Document doc) {
        try {
            NodeList nodeList = doc.getElementsByTagName("latitude");
            return nodeList.item(0).getTextContent();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to retrieve latitude from Document.");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deliveredButton;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXMapKit mapView;
    private javax.swing.JList ordersToDeliverList;
    private javax.swing.JButton recieptButton;
    // End of variables declaration//GEN-END:variables

}
