package com.arken.reports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.arken.connection.InitCon;

public class ReportsDB 
{

	// Function for select the items reports
	
		public static ArrayList CustomerReports(int pid) throws SQLException
		{
			
			int po = 0;
			
			InitCon it = new InitCon();
			Connection con = it.InitConnection();
			PreparedStatement ps,ps1;
			ArrayList al;
			ArrayList customer_report =new ArrayList();
			
			try
			{
				con.setAutoCommit(false);
				
				ps = con.prepareStatement("select item_name,qty from arken.customer_quote,arken.items where project_id=? and customer_quote.description=items.itemid group by description");
				ps.setInt(1, pid);
				
				
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					al = new ArrayList();
					
					al.add(rs.getString(1));
					al.add(rs.getInt(2));
					
					customer_report.add(al);
				}
				
				con.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return customer_report;
		}
		
		
		
		// Function for select the items consolidate PO reports
		
			public static ArrayList ConsolidateReports(int pid) throws SQLException
			{
				
				int po = 0;
				
				InitCon it = new InitCon();
				Connection con = it.InitConnection();
				PreparedStatement ps,ps1;
				ArrayList al;
				ArrayList consolidate_report =new ArrayList();
				
				try
				{
					con.setAutoCommit(false);
					
					ps = con.prepareStatement("select item_name,qty from suppliers_master,supplier_quote,items where project_id=? and po_id=sup_id and description=itemid group by description");
					ps.setInt(1, pid);
					
					
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						al = new ArrayList();
						
						al.add(rs.getString(1));
						al.add(rs.getInt(2));
						
						consolidate_report.add(al);
					}
					
					con.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				return consolidate_report;
			}
}
