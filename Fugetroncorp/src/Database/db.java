package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class db {
	
	public static void  main(String[] args) throws  ClassNotFoundException, SQLException {													
				
        String dbUrl = "jdbc:mysql://localhost:3036/emp";						
		String username = "root";	
        	
		String password = "";				

			
		String query = "select *  from employee;";	
        
 	    		
   	    Class.forName("com.mysql.jdbc.Driver");			
   
   			
    Connection con = DriverManager.getConnection(dbUrl,username,password);
  
  			
	   Statement stmt = con.createStatement();					

					
 		ResultSet rs= stmt.executeQuery(query);							
 
 		
		while (rs.next()){
	        		String myName = rs.getString(1);								        
                    String myAge = rs.getString(2);					                               
                    System. out.println(myName+"  "+myAge);		
            }		
		
			con.close();			
}
}



