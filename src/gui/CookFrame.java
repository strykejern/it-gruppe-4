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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
        try {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode();

            ArrayList<FetchedOrder> orders = OrderDB.getCooksOrders();

            for (FetchedOrder order : orders){
                DefaultMutableTreeNode orderNode = new DefaultMutableTreeNode(order);

                for (DishOrder dish : order.getDishes()){
                    DefaultMutableTreeNode dishNode = new DefaultMutableTreeNode(dish, false);
                    orderNode.add(dishNode);
                }

                root.add(orderNode);
            }

            DefaultTreeModel model = new DefaultTreeModel(root);
            orderTree.setModel(model);
        }
        catch (SQLException e) {
            
        }

        updater = new UpdaterThread(this, 10 * 1000); // GUI updater with 10 second interval
        updater.start();
    }

    public void updateGUI() {
        // TODO: keep expanded trees and selection

        try {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode();

            ArrayList<FetchedOrder> orders = OrderDB.getCooksOrders();

            for (FetchedOrder order : orders){
                DefaultMutableTreeNode orderNode = new DefaultMutableTreeNode(order);

                for (DishOrder dish : order.getDishes()){
                    DefaultMutableTreeNode dishNode = new DefaultMutableTreeNode(dish, false);
                    orderNode.add(dishNode);
                }

                root.add(orderNode);
            }

            DefaultTreeModel model = new DefaultTreeModel(root);
            orderTree.setModel(model);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        orderTree = new javax.swing.JTree();
        orderInfoPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("JTree");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("food");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        orderTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        orderTree.setRootVisible(false);
        jScrollPane1.setViewportView(orderTree);

        orderInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        orderInfoPanel.setMaximumSize(new java.awt.Dimension(350, 32767));
        orderInfoPanel.setMinimumSize(new java.awt.Dimension(350, 100));
        orderInfoPanel.setPreferredSize(new java.awt.Dimension(350, 578));

        javax.swing.GroupLayout orderInfoPanelLayout = new javax.swing.GroupLayout(orderInfoPanel);
        orderInfoPanel.setLayout(orderInfoPanelLayout);
        orderInfoPanelLayout.setHorizontalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        orderInfoPanelLayout.setVerticalGroup(
            orderInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(orderInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
        updater.end();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel orderInfoPanel;
    private javax.swing.JTree orderTree;
    // End of variables declaration//GEN-END:variables

}
