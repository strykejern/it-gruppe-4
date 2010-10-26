package testing;

import java.sql.SQLException;
import java.util.ArrayList;

import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class CustomerWidget extends QWidget{
	private QTableView customerTableViewer;
	private QAbstractItemModel customerTableModel;
	private ArrayList<Customer> customers;
	
    public CustomerWidget(QWidget parent){
        super(parent);
        createModel();
        createViewer();
        updateModel();
    }
    private void createViewer() {
		customerTableViewer = new QTableView();
		customerTableViewer.setSelectionMode(SelectionMode.SingleSelection);
		customerTableViewer.setModel(customerTableModel);
		customerTableViewer.resizeColumnsToContents();
	}
    private void customerTableViewerUpdate(){
    	customerTableViewer.setModel(customerTableModel);
		customerTableViewer.resizeColumnsToContents();
    }
    private void createModel() {
		customerTableModel = new QStandardItemModel(0, 7);
		customerTableModel.setHeaderData(0, Orientation.Horizontal, "Id");
		customerTableModel.setHeaderData(1, Orientation.Horizontal, "Fornavn");
		customerTableModel.setHeaderData(2, Orientation.Horizontal, "Etternavn");
		customerTableModel.setHeaderData(3, Orientation.Horizontal, "Telefon");
		customerTableModel.setHeaderData(4, Orientation.Horizontal, "Adresse");
		customerTableModel.setHeaderData(5, Orientation.Horizontal, "Postboks");
		customerTableModel.setHeaderData(6, Orientation.Horizontal, "Kommentar");

	}
    public void updateModel(){
    	try {
			customers = OrderDB.getAllCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		putCustomerListInModel(customers);
    	customerTableViewerUpdate();	
    }
    private void putCustomerListInModel(ArrayList<Customer> list){
    	createModel();
    	for(Customer i: list){ 		
    		customerTableModel.insertRow(0);
    		customerTableModel.setData(customerTableModel.index(0,1),i.firstName);
    		customerTableModel.setData(customerTableModel.index(0,2),i.lastName);
    		customerTableModel.setData(customerTableModel.index(0,3),i.phoneNumber);
    		customerTableModel.setData(customerTableModel.index(0,4),i.address);
    		customerTableModel.setData(customerTableModel.index(0,5),i.postalCode);
    		customerTableModel.setData(customerTableModel.index(0,6),i.comment);
    	}
    }
}
