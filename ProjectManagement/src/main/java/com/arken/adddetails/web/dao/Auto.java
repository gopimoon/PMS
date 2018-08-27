package com.arken.adddetails.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.arken.connection.InitCon;

public class Auto 
{

	public ArrayList<String> getFramework(String name) throws SQLException
	{
		InitCon it = new InitCon();
		Connection con=it.InitConnection();
		ArrayList<String> list=new ArrayList<String>();
		PreparedStatement ps=null;
		String data;
		try
		{
			ps=con.prepareStatement("select item_name from items where item_name LIKE ?");
			ps.setString(1, name+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				data=rs.getString("item_name");
				list.add(data);
			}

		}
		catch (SQLException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
