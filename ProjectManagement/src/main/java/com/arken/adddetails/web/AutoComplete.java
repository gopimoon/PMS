package com.arken.adddetails.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.arken.adddetails.web.dao.Auto;
import com.arken.connection.InitCon;


public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		/*String v = request.getParameter("t");
		//String v = "c";
		System.out.println(v);
		ArrayList al=new ArrayList();
		  try
		  {
			  InitCon it=new InitCon();
			  
		      Connection con=it.InitConnection();
		      Statement stmt=con.createStatement();
		      String sql="select item_name from items where item_name like '"+v+"%'";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		       al.add(rs.getString("item_name"));
		      }
		      rs.close();
		      stmt.close();
		      con.close();
		  }
		  catch(Exception e)
		  {
		   e.printStackTrace();
		  }
		   JSONArray json=new JSONArray(al);
		   response.setContentType("application/json");
		         response.getWriter().print(json);
		    
		}*/
	
	
	response.setContentType("application/json");
	try
	{
		String term=request.getParameter("term");
		System.out.println("data from ajax call" +term);
		
		Auto at = new Auto();
		ArrayList<String> list=at.getFramework(term);
		
		JSONArray json=new JSONArray(list);
		response.getWriter().print(list);
	}
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
	}
		
	}
}

