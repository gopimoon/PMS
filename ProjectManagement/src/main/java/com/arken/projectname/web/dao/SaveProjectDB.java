package com.arken.projectname.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.arken.connection.InitCon;

public class SaveProjectDB 
{
	
	public static int SaveProject(String projectname)
	{
		int status = 0;
		int pid = 0;
			try
			{
				InitCon it = new InitCon();
				
				Connection con = it.InitConnection();
				PreparedStatement ps,ps1;
				
				con.setAutoCommit(false);
				ps = con.prepareStatement("insert into project_master(project_name) values(?)");
				ps.setString(1, projectname);
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
