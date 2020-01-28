package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FeedController implements Initializable {

	Model model=new Model();
	
	@FXML
	private TextField txtname;
	@FXML
	private TextArea txtfeed;
	
	@FXML
	private Slider sliderpt;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void submit_feed(ActionEvent event)
	{
		try {
			model.Feedback(txtname.getText(), txtfeed.getText(), sliderpt.getValue());
			((Node)event.getSource()).getScene().getWindow().hide();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void vbad(ActionEvent event)
	{
		sliderpt.setValue(0.5);
	}
	
	public void bad(ActionEvent event)
	{
		sliderpt.setValue(2.5);
	}
	
	public void ok(ActionEvent event)
	{
		sliderpt.setValue(5.0);
	}
	
	public void good(ActionEvent event)
	{
		sliderpt.setValue(7.5);
	}
	
	public void vgood(ActionEvent event)
	{
		sliderpt.setValue(10.0);
	}
	
}