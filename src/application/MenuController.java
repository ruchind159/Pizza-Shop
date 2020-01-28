package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	
	public Model model=new Model();

	@FXML
	private Label userlbl,lblchoice,amount;
	
	@FXML
	public ComboBox<String> size,veg,pizza,base,toppings;
	
	@FXML
	public CheckBox gb,wpv,wpn,clc,cold,pepsi,tm,hc,cappu,esp;
	
	ObservableList<String> Size=FXCollections.observableArrayList("Regular @39","Medium @49","Large @59");
	ObservableList<String> Veg=FXCollections.observableArrayList("Veg","Non-Veg");
	ObservableList<String> PizzaV=FXCollections.observableArrayList("Deluxe Veggie @99","Indi Tandoori Paneer @119","Peppy Paneer @99","Mexican Green Wave @149","Double Cheese Margherita @199","Veggie Paradise @199");
	ObservableList<String> PizzaN=FXCollections.observableArrayList("Non Veg Supreme @119","Chicken Pepperoni @149","Chicken Dominator @149","Pepper Barbecue Chicken @199","Indi Chicken Tikka @199","Chicken Golden Delight @199");
	ObservableList<String> Base=FXCollections.observableArrayList("Thin Crust @29","Thick Crust @49","Flatbread crust @39","Focaccia @49");
	ObservableList<String> Toppings=FXCollections.observableArrayList("Pepperoni @39","Mushrooms @29","Sausage @39","Bacon @39","Extra Cheese @39","Green peppers @29","Black olives @29","None");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		size.setItems(Size);
		veg.setItems(Veg);
		base.setItems(Base);
		toppings.setItems(Toppings);
		
	}
	
	public void comboChanged (ActionEvent ev)
	{
		if(veg.getValue().contentEquals("Veg"))
			pizza.setItems(PizzaV);	
		else
			pizza.setItems(PizzaN);
	}
	
	
	public int calc()
	{
		int amt=0;
		//Size
		if (size.getValue().contentEquals("Regular @39"))
			amt=amt+39;
		else if (size.getValue().contentEquals("Medium @49"))
			amt=amt+49;
		else if(size.getValue().contentEquals("Large @59"))
			amt=amt+59;
		
		//Pizza
				if (pizza.getValue().contentEquals("Deluxe Veggie @99")||pizza.getValue().contentEquals("Peppy Paneer @99"))
					amt=amt+99;
				else if (pizza.getValue().contentEquals("Indi Tandoori Paneer @119")||pizza.getValue().contentEquals("Non Veg Supreme @119"))
					amt=amt+119;
				else if (pizza.getValue().contentEquals("Chicken Pepperoni @149")||pizza.getValue().contentEquals("Chicken Dominator @149")||pizza.getValue().contentEquals("Mexican Green Wave @149"))
					amt=amt+149;
				else if(pizza.getValue().contentEquals("Double Cheese Margherita @199")||pizza.getValue().contentEquals("Veggie Paradise @199")||pizza.getValue().contentEquals("Pepper Barbecue Chicken @199")||pizza.getValue().contentEquals("Indi Chicken Tikka @199")||pizza.getValue().contentEquals("Chicken Golden Delight @199"))
					amt=amt+199;
				
		//base
		if (base.getValue().contentEquals("Flatbread crust @39"))
			amt=amt+39;
		else if (base.getValue().contentEquals("Thick Crust @49")||base.getValue().contentEquals("Focaccia @49"))
			amt=amt+49;
		else if(base.getValue().contentEquals("Thin Crust @29"))
			amt=amt+29;
		
		//toppings
				if (toppings.getValue().contentEquals("Pepperoni @39")||toppings.getValue().contentEquals("Sausage @39")||toppings.getValue().contentEquals("Bacon @39")||toppings.getValue().contentEquals("Extra Cheese @39"))
					amt=amt+39;
				else if (toppings.getValue().contentEquals("Mushrooms @29")||toppings.getValue().contentEquals("Green peppers @29")||toppings.getValue().contentEquals("Black olives @29"))
					amt=amt+29;
				else 
					amt=amt+0;
				
		return amt;
	}
	
	
	public void buttonAction (ActionEvent ev)
	{
		int a=calc();
		if (pizza.getValue().contentEquals("")||size.getValue().contentEquals(""))
			JOptionPane.showMessageDialog(null, "Select a Pizza And Size");
		else
			{
				if(toppings.getValue().equals("None"))
					lblchoice.setText(size.getValue()+" - "+pizza.getValue()+" - "+base.getValue());
				else					
				lblchoice.setText(size.getValue()+" - "+pizza.getValue()+" - "+base.getValue()+" - "+toppings.getValue());
				amount.setText("Rs."+a);
			}

	}
	
	public void add_cart (ActionEvent event)
	{
		String order;
		int amt=calc();
		order=lblchoice.getText()+"\n";
		
		
		if (gb.isSelected())
			{
			order=order+" - "+gb.getText();
			amt=amt+49;
			}
		if (wpv.isSelected())
		{
		order=order+" - "+wpv.getText();
		amt=amt+99;
		}
		if (wpn.isSelected())
		{
		order=order+" - "+wpn.getText();
		amt=amt+119;
		}
		if (clc.isSelected())
		{
		order=order+" - "+clc.getText();
		amt=amt+99;
		}
		if (tm.isSelected())
		{
		order=order+" - "+tm.getText();
		amt=amt+99;
		}
		if (hc.isSelected())
		{
		order=order+" - "+hc.getText();
		amt=amt+99;
		}
		if (cappu.isSelected())
		{
		order=order+" - "+cappu.getText();
		amt=amt+89;
		}
		if (esp.isSelected())
		{
		order=order+" - "+esp.getText();
		amt=amt+99;
		}
		if (pepsi.isSelected())
		{
		order=order+" - "+pepsi.getText();
		amt=amt+60;
		}
		if (cold.isSelected())
		{
		order=order+" - "+cold.getText();
		amt=amt+60;
		}
		
		//new
		((Node)event.getSource()).getScene().getWindow().hide();
		
		Stage primaryStage = new Stage();
		FXMLLoader loader= new FXMLLoader();
		Pane root;
		try {
		model.CartInsert(userlbl.getText(), order, amt);
		root = loader.load(getClass().getResource("Cart.fxml").openStream());
		CartController cartController= (CartController)loader.getController();
		cartController.GetUser(userlbl.getText());
		cartController.InitOrder(userlbl.getText());
		//cartController.GetOrder(order,amt);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Your Cart");
		primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
