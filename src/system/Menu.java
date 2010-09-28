package system;

import java.*;

/* Class for editing menu-DB 
 * 
 * @author Lars
 */
public class Menu {
	//String for printing menu
	public String menuPrint ="Nr" + "\t" + "Name" + "\t" + "\t" + "Contents" + "\n"; 
	
	//constructs a menu and prints it
	public Menu(){
		OrderDB dishGetter = new OrderDB();
		  int nr=0;
		  Dish nextDish = dishGetter.getDish(nr); //next dish for menubuilding
		  while(nextDish.name!=null){
			  menuPrint = menuPrint + nextDish.nr + "\t" + nextDish.name +"\t" + 
			  "\t" + nextDish.contents + "\n";
			  nr++;
			  nextDish=dishGetter.getDish(nr);
		  }
		  System.out.print(this.menuPrint);
		}
	}
	
	

class Dish{
	public int nr;			//number in menu
	public String name;		//name of dish
	public String contents;	//contents of dish

	//creates new Dish-object
	public Dish(int nrIn, String nameIn, String contentsIn){ 
		this.nr=nrIn;
		this.name=nameIn;
		this.contents=contentsIn;
	}
}


