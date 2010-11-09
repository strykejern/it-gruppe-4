/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CookFrame.java
 *
 * Created on 08.nov.2010, 17:41:02
 */

package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import system.DishOrder;
import system.FetchedOrder;
import system.OrderDB;

/**
 *
 * @author Anders
 */
public class CookFrame extends javax.swing.JFrame implements GUIUpdater{

    private MainFrame parent;
    private UpdaterThread updater;

    /** Creates new form CookFrame */
    public CookFrame(MainFrame parent) {
        this.parent = parent;

        initComponents();

        init();
    }

    private void init(){
        updater = new UpdaterThread(this, 10 * 1000); // GUI updater with 10 second interval
        updater.start();
    }

    public void updateGUI() {
        try {
            JList focused = null;
            if (orderList.hasFocus()) focused = orderList;
            else if (dishList.hasFocus()) focused = dishList;

            ArrayList<FetchedOrder> orders = OrderDB.getCooksOrders();
            FetchedOrder selectedOrder = (FetchedOrder)orderList.getSelectedValue();

            DefaultListModel orderModel = new DefaultListModel();

            for (FetchedOrder order : orders){

                orderModel.addElement(order);

                if (selectedOrder != null && order.equals(selectedOrder)) {
                    DishOrder selectedDish = (DishOrder)dishList.getSelectedValue();

                    DefaultListModel dishModel = new DefaultListModel();
                    for (DishOrder dish : order.getDishes()) {
                        dishModel.addElement(dish);
                    }
                    dishList.setModel(dishModel);
                    dishList.setSelectedValue(selectedDish, false);
                }
            }

            orderList.setModel(orderModel);
            orderList.setSelectedValue(selectedOrder, false);

            if (focused != null){
                focused.requestFocusInWindow();
            }
        }
        catch (SQLException e) {

        }
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderInfoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDishComment = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        dishList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLastOrder = new javax.swing.JButton();
        btnOrderDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        orderInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        orderInfoPanel.setMaximumSize(new java.awt.Dimension(350, 32767));
        orderInfoPanel.setMinimumSize(new java.awt.Dimension(350, 100));
        orderInfoPanel.setPreferredSize(new java.awt.Dimension(350, 578));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Dish comment:");

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDishComment.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        txtDishComment.setColumns(20);
        txtDishComment.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        txtDishComment.setRows(5);
        jScrollPane3.setViewportView(txtDishComment);

        javax.swing.GroupLayout orderInfoPanelLayout = new javax.swing.GroupLayout(orderInfoPanel);
        orderInfoPanel.setLayout(orderInfoPanelLayout);
        orderInfoPanelLayout.setHorizontalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        orderInfoPanelLayout.setVerticalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        orderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        orderList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                orderListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(orderList);

        dishList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                dishListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(dishList);

        jLabel1.setText("Orders");

        jLabel2.setText("Dishes");

        btnLastOrder.setText("Get last order");

        btnOrderDone.setText("Done with order");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLastOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addComponent(btnOrderDone))
                    .addComponent(jLabel2)
                    .addComponent(orderInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrderDone)
                            .addComponent(btnLastOrder)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
        updater.end();
    }//GEN-LAST:event_formWindowClosed

    private void orderListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_orderListValueChanged
        FetchedOrder selectedOrder = (FetchedOrder)orderList.getSelectedValue();
        if (selectedOrder != null) {

            DefaultListModel dishModel = new DefaultListModel();
            for (DishOrder dish : selectedOrder.getDishes()) {
                dishModel.addElement(dish);
            }
            dishList.setModel(dishModel);
        }
    }//GEN-LAST:event_orderListValueChanged

    private void dishListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_dishListValueChanged
        DishOrder selectedDish = (DishOrder)dishList.getSelectedValue();
        if (selectedDish != null){
            if (selectedDish.comments != null){
                txtDishComment.setText(selectedDish.comments);
            }
            else {
                txtDishComment.setText("No comment");
            }
        }
    }//GEN-LAST:event_dishListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLastOrder;
    private javax.swing.JButton btnOrderDone;
    private javax.swing.JList dishList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel orderInfoPanel;
    private javax.swing.JList orderList;
    private javax.swing.JTextArea txtDishComment;
    // End of variables declaration//GEN-END:variables

}
