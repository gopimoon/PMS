package com.arken.suppliers.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.arken.connection.InitCon;
import com.arken.finalquote.web.model.FinalQuoteBean;
import com.arken.suppliers.web.model.SuppliersBean;

public class SuppliersDB 
{

	// Function for Save the supplier_master table 
	
	public static int SavePO(int pid,int sid) throws SQLException
	{
		
		int status = 0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps,ps1;
		
		try
		{
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("insert into suppliers_master(project_id,supplier_id) values(?,?)");
			ps.setInt(1, pid);
			ps.setInt(2, sid);
			ps.executeUpdate();
			con.commit();
			
			status = 1;
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}	
	
	
	// Function for select the max po_id from suppliers master table
	
	public static int SelectMaxPOid(int pid,int sid) throws SQLException
	{
		
		int po = 0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps,ps1;
		
		try
		{
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("select max(po_id) from arken.suppliers_master where project_id=? and supplier_id=?");
			ps.setInt(1, pid);
			ps.setInt(2, sid);
			
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				po = rs.getInt(1);
			}
			
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return po;
	}	
	
	
	// Function for Save the Supplier Quote table
	
	public static int SaveSupplierQuote(SuppliersBean sb) throws SQLException
	{
		int status=0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		try
		{
			con.setAutoCommit(false);
			
			ps=con.prepareStatement("INSERT INTO `arken`.`supplier_quote`(`sup_id`,`s_no`,`description`,`model`,`qty`,`units`,`unitprice`,`totalprice`,"
					+ "`insunitprice`,`instotalprice`) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, sb.getPoid());
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
	
	
	
	// Function for update the Suppliers Quote value
	
		public static int UpdateSupplierPO(SuppliersBean sb) throws SQLException
		{
			int status=0;
			
			InitCon it = new InitCon();
			Connection con = it.InitConnection();
			PreparedStatement ps;
			
			try
			{
				con.setAutoCommit(false);
				ps=con.prepareStatement("UPDATE `arken`.`supplier_quote` SET `qty` = ?,`units` = ?,"
						+ "`unitprice` = ?,`totalprice` = ? WHERE `sup_id` = ? and `s_no` = ?");
				
				
				
				
				ps.setString(1, sb.getQty());
				ps.setString(2, sb.getUnits());
				ps.setString(3, sb.getUnitprice());
				ps.setString(4, sb.getTotalprice());
				ps.setInt(5, sb.getPoid());
				ps.setInt(6, sb.getSno());
				
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
	
	
	// Function for retrieving data from Items table for display
	
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
	
				
				
	// Function for retrieving data from Total,Sub total,GST,Discount Test table for display
	
	public static ArrayList getTotals(int po) throws SQLException
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
			  		+ "((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))-round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100)) as final from supplier_quote where sup_id="+po+" ");
			 
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
	
	
	// Function for retrieving data from suppliers table for display
	
			public static ArrayList getSupplierQuote(int po) throws SQLException
			{
				
				InitCon it = new InitCon();
				Connection con = it.InitConnection();
				PreparedStatement ps;
				ResultSet rs1;
				ArrayList alSupplierQuote = null;
		        ArrayList Supplier_Quote = new ArrayList();
		        
				  try
			        {
					  ps = con.prepareStatement("select s_no,item_name,model,qty,units,unitprice,totalprice from arken.supplier_quote,arken.items where sup_id=? and supplier_quote.description=items.itemid;");
					  ps.setInt(1, po);
					 
					  rs1 = ps.executeQuery();
					 
					  while (rs1.next())
					  {
						  alSupplierQuote = new ArrayList();
						  
						  alSupplierQuote.add(rs1.getString(1));
						  alSupplierQuote.add(rs1.getString(2));
						  alSupplierQuote.add(rs1.getString(3));
						  alSupplierQuote.add(rs1.getString(4));
						  alSupplierQuote.add(rs1.getString(5));
						  alSupplierQuote.add(rs1.getString(6));
						  alSupplierQuote.add(rs1.getString(7));
						  
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
			
			
			
			// Function for Checking the Customer PO and Suppliers PO for qty mismatch
			
			public static int getCheckPOs(int pid, String description,int poid,String currentqty) throws SQLException
			{
				
				InitCon it = new InitCon();
				Connection con = it.InitConnection();
				PreparedStatement ps,ps1;
				ResultSet rs,rs1;
				int qty = 0;
				int qty1 = 0;
				int qty2 = 0;
				int status1 = 0;
				int currentqty1 = Integer.valueOf(currentqty);
				  try
				  {
					  ps = con.prepareStatement("select qty from arken.customer_quote where project_id=? and description = ?;");
					  ps.setInt(1, pid);
					  ps.setString(2, description);
					  rs = ps.executeQuery();
					 
					  while (rs.next())
					  {
						  qty = rs.getInt(1);
					  }
					  
					  ps1 = con.prepareStatement("select sum(qty) from arken.supplier_quote where sup_id=? and description= ?");
					  ps1.setInt(1, poid);
					  ps1.setString(2, description);
					  rs1 = ps1.executeQuery();
					 
					  while (rs1.next())
					  {
						  qty1 = rs1.getInt(1);
					  }
					  
					  qty2 = qty1 + currentqty1;
					  
					  if(qty < qty2)
					  {
						  status1 =1;
					  }
					  
					  con.close();
				  }
			      catch (SQLException e)
			      {
			    	  e.printStackTrace();
			        	
			       }
				return status1; 
			}
}
