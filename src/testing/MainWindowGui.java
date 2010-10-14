package testing;

import com.trolltech.qt.gui.*;
import com.trolltech.qt.core.QAbstractItemModel;
import com.trolltech.qt.core.Qt.Orientation;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;
/**
 * An early test of how the main window works, you will have to import qtjambi-4.5.2_01.jar and the qtjambi[yourOShere].jar to the local library
 * to compile and run the class locally, they can all be found in a zip file uploaded to the google code SVN directory, under Downloads.
 * 
 * @author Audun
 *
 */
public class MainWindowGui extends QMainWindow{
	private QMenu fileMenu;
    private QTabWidget tabs;
    private QAction exitAct;
    private QAction newCustomerAct;
    private NewCustomerWidget window;
    private QTableView tableViewer;
    private QAbstractItemModel tableModel;
    String[] newCustomer;
    

    public static void main(String[] args) {
        QApplication.initialize(args);

        MainWindowGui main = new MainWindowGui(null);
        main.resize(450, 390);
        main.show();
        
        QApplication.exec();
    }

    public MainWindowGui(QWidget parent){
        super(parent);
        createActions();
        createViewer();
        createModel();
        createMenus();
        createTabs();
    }

    private void createActions()
    {
        exitAct = new QAction(tr("E&xit"), this);
        exitAct.setShortcut(tr("Ctrl+Q"));
        exitAct.setStatusTip(tr("Avslutt"));
        exitAct.triggered.connect(this, "close()");
        
        newCustomerAct = new QAction(tr("&Ny Kunde"), this);
        newCustomerAct.setShortcut(tr("Ctrl+N"));
        newCustomerAct.setStatusTip(tr("Opprett ny kunde"));
        newCustomerAct.triggered.connect(this, "nyKunde()");
    }
    
    private void createModel(){
    	tableModel = new QStandardItemModel(0,6);
    	tableModel.setHeaderData(0,Orientation.Horizontal, "Id");
    	tableModel.setHeaderData(1,Orientation.Horizontal, "Fornavn");
    	tableModel.setHeaderData(2,Orientation.Horizontal, "Etternavn");
    	tableModel.setHeaderData(3,Orientation.Horizontal, "Telefon");
    	tableModel.setHeaderData(4,Orientation.Horizontal, "Adresse");
    	tableModel.setHeaderData(5,Orientation.Horizontal, "Postboks");
    	tableViewer.setSelectionMode(SelectionMode.SingleSelection);
    	tableViewer.setModel(tableModel);
    	tableViewer.resizeColumnsToContents();
    	
    }
    
    private void createViewer(){
    	tableViewer = new QTableView();
    }
    
    private void createTabs(){
    	tabs = new QTabWidget(this);
    	tabs.addTab(tableViewer, "Vis Kunder");
    	tabs.addTab(new WebMap("file:///home/audun/workspace/guiTest/map.html"), "Kart");
    	setCentralWidget(tabs);
    }

    private void createMenus()
    {
        fileMenu = menuBar().addMenu(tr("&Fil"));
        fileMenu.addAction(exitAct);
        fileMenu.addAction(newCustomerAct);
    }
    private void nyKunde(){
    	window = new NewCustomerWidget(this);
    	window.exec();
    	
    }
    private void addCustomerToDB(String prename, String lastname, String address, String zip){
    	newCustomer = new String[4];
    	newCustomer[0] = prename;
    	newCustomer[1] = lastname;
    	newCustomer[2] = address;
    	newCustomer[3] = zip;
    	
    	tableModel.insertRow(0);
    	tableModel.setData(tableModel.index(0,1),newCustomer[0]);
    	tableModel.setData(tableModel.index(0,2),newCustomer[1]);
    	tableModel.setData(tableModel.index(0,4),newCustomer[2]);
    	tableModel.setData(tableModel.index(0,5),newCustomer[3]);
    	tableViewer.setModel(tableModel);
    }
}