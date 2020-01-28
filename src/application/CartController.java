package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CartController implements Initializable {

	public Model model=new Model();
	@FXML
	private Label userlbl,cartstatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}
	
	public void InitOrder(String user)
	{
		try {
			cartstatus.setText((model.cartadd(user)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*public void GetOrder(String order, int amt) {
		// TODO Auto-generated method stub
		cartstatus.setText(order);
		System.out.println(amt);
	}*/
	
	
	
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
	
	public void UpdateInfo(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("UpdateInfo.fxml").openStream());
				UpdateInfoController updateInfoController= (UpdateInfoController)loader.getController();
				updateInfoController.GetUser(userlbl.getText());
				updateInfoController.InitUpdate(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Update Info");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	public void Payment(ActionEvent event)
	{
		if(cartstatus.getText().equals("Cart is Empty"))
		{
			JOptionPane.showMessageDialog(null, "Add items to your cart first.");
		}
		else {
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
				root = loader.load(getClass().getResource("Payment.fxml").openStream());
				PaymentController paymentController= (PaymentController)loader.getController();
				paymentController.GetUser(userlbl.getText());
				paymentController.InitAmt(userlbl.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Payment Portal");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
	}
	
	public void empty_cart(ActionEvent event)
	{
		try {
			model.deleteCart(userlbl.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InitOrder(userlbl.getText());
	}
}
