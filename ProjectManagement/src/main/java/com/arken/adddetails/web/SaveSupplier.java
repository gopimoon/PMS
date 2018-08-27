package com.arken.adddetails.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.adddetails.web.dao.SaveSupplierDB;

@WebServlet("/SaveSupplier")
public class SaveSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		int status = 0;
		int cid=0;
		
		String sup_name = request.getParameter("Supplier_name");
		String sup_address = request.getParameter("address");
		String sup_state = request.getParameter("state");
		String sup_country = request.getParameter("country");
		String sup_pin = request.getParameter("pincode");
		String sup_panno = request.getParameter("panid");
		String sup_gstno = request.getParameter("gstno");
		
		
		SaveSupplierDB sdb = new SaveSupplierDB();
		
		status = sdb.SaveSuppliers(sup_name,sup_address,sup_state,sup_country,sup_pin,sup_panno,sup_gstno);
		
		
		if(status>0)
		{
			   pw.println("<script type=\"text/javascript\">");
			   pw.println("alert('Supplier Added Successfully');");
			   pw.println("location='Add_Suppliers.jsp';");
			   pw.println("</script>");
		}
	}

}

