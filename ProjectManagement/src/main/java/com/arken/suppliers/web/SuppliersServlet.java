package com.arken.suppliers.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arken.suppliers.web.dao.SuppliersDB;
import com.arken.suppliers.web.model.SuppliersBean;

@WebServlet("/SuppliersServlet")
public class SuppliersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		int status = 0;
		int status1 = 0;
		String TableIdentifier = request.getParameter("TableIdentifier");
		
		if(TableIdentifier.equals("suppliertable"))
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
			int sid = Integer.parseInt(request.getParameter("sid"));
			int po = Integer.parseInt(request.getParameter("po"));
			
			
			
			SuppliersBean sb = new SuppliersBean();
			
			sb.setUpdateflag(updateflag);
			sb.setProjectid(projectid);
			sb.setQuoteid(quoteid);
			sb.setSno(sno);
			sb.setDescription(description);
			sb.setModel(model);
			sb.setQty(qty);
			sb.setUnits(units);
			sb.setUnitprice(unitprice);
			sb.setTotalprice(totalprice);
			sb.setInsunitprice(insunitprice);
			sb.setInstotalprice(instotalprice);
			sb.setSupplierid(sid);
			sb.setPoid(po);
			
			
			SuppliersDB sdb = new SuppliersDB();
			
			try 
			{
				//status1 = sdb.getCheckPOs(projectid, description, po, qty);
				
				/*if(status1>0)
				{
					String error = "qty mismatch";	
				}
				else
				{*/
					status = sdb.SaveSupplierQuote(sb);
				/*}*/
				if(status>0)
				{
					response.sendRedirect("Suppliers_2.jsp?projectid="+sb.getProjectid()+"&sid="+sb.getSupplierid()+"&po="+sb.getPoid()+"");
				}	
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		
		
		
		if(TableIdentifier.equals("updatesuppliertable"))
		{
			String updateflag = request.getParameter("updateflag");
			int projectid =Integer.parseInt(request.getParameter("projectid"));
			int sno=Integer.parseInt(request.getParameter("sno"));
			//String description = request.getParameter("description");
			//String model = request.getParameter("model");
			String qty = request.getParameter("qty");
			String units = request.getParameter("units");
			String unitprice = request.getParameter("unitprice");
			String totalprice = request.getParameter("totalprice");
			//String insunitprice = request.getParameter("insunitprice");
			//String instotalprice = request.getParameter("instotalprice");
			int sid = Integer.parseInt(request.getParameter("supplierid"));
			int po = Integer.parseInt(request.getParameter("po"));
			
			
			
			SuppliersBean sb = new SuppliersBean();
			
			sb.setUpdateflag(updateflag);
			sb.setProjectid(projectid);
			sb.setSno(sno);
			//sb.setDescription(description);
			//sb.setModel(model);
			sb.setQty(qty);
			sb.setUnits(units);
			sb.setUnitprice(unitprice);
			sb.setTotalprice(totalprice);
			//sb.setInsunitprice(insunitprice);
			//sb.setInstotalprice(instotalprice);
			sb.setSupplierid(sid);
			sb.setPoid(po);
			
			
			SuppliersDB sdb = new SuppliersDB();
			
			try 
			{
				status = sdb.UpdateSupplierPO(sb);
				
				if(status>0)
				{
					response.sendRedirect("View_Suppliers_3.jsp?projectid="+sb.getProjectid()+"&supplierid="+sb.getSupplierid()+"&po_id="+sb.getPoid()+"");
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
