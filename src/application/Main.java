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


package application;
	
    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

	public class Main extends Application {

        @Override
        public void start(Stage stage) throws Exception {

        	Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

