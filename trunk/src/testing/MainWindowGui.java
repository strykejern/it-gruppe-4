package testing;
import static java.lang.System.out;
import java.sql.SQLException;
import com.trolltech.qt.gui.*;
/**
 * An early test of how the main window works, you will have to import qtjambi-4.5.2_01.jar and the qtjambi[yourOShere].jar to the local library
 * to compile and run the class locally, they can all be found in a zip file uploaded to the google code SVN directory, under Downloads.
 * 
 * @author Audun
 *
 */
public class MainWindowGui extends QMainWindow {
	private QMenu fileMenu;
	private QTabWidget tabs;
	private QAction exitAct;
	private QAction newCustomerAct;
	private NewCustomerWidget window;
	private CustomerWidget customerWidget;
	String[] newCustomer;
	String menuString;

	public static void main(String[] args) {
		try {
			OrderDB.initializeDB("andereie_system", "restaurant",
					"jdbc:mysql://mysql.stud.ntnu.no/andereie_itgrupp");
		
		
		
		} catch (Exception e) {
			out.println("Connection failed: " + e.toString());
			return;
		}
		QApplication.initialize(args);
		MainWindowGui main = new MainWindowGui();
		main.resize(450, 390);
		main.setWindowTitle("Restaurant");
		main.show();
	}

	public MainWindowGui() {
		createActions();
		createCustomerGui();
		createMenus();
		createTabs();
	}

	private void createActions() {
		exitAct = new QAction(tr("E&xit"), this);
		exitAct.setShortcut(tr("Ctrl+Q"));
		exitAct.setStatusTip(tr("Avslutt"));
		exitAct.triggered.connect(this, "close()");

		newCustomerAct = new QAction(tr("&Ny Kunde"), this);
		newCustomerAct.setShortcut(tr("Ctrl+N"));
		newCustomerAct.setStatusTip(tr("Opprett ny kunde"));
		newCustomerAct.triggered.connect(this, "nyKunde()");
	}

	private void createCustomerGui() {
		customerWidget = new CustomerWidget(this);
	}

	private void createTabs() {
		tabs = new QTabWidget(this);
		tabs.addTab(customerWidget, "Vis Kunder");
		tabs.addTab(
				new WebMap("file:///home/audun/workspace/guiTest/map.html"),
				"Kart");

		setCentralWidget(tabs);
	}

	private void createMenus() {
		fileMenu = menuBar().addMenu(tr("&Fil"));
		fileMenu.addAction(exitAct);
		fileMenu.addAction(newCustomerAct);
	}

	public void addCustomerToDB(Customer cust) {

		try {
			OrderDB.addCustomer(cust);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception o) {
			o.printStackTrace();

		}
		customerWidget.updateModel();
	}

	private void nyKunde() {
		window = new NewCustomerWidget(this);
		window.exec();

	}
}
