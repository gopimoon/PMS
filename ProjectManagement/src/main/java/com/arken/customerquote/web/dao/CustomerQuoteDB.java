package com.arken.customerquote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.arken.connection.InitCon;
import com.arken.customerquote.web.model.CustomerQuoteBean;

public class CustomerQuoteDB 
{

	
	
	public static int SaveCustomerQuote(CustomerQuoteBean cqb) throws SQLException
	{
		int status=0;
		
		InitCon it = new InitCon();
		Connection con = it.InitConnection();
		PreparedStatement ps;
		
		try
		{
			con.setAutoCommit(false);
			ps=con.prepareStatement("INSERT INTO `arken`.`customer_quote`(`project_id`,`quote_id`,`s_no`,`description`,`model`,`qty`,`units`,`unitprice`,`totalprice`,"
					+ "`insunitprice`,`instotalprice`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, cqb.getProjectid());
			ps.setInt(2, cqb.getQuoteid());
			ps.setInt(3, cqb.getSno());
			ps.setString(4, cqb.getDescription());
			ps.setString(5, cqb.getModel());
			ps.setString(6, cqb.getQty());
			ps.setString(7, cqb.getUnits());
			ps.setString(8, cqb.getUnitprice());
			ps.setString(9, cqb.getTotalprice());
			ps.setString(10, cqb.getInsunitprice());
			ps.setString(11, cqb.getInstotalprice());
			
			
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
	
	
	
		public static int UpdateCustomerQuote(CustomerQuoteBean cqb) throws SQLException
		{
			int status=0;
			
			InitCon it = new InitCon();
			Connection con = it.InitConnection();
			PreparedStatement ps;
			
			try
			{
				con.setAutoCommit(false);
				ps=con.prepareStatement("UPDATE `arken`.`customer_quote` SET `qty` = ?,"
						+ "`unitprice` = ?,`totalprice` = ?,`insunitprice` = ?,`instotalprice` = ? WHERE project_id = ? and s_no = ? and `quote_id` = ?");
				
				
				
				//ps.setString(1, cqb.getDescription());
				//ps.setString(2, cqb.getModel());
				ps.setString(1, cqb.getQty());
				//ps.setString(4, cqb.getUnits());
				ps.setString(2, cqb.getUnitprice());
				ps.setString(3, cqb.getTotalprice());
				ps.setString(4, cqb.getInsunitprice());
				ps.setString(5, cqb.getInstotalprice());
				ps.setInt(6, cqb.getProjectid());
				ps.setInt(7, cqb.getSno());
				ps.setInt(8, cqb.getQuoteid());
				
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
	
		public static ArrayList getCustomerQuote(int pid) throws SQLException
		{
			
			InitCon it = new InitCon();
			Connection con = it.InitConnection();
			PreparedStatement ps;
			ResultSet rs1;
			ArrayList alCustomerQuote = null;
	        ArrayList Customer_Quote = new ArrayList();
	        
			  try
		        {
				  ps = con.prepareStatement("SELECT s_no,item_name,model,qty,units,unitprice,totalprice,insunitprice,instotalprice FROM arken.customer_quote,arken.items where customer_quote.description=items.itemid and project_id=?;");
				  ps.setInt(1, pid);
				 
				  rs1 = ps.executeQuery();
				 
				  while (rs1.next())
				  {
					  alCustomerQuote = new ArrayList();
					  
					  alCustomerQuote.add(rs1.getString(1));
					  alCustomerQuote.add(rs1.getString(2));
					  alCustomerQuote.add(rs1.getString(3));
					  alCustomerQuote.add(rs1.getString(4));
					  alCustomerQuote.add(rs1.getString(5));
					  alCustomerQuote.add(rs1.getString(6));
					  alCustomerQuote.add(rs1.getString(7));
					  alCustomerQuote.add(rs1.getString(8));
					  alCustomerQuote.add(rs1.getString(9));
					  
					  Customer_Quote.add(alCustomerQuote);
				  }
				  
				  con.close();
				}
		        catch (SQLException e)
		        {
		        	e.printStackTrace();
		        	
		        }
			return Customer_Quote; 
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
						  		+ "((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))-round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100)) as final from customer_quote where project_id="+pid+" ");
						 
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
