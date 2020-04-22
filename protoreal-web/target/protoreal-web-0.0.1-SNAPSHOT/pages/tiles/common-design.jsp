<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Protoreal</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script src="vendor/jquery/jquery.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="js/loader.js"></script>

<script src='dwr/engine.js'></script>
<script src='dwr/util.js'></script>
<script src='dwr/interface/Controller.js'></script>

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">
				<html:link forward="home">
					<img src="icons/icons8-prototype-32.png">
				</html:link>
			</div>

			<div class="list-group list-group-flush">

				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="container" src="icons/icons8-health-data-32.png"
					draggable="true" ondragstart="drag(event)"></a> 
					
				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="textInput" src="icons/icons8-text-input-form-32.png"
					draggable="true" ondragstart="drag(event)"></a> 

				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="textArea" src="icons/icons8-align-text-left-32.png"
					draggable="true" ondragstart="drag(event)"></a> 				
					
				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="selectItem" src="icons/icons8-checklist-48.png"
					draggable="true" ondragstart="drag(event)" style="height:32px; width: 32px;"></a> 
					
				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="button" src="icons/icons8-button-32.png"
					draggable="true" ondragstart="drag(event)"></a> 					
					
				<a href="#"
					class="list-group-item list-group-item-action"><img
					id="dataTable" src="icons/icons8-data-grid-32.png" draggable="true"
					ondragstart="drag(event)"></a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<div id="container" style="height: 90%; margin: 10px 10px 10px 15px;">
				<tiles:useAttribute name="title" />
				<tiles:insert attribute="header">
					<h2>
						<bean:message key="<%=(String) title%>" />
					</h2>
				</tiles:insert>
				<h5>
					<bean:write name="page" property="parent.title" /> >> 
					<bean:write name="page" property="title" />
					<br />
				</h5>
				<hr />

				<tiles:insert attribute="body" />
			</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript -->
	<script src="js/protoreal.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
