<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SITE Login APP</title>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="dist/css/formValidation.min.css" />
<link rel="stylesheet" href="css/custom.css" />
<link rel="stylesheet" href="css/validationEngine.jquery.css" />
<link rel="stylesheet" href="css/jquery.fileupload.css" />
<!-- <link rel="stylesheet" href="css/jquery.fileupload-noscript.css" /> -->

<script type="text/javascript" src="vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="dist/js/formValidation.min.js"></script>
<script type="text/javascript" src="dist/js/framework/bootstrap.js"></script>
<script src="js/json2.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="js/jquery.validationEngine-en.js" type="text/javascript"></script>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js" type="text/javascript"></script>
<script src="js/jquery.filestyle.js" type="text/javascript"></script>


<script type="text/javascript" src="js/pagesjs/AccountRegister.js"></script>
<script type="text/javascript" src="js/to/AccountRegisterTO.js"></script>
<script type="text/javascript" src="js/pagesjs/RegisterService.js"></script>
<script type="text/javascript" src="js/to/RegisterServiceTO.js"></script>
<script type="text/javascript" src="js/pagesjs/Login.js"></script>
<script type="text/javascript" src="js/to/UserLoginTO.js"></script>
<script type="text/javascript" src="js/util/Constants.js"></script>
<script type="text/javascript" src="js/util/Utility.js"></script>
<script>
	var APP_CONTEXT = "${pageContext.request.contextPath}/";
</script>
<script type="text/javascript" src="js/util/HttpAjaxServices.js"></script>
<script type="text/javascript" src="js/to/Model.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
  
  		$(function(){
  			loadHeaderPage();
  			setInteroperabilityServicesPage();
	  		$('ul.navbar-nav li').click(function(e) {
	  			var id = $(this).attr('id');
	  			if((id != 'signupID' && id != 'loginID' && id != 'logoutLI'))
	  			{
	  				$('.navbar-nav li.active').removeClass('active');
	  		        $(this).addClass('active');	
	  			}
  		        e.preventDefault();
  		    });
  			
  			$('#contactUs').click(function(){
  			    $(location).attr('href', 'mailto:admin@sitenv.org');
  			});
  		});
  		
  		function setRegisterServicePage(){
  			
  			if(MODEL.userLoggedIn == 0)
  			{
  				$("#rightNavbarID").show();
  			}
			$.get('pages/RegisterService.html',setRegisterServicePageContent);
  		}
  		
  		function setRegisterServicePageContent(data){
  			$("#vendorRegLIId").addClass('active');
			$("#mainPageContent").html(data);
  		}
  		
  		function setInteroperabilityServicesPage(){
  				
  			if(MODEL.userLoggedIn == 0)
  			{
  				$("#rightNavbarID").hide();
  			}
  		
			$.get('pages/InteroperabilityServices.html',setInteroperabilityServicesPageContent);
  		}
  		
  		function setInteroperabilityServicesPageContent(data){
  			$("#interopLIId").addClass('active');
			$("#mainPageContent").html(data);
  		}
  		
  		function openLoginPage(){
  			$.get('pages/LoginPage.html',showLoginPage);
  		}
  		function showLoginPage(data){
  			$("#loginModel").html(data)
  			$("#loginModel").modal('show');
  		}
  		
  		function openSignUpPage(){
  			$.get('pages/AccountRegister.html',showLoginPage);
  		}
  		
  		function showSignUpPage(data){
  			$("#signupModel").html(data)
  			$("#signupModel").modal('show');
  		}
  		
  		function loadHeaderPage()
  		{
  			$.get('pages/Header.html',loadHeaderPageContent);
  		}
  		
  		function loadHeaderPageContent(data)
  		{
  			$("#headerId").html(data)
  		}
  		
  		function onlogout(){
  			$("#logoutId").hide();
  			$("#rightNavbarID").show();
  			MODEL.userLoggedIn = 0;
  			MODEL.userEmail = "";
  			setRegisterServicePage();
  		}
 
	</script>
</head>

<body>
	<header id="headerId">
	</header>
	<nav class="navbar navbar-default navbar-static-top"
		style="margin-bottom: 0px;">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar" aria-expanded="true" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="navbar-collapse collapse in"
				aria-expanded="true">
				<ul class="nav navbar-nav">
				 <li  id="interopLIId"><a href="#" onclick="setInteroperabilityServicesPage()" style="text-decoration: none;">Direct
							Services</a></li>
				<li id="vendorRegLIId"><a href="#" style="text-decoration: none;"
						onclick="setRegisterServicePage()">Vendor Registration</a></li>
					<li onclick="javascript:location.href='http://54.200.44.56/'">
						<a href="#" style="text-decoration: none;">Home</a></li>
					
					
				</ul>
				
				<ul class="nav navbar-nav navbar-right" id="rightNavbarID">
					<li id="signupID"><a href="#" onclick="openSignUpPage()" style="text-decoration: none;">Sign Up </a></li>
					<li id="loginID"><a href="#" onclick="openLoginPage()" style="text-decoration: none;">Login</a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right" id="logoutId" hidden="true">
					<li id="logoutLI"><a href="#" onclick="onlogout()" style="text-decoration: none;">Logout</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div id="mainPageContent"></div>
	<footer class="panel-footer" style="padding-bottom: 0px;">
		<div class="container" role="contentinfo">
			<div class="row">
				<div>
					<p>
						This project was funded by a contract from the <a
							href="http://www.healthit.gov" >Office of the
							National Coordinator for Health Information Technology (ONC)</a>
					</p>
					<p>
						<a href="http://www.hhs.gov/Privacy.html" >Privacy
							Policy</a> | <a href="http://www.hhs.gov/Disclaimer.html"
							>Disclaimer</a> | <a href="#" id="contactUs"
							>Contact US </a>
					</p>
				</div>
			</div>
		</div>
	</footer>

	<div id="loginModel" class="modal fade"></div>
	
	<div id="signupModel" class="modal fade"></div>
</body>
</html>
