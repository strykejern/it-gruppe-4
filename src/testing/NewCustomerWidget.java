package testing;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QWidget;

public class NewCustomerWidget extends QWidget{

    public NewCustomerWidget(QWidget parent){
        super(parent);
        makeWidget();
    }
    public NewCustomerWidget(){
        makeWidget();
    }
    private void makeWidget(){
    	QGridLayout layout = new QGridLayout();
    	QLineEdit nameWrite = new QLineEdit();
    	QLineEdit famNameWrite = new QLineEdit();
    	QLineEdit addressWrite = new QLineEdit();
    	QLineEdit zipWrite = new QLineEdit();
    	
    	layout.addWidget(new QLabel("Fornavn:"), 0, 0);
    	layout.addWidget(new QLabel("Etternavn"),1, 0);
    	layout.addWidget(new QLabel("Gateadresse:"), 2, 0);
    	layout.addWidget(new QLabel("Postboks:"), 3, 0);
    	
    	layout.addWidget(nameWrite, 0, 1);
    	layout.addWidget(famNameWrite, 1, 1);
    	layout.addWidget(addressWrite, 2, 1);
    	layout.addWidget(zipWrite, 3, 1);
    	setLayout(layout);
    }
}
