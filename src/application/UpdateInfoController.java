package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UpdateInfoController implements Initializable {

	public Model model=new Model();
	
	@FXML
	private TextField txtfname,txtlname,txtmail,txtpwd;
		
	@FXML
	private TextArea txtaddr;
	
	
	@FXML
	private Label userlbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void InitUpdate(String user)
	{
		try {
			txtfname.setText((model.GetFname(user)));
			txtlname.setText((model.GetLname(user)));
			txtmail.setText((model.Getemail(user)));
			txtpwd.setText((model.GetPass(user)));
			txtaddr.setText((model.GetAddress(user)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GetUser(String Name) {
		// TODO Auto-generated method stub
		userlbl.setText(Name);
	}

	public void SignOut(ActionEvent event)
	{
		try {
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage primaryStage = new Stage();
		FXMLLoader loader= new FXMLLoader();
		Pane root =loader.load(getClass().getResource("Login.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pizza Shop Login");
		primaryStage.show();
		}
		catch (Exception e)
		{
			
		}
	}
	
	//new
	public void Home(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("Home.fxml").openStream());
				HomeController homeController= (HomeController)loader.getController();
				homeController.GetUser(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Home Page");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	public void Cart(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("Cart.fxml").openStream());
				CartController cartController= (CartController)loader.getController();
				cartController.GetUser(userlbl.getText());
				cartController.InitOrder(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Your Cart");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	public void Order(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("Order.fxml").openStream());
				OrderController orderController= (OrderController)loader.getController();
				orderController.GetUser(userlbl.getText());
				orderController.InitOrderHistory(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Order History");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	public void Menu(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("Menu.fxml").openStream());
				MenuController menuController= (MenuController)loader.getController();
				menuController.GetUser(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Menu");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	public void UpdateNow(ActionEvent event)
	{
		try {
			model.deleteUser(userlbl.getText());
			model.SqlInsert(txtfname.getText(),txtlname.getText(),txtmail.getText(),txtaddr.getText(),userlbl.getText(),txtpwd.getText());
			InitUpdate(userlbl.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

