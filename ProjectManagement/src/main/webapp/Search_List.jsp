<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
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
		String name = request.getParameter("name");
		JSONArray jsonArr = new JSONArray();
		
		JSONObject json=new JSONObject();
        
		json.put("name","abc");
        json.put("value","abc101");
        jsonArr.add(json);
		
		json.put("name","bcd");
        json.put("value","bcd201");
        jsonArr.add(json);
		
		json.put("name","cde");
        json.put("value","cde301");
        jsonArr.add(json);
		
		json.put("name","efg");
        json.put("value","efg401");
        jsonArr.add(json);
        
		out.println(jsonArr);
%>

</body>
</html>