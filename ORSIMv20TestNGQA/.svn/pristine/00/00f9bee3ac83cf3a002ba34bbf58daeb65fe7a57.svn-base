package com.oracle.sim.testcases.InventoryAdjustment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {
	public static void main(String[] args) {
		try {
			//add below maven dependency in pom.xml db code before execution
//			<!-- https://mvnrepository.com/artifact/com.oracle.jdbc/ojdbc8 -->
//				<dependency>
//					<groupId>com.oracle.ojdbc</groupId>
//					<artifactId>ojdbc8</artifactId>
//					<version>19.3.0.0</version>
//				</dependency>
			
			//step1 load the driver class  
			Class.forName("oracle.jdbc.OracleDriver");  

			//step2 create  the connection object  
			//getConnection(url,username,pwd)
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@//msp00aso.us.oracle.com:1521/dolsp98sim","sim01app","retail"); 

			//step3 create the statement object  
			Statement stmt=con.createStatement(); 

			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select * from store_item_stock_nonsell where item_id='100050056'");  
			while(rs.next()) { 
				System.out.println("item id: "+rs.getString(1));  //not a good practice
				System.out.println("store id: "+rs.getInt("store_id"));
				System.out.println("nonsellable type id: "+rs.getInt("nonsellable_type_id"));
				System.out.println("quantity: "+rs.getInt("quantity"));
			}
			
			
			//step5 close the connection object  
			con.close();  

		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}
}
