package testing;
import com.trolltech.qt.core.QUrl;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.webkit.QWebView;

/** This class creates a map using parameters containing the
 * directions to the delivery place. It receives the delivery address 
 * from the Client Database.
 * 
 * @author Audun
 * (http://maps.google.com/maps/api/directions/json?) */

public class WebMap extends QWidget{

	public WebMap(String url){
		QWebView view = new QWebView(this);
		view.setUrl(new QUrl(url));
		}
	
	public static void main(String[] args){
		QApplication.initialize(args);
		WebMap window = new WebMap("file:///home/audun/workspace/guiTest/map.html");
		window.resize(450, 390);
		window.show();
		QApplication.exec();
	}
}