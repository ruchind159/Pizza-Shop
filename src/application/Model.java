package application;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Model {

	Connection connection;
	
	public Model()
	{
		connection=SqliteConnection.Connector();
		if(connection==null)
		{
			System.out.println("Connection Not Successfull");
			System.exit(1);
		}
	}
	
	public boolean isDBConnected()
	{
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String user, String pass) throws SQLException
	{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query="select * from user where username= ? and password= ?";
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
				return false;
		}
		catch (Exception e)
		{
			return false;
		}
		finally
		{
			ps.close();
			rs.close();
		}
	}
	
	//new
		public void SqlInsert(String fname, String lname,String email,String addr, String user, String pass) throws SQLException
		{
			
			String query="insert into user(first_name,last_name,email,address,username,password) values(? , ? , ?, ?, ?, ?)";
			try {
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, fname);
				ps.setString(2, lname);
				ps.setString(3, email);
				ps.setString(4, addr);
				ps.setString(5, user);
				ps.setString(6, pass);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully inserted");
				
				ps.close();
				
			}
			catch (Exception e)
			{
				//exception
			}
		}
		
		
	//Even more new
		
		public void CartInsert(String uname, String orderr, int amt) throws SQLException
		{
			
			String query="insert into cart values(? , ? , ?)";
			try {
				
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, uname);
				ps.setString(2, orderr);
				ps.setInt(3, amt);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully added to cart");
				
				ps.close();
				
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
		
		public String cartadd(String user) throws SQLException
		{
			String empty="Cart is Empty";
			String items=" ";
			int i=1;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from cart where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=items+"\n"+i+"."+rs.getString("order");
					i++;
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		//extra function

		public String cartGet(String user) throws SQLException
		{
			String empty="Cart is Empty";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from cart where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=items+"\n"+rs.getString("order");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		
		public int calcAmt(String user) throws SQLException
		{
			int amount=0;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from cart where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					amount=amount+rs.getInt("amount");
				}
				return amount;
				
			}
			catch (Exception e)
			{
				return 0;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		
	//latest
		public void OrderInsert(String uname, String orderr, int amt) throws SQLException
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			String d;
			d=dtf.format(now);  
			
			String query="insert into ordering values(? , ? , ?, ?)";
			try {
				
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, uname);
				ps.setString(2, orderr);
				ps.setInt(3, amt);
				ps.setString(4, d);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Order Placed Successfully");
				
				ps.close();
				
			}
			catch (Exception e)
			{
				System.out.println(d+uname+orderr+amt);
				System.out.println(e);
			}
		}
		
		
		//Delete from Cart
		public void deleteCart(String uname) throws SQLException
		{
			
			String query="delete from cart where username=?";
			try {
				
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, uname);
				
				ps.executeUpdate();
				
				ps.close();
				
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
		
		//Delete from user table
		public void deleteUser(String uname) throws SQLException
		{
			
			String query="delete from user where username=?";
			try {
				
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, uname);
				
				ps.executeUpdate();
				
				ps.close();
				
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
		
		public String orderAdd(String user) throws SQLException
		{
			String empty="No Order History.";
			String orderlist=" ";
			int i=1;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from ordering where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					orderlist=orderlist+"\n"+i+"."+rs.getString("orders")+"\nAmount Paid : "+rs.getString("amount_paid")+"\nDate and Time of order :"+rs.getString("order_date");
					i++;
				}
				return orderlist;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		
		
	//UPDATE EXTRA FUNCTION TO GET DATA
		public String GetFname(String user) throws SQLException
		{
			String empty="First Name";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from user where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=rs.getString("first_name");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		public String GetLname(String user) throws SQLException
		{
			String empty="Last Name";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from user where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=rs.getString("last_name");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		
		public String Getemail(String user) throws SQLException
		{
			String empty="Email";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from user where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=rs.getString("email");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		public String GetAddress(String user) throws SQLException
		{
			String empty="Adress";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from user where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=rs.getString("address");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		public String GetPass(String user) throws SQLException
		{
			String empty="pass";
			String items=" ";
			PreparedStatement ps=null;
			ResultSet rs=null;
			String query="select * from user where username= ?";
			try {
				ps=connection.prepareStatement(query);
				ps.setString(1, user);
				
				rs=ps.executeQuery();
				
				if(rs.isAfterLast())
					return empty;
				
				while(rs.next())
				{
					items=rs.getString("password");
				}
				return items;
				
			}
			catch (Exception e)
			{
				return empty;
			}
			finally
			{
				ps.close();
				rs.close();
			}
		}
		
		//FEEDBACK
		
		public void Feedback(String uname, String feed, Double pt) throws SQLException
		{
			
			String query="insert into feedback values(? , ? , ?)";
			try {
				
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, uname);
				ps.setString(2, feed);
				ps.setDouble(3, pt);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Thank You For Your Feedback");
				
				ps.close();
				
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
}


