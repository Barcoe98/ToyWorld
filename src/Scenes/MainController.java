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



package Scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.Toy;
import Model.ToyModel;



public class MainController implements Initializable {
	
	private Stage dialogStage = new Stage();  
    private Scene scene;    
    private ToyModel shop;
    
    @FXML
    private TableView<Toy> toyTable;
    @FXML
    private TableColumn<Toy, Integer> colToyId;
    @FXML
    private TableColumn<Toy, String> colName;
    @FXML
    private TableColumn<Toy, Double> colCost;
    @FXML
    private TableColumn<Toy, Double> colWeight;
    @FXML
    private TableColumn<Toy, String> colCategory;
    @FXML
    private TableColumn<Toy, String> colSubCategory;
    @FXML
    private TextField txtToyId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCost;
    @FXML
    private TextField txtWeight;
    @FXML
    private ComboBox<String> comboCategory;
    @FXML
    private ComboBox<String> comboSubCategory;
    @FXML
    private Label txtFeedBack;
    
    @FXML
    private TableView<Toy> cartTable;
    @FXML
    private TableColumn<Toy, String> cartName;
    @FXML
    private TableColumn<Toy, Integer> cartQuantity;;
    @FXML
    private TableColumn<Toy, Double> cartCost;
    @FXML
    private TextField cartTxtName;
    @FXML
    private TextField cartTxtCost;
    @FXML
    private TextField cartTxtQuantity;
    @FXML
    private Label cartFeedBackLabel;
    @FXML
    private Label cartQuantityLabel; 
    @FXML
    private Label totalCostLabel;
    @FXML
    private double totalCost = 0.00;
    @FXML
    private int QuantityCount = 0;

    
    /*
     * mainController()
     */
    public MainController() {
    }
    
    /**
     * AddNewToy()
     * when user fills in all the fields correctly
     * and then clocks the add toy button the toy will be added to the shop
     * *else if hte us iputs the wrong fields they will get ana error message
     * @throws Exception 
     */
	@FXML
    void AddNewToy(ActionEvent event) throws Exception {
	if (TextValidation()) {
    	int toyId = Integer.parseInt(txtToyId.getText());
		String name = txtName.getText();
		double cost = Double.parseDouble(txtCost.getText());
		double weight = Double.parseDouble(txtWeight.getText());
		
		shop.addToy(toyId, name, cost, weight);
		shop.saveToys();
		txtFeedBack.setText("Toy Added to the Shop");
		txtFeedBack.setTextFill(Color.rgb(21, 117, 84));
		
    	txtToyId.clear();
    	txtName.clear();
    	txtCost.clear();
    	txtWeight.clear();
   	 }
	}

     
	 /**
	 * DeleteToy()
     * when item clicked on toyTable , it will highlight the item and will be deleted from toyTable
     * if user deletes the toy correctly they will get a alert warning saying the toy has been deleted 
     * *else the the user will get an alert warning saying no toy selected 
	 * @throws Exception 
     */
    @FXML
    void DeleteToy(ActionEvent event) throws Exception {
    	int selectedToyIndex = toyTable.getSelectionModel().getSelectedIndex();
    	  
    	    if (selectedToyIndex >= 0) {
    	        shop.remove(selectedToyIndex); 
    	        shop.saveToys();
    	        txtFeedBack.setText("Toy Deleted from Shop"); 
    	        txtFeedBack.setTextFill(Color.rgb(210, 39, 30));
    	    } else {
    	        txtFeedBack.setText("Please Select a Toy"); 
    	    }
    }

    /*
     * editToysDetails()
     * show toy attributes to be able to set to the textfields
     */
  	public void editToysDetails(Toy stock) {
 		if (stock != null) {
            txtName.setText(stock.getName());
            txtToyId.setText(Integer.toString(stock.getToyId()));
            txtCost.setText(Double.toString(stock.getCost()));
            txtWeight.setText(Double.toString(stock.getWeight()));
 		} 
 		else {
        	txtName.setText("");
        	txtToyId.setText("");
        	txtCost.setText("");
        	txtWeight.setText("");
        }
 }


    /*
     * DisplayToy()
     * gets selected toy in table and displays it into th textfields
     * so that they can be edited
     * if there is nothing selected you will get an error message saying no Toy selected
     */
     @FXML
     void DisplayToy(ActionEvent event) {
    	    Toy selectedToy = toyTable.getSelectionModel().getSelectedItem();      
    	 
    	        if (selectedToy != null) {
    	        	editToysDetails(selectedToy);
    	        } 
    	        else {  
    	        	txtFeedBack.setText("Please Select a Toy");
    	        }
    	    }
     
     
     /*
  	 * saveEdit()
  	 * if text is valid set the text of each textfield to the each attributes of the toy
  	 * then when toy is edited save edit and it will update the item 
  	 */
     @FXML
     public void saveEdit(ActionEvent event) throws Exception {     
    	/*
    	 if (TextValidation()) {
             stock.setName(txtName.getText());
             stock.setToyId(Integer.parseInt(txtToyId.getText()));
             stock.setCost(Double.parseDouble(txtCost.getText()));
             stock.setWeight(Double.parseDouble(txtWeight.getText()));
             shop.saveToys();
             }
             */
     }
     
     
     
     
	/*
 	 * collectToyDetails()
 	 * set the attributes of the toy selected to the textFields
 	 */
 	void collectToyDetails(Toy stock){
 		if (stock != null) {
            cartTxtName.setText(stock.getName());
            cartTxtCost.setText(Double.toString(stock.getCost()));
        } else {
        	txtName.setText("");
        	txtCost.setText("");
        	cartTxtQuantity.setText("");
        }
		}
 	
 	
     	 /*
         * displayCartToy()
         * displays a selected toy to the textfields so it can be added to the cart  
         * else you will get an error message saying please select a toy if there is no toy selected
         */
      @FXML
     void displayCartToy(ActionEvent event) {
       Toy selectedToy = toyTable.getSelectionModel().getSelectedItem();  
    	  if (selectedToy!= null) {        	
        	     collectToyDetails(selectedToy);
        	     } else {
        	    	 txtFeedBack.setText("Please Select a Toy");
        	        }
        	    }

      
     	/*
     	 * AddToCart()
     	 * adds item from the textfields to your cart 
     	 * then it also add quantity and total cost to cart 
     	 */
		@FXML
     	void AddToCart(ActionEvent event) {
     		cartName.setCellValueFactory(new PropertyValueFactory<Toy,String>("name"));
     		cartCost.setCellValueFactory(new PropertyValueFactory<Toy,Double>("cost"));
     		cartQuantity.setCellValueFactory(new PropertyValueFactory<Toy,Integer>("quantity"));

     		if (cartValidation()) {
     	    cartTable.getItems().add(new Toy(
     		cartTxtName.getText(),
     		Double.parseDouble(cartTxtCost.getText()),
     		Integer.parseInt(cartTxtQuantity.getText())));
     	   
     	    totalCost();

     		cartTxtName.clear();
     	    cartTxtCost.clear();
     		cartTxtQuantity.clear();
     		cartFeedBackLabel.setTextFill(Color.rgb(21, 117, 84));
       	        }
 
	        }
		
		
		/*
		 * totalCost()
		 *  multiplies quantity with cost to get sub-total 
		 *  then adds subtotal with total cost 
		 *  and prints to the total cost label
		 *  so that the user knows the amount of items in there basket 
		 *  get the quantity from textfield and prints it to the label 
		 *  so that the user knows the amount of items in there basket 
		 */
		public void totalCost()  {       
			 int quantity = Integer.parseInt(cartTxtQuantity.getText());
			 int selectedToyIndex = toyTable.getSelectionModel().getSelectedIndex();
	         double subTotal = quantity * toyTable.getItems().get(selectedToyIndex).getCost();

			if (selectedToyIndex >= 0) {
	        	//add the subtotal to totalCost and prints to label
	        	totalCost = totalCost + subTotal;
	            totalCostLabel.setText("" + totalCost);

	            //add the qaunity of each item to the label
	            QuantityCount =  QuantityCount + quantity;
	        	cartQuantityLabel.setText("" + QuantityCount);
	        }
	        	else {
	        		totalCostLabel.setText("Error");
			}
	  }

		
		/*
		 * removeCost()
		 * when a user removes a toy from there cart it will update the cost and cart quantity
		 */
		public void removeCost() {
			 int quantity = Integer.parseInt(cartTxtQuantity.getText());
			 int selectedToyIndex = toyTable.getSelectionModel().getSelectedIndex();
			 double subTotal = quantity * toyTable.getItems().get(selectedToyIndex).getCost();
			 
			if (selectedToyIndex >= 0) {
			
	            totalCost = totalCost - subTotal;
	            totalCostLabel.setText("" + totalCost);
	            

	            QuantityCount =  QuantityCount - quantity;
	        	cartQuantityLabel.setText("" + QuantityCount);
	        }
	        	else {
	        		totalCostLabel.setText("Error");
			}
	  }
		
		
		/*
		 * DeleteFromCart()
		 * deletes selected toy from the cart
		 */
     	@FXML
     	public void DeleteFromCart(ActionEvent event) {
      int selectedToyIndex = toyTable.getSelectionModel().getSelectedIndex();

 	    if (selectedToyIndex >= 0) {
 	        cartTable.getItems().remove(selectedToyIndex); 
 	        //removeCost();
 	        cartFeedBackLabel.setText("Toy deleted from cart");
 	    } else {
 	    	txtFeedBack.setText("Please Select a Toy");
 	    }
      }
     	   
     	
     			/*
     		 	* CheckOutBtn
     		 	* when user has completed there order
     		 	* a dialog box pops up and teel you what your total cost is 
     		 	* and clears your cart 
     	 		*/
     		@FXML
     	void CheckOutBtn(ActionEvent event) {   
     			Alert alert = new Alert(AlertType.INFORMATION);
     	        alert.setTitle("Order Completed");
     	        alert.setHeaderText("Order Completed");
     	        alert.setContentText("Thank You for Your order, have a nice day \n" + "Total Cost: " + totalCost);   	  
     	        alert.showAndWait();
     	        totalCostLabel.setText("0.00");
     	        cartQuantityLabel.setText("0");
     	        cartTable.getItems().clear();
     			}


			/**
			 	* if user enters a toyId that is either a null entry , no characters, any letters or a number greater then 6 chracters 
			 	* they will get a error
			 	* if user enters a name is either a null entry , no characters or contains numbers
			 	* they will get a error
			 	* if user enters a cost is either a null entry , no characters or contains letters
			 	* they will get a error
			 	* if user enters a weight is either a null entry , no characters or contains letters
			 	* they will get a error
			 	* all the errors are added to a string and printed in a alert box
			 **/
       	private boolean TextValidation() {
    	   	String Error = "";

           if (txtToyId.getText() == null || txtToyId.getText().length() == 0 
        		   || txtToyId.getText().contains("[a-zA-Z]+") || txtToyId.getText().length() > 6) {
        	   Error += "Please enter a Valid Id!\n"; 
           }
           if (txtName.getText() == null || txtName.getText().length() == 0 || txtName.getText().matches(".*\\d+.*")) {
        	   Error += "Please enter a Valid Name!\n"; 
           }
           if (txtCost.getText() == null || txtCost.getText().length() == 0 || txtCost.getText().contains("[a-zA-Z]+")) { 
        	   Error += "Please enter a Valid Cost!\n"; 
           }
           if (txtWeight.getText() == null || txtWeight.getText().length() == 0 || txtWeight.getText().contains("[a-zA-Z]+")) {
        	   Error += "Please enter a Valid Weight!\n"; 
   
           } 
           if (Error.length() == 0) {
               return true;
           }
           else {
               // Show the error message.
               Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Invalid Fields");
               alert.setHeaderText("Please correct invalid fields");
               alert.setContentText(Error);
               alert.showAndWait();
               return false;
           }
       }

        /**
         * SignOut()
         * when button clicked returns user to login screen
         * so they can exit application or sign in as a differnet user
         */
        	public void SignOut(ActionEvent event){
       	
        		Node node = (Node)event.getSource();
        		dialogStage = (Stage) node.getScene().getWindow();
        		dialogStage.close();
                   	try {
                   		scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                   		} 
                   	catch (IOException e1) {
                   		e1.printStackTrace();
                   		}
                   	dialogStage.setScene(scene);
                   	dialogStage.show();
        			}     
        	
        	
        	/**
        	 * cartValidation()
		 	* if user enters a name is either a null entry , no characters or contains numbers
		 	* they will get a error
		 	* if user enters a cost is either a null entry , no characters or contains letters
		 	* they will get a error
		 	* if user enters a quantity is either a null entry , no characters or contains letters
		 	* they will get a error
		 	* all the errors are added to a string and printed in a alert box
		    **/
        	
        	private boolean cartValidation() {
        	   	String Error = "";
        	   	
               if (cartTxtName.getText() == null || cartTxtName.getText().length() == 0 || cartTxtName.getText().contains(".*\\d+.*")) {
            	   Error += "No valid Name!\n"; 
               }
               if (cartTxtCost.getText() == null || cartTxtCost.getText().length() == 0 || cartTxtCost.getText().contains("[a-zA-Z]+")) { 
            	   Error += "No valid Cost!\n"; 
               }
               if (cartTxtQuantity.getText() == null || cartTxtQuantity.getText().length() == 0 || cartTxtQuantity.getText().contains("[a-zA-Z]+")) {
            	   Error += "No valid Quantity!\n"; 
               } 
               if (Error.length() == 0) {
                   return true;
               }
               else {
                   // Show the error message.
                   Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Invalid Fields");
                   alert.setHeaderText("Please correct invalid fields");
                   alert.setContentText(Error);
                   alert.showAndWait();
                   return false;
               }
           }
        	
        	
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			shop = new ToyModel();
			//loads toys from toys.xml and puts them into table
			try {
				shop.loadToys();
			} catch (Exception e) {
				e.printStackTrace();
			}
				//sets up each coloum of the table to take data from the user
			    //toyTable.setEditable(true);
			    colToyId.setCellValueFactory(new PropertyValueFactory<Toy,Integer>("toyId"));
		        colName.setCellValueFactory(new PropertyValueFactory<Toy,String>("name"));
		        colCost.setCellValueFactory(new PropertyValueFactory<Toy,Double>("cost"));
		        colWeight.setCellValueFactory(new PropertyValueFactory<Toy,Double>("weight"));
		        colCategory.setCellValueFactory(new PropertyValueFactory<Toy,String>("category"));
		        colSubCategory.setCellValueFactory(new PropertyValueFactory<Toy,String>("subCategory"));
		        toyTable.setItems(shop.getStock());
		}
		
}
