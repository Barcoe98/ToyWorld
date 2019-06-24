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

public class User {

	/*ATTRIBUTES*/
	private String Fname;
	private String Lname;
	private String Username;
	private String Pword;
	private String Age;
	private String Gender;
	
	
/*CONSTRUCTOR*/
	public User(String fname, String lname, String username, String pword, String age, String gender) {
		super();
		Fname = fname;
		Lname = lname;
		Username = username;
		Pword = pword;
		Age = age;
		Gender = gender;
	}
	
	/*GETTERS AND SETTERS*/
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPword() {
		return Pword;
	}
	public void setPword(String pword) {
		Pword = pword;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}




		
	

	
	


	
	
	
	
}
