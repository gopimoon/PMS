<%@page import="com.arken.customerquote.web.dao.CustomerQuoteDB"%>
<%@page import="com.arken.connection.InitCon"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%
		CustomerQuoteDB cqd = new CustomerQuoteDB();
		
		
		int pid = Integer.parseInt(request.getParameter("project_id"));
		
		System.out.println(pid);
		ArrayList al;
		
		al = new ArrayList();
		al = cqd.getCustomerQuote(pid);
		
		
		ArrayList al1;
		
		al1 = new ArrayList();
		al1 = cqd.getItems();
		
		
		ArrayList al2;
		
		al2 = new ArrayList();
		al2 = cqd.getTotals(pid);
		
		

%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Arken PMS</title>
  
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">

   <script src="js/jquery-1.11.1.js" type="text/javascript"></script>
   
   <script>
   
// Function for add row and save 
   
	$(document).ready(function() 
	{
		            $('.sub').click(function ()
			        {
		            	alert(id)
		            	var cid = this.id;
		            	alert(cid)
		            	var updateflag = $('#updateflag').val();
	                	var projectid = $('#projectid').val();
	                	var quoteid = $('#quoteid').val();
			        	var sno = $('#sno').val();
			        	alert(sno)
			        	var description = $('#description').val();
			        	alert(description)
			        	var model = $('#model').val();
			        	var qty = $('#qty').val();
			        	var units = $('#units').val();
			        	var unitprice = $('#unitprice').val();
			        	var totalprice = $('#totalprice').val();
			        	var insunitprice = $('#insunitprice').val();
			        	var instotalprice = $('#instotalprice').val();
			        	
			        	
			        	if (sno == '') 
			        	{
			        		
			                alert('S.No-field is empty.');
			               
			                return false;
			            }
			        	
			        	
			            $.ajax({
			            	type: "post",
			                url: "FinalQuote", //this is my servlet
			                
			                data: 
			                {
			                	TableIdentifier :"updatequotetable",
			                	updateflag: $('#updateflag').val(),
			                	projectid: $('#projectid').val(),
			                	quoteid: $('#quoteid').val(),
			                	sno: $('#sno').val(),
			                	description: $('#description').val(),
			                	model :$('#model').val(),
			                	qty :$('#qty').val(),
			                	units :$('#units').val(),
			                	unitprice :$('#unitprice').val(),
			                	totalprice :$('#totalprice').val(),
			                	insunitprice :$('#insunitprice').val(),
			                	instotalprice :$('#instotalprice').val(),
			                	
			                },
			                
			                success: function(msg)
			                {      
			                	window.location.reload(true);
			                        $('#output').append(msg);
			                }
			                
			            });
			          
			          });
	
		}); 
		
		
		
		
	 function writeabdata(sno,qty) {
		
		alert("test")
		alert(sno)
	    alert ("table = "+sno+" id = "+qty); //+" field = \'"+field+"\' value = "+value

	    var dataObj = {
	        'table': sno,
	        'id': qty
	    };
	    dataObj[field] = mapstring;

	    $.ajax({
	        type: 'GET',
	        url: 'writeabdata.php',
	        data: dataObj,
	        success: function (data) {
	            alert ("data Saved "+ data);
	        }
	    });
	} 
		
	
	
		
		<%-- 
		            function insert(id)
		      	  	{      
		      		
		            	
		      		 if((id=="investigationtable"))
		      		   { 
		      			 	
		      				
		      			    var filas = document.getElementById("investigationtable").rows.length;
		      			 
		      			    var x = document.getElementById(id).insertRow(filas);
		      			    if(!document.getElementById("sno")=='')
		      				{
		      					
		      					alert("Please enter date field and click to save");
		      					return false;
		      				}	
		      			    
		      			    var a = x.insertCell(0);
		      			    var b = x.insertCell(1);                                          
		      			  	var c= x.insertCell(2);
		      			 	var d= x.insertCell(3);
		      			  	var e= x.insertCell(4);
		      			  	var f= x.insertCell(5);
		      			  	var g= x.insertCell(6);
		      			  	var h= x.insertCell(7);
		      			  	var i= x.insertCell(8);
		      			  	
		      			    a.innerHTML = '<input type="text" name="sno" id="sno" maxlength="25">';
		      			  	<%
				      			  
		      			 			
		      			  			Context initContext  = new InitialContext();
				      				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				      				DataSource datasource = (DataSource)envContext.lookup("jdbc/ark");
				      				
				      				Connection con=datasource.getConnection();
				      				
				      				PreparedStatement ps;
				      				ResultSet rs1;
				      		        ArrayList al2 = new ArrayList();
				      		        
				      				  try
				      			        { 
				      					  ps = con.prepareStatement("SELECT * FROM arken.items;");
				      					  
				      					  rs1 = ps.executeQuery();
				      					 %>
				      					
				      					<%
				      					  while (rs1.next())
				      					  {
				      						al2.add(rs1.getString(2)) ;
				      						  
				      					  }
				      					  %>
				      					
				      						
				      					<%
				      					System.out.println(al2);
				      					  con.close();
				      					
				      					}
				      			        catch (SQLException e)
				      			        {
				      			        	e.printStackTrace();
				      			        	
				      			        }
		      			  	
		      			  		%>			
		      			  					
		      			  					/* b.innerHTML ='' ; */
						      			 	c.innerHTML = '<input type="text" name="model" id="model" class="form-table" maxlength="25" >';
						      			 	d.innerHTML = '<input type="text" name="qty" id="qty" class="form-table" maxlength="25" >';
						      			 	e.innerHTML = '<input type="text" name="units" id="units" class="form-table" maxlength="25" >';
						      			 	f.innerHTML = '<input type="text" name="unitprice" id="unitprice" class="form-table" maxlength="25" >';
						      			 	g.innerHTML = '<input type="text" name="totalprice" id="totalprice" class="form-table" maxlength="25" >';
						      			 	h.innerHTML = '<input type="text" name="insunitprice" id="insunitprice" class="form-table" maxlength="25" >';
						      			 	i.innerHTML = '<input type="text" name="instotalprice" id="instotalprice" class="form-table" maxlength="25" >';
						      			
						            
			      		   }
			      	  	}
		             --%>
		            
		            
		            
   </script>
   
   <script>
   
   
   function multiply(val,val1,val2,val3,val4)
   {
	  
	   var x = document.getElementById(val).value;
	   var y = document.getElementById(val1).value;
	   z = x * y;
	   document.getElementById(val2).value = z;
	   
	   
	   var x1 = document.getElementById(val).value;
	   var y1 = document.getElementById(val3).value;
	   z1 = x1 * y1;
	   document.getElementById(val4).value = z1;
   }   
   
   
   </script>
 
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.jsp">Arken PMS</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
      
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="index.jsp">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Charts">
          <a class="nav-link" href="index.jsp">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Create Projects</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
          <a class="nav-link" href="View_Projects.jsp">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">View Projects</span>
          </a>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Components</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="navbar.html">Navbar</a>
            </li>
            <li>
              <a href="cards.html">Cards</a>
            </li>
          </ul>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">Example Pages</span>
          </a>
          
          <ul class="sidenav-second-level collapse" id="collapseExamplePages">
            <li>
              <a href="login.html">Login Page</a>
            </li>
            <li>
              <a href="register.html">Registration Page</a>
            </li>
            <li>
              <a href="forgot-password.html">Forgot Password Page</a>
            </li>
            <li>
              <a href="blank.html">Blank Page</a>
            </li>
          </ul>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu Levels">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-sitemap"></i>
            <span class="nav-link-text">Menu Levels</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseMulti">
            <li>
              <a href="#">Second Level Item</a>
            </li>
            <li>
              <a href="#">Second Level Item</a>
            </li>
            <li>
              <a href="#">Second Level Item</a>
            </li>
            <li>
              <a class="nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti2">Third Level</a>
              <ul class="sidenav-third-level collapse" id="collapseMulti2">
                <li>
                  <a href="#">Third Level Item</a>
                </li>
                <li>
                  <a href="#">Third Level Item</a>
                </li>
                <li>
                  <a href="#">Third Level Item</a>
                </li>
              </ul>
            </li>
          </ul>
        </li>
        
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
          <a class="nav-link" href="#">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Link</span>
          </a>
        </li>
      </ul>
      
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      
      <ul class="navbar-nav ml-auto">
      
      
<!--         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-envelope"></i>
            <span class="d-lg-none">Messages
              <span class="badge badge-pill badge-primary">12 New</span>
            </span>
            <span class="indicator text-primary d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="messagesDropdown">
            <h6 class="dropdown-header">New Messages:</h6>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>David Miller</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">Hey there! This new version of SB Admin is pretty awesome! These messages clip off when they reach the end of the box so they don't overflow over to the sides!</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>Jane Smith</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">I was wondering if you could meet for an appointment at 3:00 instead of 4:00. Thanks!</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <strong>John Doe</strong>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">I've sent the final files over to you for review. When you're able to sign off of them let me know and we can discuss distribution.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="#">View all messages</a>
          </div>
        </li> -->
        
        
        <!-- <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle mr-lg-2" id="alertsDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-bell"></i>
            <span class="d-lg-none">Alerts
              <span class="badge badge-pill badge-warning">6 New</span>
            </span>
            <span class="indicator text-warning d-none d-lg-block">
              <i class="fa fa-fw fa-circle"></i>
            </span>
          </a>
          <div class="dropdown-menu" aria-labelledby="alertsDropdown">
            <h6 class="dropdown-header">New Alerts:</h6>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-success">
                <strong>
                  <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-danger">
                <strong>
                  <i class="fa fa-long-arrow-down fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">
              <span class="text-success">
                <strong>
                  <i class="fa fa-long-arrow-up fa-fw"></i>Status Update</strong>
              </span>
              <span class="small float-right text-muted">11:21 AM</span>
              <div class="dropdown-message small">This is an automated server response message. All systems are online.</div>
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="#">View all alerts</a>
          </div>
        </li> -->
        
        
        <li class="nav-item">
          <form class="form-inline my-2 my-lg-0 mr-lg-2">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="Search for...">
              <span class="input-group-btn">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
            </div>
          </form>
        </li>
        
        
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Logout</a>
        </li>
        
      </ul>
      
    </div>
  </nav>
  
  <div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="index.jsp">Dashboard</a>
        </li>
       <!--  <li class="breadcrumb-item active">My Dashboard</li> -->
      </ol>
      
                <!--  <form action="SearchServlet" method="post">               Icon Cards
      	<div class="row">
     
        <div class="col-xl-3 col-sm-6 mb-3">  ---------------   row define---------------------
          
          <div class="card text-white bg-primary o-hidden h-100"> ---------------   row colour define---------------------
           
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-comments"></i> 
              </div>
              
              <div class="mr-5">Sex :  <select name="gender" class="form-control" >
								  <option value="">Select</option>
								  <option value="Male">Male</option>
								  <option value="Female">Female</option>
								  </select>
			  </div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
        
        
        
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">Age : <select name="agerange" class="form-control">
								  <option value="">Select</option>
								  <option value="0-10">0-10</option>
								  <option value="10-20">10-20</option>
								  <option value="20-30">20-30</option>
								  <option value="30-40">30-40</option>
								  <option value="40-50">40-50</option>
								  <option value="50-60">50-60</option>
								  <option value="60-70">60-70</option>
								  <option value="70-80">70-80</option>
								  <option value="80-90">80-90</option>
								  <option value="90-100">90-100</option>
								  <option value="100-110">100-110</option>
								  </select></div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
        
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-shopping-cart"></i>
              </div>
              <div class="mr-5">Disease Types : <select name="diseasetype" class="form-control">
								  <option value="">Select</option>
								  <option value="ADRENAL">ADRENAL</option>
								  <option value="ANAL">ANAL</option>
								  <option value="BILE DUCT">BILE DUCT</option>
								  <option value="BLADDER">BLADDER</option>
								  <option value="BONE">BONE</option>
								  <option value="BRAIN">BRAIN</option>
								  <option value="BREAST">BREAST</option>
								  <option value="CERVIX">CERVIX</option>
								  <option value="COLON">COLON</option>
								  <option value="ESOPHAGEAL">ESOPHAGEAL</option>
								  <option value="EYE">EYE</option>
					              <option value="GALLBLADDER">GALLBLADDER</option>
					              <option value="GASTRIC">GASTRIC</option>
					              <option value="HEAD&NECK">HEAD&NECK</option>
					              <option value="LEUKAEMIA">LEUKAEMIA</option>
					              <option value="LIVER">LIVER</option>
					              <option value="LUNG">LUNG</option>
					              <option value="LYMPHOMA">LYMPHOMA</option>
					              <option value="OTHER">OTHER</option>
					              <option value="OVARIAN">OVARIAN</option>
					              <option value="PANCREATIC">PANCREATIC</option>
					              <option value="PENILE">PENILE</option>
					              <option value="PROSTATE">PROSTATE</option>
					              <option value="RECTAL">RECTAL</option>
					              <option value="RENAL">RENAL</option>
					              <option value="SARCOMA">SARCOMA</option>
					              <option value="SKIN">SKIN</option>
					              <option value="THYROID">THYROID</option>
					              <option value="UNASSIGNED">UNASSIGNED</option>
					              <option value="VAGINAL">VAGINAL</option>
					              </select>
			</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
        
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-support"></i>
              </div>
              <div class="mr-5">
              Location : <input type="text" name="age" id="age" class="form-control"  value="" >
			</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        
        
        <div class="col-xl-3 col-sm-6 mb-3">
          <div >class="card text-white bg-danger o-hidden h-100"
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-support"></i>
              </div>
              <div class="mr-5">
              <input type="submit" class="button" id="submit" class="form-control" value="Submit" >
              
			</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      
      </div>
      </form> -->
      <!-- Area Chart Example-->
      <div class="card mb-3">
        <div class="card-header">
          <i></i>Final Quote</div>
        <div  class="card-body" >
         <div id="myAreaChart" style="height: 477px; width: 80%;" >
         <%-- <form action="FinalQuote" method="post">
         
         <input type="hidden" name="projectid" id="projectid" value="<%=pid%>">
         <input type="hidden" name="updateflag" id="updateflag" value="YES">
         <input type="hidden" name="quoteid" id="quoteid" value="<%=pid%>">
         <input type="hidden" name="TableIdentifier" id="TableIdentifier" value="updatequotetable"> --%>
         <legend>Final Quote PO</legend>
								
											<div class="form-group">
										   <label class="col-md-2 control-label" >Purchase Order</label>  
										  	
											    <table  class="table-bordered table-hover" id="investigationtable">
												<tr>
												<th class="text-center" colspan="5">Mapleton Apartment</th>
												<th class="text-center" colspan="2">Supply of Meterials</th>
												<th class="text-center" colspan="2">Installation of Materials</th>
												</tr>
												<tr>
													<th class="text-center" rowspan="2">S.No</th>
													<th class="text-center" rowspan="2">Discription</th>
													<th class="text-center" rowspan="2">Make/Model</th>
													<th class="text-center" rowspan="2">Qty</th>
													<th class="text-center" rowspan="2">Units</th>
													<th class="text-center" rowspan="">Unitprice</th>
													<th class="text-center" colspan="">Total Price</th>
													<th class="text-center" rowspan="">Unit Price</th>
													<th class="text-center" rowspan="">Total Price</th>
												</tr>
												<tr>
												</tr>
									
									 
									 <%
						                int count = 0;
						                String color = "#F9EBB3";
						             
						                if (!al.isEmpty()) 
						               {
						                  
						                    Iterator itr = al.iterator();
						                    
						                    while (itr.hasNext()) 
						                    {
						 
						                        if ((count % 2) == 0) 
						                        {
						                            color = "#eeffee";
						                        }
						                        count++;
						                        ArrayList pList = (ArrayList) itr.next();
						           		 %>
						           		<form action="CustomerQuote" method="post">
         
									         <input type="hidden" name="projectid" id="projectid" value="<%=pid%>">
									         <input type="hidden" name="updateflag" id="updateflag" value="YES">
									         <input type="hidden" name="quoteid" id="quoteid" value="<%=pid%>">
									         <input type="hidden" name="TableIdentifier" id="TableIdentifier" value="updatequotetable">
								            	<tr style="background-color:<%=color%>;">
								            
								                <td class="text-center"><input typ="text" name="sno" id="sno" value="<%=pList.get(0)%>" readonly></td>
								                <td class="text-center"><input type="text" name="description" id="description<%=pList.get(0)%>"  value="<%=pList.get(1)%>" readonly></td>
								                <td class="text-center"><input type="text" name="model" id="model<%=pList.get(0)%>" value="<%=pList.get(2)%>" readonly></td>
								                <td class="text-center"><input type="text" name="qty" id="qty<%=pList.get(0)%>" value="<%=pList.get(3)%>" onChange="multiply('qty<%=pList.get(0)%>','unitprice<%=pList.get(0)%>','totalprice<%=pList.get(0)%>','insunitprice<%=pList.get(0)%>','instotalprice<%=pList.get(0)%>');"></td>
								                <td class="text-center"><input type="text" name="units" id="units<%=pList.get(0)%>" value="<%=pList.get(4)%>" readonly></td>
								                <td class="text-center"><input type="text" name="unitprice" id="unitprice<%=pList.get(0)%>" value="<%=pList.get(5)%>" onChange="multiply('qty<%=pList.get(0)%>','unitprice<%=pList.get(0)%>','totalprice<%=pList.get(0)%>','insunitprice<%=pList.get(0)%>','instotalprice<%=pList.get(0)%>');"></td>
								                <td class="text-center"><input type="text" name="totalprice" id="totalprice<%=pList.get(0)%>" value="<%=pList.get(6)%>" readonly></td>
								                <td class="text-center"><input type="text" name="insunitprice" id="insunitprice<%=pList.get(0)%>" value="<%=pList.get(7)%>" onChange="multiply('qty<%=pList.get(0)%>','unitprice<%=pList.get(0)%>','totalprice<%=pList.get(0)%>','insunitprice<%=pList.get(0)%>','instotalprice<%=pList.get(0)%>');"></td>
								                <td class="text-center"><input type="text" name="instotalprice" id="instotalprice<%=pList.get(0)%>" value="<%=pList.get(8)%>" readonly></td>
								              <%--  <td><a href="" data-id="<%=pList.get(0)%>" class="clik_for_fetch">Update</a></td>
								               <td><input type="button"  onClick="writeabdata(this.sno,this.qty);">check</a></td> --%>
									  			<td><input type="submit" value="Save Row"></td>
									  			</tr>
									  	</form>
						            	<%
						                   }
						                }
						                
									  %>
									  
									  		
									  			
									  			<%
						                int count2 = 0;
						                String color2 = "#F9EBB3";
						             
						                if (!al2.isEmpty()) 
						               {
						                  
						                    Iterator itr2 = al2.iterator();
						                    
						                    while (itr2.hasNext()) 
						                    {
						 
						                        if ((count2 % 2) == 0) 
						                        {
						                            color2 = "#eeffee";
						                        }
						                        count2++;
						                        ArrayList pList2 = (ArrayList) itr2.next();
						           		 %>
								            
								            <tr>
									  			<td colspan="6"><b>Sub Total</b></td>
								                <td class="text-center"><b><input value="<%=pList2.get(0)%>" readonly></b></td>
												<td class="text-center"></td>
									    		<td class="text-center"><b><input value="<%=pList2.get(1)%>" readonly></b></td>
								              </tr>
											<%-- <tr>
													
											<td colspan="6"><b>GST on 18 %</b></td>
											<td class="text-center"><b><%=pList2.get(2)%></b></td>
											<td class="text-center"></td>
										    <td class="text-center"><b><%=pList2.get(3)%></b></td>
									    
												</tr> --%>
												
												<%-- <tr>
													
										<td colspan="8"><b>Total</b></td>
										<td class="text-center"><b><%=pList2.get(4)%></b></td>
										
									    		
										</tr> --%>
										<%-- <tr>
													
										<td colspan="8"><b>Customer special Discount 5 %</b></td>
										<td class="text-center"><b><%=pList2.get(5)%></b></td>
										
												</tr>
												
												
												<tr>
													
										<td colspan="8"><b>Grand Total After 5 % Discount</b></td>
										<td class="text-center"><b><%=pList2.get(6)%></b></td>
										
												</tr> --%>
								            
						            	<%
						                   }
						                }
						                
									  %>
									  
									  <%-- <tr>
										<td><input type="text" name="sno" id="sno" maxlength="25"></td>
										<td>
												<select id="description" name="description">
											 	 <option>Select</option>
											  <%
											 
							      			  
					      			 			
											  	InitCon it = new InitCon();
												Connection con = it.InitConnection();
							      				
							      				PreparedStatement ps1;
							      				ResultSet rs2;
							      		        
							      				  try
							      			        { 
							      					  ps1 = con.prepareStatement("SELECT * FROM arken.items;");
							      					  
							      					  rs2 = ps1.executeQuery();
							      					 %>
							      					
							      					<%
							      					  while (rs2.next())
							      					  {%>
														  <option value="<%=rs2.getString(2)%>"><%=rs2.getString(2)%></option>
													<%	  
														
							      					  }
							      					  
							      					  con.close();
							      					  
							      					}
							      			        catch (SQLException e)
							      			        {
							      			        	e.printStackTrace();
							      			        	
							      			        }
							      				
					      			  		%>	
											
											 </select>
										
										
										
										</td>
										<td><input type="text" name="model" id="model" class="form-table" maxlength="25" ></td>
										<td><input type="text" name="qty" id="qty" onChange="multiply()" class="form-table" maxlength="25" ></td>
										<td><input type="text" name="units" id="units" class="form-table" maxlength="25" ></td>
									    <td><input type="text" name="unitprice" id="unitprice" onChange="multiply()" class="form-table" maxlength="25" ></td>
									    <td><input type="text" name="totalprice" id="totalprice" class="form-table" maxlength="25" ></td>
										<td><input type="text" name="insunitprice" id="insunitprice" onChange="multiply()" class="form-table" maxlength="25" ></td>
								 		<td><input type="text" name="instotalprice" id="instotalprice" class="form-table" maxlength="25" ></td>
							 			
									</tr> --%>
									
									  </table>	
									 <!-- 
									  <input type="button"  class="button" id="investigationtable" onclick="insert('investigationtable')" value="Insert Row">
									  <input type="button" class="button" id="investtable" value="Save Row" /> -->
									  
									  
								</div>
								<!-- </form> -->
								</div>
         </div>
        </div>
       <!-- <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
      </div>
     
      <div class="row">
        <div class="col-lg-8">
          <!-- Example Bar Chart Card-->
          <!-- <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Pie Chart for Disease Types</div>
            <div class="card-body">
              <div class="row">
                <div class="col-sm-8 my-auto">
                <div id="myBarChart" style="height: 477px; width: 1000px;" ></div>
                </div>
                <div class="col-sm-4 text-center my-auto">
                  <div class="h4 mb-0 text-primary">$34,693</div>
                  <div class="small text-muted">YTD Revenue</div>
                  <hr>
                  <div class="h4 mb-0 text-warning">$18,474</div>
                  <div class="small text-muted">YTD Expenses</div>
                  <hr>
                  <div class="h4 mb-0 text-success">$16,219</div>
                  <div class="small text-muted">YTD Margin</div>
                </div>
              </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div> -->
          
          
          <!-- <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-pie-chart"></i> Pie Chart for Disease Types</div>
            <div class="card-body">
              <div class="row">
                <div class="col-sm-8 my-auto" >
                <div id="resultdiv" style="height: 477px; width: 1000px;" >
                Select any drop drown value and get the results here
                </div>
                </div>
                <div class="col-sm-4 text-center my-auto">
                  <div class="h4 mb-0 text-primary">$34,693</div>
                  <div class="small text-muted">YTD Revenue</div>
                  <hr>
                  <div class="h4 mb-0 text-warning">$18,474</div>
                  <div class="small text-muted">YTD Expenses</div>
                  <hr>
                  <div class="h4 mb-0 text-success">$16,219</div>
                  <div class="small text-muted">YTD Margin</div>
                </div>
              </div> 
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div> -->
          
           
          
          <!-- Card Columns Example Social Feed-->
          <!-- <div class="mb-0 mt-4">
            <i class="fa fa-newspaper-o"></i> News Feed</div>
          <hr class="mt-2">
          <div class="card-columns">
            Example Social Card
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top img-fluid w-100" src="https://unsplash.it/700/450?image=610" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a href="#">David Miller</a></h6>
                <p class="card-text small">These waves are looking pretty good today!
                  <a href="#">#surfsup</a>
                </p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-thumbs-up"></i>Like</a>
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-comment"></i>Comment</a>
                <a class="d-inline-block" href="#">
                  <i class="fa fa-fw fa-share"></i>Share</a>
              </div>
              <hr class="my-0">
              <div class="card-body small bg-faded">
                <div class="media">
                  <img class="d-flex mr-3" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <h6 class="mt-0 mb-1"><a href="#">John Smith</a></h6>Very nice! I wish I was there! That looks amazing!
                    <ul class="list-inline mb-0">
                      <li class="list-inline-item">
                        <a href="#">Like</a>
                      </li>
                      <li class="list-inline-item">·</li>
                      <li class="list-inline-item">
                        <a href="#">Reply</a>
                      </li>
                    </ul>
                    <div class="media mt-3">
                      <a class="d-flex pr-3" href="#">
                        <img src="http://placehold.it/45x45" alt="">
                      </a>
                      <div class="media-body">
                        <h6 class="mt-0 mb-1"><a href="#">David Miller</a></h6>Next time for sure!
                        <ul class="list-inline mb-0">
                          <li class="list-inline-item">
                            <a href="#">Like</a>
                          </li>
                          <li class="list-inline-item">·</li>
                          <li class="list-inline-item">
                            <a href="#">Reply</a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-footer small text-muted">Posted 32 mins ago</div>
            </div>
            Example Social Card
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top img-fluid w-100" src="https://unsplash.it/700/450?image=180" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a href="#">John Smith</a></h6>
                <p class="card-text small">Another day at the office...
                  <a href="#">#workinghardorhardlyworking</a>
                </p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-thumbs-up"></i>Like</a>
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-comment"></i>Comment</a>
                <a class="d-inline-block" href="#">
                  <i class="fa fa-fw fa-share"></i>Share</a>
              </div>
              <hr class="my-0">
              <div class="card-body small bg-faded">
                <div class="media">
                  <img class="d-flex mr-3" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <h6 class="mt-0 mb-1"><a href="#">Jessy Lucas</a></h6>Where did you get that camera?! I want one!
                    <ul class="list-inline mb-0">
                      <li class="list-inline-item">
                        <a href="#">Like</a>
                      </li>
                      <li class="list-inline-item">·</li>
                      <li class="list-inline-item">
                        <a href="#">Reply</a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="card-footer small text-muted">Posted 46 mins ago</div>
            </div>
            Example Social Card
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top img-fluid w-100" src="https://unsplash.it/700/450?image=281" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a href="#">Jeffery Wellings</a></h6>
                <p class="card-text small">Nice shot from the skate park!
                  <a href="#">#kickflip</a>
                  <a href="#">#holdmybeer</a>
                  <a href="#">#igotthis</a>
                </p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-thumbs-up"></i>Like</a>
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-comment"></i>Comment</a>
                <a class="d-inline-block" href="#">
                  <i class="fa fa-fw fa-share"></i>Share</a>
              </div>
              <div class="card-footer small text-muted">Posted 1 hr ago</div>
            </div>
            Example Social Card
            <div class="card mb-3">
              <a href="#">
                <img class="card-img-top img-fluid w-100" src="https://unsplash.it/700/450?image=185" alt="">
              </a>
              <div class="card-body">
                <h6 class="card-title mb-1"><a href="#">David Miller</a></h6>
                <p class="card-text small">It's hot, and I might be lost...
                  <a href="#">#desert</a>
                  <a href="#">#water</a>
                  <a href="#">#anyonehavesomewater</a>
                  <a href="#">#noreally</a>
                  <a href="#">#thirsty</a>
                  <a href="#">#dehydration</a>
                </p>
              </div>
              <hr class="my-0">
              <div class="card-body py-2 small">
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-thumbs-up"></i>Like</a>
                <a class="mr-3 d-inline-block" href="#">
                  <i class="fa fa-fw fa-comment"></i>Comment</a>
                <a class="d-inline-block" href="#">
                  <i class="fa fa-fw fa-share"></i>Share</a>
              </div>
              <hr class="my-0">
              <div class="card-body small bg-faded">
                <div class="media">
                  <img class="d-flex mr-3" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <h6 class="mt-0 mb-1"><a href="#">John Smith</a></h6>The oasis is a mile that way, or is that just a mirage?
                    <ul class="list-inline mb-0">
                      <li class="list-inline-item">
                        <a href="#">Like</a>
                      </li>
                      <li class="list-inline-item">·</li>
                      <li class="list-inline-item">
                        <a href="#">Reply</a>
                      </li>
                    </ul>
                    <div class="media mt-3">
                      <a class="d-flex pr-3" href="#">
                        <img src="http://placehold.it/45x45" alt="">
                      </a>
                      <div class="media-body">
                        <h6 class="mt-0 mb-1"><a href="#">David Miller</a></h6>
                        <img class="img-fluid w-100 mb-1" src="https://unsplash.it/700/450?image=789" alt="">I'm saved, I found a cactus. How do I open this thing?
                        <ul class="list-inline mb-0">
                          <li class="list-inline-item">
                            <a href="#">Like</a>
                          </li>
                          <li class="list-inline-item">·</li>
                          <li class="list-inline-item">
                            <a href="#">Reply</a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-footer small text-muted">Posted yesterday</div>
            </div>
          </div> -->
          <!-- /Card Columns-->
        </div>
        <div class="col-lg-4">
          <!-- Example Pie Chart Card-->
          <!-- <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bar-chart"></i> Bar Chart for Gender</div>
            <div class="card-body" >
              <div id="myPieChart" style="height: 477px; width: 100%;" ></div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div> -->
          
          <!-- Treatment policy chart-->
          
         
          
          <!-- <div class="card mb-3">
            <div class="card-header">
              <i class="fa fa-bar-chart"></i> Bar Chart for Treatment Policy</div>
            <div class="card-body" >
              <div id="TreatPolicy" style="height: 477px; width: 100%;" ></div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div> -->
          
          
          <!-- Example Notifications Card-->
          <!-- <form action="SearchServlet" method="post">
         <div class="card mb-3">
         
         <div class="card text-white bg-warning o-hidden h-100">
         <div class="card-body">
         <div class="mr-5">
         	Sex :  <select name="gender" class="form-control" >
								  <option value="">Select</option>
								  <option value="Male">Male</option>
								  <option value="Female">Female</option>
								  </select>
		 </div>
		 </div>
		 </div>
		 
		 
		 <div class="card text-white bg-warning o-hidden h-100">
         <div class="card-body">
		 <div class="mr-5">
		 	Age : <select name="agerange" class="form-control">
								  <option value="">Select</option>
								  <option value="0-10">0-10</option>
								  <option value="10-20">10-20</option>
								  <option value="20-30">20-30</option>
								  <option value="30-40">30-40</option>
								  <option value="40-50">40-50</option>
								  <option value="50-60">50-60</option>
								  <option value="60-70">60-70</option>
								  <option value="70-80">70-80</option>
								  <option value="80-90">80-90</option>
								  <option value="90-100">90-100</option>
								  <option value="100-110">100-110</option>
								  </select>
		  </div>
		  </div>
		  </div>
		  
		  <div class="card text-white bg-warning o-hidden h-100">
         <div class="card-body">
		 <div class="mr-5">
		 	Disease Types : <select name="diseasetype" class="form-control">
								  <option value="">Select</option>
								  <option value="ADRENAL">ADRENAL</option>
								  <option value="ANAL">ANAL</option>
								  <option value="BILE DUCT">BILE DUCT</option>
								  <option value="BLADDER">BLADDER</option>
								  <option value="BONE">BONE</option>
								  <option value="BRAIN">BRAIN</option>
								  <option value="BREAST">BREAST</option>
								  <option value="CERVIX">CERVIX</option>
								  <option value="COLON">COLON</option>
								  <option value="ESOPHAGEAL">ESOPHAGEAL</option>
								  <option value="EYE">EYE</option>
					              <option value="GALLBLADDER">GALLBLADDER</option>
					              <option value="GASTRIC">GASTRIC</option>
					              <option value="HEAD&NECK">HEAD&NECK</option>
					              <option value="LEUKAEMIA">LEUKAEMIA</option>
					              <option value="LIVER">LIVER</option>
					              <option value="LUNG">LUNG</option>
					              <option value="LYMPHOMA">LYMPHOMA</option>
					              <option value="OTHER">OTHER</option>
					              <option value="OVARIAN">OVARIAN</option>
					              <option value="PANCREATIC">PANCREATIC</option>
					              <option value="PENILE">PENILE</option>
					              <option value="PROSTATE">PROSTATE</option>
					              <option value="RECTAL">RECTAL</option>
					              <option value="RENAL">RENAL</option>
					              <option value="SARCOMA">SARCOMA</option>
					              <option value="SKIN">SKIN</option>
					              <option value="THYROID">THYROID</option>
					              <option value="UNASSIGNED">UNASSIGNED</option>
					              <option value="VAGINAL">VAGINAL</option>
					              </select>
			</div>
			</div>
			</div>
			
			<div class="card text-white bg-warning o-hidden h-100">
        	 <div class="card-body">
			<div class="mr-5">
              <input type="submit" class="button" id="submit" class="form-control" value="Submit" >
              
			</div>
			
			</div>
			</div>
			 </form> -->
           <!--  <div class="card-header">
              <i class="fa fa-bell-o"></i> Feed Example</div>
            <div class="list-group list-group-flush small">
              <a class="list-group-item list-group-item-action" href="#">
                <div class="media">
                  <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <strong>David Miller</strong>posted a new article to
                    <strong>David Miller Website</strong>.
                    <div class="text-muted smaller">Today at 5:43 PM - 5m ago</div>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action" href="#">
                <div class="media">
                  <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <strong>Samantha King</strong>sent you a new message!
                    <div class="text-muted smaller">Today at 4:37 PM - 1hr ago</div>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action" href="#">
                <div class="media">
                  <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <strong>Jeffery Wellings</strong>added a new photo to the album
                    <strong>Beach</strong>.
                    <div class="text-muted smaller">Today at 4:31 PM - 1hr ago</div>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action" href="#">
                <div class="media">
                  <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/45x45" alt="">
                  <div class="media-body">
                    <i class="fa fa-code-fork"></i>
                    <strong>Monica Dennis</strong>forked the
                    <strong>startbootstrap-sb-admin</strong>repository on
                    <strong>GitHub</strong>.
                    <div class="text-muted smaller">Today at 3:54 PM - 2hrs ago</div>
                  </div>
                </div>
              </a>
              <a class="list-group-item list-group-item-action" href="#">View all activity...</a>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div> -->
            
          </div> 
        
        </div>
        
         <!-- <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-bar-chart"></i> Bar Chart for Disease Types</div>
        <div  class="card-body" >
         <div id="TreatPolicy" style="height: 477px; width: 100%;" ></div>
        </div>
       <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div> -->
      
        </div>
     
      
      <!-- Example DataTables Card-->
      <!-- <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Table Results</div>
        <div class="card-body" >
          <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>Patient Id</th>
                  <th>Name</th>
                  <th>Age</th>
                  <th>Sex</th>
                  <th>Cd No</th>
                  <th>Address</th>
                  <th>Phone no</th>
                  <th>Occupation</th>
                  <th>Diagnosis Type</th>
                  <th>Diagnosis Organ</th>
                  <th>Diagnosis Subtypes</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>Name</th>
                  <th>Position</th>
                  <th>Office</th>
                  <th>Age</th>
                  <th>Start date</th>
                  <th>Salary</th>
                </tr>
              </tfoot>
              <tbody>
                <tr>
                  <td>Tiger Nixon</td>
                  <td>System Architect</td>
                  <td>Edinburgh</td>
                  <td>61</td>
                  <td>2011/04/25</td>
                  <td>$320,800</td>
                </tr>
                <tr>
                  <td>Garrett Winters</td>
                  <td>Accountant</td>
                  <td>Tokyo</td>
                  <td>63</td>
                  <td>2011/07/25</td>
                  <td>$170,750</td>
                </tr>
                <tr>
                  <td>Ashton Cox</td>
                  <td>Junior Technical Author</td>
                  <td>San Francisco</td>
                  <td>66</td>
                  <td>2009/01/12</td>
                  <td>$86,000</td>
                </tr>
                <tr>
                  <td>Cedric Kelly</td>
                  <td>Senior Javascript Developer</td>
                  <td>Edinburgh</td>
                  <td>22</td>
                  <td>2012/03/29</td>
                  <td>$433,060</td>
                </tr>
                <tr>
                  <td>Airi Satou</td>
                  <td>Accountant</td>
                  <td>Tokyo</td>
                  <td>33</td>
                  <td>2008/11/28</td>
                  <td>$162,700</td>
                </tr>
                <tr>
                  <td>Brielle Williamson</td>
                  <td>Integration Specialist</td>
                  <td>New York</td>
                  <td>61</td>
                  <td>2012/12/02</td>
                  <td>$372,000</td>
                </tr>
                <tr>
                  <td>Herrod Chandler</td>
                  <td>Sales Assistant</td>
                  <td>San Francisco</td>
                  <td>59</td>
                  <td>2012/08/06</td>
                  <td>$137,500</td>
                </tr>
                <tr>
                  <td>Rhona Davidson</td>
                  <td>Integration Specialist</td>
                  <td>Tokyo</td>
                  <td>55</td>
                  <td>2010/10/14</td>
                  <td>$327,900</td>
                </tr>
                <tr>
                  <td>Colleen Hurst</td>
                  <td>Javascript Developer</td>
                  <td>San Francisco</td>
                  <td>39</td>
                  <td>2009/09/15</td>
                  <td>$205,500</td>
                </tr>
                <tr>
                  <td>Sonya Frost</td>
                  <td>Software Engineer</td>
                  <td>Edinburgh</td>
                  <td>23</td>
                  <td>2008/12/13</td>
                  <td>$103,600</td>
                </tr>
                <tr>
                  <td>Jena Gaines</td>
                  <td>Office Manager</td>
                  <td>London</td>
                  <td>30</td>
                  <td>2008/12/19</td>
                  <td>$90,560</td>
                </tr>
                <tr>
                  <td>Quinn Flynn</td>
                  <td>Support Lead</td>
                  <td>Edinburgh</td>
                  <td>22</td>
                  <td>2013/03/03</td>
                  <td>$342,000</td>
                </tr>
                <tr>
                  <td>Charde Marshall</td>
                  <td>Regional Director</td>
                  <td>San Francisco</td>
                  <td>36</td>
                  <td>2008/10/16</td>
                  <td>$470,600</td>
                </tr>
                <tr>
                  <td>Haley Kennedy</td>
                  <td>Senior Marketing Designer</td>
                  <td>London</td>
                  <td>43</td>
                  <td>2012/12/18</td>
                  <td>$313,500</td>
                </tr>
                <tr>
                  <td>Tatyana Fitzpatrick</td>
                  <td>Regional Director</td>
                  <td>London</td>
                  <td>19</td>
                  <td>2010/03/17</td>
                  <td>$385,750</td>
                </tr>
                <tr>
                  <td>Michael Silva</td>
                  <td>Marketing Designer</td>
                  <td>London</td>
                  <td>66</td>
                  <td>2012/11/27</td>
                  <td>$198,500</td>
                </tr>
                <tr>
                  <td>Paul Byrd</td>
                  <td>Chief Financial Officer (CFO)</td>
                  <td>New York</td>
                  <td>64</td>
                  <td>2010/06/09</td>
                  <td>$725,000</td>
                </tr>
                <tr>
                  <td>Gloria Little</td>
                  <td>Systems Administrator</td>
                  <td>New York</td>
                  <td>59</td>
                  <td>2009/04/10</td>
                  <td>$237,500</td>
                </tr>
                <tr>
                  <td>Bradley Greer</td>
                  <td>Software Engineer</td>
                  <td>London</td>
                  <td>41</td>
                  <td>2012/10/13</td>
                  <td>$132,000</td>
                </tr>
                <tr>
                  <td>Dai Rios</td>
                  <td>Personnel Lead</td>
                  <td>Edinburgh</td>
                  <td>35</td>
                  <td>2012/09/26</td>
                  <td>$217,500</td>
                </tr>
                <tr>
                  <td>Jenette Caldwell</td>
                  <td>Development Lead</td>
                  <td>New York</td>
                  <td>30</td>
                  <td>2011/09/03</td>
                  <td>$345,000</td>
                </tr>
                <tr>
                  <td>Yuri Berry</td>
                  <td>Chief Marketing Officer (CMO)</td>
                  <td>New York</td>
                  <td>40</td>
                  <td>2009/06/25</td>
                  <td>$675,000</td>
                </tr>
                <tr>
                  <td>Caesar Vance</td>
                  <td>Pre-Sales Support</td>
                  <td>New York</td>
                  <td>21</td>
                  <td>2011/12/12</td>
                  <td>$106,450</td>
                </tr>
                <tr>
                  <td>Doris Wilder</td>
                  <td>Sales Assistant</td>
                  <td>Sidney</td>
                  <td>23</td>
                  <td>2010/09/20</td>
                  <td>$85,600</td>
                </tr>
                <tr>
                  <td>Angelica Ramos</td>
                  <td>Chief Executive Officer (CEO)</td>
                  <td>London</td>
                  <td>47</td>
                  <td>2009/10/09</td>
                  <td>$1,200,000</td>
                </tr>
                <tr>
                  <td>Gavin Joyce</td>
                  <td>Developer</td>
                  <td>Edinburgh</td>
                  <td>42</td>
                  <td>2010/12/22</td>
                  <td>$92,575</td>
                </tr>
                <tr>
                  <td>Jennifer Chang</td>
                  <td>Regional Director</td>
                  <td>Singapore</td>
                  <td>28</td>
                  <td>2010/11/14</td>
                  <td>$357,650</td>
                </tr>
                <tr>
                  <td>Brenden Wagner</td>
                  <td>Software Engineer</td>
                  <td>San Francisco</td>
                  <td>28</td>
                  <td>2011/06/07</td>
                  <td>$206,850</td>
                </tr>
                <tr>
                  <td>Fiona Green</td>
                  <td>Chief Operating Officer (COO)</td>
                  <td>San Francisco</td>
                  <td>48</td>
                  <td>2010/03/11</td>
                  <td>$850,000</td>
                </tr>
                <tr>
                  <td>Shou Itou</td>
                  <td>Regional Marketing</td>
                  <td>Tokyo</td>
                  <td>20</td>
                  <td>2011/08/14</td>
                  <td>$163,000</td>
                </tr>
                <tr>
                  <td>Michelle House</td>
                  <td>Integration Specialist</td>
                  <td>Sidney</td>
                  <td>37</td>
                  <td>2011/06/02</td>
                  <td>$95,400</td>
                </tr>
                <tr>
                  <td>Suki Burks</td>
                  <td>Developer</td>
                  <td>London</td>
                  <td>53</td>
                  <td>2009/10/22</td>
                  <td>$114,500</td>
                </tr>
                <tr>
                  <td>Prescott Bartlett</td>
                  <td>Technical Author</td>
                  <td>London</td>
                  <td>27</td>
                  <td>2011/05/07</td>
                  <td>$145,000</td>
                </tr>
                <tr>
                  <td>Gavin Cortez</td>
                  <td>Team Leader</td>
                  <td>San Francisco</td>
                  <td>22</td>
                  <td>2008/10/26</td>
                  <td>$235,500</td>
                </tr>
                <tr>
                  <td>Martena Mccray</td>
                  <td>Post-Sales support</td>
                  <td>Edinburgh</td>
                  <td>46</td>
                  <td>2011/03/09</td>
                  <td>$324,050</td>
                </tr>
                <tr>
                  <td>Unity Butler</td>
                  <td>Marketing Designer</td>
                  <td>San Francisco</td>
                  <td>47</td>
                  <td>2009/12/09</td>
                  <td>$85,675</td>
                </tr>
                <tr>
                  <td>Howard Hatfield</td>
                  <td>Office Manager</td>
                  <td>San Francisco</td>
                  <td>51</td>
                  <td>2008/12/16</td>
                  <td>$164,500</td>
                </tr>
                <tr>
                  <td>Hope Fuentes</td>
                  <td>Secretary</td>
                  <td>San Francisco</td>
                  <td>41</td>
                  <td>2010/02/12</td>
                  <td>$109,850</td>
                </tr>
                <tr>
                  <td>Vivian Harrell</td>
                  <td>Financial Controller</td>
                  <td>San Francisco</td>
                  <td>62</td>
                  <td>2009/02/14</td>
                  <td>$452,500</td>
                </tr>
                <tr>
                  <td>Timothy Mooney</td>
                  <td>Office Manager</td>
                  <td>London</td>
                  <td>37</td>
                  <td>2008/12/11</td>
                  <td>$136,200</td>
                </tr>
                <tr>
                  <td>Jackson Bradshaw</td>
                  <td>Director</td>
                  <td>New York</td>
                  <td>65</td>
                  <td>2008/09/26</td>
                  <td>$645,750</td>
                </tr>
                <tr>
                  <td>Olivia Liang</td>
                  <td>Support Engineer</td>
                  <td>Singapore</td>
                  <td>64</td>
                  <td>2011/02/03</td>
                  <td>$234,500</td>
                </tr>
                <tr>
                  <td>Bruno Nash</td>
                  <td>Software Engineer</td>
                  <td>London</td>
                  <td>38</td>
                  <td>2011/05/03</td>
                  <td>$163,500</td>
                </tr>
                <tr>
                  <td>Sakura Yamamoto</td>
                  <td>Support Engineer</td>
                  <td>Tokyo</td>
                  <td>37</td>
                  <td>2009/08/19</td>
                  <td>$139,575</td>
                </tr>
                <tr>
                  <td>Thor Walton</td>
                  <td>Developer</td>
                  <td>New York</td>
                  <td>61</td>
                  <td>2013/08/11</td>
                  <td>$98,540</td>
                </tr>
                <tr>
                  <td>Finn Camacho</td>
                  <td>Support Engineer</td>
                  <td>San Francisco</td>
                  <td>47</td>
                  <td>2009/07/07</td>
                  <td>$87,500</td>
                </tr>
                <tr>
                  <td>Serge Baldwin</td>
                  <td>Data Coordinator</td>
                  <td>Singapore</td>
                  <td>64</td>
                  <td>2012/04/09</td>
                  <td>$138,575</td>
                </tr>
                <tr>
                  <td>Zenaida Frank</td>
                  <td>Software Engineer</td>
                  <td>New York</td>
                  <td>63</td>
                  <td>2010/01/04</td>
                  <td>$125,250</td>
                </tr>
                <tr>
                  <td>Zorita Serrano</td>
                  <td>Software Engineer</td>
                  <td>San Francisco</td>
                  <td>56</td>
                  <td>2012/06/01</td>
                  <td>$115,000</td>
                </tr>
                <tr>
                  <td>Jennifer Acosta</td>
                  <td>Junior Javascript Developer</td>
                  <td>Edinburgh</td>
                  <td>43</td>
                  <td>2013/02/01</td>
                  <td>$75,650</td>
                </tr>
                <tr>
                  <td>Cara Stevens</td>
                  <td>Sales Assistant</td>
                  <td>New York</td>
                  <td>46</td>
                  <td>2011/12/06</td>
                  <td>$145,600</td>
                </tr>
                <tr>
                  <td>Hermione Butler</td>
                  <td>Regional Director</td>
                  <td>London</td>
                  <td>47</td>
                  <td>2011/03/21</td>
                  <td>$356,250</td>
                </tr>
                <tr>
                  <td>Lael Greer</td>
                  <td>Systems Administrator</td>
                  <td>London</td>
                  <td>21</td>
                  <td>2009/02/27</td>
                  <td>$103,500</td>
                </tr>
                <tr>
                  <td>Jonas Alexander</td>
                  <td>Developer</td>
                  <td>San Francisco</td>
                  <td>30</td>
                  <td>2010/07/14</td>
                  <td>$86,500</td>
                </tr>
                <tr>
                  <td>Shad Decker</td>
                  <td>Regional Director</td>
                  <td>Edinburgh</td>
                  <td>51</td>
                  <td>2008/11/13</td>
                  <td>$183,000</td>
                </tr>
                <tr>
                  <td>Michael Bruce</td>
                  <td>Javascript Developer</td>
                  <td>Singapore</td>
                  <td>29</td>
                  <td>2011/06/27</td>
                  <td>$183,000</td>
                </tr>
                <tr>
                  <td>Donna Snider</td>
                  <td>Customer Support</td>
                  <td>New York</td>
                  <td>27</td>
                  <td>2011/01/25</td>
                  <td>$112,000</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
      </div> -->
    </div>
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Jana Analysis</small>
        </div>
      </div>
    </footer>
    
    
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.min.js"></script>
     <!--  <script src="js/canvasjs.min.js"></script>
       <script src="js/jquery-1.11.1.js" type="text/javascript"></script> -->
       <script src="js/jquery.canvasjs.min.js"></script>
       <script src="js/jquery-1.11.1.js"></script>
 
</body>

	 

</html>
