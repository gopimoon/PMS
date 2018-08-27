package com.arken.finalquote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;



import com.arken.connection.InitCon;
import com.arken.finalquote.web.model.FinalQuoteBean;

public class FinalQuoteDB 
{

	/*private static DataSource dataSource;
	private static Connection con;
	
	//Function for connection created for context.xml access for multiple way
	
	public void init() 
	{
		try
		{
			// Get DataSource
			Context initContext  = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/ark");

			
		} 
		catch(NamingException e) 
		{
			e.printStackTrace();
		}
		
	}*/
	
	
	public static int SaveFinalQuote(FinalQuoteBean fqb) throws SQLException
	{
		int status=0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		
		try
		{
			con.setAutoCommit(false);
			ps=con.prepareStatement("INSERT INTO `arken`.`final_quote`(`project_id`,`quote_id`,`s_no`,`description`,`model`,`qty`,`units`,`unitprice`,`totalprice`,"
					+ "`insunitprice`,`instotalprice`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, fqb.getProjectid());
			ps.setInt(2, fqb.getQuoteid());
			ps.setInt(3, fqb.getSno());
			ps.setString(4, fqb.getDescription());
			ps.setString(5, fqb.getModel());
			ps.setString(6, fqb.getQty());
			ps.setString(7, fqb.getUnits());
			ps.setString(8, fqb.getUnitprice());
			ps.setString(9, fqb.getTotalprice());
			ps.setString(10, fqb.getInsunitprice());
			ps.setString(11, fqb.getInstotalprice());
			
			
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
	
	
	
	//Function for update the Final Quote value
	
	
	
	public static int UpdateFinalQuote(FinalQuoteBean fqb) throws SQLException
	{
		int status=0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		
		try
		{
			con.setAutoCommit(false);
			ps=con.prepareStatement("UPDATE `arken`.`final_quote` SET `qty` = ?,"
					+ "`unitprice` = ?,`totalprice` = ?,`insunitprice` = ?,`instotalprice` = ? WHERE project_id = ? and s_no = ? and `quote_id` = ?");
			
			
			
			//ps.setString(1, fqb.getDescription());
			//ps.setString(2, fqb.getModel());
			ps.setString(1, fqb.getQty());
			//ps.setString(4, fqb.getUnits());
			ps.setString(2, fqb.getUnitprice());
			ps.setString(3, fqb.getTotalprice());
			ps.setString(4, fqb.getInsunitprice());
			ps.setString(5, fqb.getInstotalprice());
			ps.setInt(6, fqb.getProjectid());
			ps.setInt(7, fqb.getSno());
			ps.setInt(8, fqb.getQuoteid());
			
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
	
	
	
	
	// Funciton for retriving data from finalquote table for display
	
	public static ArrayList getFinalQuote(int pid) throws SQLException
	{
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		ResultSet rs1;
		ArrayList alFinalQuote = null;
        ArrayList Final_Quote = new ArrayList();
        
		  try
	        {
			  //ps = con.prepareStatement("SELECT * FROM arken.final_quote where project_id = ?");
			  
			  ps = con.prepareStatement("SELECT s_no,item_name,model,qty,units,unitprice,totalprice,insunitprice,instotalprice FROM arken.final_quote,arken.items where final_quote.description=items.itemid and project_id=?;");
			  ps.setInt(1, pid);
			  rs1 = ps.executeQuery();
			 
			  while (rs1.next())
			  {
				  alFinalQuote = new ArrayList();
				  
				  alFinalQuote.add(rs1.getString(1));
				  alFinalQuote.add(rs1.getString(2));
				  alFinalQuote.add(rs1.getString(3));
				  alFinalQuote.add(rs1.getString(4));
				  alFinalQuote.add(rs1.getString(5));
				  alFinalQuote.add(rs1.getString(6));
				  alFinalQuote.add(rs1.getString(7));
				  alFinalQuote.add(rs1.getString(8));
				  alFinalQuote.add(rs1.getString(9));
				  
				  Final_Quote.add(alFinalQuote);
			  }
			  
			  con.close();
			}
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        	
	        }
		return Final_Quote; 
	}
	
	
	
	// Funciton for retriving data from Investication Test table for display
	
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
		
			public static ArrayList getTotals(int pid) throws SQLException
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
					  		+ "((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))-round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100)) as final from final_quote where project_id = "+pid+"");
					 
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
	
}
