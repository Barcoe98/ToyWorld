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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import Model.UserModel;




public class LoginController implements Initializable {

	Stage dialogStage = new Stage(); 
    Scene scene;  
    private UserModel userlog;
    
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button ExitButton;
    @FXML
    private TextField txtFname;
    @FXML
    private TextField txtLname;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtGender;
    @FXML
    private Label txtFeedback;
	

    
    public LoginController()  {
	}
    
    
    /*
     * AddNewUser()
     * get the users data from the textfields
     * and creates a user
     */
    @FXML
    	public void AddNewUser(ActionEvent event) throws Exception {

    		String fname = txtFname.getText();
    		String lname = txtLname.getText();
    		String uname = txtUsername.getText();
    		String pword = txtPassword.getText();
    		String age = txtAge.getText();
    		String gender = txtGender.getText();
    		
    		userlog.addUser(fname, lname, uname, pword, age, gender);
    		userlog.saveUsers();
    		txtFeedback.setText("User Added");

    		}


    
    	/**
    	 	*when user enters their login details
    	 	*they will click the login buttonand 
    	 	*they will check against a right match to bring up screen 
    	 	*
    	*/
    public void LoginButton(ActionEvent event) throws Exception {
	
    	//check if the user has put in the correct admin username and password
    	if (adminLoginCheck(txtUsername, txtPassword)) {
			txtFeedback.setText("Admin Login Success");
			txtFeedback.setTextFill(Color.rgb(21, 117, 84));
			dialogStage.close();
			Stage dialogStage = new Stage();	
			Parent root = FXMLLoader.load(getClass().getResource("/Scenes/MainScreen.fxml"));
			Scene scene = new Scene(root,1000,500);
			dialogStage.setScene(scene);
			dialogStage.show();
			
			}	
    	
    	//if the user doesnt match the admin username and password
    	//try to match with the customer username and password
    	
			else if (userLoginCheck(txtUsername, txtPassword)) {
				txtFeedback.setText("User Login Success");
				txtFeedback.setTextFill(Color.rgb(21, 117, 84));
				dialogStage.close();
				Stage dialogStage = new Stage();	
				Parent root = FXMLLoader.load(getClass().getResource("/Scenes/CustomerScreen.fxml"));
				Scene scene = new Scene(root,1000,500);
				dialogStage.setScene(scene);
				dialogStage.show();
			
			} 
    			//else they will get a pop up saying incorrect username and password
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Incorect Username & Password");
					alert.setHeaderText("Incorect Username & Password");
					alert.setContentText("Please enter a different Username & Password");
					alert.showAndWait();
					}
			}

    		/**
    		 	*if the users wants to create and account 
    		 	*the user will press register and they will be brought to the registration screen
    		 */
    	public void RegisterButton(ActionEvent e){
    		Node node = (Node)e.getSource();
    		dialogStage = (Stage) node.getScene().getWindow();
    		dialogStage.close();
    		try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("/Scenes/Register.fxml")));
            		} 
    		catch (IOException e1) {
            e1.printStackTrace();
            		}
    		dialogStage.setScene(scene);
    		dialogStage.show();
    				}
    	
    	/**
    	 	* if the user decides to not register 
    	 	* they can press the cancel button and they will be brought back to the login screen
    	*/
    	public void CancelButton(ActionEvent e){
    		Node node = (Node)e.getSource();
    		dialogStage = (Stage) node.getScene().getWindow();
    		dialogStage.close();
            try {
            	scene = new Scene(FXMLLoader.load(getClass().getResource("/Scenes/Login.fxml")));
            	} 
            catch (IOException e1) {
            	e1.printStackTrace();
            	}
            dialogStage.setScene(scene);
            dialogStage.show();
            }


    		/**
    		 * when exit button is pressed the application will stop
    		 */
    	public void ExitButton(){

    			Platform.exit();
    		}

    		/**
    		 	*adminLoginCheck checks if the required username and password is given  
    		 	*if the details are valid the admin will be brought to the admin screen
    		 */
    	private boolean adminLoginCheck(TextField txtUsername, TextField txtPassword){
    		if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("pass"))
    			return true;
    		return false;
    		}


    		/**
    		 	*userLoginCheck checks if the required username and password is given  
    		 	*if the details are valid the user will be brought to the customer screen
    		 */
			private boolean userLoginCheck(TextField txtUsername, TextField txtPassword){
    			if(txtUsername.getText().equals("user") && txtPassword.getText().equals("pass"))
    				return true;
    			return false;
    			}


			@Override
			public void initialize(URL location, ResourceBundle resources) {
				userlog = new UserModel();
				//loads toys from toys.xml and puts them into table
				try {
					userlog.loadUsers();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			/*
			@SuppressWarnings("unlikely-arg-type")
			private boolean checkLogin(TextField txtUsername,TextField txtPassword) {

					if(userlog.getUsers().equals(txtUsername) && userlog.getUsers().equals(txtPassword)) {
						return true;
					}
					else {
				return false;

			}
			}
			*/


}

