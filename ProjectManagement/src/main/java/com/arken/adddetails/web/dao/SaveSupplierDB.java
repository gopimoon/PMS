package com.arken.adddetails.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.arken.connection.InitCon;

public class SaveSupplierDB 
{

		public static int SaveSuppliers(String sup_name,String sup_address,String sup_state,String sup_country,String sup_pin,String sup_panno,String sup_gstno)
		{
			int status = 0;
				try
				{
					InitCon it = new InitCon();
					
					Connection con = it.InitConnection();
					PreparedStatement ps;
					
					con.setAutoCommit(false);
					ps = con.prepareStatement("insert into supplier_master(sup_name,sup_address,sup_state,sup_country,sup_pincode,sup_panno,sup_gstno) values(?,?,?,?,?,?,?)");
					ps.setString(1, sup_name);
					ps.setString(2, sup_address);
					ps.setString(3, sup_state);
					ps.setString(4, sup_country);
					ps.setString(5, sup_pin);
					ps.setString(6, sup_panno);
					ps.setString(7, sup_gstno);
					ps.executeUpdate();
					con.commit();
					
					status = 1;
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					
				}
				return status;
		}
}

