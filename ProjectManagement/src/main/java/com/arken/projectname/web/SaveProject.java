package com.arken.projectname.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.connection.InitCon;
import com.arken.projectname.web.dao.SaveProjectDB;

@WebServlet("/SaveProject")
public class SaveProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		String projectname  = request.getParameter("project_name");
	
		int status = 0;
		int pid = 0;
		SaveProjectDB sp = new SaveProjectDB();
		status = sp.SaveProject(cid,projectname);
		try
		{
			InitCon it = new InitCon();
			
			Connection con = it.InitConnection();
			PreparedStatement ps1=con.prepareStatement("SELECT MAX(project_id)FROM project_master");
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				pid=rs.getInt(1);
			}	
		}
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
			
		
		if(status>0)
		{
			response.sendRedirect("SplitLinks.jsp?project_id="+pid+"");
		}
		
	
		
	}

	
	}


