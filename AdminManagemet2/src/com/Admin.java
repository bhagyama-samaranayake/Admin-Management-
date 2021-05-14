package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
	
	
		
		//A common method to connect to the DB
	//A common method to connect to the DB
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/admin", "root", "");
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
			
		
		
		//Insert Project Details
		public String insertResearch(String sellerId, String sellerName, String sellerCategory, String sellerPhone,String sellerEmail ){
			String output = "" ;
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = "INSERT INTO `admin`(`sellerId`, `sellerName`, `sellerCategory`, `sellerPhone`,  `sellerEmail`) VALUES (?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, sellerName);
					 preparedStmt.setString(3, sellerCategory);
					 preparedStmt.setString(4, sellerPhone);					 
					 preparedStmt.setString(5, sellerEmail); 
					
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newAdmin= readResearch(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newAdmin + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Admin.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readResearch(){
			String output = "";
			try{
				Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
						     
							 "<th >Seller Name</th>" +
							 "<th>Seller Category</th>" +
							 "<th>Seller Phone</th>" +							 
							 "<th>Seller Email</th>" +							 
							 "<th>Update</th>" +
							 "<th>Remove</th>" +
						
						 "</tr>";
	
				String query = "select * from `admin`";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 //String researchID =  Integer.toString(rs.getInt("researchID"));
					 int sellerId = rs.getInt("sellerId");
					 String sellerName = rs.getString("sellerName");
					 String sellerCategory = rs.getString("sellerCategory");
					 String sellerPhone = rs.getString("sellerPhone");					 
					 String sellerEmail = rs.getString("sellerEmail");
					 
	
					 
					 // Add into the html table
					 
					 //output += "<tr><td>" + sellerId + "</td>";
					 output += "<tr><td>" + sellerName + "</td>";
					 output += "<td>" + sellerCategory + "</td>";
					 output += "<td>" + sellerPhone + "</td>";					 
					 output += "<td>" + sellerEmail + "</td>";
					
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + sellerId + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + sellerId + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the researches.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateResearch(String sellerId, String sellerName, String sellerCategory, String sellerPhone, String sellerEmail){ 
			String output = ""; 
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				String query = "UPDATE `admin` SET `sellerName`=?,`sellerCategory`=?,`sellerPhone`=?,`sellerEmail`=? WHERE `sellerId`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, sellerName);
				 preparedStmt.setString(2, sellerCategory);
				 preparedStmt.setString(3, sellerPhone);				 
				 preparedStmt.setString(4, sellerEmail); 
				 preparedStmt.setString(5, sellerId);
				 
				// preparedStmt.setString(4, sector);
				
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newAdmin = readResearch(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newAdmin + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the admin.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteResearch(String sellerId) { 
			String output = ""; 
			try{ 
				Connection con = connect();
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
				    String query ="DELETE FROM `admin` WHERE sellerId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(sellerId)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					  
					String newAdmin = readResearch(); 
					output = "{\"status\":\"success\", \"data\": \"" + newAdmin + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the research.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}