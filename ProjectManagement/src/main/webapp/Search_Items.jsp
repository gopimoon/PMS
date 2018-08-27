<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>

 
 <script>

		  $(function() {
		  
		   $('#search').keyup(function () {   
			   alert("here")
			   var t = $('#search').val();
			   alert(t)
		    $.ajax({
		     url:"AutoComplete",	
		     type:"get",
		     dataType: 'json',
		     data:
		    	 {
		    	 term: $('#search').val(),
		    	 
		    	 },
		     success:function(data){
		       $( "#search" ).autocomplete({   
		          source: json   
		        });
		     
		     },error:  function(data, status, er){
		              console.log(data+"_"+status+"_"+er);
		          },
		           
		    });
		    
		    });
		 
		  });

  </script> 
  
  <!-- <script>

$document.ready(function()
		{
	alert("here")
			$(function()
					{
				
						$("#search").autocomplete({
							source :function(request,response)
							{
								$.ajax({
									
									url :"AutoComplete",
									type :"GET",
									data :
									{
										term:request.term
									},
									dataType :"json",
									success : function(data)
									{
										response(data);
									}
								});
							},
							select: function( event,ui)
							{
								alert(ui.item.value);
								return false;
							}
							
						});
					});
		});

</script> -->

</head>
<body>


  <!-- <label >Tags: </label>
  <input type="text" id="test" name="test">
   -->
  <input type="text" id="search" name="term">

</body>
</html>