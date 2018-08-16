package com.arken.finalquote.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.finalquote.web.dao.FinalQuoteDB;
import com.arken.finalquote.web.model.FinalQuoteBean;

@WebServlet("/FinalQuote")
public class FinalQuote extends HttpServlet {
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
			
			FinalQuoteBean fqb = new FinalQuoteBean();
			
			fqb.setUpdateflag(updateflag);
			fqb.setProjectid(projectid);
			fqb.setQuoteid(quoteid);
			fqb.setSno(sno);
			fqb.setDescription(description);
			fqb.setModel(model);
			fqb.setQty(qty);
			fqb.setUnits(units);
			fqb.setUnitprice(unitprice);
			fqb.setTotalprice(totalprice);
			fqb.setInsunitprice(insunitprice);
			fqb.setInstotalprice(instotalprice);
			
			
			FinalQuoteDB fqd = new FinalQuoteDB();
			
			try 
			{
				
				if(fqb.getUpdateflag().equals("NO"))
				{
					status = fqd.SaveFinalQuote(fqb);
				}
				
				
				/*if(status>0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("Final_Quote.jsp?project_id="+fqb.getProjectid()+"");
					rd.forward(request, response);
				}*/
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
			String description = request.getParameter("description");
			String model = request.getParameter("model");
			String qty = request.getParameter("qty");
			String units = request.getParameter("units");
			String unitprice = request.getParameter("unitprice");
			String totalprice = request.getParameter("totalprice");
			String insunitprice = request.getParameter("insunitprice");
			String instotalprice = request.getParameter("instotalprice");
			
			FinalQuoteBean fqb = new FinalQuoteBean();
			
			fqb.setUpdateflag(updateflag);
			fqb.setProjectid(projectid);
			fqb.setQuoteid(quoteid);
			fqb.setSno(sno);
			fqb.setDescription(description);
			fqb.setModel(model);
			fqb.setQty(qty);
			fqb.setUnits(units);
			fqb.setUnitprice(unitprice);
			fqb.setTotalprice(totalprice);
			fqb.setInsunitprice(insunitprice);
			fqb.setInstotalprice(instotalprice);
			
			
			FinalQuoteDB fqd = new FinalQuoteDB();
			
			try 
			{
				if(fqb.getUpdateflag().equals("YES"))
				{
					status = fqd.UpdateFinalQuote(fqb);
				}
				
				
				if(status>0)
				{
					RequestDispatcher rd = request.getRequestDispatcher("Final_Quote1.jsp?project_id="+fqb.getProjectid()+"");
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
