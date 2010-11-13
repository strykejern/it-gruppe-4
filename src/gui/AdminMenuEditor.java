/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AdminMenuEditor.java
 *
 * Created on 13.nov.2010, 18:41:56
 */

package gui;

import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import system.Dish;
import system.Menu;
import system.OrderDB;

/**
 *
 * @author Anders
 */
public class AdminMenuEditor extends javax.swing.JFrame implements GUIUpdater {

    JFrame parent;

    UpdaterThread guiUpdater;

    /** Creates new form AdminMenuEditor */
    public AdminMenuEditor(JFrame parent) {
        this.parent = parent;

        initComponents();

        guiUpdater = new UpdaterThread(this, 10 * 1000);
        guiUpdater.start();
    }

    public void updateGUI() {
        try {
            Menu menu = OrderDB.getMenu();

            ListSelectionModel selection = menuList.getSelectionModel();

            DefaultListModel mod = new DefaultListModel();

            for (Dish dish : menu.getMenu()){
                mod.addElement(dish);
            }

            menuList.setModel(mod);

            menuList.setSelectionModel(selection);
        }
        catch (SQLException e){
            
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
        menuList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMenuComment = new javax.swing.JTextField();
        txtMenuPrice = new javax.swing.JTextField();
        txtMenuName = new javax.swing.JTextField();
        txtMenuId = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        menuList.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        menuList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                menuListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(menuList);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Id:");

        jLabel2.setText("Name:");

        jLabel3.setText("Price:");

        jLabel4.setText("Comment:");

        btnClear.setText("Clear fields");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMenuComment, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtMenuPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtMenuName, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtMenuId, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClear)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMenuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMenuPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMenuComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
        guiUpdater.end();
    }//GEN-LAST:event_formWindowClosed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            int dishId = Integer.parseInt(txtMenuId.getText());

            OrderDB.getDish(dishId);

            JOptionPane.showMessageDialog(this, "A dish with that ID already exists, either choose an available ID or click \"Edit\" to edit the current dish");
            return;
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid ID");
            return;
        }
        catch (SQLException e) {
            // Just continue, because this means there are no other dishes with the ID we are trying
        }

        try {
            Dish newDish = new Dish(txtMenuId.getText(), txtMenuName.getText(), txtMenuPrice.getText(), txtMenuComment.getText());

            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to create this dish?\n" + newDish, "Create dish", JOptionPane.OK_CANCEL_OPTION);

            if (answer == JOptionPane.CANCEL_OPTION) return;

            OrderDB.newDish(newDish);

            updateGUI();
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error creating the dish:\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            int dishId = Integer.parseInt(txtMenuId.getText());

            Dish oldDish = null;

            try {
                oldDish = OrderDB.getDish(dishId); // If this doesn't fail we know we are editing an exisiting dish
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "There are no dishes with that ID, use the \"New\" button to create a new dish");
                return;
            }

            Dish updatedDish = new Dish(txtMenuId.getText(), txtMenuName.getText(), txtMenuPrice.getText(), txtMenuComment.getText());

            int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit this dish:\n" + oldDish + "\nTo this:\n" + updatedDish, "Update dish", JOptionPane.OK_CANCEL_OPTION);

            if (answer == JOptionPane.CANCEL_OPTION) return;

            OrderDB.updateDish(updatedDish, dishId);

            updateGUI();
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error editing dish:\n" + e.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMenuId.setText("");
        txtMenuName.setText("");
        txtMenuPrice.setText("");
        txtMenuComment.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void menuListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_menuListValueChanged
        Dish selected = (Dish)menuList.getSelectedValue();

        if (selected == null) return;

        txtMenuId.setText(selected.nr+"");
        txtMenuName.setText(selected.name);
        txtMenuPrice.setText(selected.price+"");
        txtMenuComment.setText(selected.description != null ? selected.description : "");
    }//GEN-LAST:event_menuListValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Dish selected = (Dish)menuList.getSelectedValue();

        if (selected == null) return;

        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this dish:\n" + selected, "Delete dish", JOptionPane.OK_CANCEL_OPTION);

        if (answer == JOptionPane.CANCEL_OPTION) return;

        try {
            OrderDB.deleteDish(selected);

            updateGUI();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error deleting the dish:\n" + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList menuList;
    private javax.swing.JTextField txtMenuComment;
    private javax.swing.JTextField txtMenuId;
    private javax.swing.JTextField txtMenuName;
    private javax.swing.JTextField txtMenuPrice;
    // End of variables declaration//GEN-END:variables

}
