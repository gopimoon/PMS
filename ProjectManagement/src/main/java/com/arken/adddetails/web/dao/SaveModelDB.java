package com.arken.adddetails.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.arken.connection.InitCon;

public class SaveModelDB 
{

	public static int SaveModel(String model_name)
	{
		int status = 0;
			try
			{
				InitCon it = new InitCon();
				
				Connection con = it.InitConnection();
				PreparedStatement ps;
				
				con.setAutoCommit(false);
				ps = con.prepareStatement("insert into model(model_name) values(?)");
				ps.setString(1, model_name);
				ps.executeUpdate();
				con.commit();
				
				status = 1;
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				
			}
			return status;
	}
}
