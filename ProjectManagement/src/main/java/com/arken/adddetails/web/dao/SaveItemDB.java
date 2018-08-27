package com.arken.adddetails.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.arken.connection.InitCon;

public class SaveItemDB 
{

	public static int SaveItem(String item_name)
	{
		int status = 0;
			try
			{
				InitCon it = new InitCon();
				
				Connection con = it.InitConnection();
				PreparedStatement ps;
				
				con.setAutoCommit(false);
				ps = con.prepareStatement("insert into items(item_name) values(?)");
				ps.setString(1, item_name);
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
