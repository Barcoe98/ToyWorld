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

	import java.util.ArrayList;

	import java.io.FileReader;

	import java.io.FileWriter;

	import java.io.ObjectInputStream;

	import java.io.ObjectOutputStream;


	import com.thoughtworks.xstream.XStream;

	import com.thoughtworks.xstream.io.xml.DomDriver;

	public class UserModel {

		/*ATTRIBUTES*/
		private ArrayList<User> users;

		
		public ArrayList<User> getUsers() {
			return users;
		}


		public void setUsers(ArrayList<User> users) {
			this.users = users;
		}


		public UserModel()
		{
			users = new ArrayList<User>();
		}

		//adds user to users
	    public void addUser(String fname, String lname, String username, String pword, String age, String gender) {
			User user = new User(fname,lname, username,pword, age, gender);
		     users.add(user);
		}

		
		/**
	     * save() - This method saves the contents of the users ArrayList to 
	     * an XML file called users.xml
	     * */
	     public void saveUsers() throws Exception {
	     XStream xstream = new XStream(new DomDriver());
	     ObjectOutputStream out = xstream.createObjectOutputStream
	     (new FileWriter("users.xml"));
	     out.writeObject(users);
	     out.close();
	     }


	     /**
	     * load() - This method reads the contents of the XML file called 
	     * users.xml stored in the project directory.  
	     * The contents are casted as an ArrayList of User and stored in the users variable. 
	     * */
	     @SuppressWarnings("unchecked")
		public void loadUsers() throws Exception {
	     XStream xstream = new XStream(new DomDriver());
	     ObjectInputStream is = xstream.createObjectInputStream
	     (new FileReader("users.xml"));
	     users = (ArrayList<User>) is.readObject();
	     is.close();
	     }
	}

