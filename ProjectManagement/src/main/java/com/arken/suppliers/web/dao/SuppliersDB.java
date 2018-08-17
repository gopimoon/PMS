package com.arken.suppliers.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.arken.connection.InitCon;
import com.arken.suppliers.web.model.SuppliersBean;

public class SuppliersDB 
{

	public static int SaveSupplierQuote(SuppliersBean sb) throws SQLException
	{
		int status=0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps,ps1,ps3;
		int pid = 0;
		try
		{
			con.setAutoCommit(false);
			
			ps1 = con.prepareStatement("insert into suppliers_master(project_id,supplier_id) values(?,?)");
			ps1.setInt(1, sb.getProjectid());
			ps1.setInt(2, sb.getSupplierid());
			status = ps1.executeUpdate();
			con.commit();
			
			
			ps3=con.prepareStatement("SELECT MAX(po_id)FROM suppliers_master");
			ResultSet rs=ps3.executeQuery();
			while(rs.next())
			{
				pid=rs.getInt(1);
				//b.setPatientid(pid);
			}	
			
			ps=con.prepareStatement("INSERT INTO `arken`.`supplier_quote`(`sup_id`,`s_no`,`description`,`model`,`qty`,`units`,`unitprice`,`totalprice`,"
					+ "`insunitprice`,`instotalprice`) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, pid);
			ps.setInt(2, sb.getSno());
			ps.setString(3, sb.getDescription());
			ps.setString(4, sb.getModel());
			ps.setString(5, sb.getQty());
			ps.setString(6, sb.getUnits());
			ps.setString(7, sb.getUnitprice());
			ps.setString(8, sb.getTotalprice());
			ps.setString(9, sb.getInsunitprice());
			ps.setString(10, sb.getInstotalprice());
			
			
			ps.executeUpdate();
			con.commit();
			
			status=1;
			
			con.close();
		}
		catch (SQLException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return status;
	}	
	
	
	// Funciton for retriving data from Items table for display
	
	public static ArrayList getItems() throws SQLException
	{
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		ResultSet rs1;
		ArrayList alitems = null;
        ArrayList Items = new ArrayList();
        
		  try
	        {
			  ps = con.prepareStatement("SELECT * FROM arken.items;");
			 
			  rs1 = ps.executeQuery();
			 
			  while (rs1.next())
			  {
				  alitems = new ArrayList();
				  
				  alitems.add(rs1.getString(2));
				  
				  
				  Items.add(alitems);
			  }
			  
			  con.close();
			}
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        	
	        }
		return Items; 
	}
	
				
				
	// Funciton for retriving data from Total,Subtotal,GST,Discount Test table for display
	
	public static ArrayList getTotals(int sid) throws SQLException
	{
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		ResultSet rs1;
		ArrayList alitems = null;
        ArrayList Items = new ArrayList();
        
        
		  try
	        {
			  ps = con.prepareStatement("select (sum(totalprice)) as subtotal, "
			  		+ "sum(instotalprice) as subtotalprice, round(sum(totalprice)*18/100) as gst, "
			  		+ "round(sum(instotalprice)*18/100) as gst1, "
			  		+ "(sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100)) as total, "
			  		+ "round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100) as discount, "
			  		+ "((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))-round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100)) as final from supplier_quote where sup_id="+sid+" ");
			 
			  rs1 = ps.executeQuery();
			 
			  Locale indiaLocale = new Locale("en", "IN");
		      NumberFormat india  = NumberFormat.getCurrencyInstance(indiaLocale);
			  while (rs1.next())
			  {
				  alitems = new ArrayList();
				  
				  alitems.add(india.format(rs1.getInt(1)));
				  alitems.add(india.format(rs1.getInt(2)));
				  alitems.add(india.format(rs1.getInt(3)));
				  alitems.add(india.format(rs1.getInt(4)));
				  alitems.add(india.format(rs1.getInt(5)));
				  alitems.add(india.format(rs1.getInt(6)));
				  alitems.add(india.format(rs1.getInt(7)));
				  
				  Items.add(alitems);
			  }
			  
			  con.close();
			}
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        	
	        }
		return Items; 
	}
	
	
	// Funciton for retriving data from suppliers table for display
	
			public static ArrayList getSupplierQuote(int sid) throws SQLException
			{
				
				InitCon it = new InitCon();
				Connection con = it.InitConnection();
				PreparedStatement ps;
				ResultSet rs1;
				ArrayList alSupplierQuote = null;
		        ArrayList Supplier_Quote = new ArrayList();
		        
				  try
			        {
					  ps = con.prepareStatement("SELECT * FROM arken.supplier_quote where sup_id = ?");
					  ps.setInt(1, sid);
					 
					  rs1 = ps.executeQuery();
					 
					  while (rs1.next())
					  {
						  alSupplierQuote = new ArrayList();
						  
						  alSupplierQuote.add(rs1.getString(2));
						  alSupplierQuote.add(rs1.getString(3));
						  alSupplierQuote.add(rs1.getString(4));
						  alSupplierQuote.add(rs1.getString(5));
						  alSupplierQuote.add(rs1.getString(6));
						  alSupplierQuote.add(rs1.getString(7));
						  alSupplierQuote.add(rs1.getString(8));
						  alSupplierQuote.add(rs1.getString(9));
						  alSupplierQuote.add(rs1.getString(10));
						  
						  Supplier_Quote.add(alSupplierQuote);
					  }
					  
					  con.close();
					}
			        catch (SQLException e)
			        {
			        	e.printStackTrace();
			        	
			        }
				return Supplier_Quote; 
			}
}
