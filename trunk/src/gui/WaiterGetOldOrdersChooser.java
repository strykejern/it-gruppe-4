/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WaiterGetOldOrdersChooser.java
 *
 * Created on 15.nov.2010, 19:30:29
 */

package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import system.FetchedOrder;
import system.OrderDB;
import system.WaiterFetchedOrder;

/**
 *
 * @author Anders
 */
public class WaiterGetOldOrdersChooser extends javax.swing.JFrame {

    JFrame parent;

    FormListener listener;

    /** Creates new form WaiterGetOldOrdersChooser */
    public WaiterGetOldOrdersChooser(JFrame parent, FormListener listener) {
        this.parent = parent;
        this.listener = listener;

        initComponents();

        init();


        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

    private void init(){
        /*
         * Tries to load the latest orders from the database into the list
         * (only those which are not already made by the chef or delivered)
         */
        try {

            ArrayList<FetchedOrder> orders = OrderDB.getLatestOrders();

            DefaultListModel orderModel = new DefaultListModel();

            for (FetchedOrder order : orders){
                orderModel.addElement(new WaiterFetchedOrder(order));
            }

            orderList.setModel(orderModel);
        }
        catch (SQLException e) {
            System.out.println(e);
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

        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        ScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JList();
        btnShowReciept = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        ScrollPane1.setViewportView(orderList);

        btnShowReciept.setText("Show reciept");
        btnShowReciept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowRecieptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(btnShowReciept)
                        .addGap(81, 81, 81)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnCancel)
                    .addComponent(btnShowReciept))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Set the parent to be visible when closing the frame
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /*
     * send a signal back to the waiterform which order was selected to be
     * edited, and then dispose/close self
     */
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        FetchedOrder order = (FetchedOrder)orderList.getSelectedValue();
        if (order == null) return;

        listener.tell(order.getId());
        parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    /*
     * Shows the reciept for the selected order, and gives the oppertunity to
     * mark it as done
     */
    private void btnShowRecieptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowRecieptActionPerformed
        FetchedOrder order = (FetchedOrder)orderList.getSelectedValue();
        if (order == null) return;

        try {
            JTextArea area = new JTextArea(OrderDB.getReciept(order.getId()) +
                    "\n------------------\nMark order as done?");
            area.setEnabled(false);

            int answer = JOptionPane.showConfirmDialog(
                    this, area,
                    "Reciept", JOptionPane.OK_CANCEL_OPTION);

            if (answer == JOptionPane.CANCEL_OPTION){
                return;
            }

            try {
                OrderDB.setOrderAsDone(order.getId());
            } catch (SQLException e2) {
                JOptionPane.showMessageDialog(
                        this, "Failed to mark order as done:\n" + e2.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            
        }

        
    }//GEN-LAST:event_btnShowRecieptActionPerformed

    /*
     * Dispose/close the frame when cancel is pushed
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPane1;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnShowReciept;
    private javax.swing.JList orderList;
    // End of variables declaration//GEN-END:variables

}
