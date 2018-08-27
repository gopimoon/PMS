package com.arken.adddetails.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.adddetails.web.dao.SaveModelDB;



@WebServlet("/SaveModels")
public class SaveModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		int status = 0;
		
		String model_name = request.getParameter("Model");
		
		SaveModelDB sdb = new SaveModelDB();
		
		status = sdb.SaveModel(model_name);
		
		if(status>0)
		{
			pw.println("<script type=\"text/javascript\">");
			   pw.println("alert('Model Added Successfully');");
			   pw.println("location='Add_Models.jsp';");
			   pw.println("</script>");
		}
	
	}

}
