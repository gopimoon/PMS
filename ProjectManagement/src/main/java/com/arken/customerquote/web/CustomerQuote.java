package com.arken.customerquote.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.customerquote.web.dao.CustomerQuoteDB;
import com.arken.customerquote.web.model.CustomerQuoteBean;

@WebServlet("/CustomerQuote")
public class CustomerQuote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		int status = 0;
		String TableIdentifier = request.getParameter("TableIdentifier");
		
		if(TableIdentifier.equals("quotetable"))
		{
			String updateflag = request.getParameter("updateflag");
			int projectid =Integer.parseInt(request.getParameter("projectid"));
			int quoteid = Integer.parseInt(request.getParameter("quoteid"));
			int sno=Integer.parseInt(request.getParameter("sno"));
			String description = request.getParameter("description");
			String model = request.getParameter("model");
			String qty = request.getParameter("qty");
			String units = request.getParameter("units");
			String unitprice = request.getParameter("unitprice");
			String totalprice = request.getParameter("totalprice");
			String insunitprice = request.getParameter("insunitprice");
			String instotalprice = request.getParameter("instotalprice");
			
			CustomerQuoteBean cqb = new CustomerQuoteBean();
			
			cqb.setUpdateflag(updateflag);
			cqb.setProjectid(projectid);
			cqb.setQuoteid(quoteid);
			cqb.setSno(sno);
			cqb.setDescription(description);
			cqb.setModel(model);
			cqb.setQty(qty);
			cqb.setUnits(units);
			cqb.setUnitprice(unitprice);
			cqb.setTotalprice(totalprice);
			cqb.setInsunitprice(insunitprice);
			cqb.setInstotalprice(instotalprice);
			
			
			CustomerQuoteDB cqd = new CustomerQuoteDB();
			
			try 
			{
				status = cqd.SaveCustomerQuote(cqb);
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		if(TableIdentifier.equals("updatequotetable"))
		{
			String updateflag = request.getParameter("updateflag");
			int projectid =Integer.parseInt(request.getParameter("projectid"));
			int quoteid = Integer.parseInt(request.getParameter("quoteid"));
			int sno=Integer.parseInt(request.getParameter("sno"));
			//String description = request.getParameter("description");
			//String model = request.getParameter("model");
			String qty = request.getParameter("qty");
			//String units = request.getParameter("units");
			String unitprice = request.getParameter("unitprice");
			String totalprice = request.getParameter("totalprice");
			String insunitprice = request.getParameter("insunitprice");
			String instotalprice = request.getParameter("instotalprice");
			
			CustomerQuoteBean cqb = new CustomerQuoteBean();
			
			cqb.setUpdateflag(updateflag);
			cqb.setProjectid(projectid);
			cqb.setQuoteid(quoteid);
			cqb.setSno(sno);
			//cqb.setDescription(description);
			//cqb.setModel(model);
			cqb.setQty(qty);
			//cqb.setUnits(units);
			cqb.setUnitprice(unitprice);
			cqb.setTotalprice(totalprice);
			cqb.setInsunitprice(insunitprice);
			cqb.setInstotalprice(instotalprice);
			
			
			CustomerQuoteDB cqd = new CustomerQuoteDB();
			
			try 
			{
				if(cqb.getUpdateflag().equals("YES"))
				{
					status = cqd.UpdateCustomerQuote(cqb);
				}
				
				
				if(status>0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("Customer_Quote1.jsp?project_id="+cqb.getProjectid()+"");
					rd.forward(request, response);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
