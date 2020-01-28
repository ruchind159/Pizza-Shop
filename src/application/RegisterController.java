package application;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterController implements Initializable {
	
	public Model model=new Model();
		
	@FXML
	private TextField txtfname;
	
	@FXML
	private TextField txtlname;
	
	@FXML
	private TextField txtuser;
	
	@FXML
	private TextField txtmail;
		
	@FXML
	private TextArea txtaddr;
	
	@FXML
	private TextField txtpwd;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	
	}
	
	public void LoginPage(ActionEvent event)
	{
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
					root = loader.load(getClass().getResource("Login.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Pizza Shop Login");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	public void Reg_now(ActionEvent event)
	{
				
				try {
					model.SqlInsert(txtfname.getText(),txtlname.getText(),txtmail.getText(),txtaddr.getText(),txtuser.getText(),txtpwd.getText());
				
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Stage primaryStage = new Stage();
				FXMLLoader loader= new FXMLLoader();
				Pane root;
				try {
					root = loader.load(getClass().getResource("Login.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Pizza Shop Login");
				primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}
	
}
