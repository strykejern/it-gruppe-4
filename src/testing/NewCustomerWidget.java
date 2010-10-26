package testing;

import com.trolltech.qt.core.QRegExp;
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
	public QLineEdit phoneNumber;
	public QLineEdit comment;
	private Customer customer;
	public Signal1<Customer> customerAdd = new Signal1<Customer>();
	
    public NewCustomerWidget(QWidget parent){
        super(parent);
        makeWidget();
    }
    private void makeWidget(){
    	this.setWindowTitle("Ny Kunde");
    	QVBoxLayout hLayout = new QVBoxLayout();
    	QHBoxLayout buttonLayout = new QHBoxLayout();
    	QGridLayout layout = new QGridLayout();
    	nameWrite = new QLineEdit();
    	nameWrite.setValidator(new QRegExpValidator(new QRegExp("^[\\s\\w]{0,20}$"), null));

    	famNameWrite = new QLineEdit();
    	famNameWrite.setValidator(new QRegExpValidator(new QRegExp("^[\\w]{0,20}$"), null));

    	addressWrite = new QLineEdit();
    	addressWrite.setValidator(new QRegExpValidator(new QRegExp("^[\\d\\s\\w]{0,20}$"), null));
    	
    	zipWrite = new QLineEdit();
    	zipWrite.setValidator(new QRegExpValidator(new QRegExp("^[0-9]{0,4}$"), null));
    	
    	phoneNumber = new QLineEdit();
    	phoneNumber.setValidator(new QRegExpValidator(new QRegExp("^[0-9]{0,8}$"), null));
    	
    	comment = new QLineEdit();
    	addressWrite.setValidator(new QRegExpValidator(new QRegExp("^[\\d\\s\\w]{0,30}$"), null));

    	ok = new QPushButton("Ok");
    	cancel = new QPushButton("Cancel");
    	createActions();
    	
    	layout.addWidget(new QLabel("Fornavn:"), 0, 0);
    	layout.addWidget(new QLabel("Etternavn:"),1, 0);
    	layout.addWidget(new QLabel("Gateadresse:"), 2, 0);
    	layout.addWidget(new QLabel("Postboks:"), 3, 0);
    	layout.addWidget(new QLabel("Telefon:"), 4, 0);
    	layout.addWidget(new QLabel("Kommentar:"), 5, 0);

    	
    	layout.addWidget(nameWrite, 0, 1);
    	layout.addWidget(famNameWrite, 1, 1);
    	layout.addWidget(addressWrite, 2, 1);
    	layout.addWidget(zipWrite, 3, 1);
    	layout.addWidget(phoneNumber, 4, 1);
    	layout.addWidget(comment, 5, 1);

    	
    	
    	hLayout.addLayout(layout);
    	buttonLayout.addWidget(ok);
    	buttonLayout.addWidget(cancel);
    	hLayout.addLayout(buttonLayout);
    	setLayout(hLayout);
    	
    }
    
    private void createActions(){
    	cancel.clicked.connect(this, "close()");
    	ok.clicked.connect(this, "addCustomer()");
    	customerAdd.connect(this.parentWidget(), "addCustomerToDB(Customer)");

    }
    public void addCustomer(){
    	customer = new Customer(0, nameWrite.text(),famNameWrite.text(),
    			Integer.parseInt(phoneNumber.text()),addressWrite.text(),
    			Integer.parseInt(zipWrite.text()),comment.text());
    	customerAdd.emit(customer);
    	this.close();
    }
}
