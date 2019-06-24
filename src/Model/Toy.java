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

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Toy {

	/*ATTRIBUTES*/
	private SimpleIntegerProperty toyId;
	private SimpleStringProperty name;
    private SimpleDoubleProperty cost;
    private SimpleDoubleProperty weight;
    private SimpleIntegerProperty quantity;
	private SimpleStringProperty category;
	private SimpleStringProperty subCategory;
    
    /*CONSTRUCTOR*/
    public Toy() {
    
    }

    public Toy(int toyId, String name, double cost, double weight, String category, String subCategory) {
    	
    	this.toyId = new SimpleIntegerProperty(toyId);
    	this.name = new SimpleStringProperty(name);
        this.cost = new SimpleDoubleProperty(cost);
        this.weight = new SimpleDoubleProperty(weight);
    	this.category = new SimpleStringProperty(category);
    	this.subCategory = new SimpleStringProperty(subCategory);
    }
    
    public Toy(int toyId, String name, double cost, double weight) {
    	
    	this.toyId = new SimpleIntegerProperty(toyId);
    	this.name = new SimpleStringProperty(name);
        this.cost = new SimpleDoubleProperty(cost);
        this.weight = new SimpleDoubleProperty(weight);
 
    }
    
    public Toy( String name, double cost, int quantity) {
    	
    	this.name = new SimpleStringProperty(name);
        this.cost = new SimpleDoubleProperty(cost);
        this.quantity = new SimpleIntegerProperty(quantity);
        
    }
    
	/*GETTERS AND SETTERS*/
    public int getToyId() {
        return toyId.get();
    }
    
    public void setToyId(int ToyId) {
        toyId.set(ToyId);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String Name) {
        name.set(Name);
    }

    public double getCost() {
        return cost.get();
    }
    
    public void setCost(double Cost) {
        cost.set(Cost);
    }
    
    public double getWeight() {
        return weight.get();
    }
    
    public void setWeight(double Weight) {
        weight.set(Weight);
    }
    
    public int getQuantity() {
        return quantity.get();
    }
    
    public void setQuantity(int Quantity) {
    	quantity.set(Quantity);
    }
    public SimpleStringProperty getCategory() {
		return category;
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public SimpleStringProperty getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SimpleStringProperty subCategory) {
		this.subCategory = subCategory;
	}

}


