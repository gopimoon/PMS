package com.arken.adddetails.web;

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

import com.arken.adddetails.web.dao.SaveCustomerDB;
import com.arken.connection.InitCon;

@WebServlet("/SaveCustomer")
public class SaveCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		int status = 0;
		int cid=0;
		
		String cus_name = request.getParameter("Customer_name");
		String cus_address = request.getParameter("address");
		String cus_state = request.getParameter("state");
		String cus_country = request.getParameter("country");
		String cus_pin = request.getParameter("pincode");
		String cus_panno = request.getParameter("panid");
		String cus_gstno = request.getParameter("gstno");
		
		
		SaveCustomerDB scb=new SaveCustomerDB();
		
		status = scb.SaveCustomer(cus_name,cus_address,cus_state,cus_country,cus_pin,cus_panno,cus_gstno);
		
		
		
		if(status>0)
		{
			   pw.println("<script type=\"text/javascript\">");
			   pw.println("alert('Customer Added Successfully');");
			   pw.println("location='Add_Customers.jsp';");
			   pw.println("</script>");
		}
	}

}
