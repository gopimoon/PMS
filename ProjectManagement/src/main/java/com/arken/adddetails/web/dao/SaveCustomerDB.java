package com.arken.adddetails.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.arken.connection.InitCon;

public class SaveCustomerDB 
{

	public static int SaveCustomer(String cus_name,String cus_address,String cus_state,String cus_country,String cus_pin,String cus_panno,String cus_gstno)
	{
		int status = 0;
			try
			{
				InitCon it = new InitCon();
				
				Connection con = it.InitConnection();
				PreparedStatement ps;
				
				con.setAutoCommit(false);
				ps = con.prepareStatement("insert into customer_master(cus_name,cus_address,cus_state,cus_country,cus_pincode,cus_panno,cus_gstno) values(?,?,?,?,?,?,?)");
				ps.setString(1, cus_name);
				ps.setString(2, cus_address);
				ps.setString(3, cus_state);
				ps.setString(4, cus_country);
				ps.setString(5, cus_pin);
				ps.setString(6, cus_panno);
				ps.setString(7, cus_gstno);
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
