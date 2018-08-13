package com.arken.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitCon 
{

	public Connection InitConnection() throws SQLException 
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arken","root","admin");
		
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
}