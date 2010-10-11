package testing;

import com.trolltech.qt.gui.*;
/**
 * An early test of how the main window works, you will have to import qtjambi-4.5.2_01.jar and the qtjambi[yourOShere].jar to the local library
 * to compile and run the class locally, they can all be found in a zip file uploaded to the google code SVN directory, under Downloads.
 * 
 * @author Audun
 *
 */
public class MainWindowGui extends QMainWindow{
	private QMenu fileMenu;
    private QTabWidget tabs = new QTabWidget(this);
    private QAction exitAct;
    private QAction newCustomerAct;
    

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
    private void createTabs(){
    	tabs.addTab(new NewCustomerWidget(), "Ny Kunde");
    	tabs.addTab(new WebMap("file:///home/audun/workspace/guiTest/map.html"), "Kart");
    	setCentralWidget(tabs);
    }

    private void createMenus()
    {
        fileMenu = menuBar().addMenu(tr("&Fil"));
        fileMenu.addAction(exitAct);
        fileMenu.addAction(newCustomerAct);
    }
    public void nyKunde(){
    	NewCustomerWidget window = new NewCustomerWidget();
    	window.show();
    	
    }
}