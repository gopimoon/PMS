<%@page import="java.sql.SQLException"%>
<%@page import="com.arken.suppliers.web.dao.SuppliersDB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	int status = 0;
	int po = 0;
	int pid = Integer.parseInt(request.getParameter("projectid"));
	int sid = Integer.parseInt(request.getParameter("supplier_name"));



	SuppliersDB sdb = new SuppliersDB();

	try 
	{
		status = sdb.SavePO(pid, sid);
		
		po = sdb.SelectMaxPOid(pid, sid);
		
		if(status>0)
		{
			response.sendRedirect("Suppliers_2.jsp?projectid="+pid+"&sid="+sid+"&po="+po+"");
		}	
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


%>
</body>
</html>