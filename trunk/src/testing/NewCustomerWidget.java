package testing;

import com.trolltech.qt.gui.*;
/**
 * This class takes and validates customer data input from the user, before sending 
 * 4 string objects with Signal4 customerAdd back to the parent class.
 * @author Audun
 *
 */
public class NewCustomerWidget extends QDialog{
	private QPushButton ok;
	private QPushButton cancel;
	public QLineEdit nameWrite;
	public QLineEdit famNameWrite;
	public QLineEdit addressWrite;
	public QLineEdit zipWrite;
	public Signal4<String,String,String,String> customerAdd = new Signal4<String,String,String,String>();
	
    public NewCustomerWidget(QWidget parent){
        super(parent);
        makeWidget();
    }
    public NewCustomerWidget(){
        makeWidget();
    }
    private void makeWidget(){
    	QVBoxLayout hLayout = new QVBoxLayout();
    	QHBoxLayout buttonLayout = new QHBoxLayout();
    	QGridLayout layout = new QGridLayout();
    	nameWrite = new QLineEdit();
    	famNameWrite = new QLineEdit();
    	addressWrite = new QLineEdit();
    	zipWrite = new QLineEdit();
    	ok = new QPushButton("Ok");
    	cancel = new QPushButton("Cancel");
    	createActions();
    	
    	layout.addWidget(new QLabel("Fornavn:"), 0, 0);
    	layout.addWidget(new QLabel("Etternavn:"),1, 0);
    	layout.addWidget(new QLabel("Gateadresse:"), 2, 0);
    	layout.addWidget(new QLabel("Postboks:"), 3, 0);
    	
    	layout.addWidget(nameWrite, 0, 1);
    	layout.addWidget(famNameWrite, 1, 1);
    	layout.addWidget(addressWrite, 2, 1);
    	layout.addWidget(zipWrite, 3, 1);
    	
    	
    	hLayout.addLayout(layout);
    	buttonLayout.addWidget(ok);
    	buttonLayout.addWidget(cancel);
    	hLayout.addLayout(buttonLayout);
    	setLayout(hLayout);
    	
    }
    
    private void createActions(){
    	cancel.clicked.connect(this, "close()");
    	ok.clicked.connect(this, "addCustomer()");
    	customerAdd.connect(this.parentWidget(), "addCustomerToDB(String,String,String,String)");

    }
    public void addCustomer(){
    	customerAdd.emit(nameWrite.text(), famNameWrite.text(), addressWrite.text(), zipWrite.text());
    	this.close();
    }
}