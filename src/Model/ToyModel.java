/**
* This program implements an eshop called ToyWorld
* in the shop you can login as a administrator or a customer
* you can also register as a new user
* when you log in as admin u will be able to add, delete and edit a toy
* when u login as a customer u can seach the shop and add/delete items to your cart and checkout with them 
* 
* refernces
* class work
* https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJABIEED
* https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
* https://docs.oracle.com/javafx/2/get_started/form.htm
* https://docs.oracle.com/javase/8/scene-builder-2/get-started-tutorial/jfxsb-get_started.htm
* https://www.tutorialspoint.com/javafx/javafx_ui_controls.htm
* 
* @author  michael barcoe(20080330)
* @version 1.0
* @since   2018-10-24 
*/

package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToyModel{
	
	/*ATTRIBUTES*/
	private ObservableList<Toy> stock;
	
	public ToyModel()
	{
		    stock = FXCollections.observableArrayList();
	}

	

	//adds toy to shop
  public void addToy(int toyId, String name, double cost, double weight) {
		Toy toy = new Toy(toyId, name, cost, weight);
	     stock.add(toy);
	}
  
	//removes a toy from shop
  public void remove(int index)
  {
	  stock.remove(index);
  }
	

	/**
     * save() - This method saves the contents of the stock to 
     * an XML file called toys.xml
     * */
     public void saveToys() throws Exception {
     XStream xstream = new XStream(new DomDriver());
     ObjectOutputStream out = xstream.createObjectOutputStream
     (new FileWriter("toys.xml"));
     out.writeObject(stock);
     out.close();
     }


     /**
     * load() - This method reads the contents of the XML file called 
     * toys.xml stored in the project directory.  
     * */
     @SuppressWarnings("unchecked")
	public void loadToys() throws Exception {
     XStream xstream = new XStream(new DomDriver());
     ObjectInputStream is = xstream.createObjectInputStream
     (new FileReader("toys.xml"));
     stock = (ObservableList<Toy>) is.readObject();
     is.close();
     }
     
 	public ObservableList<Toy> getStock() {
		return stock;
	}
 	
	public void setStock(ObservableList<Toy> stock) {
		this.stock = stock;
	}
}

