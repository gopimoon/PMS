package com.arken.customerquote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.arken.customerquote.web.model.CustomerQuoteBean;

public class CustomerQuoteDB 
{

	private static DataSource dataSource;
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
		
	}
	
	
	public static int SaveCustomerQuote(CustomerQuoteBean cqb) throws SQLException
	{
		int status=0;
		
		CustomerQuoteDB cqd=new CustomerQuoteDB();
		cqd.init();
		con=dataSource.getConnection();
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
	
	
	// Funciton for retriving data from finalquote table for display
	
		public static ArrayList getCustomerQuote(int pid) throws SQLException
		{
			
			CustomerQuoteDB cqd=new CustomerQuoteDB();
			cqd.init();
			con=dataSource.getConnection();
			PreparedStatement ps;
			ResultSet rs1;
			ArrayList alCustomerQuote = null;
	        ArrayList Customer_Quote = new ArrayList();
	        
			  try
		        {
				  ps = con.prepareStatement("SELECT * FROM arken.customer_quote where project_id = ?");
				  ps.setInt(1, pid);
				 
				  rs1 = ps.executeQuery();
				 
				  while (rs1.next())
				  {
					  alCustomerQuote = new ArrayList();
					  
					  alCustomerQuote.add(rs1.getString(3));
					  alCustomerQuote.add(rs1.getString(4));
					  alCustomerQuote.add(rs1.getString(5));
					  alCustomerQuote.add(rs1.getString(6));
					  alCustomerQuote.add(rs1.getString(7));
					  alCustomerQuote.add(rs1.getString(8));
					  alCustomerQuote.add(rs1.getString(9));
					  alCustomerQuote.add(rs1.getString(10));
					  alCustomerQuote.add(rs1.getString(11));
					  
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
		
		
		
		// Funciton for retriving data from Investication Test table for display
		
			public static ArrayList getItems() throws SQLException
			{
				
				CustomerQuoteDB cqd=new CustomerQuoteDB();
				cqd.init();
				con=dataSource.getConnection();
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
			
				public static ArrayList getTotals() throws SQLException
				{
					
					CustomerQuoteDB cqd=new CustomerQuoteDB();
					cqd.init();
					con=dataSource.getConnection();
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
						  		+ "((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))-round((sum(totalprice) + sum(instotalprice) + round(sum(totalprice)*18/100) + round(sum(instotalprice)*18/100))*5/100)) as final from customer_quote ");
						 
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
