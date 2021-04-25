package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class payment {

	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	     Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	     con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", ""); 
	 } 
	      catch (Exception e) 
	 {
		 
		 e.printStackTrace();
	 
	 } 
	     return con;
	     
	 } 
	
	//*************inserting******************
	public String insertpayments(String userid, String pMethod, String totalPrice, String country,String city,String addres) 
	 { 
	       String output = ""; 
	 try
	 { 
	      Connection con = connect(); 
	      if (con == null) 
	 {
		 
		 return "Error while connecting to the database for inserting.";
		 
	 } 
	 // create a prepared statement
	      
	 String query = " insert into paymenttable(`pid`,`userid`,`pMethod`,`totalPrice`,`country`,`city`,`addres`)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	     preparedStmt.setInt(1, 0); 
	     preparedStmt.setString(2, userid); 
	     preparedStmt.setString(3,pMethod); 
	     preparedStmt.setDouble(4, Double.parseDouble(totalPrice)); 
	     preparedStmt.setString(5,country);
	     preparedStmt.setString(6,city); 
	     preparedStmt.setString(7,addres); 
	// execute the statement3
	     preparedStmt.execute(); 
	     con.close();
	     
	    output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 
	 { 
	     output = "Error while inserting the item."; 
	     
	     System.err.println(e.getMessage()); 
	 } 
	 
	      return output; 
	 } 
	//********************read products***********************
	public String readpayments() 
	 { 
		String output = ""; 
	 try
	 { 
	    Connection con = connect(); 
	    if (con == null) 
	 {
	    	return "Error while connecting to the database for reading."; 
	    	
	 }
	 // Prepare the html table to be displayed
	    
	 output = "<table border='1'> <tr><th>user id</th><th>Payment method</th>" +
	 "<th>total price</th>" + 
	 "<th>country</th>" + "<th>city</th>"+ "<th>address</th>"+
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from paymenttable"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	
	 while (rs.next()) 
	 { 
		 String pid= Integer.toString(rs.getInt("pid")); 
		 String userid = rs.getString("userid"); 
		 String pMethod = rs.getString("pMethod"); 
		 String totalPrice = Double.toString(rs.getDouble("totalPrice")); 
		 String country= rs.getString("country"); 
		 String city= rs.getString("city"); 
		 String addres= rs.getString("addres"); 
	 // Add into the html table
		 output += "<tr><td>" + userid + "</td>"; 
		 output += "<td>" + pMethod  + "</td>"; 
		 output += "<td>" +totalPrice + "</td>"; 
		 output += "<td>" +  country + "</td>"; 
		 output += "<td>" +  city + "</td>"; 
		 output += "<td>" + addres + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='products.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='pid' type='hidden' value='" + pid
	 + "'>" + "</form></td></tr>"; 
	 } 
	 	con.close(); 
	 // Complete the html table
	 	output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
		 
		 output = "Error while reading the products."; 
	 	System.err.println(e.getMessage()); 
	 	
	 } 
	 	return output; 
	 } 


	//***************update******************
	
	public String updatePayments(String pid,String userid,String pMethod,String totalPrice,String country,String city,String addres)
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	    if (con == null) 
	 {
		 	return "Error while connecting to the database for updating."; 
		 	
	 } 
	 	// create a prepared statement
	 	String query = "UPDATE paymenttable SET userid=?,pMethod=?,toatalPrice=?,country=?,city=?,addres=?  WHERE pid=?"; 
	 	PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 	preparedStmt.setString(1, userid); 
	 	preparedStmt.setString(2, pMethod); 
	 	preparedStmt.setDouble(3, Double.parseDouble(totalPrice)); 
	 	preparedStmt.setString(4, country); 
	 	preparedStmt.setString(5, city); 
	 	preparedStmt.setString(6, addres); 
	 	preparedStmt.setInt(7, Integer.parseInt(pid)); 
	 // execute the statement
	    preparedStmt.execute(); 
	 	con.close(); 
	 	output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while updating."; 
		 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 }
	//*****Delete Product**************
	
	
	public String deletepayments(String pid) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
		 
	 } 
	 	String query = "delete from paymenttable where pid=?"; 
	 // create a prepared statement
	 	PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 	preparedStmt.setInt(1, Integer.parseInt(pid)); 
	 // execute the statement
	 	preparedStmt.execute(); 
	 	con.close(); 
	 	output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	  { 
		 output = "Error while deleting ."; 
		 System.err.println(e.getMessage()); 
	  } 
	     return output; 
	  } 
}
