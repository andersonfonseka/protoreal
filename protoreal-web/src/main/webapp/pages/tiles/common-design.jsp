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

					<a href="#" class="list-group-item list-group-item-action"><img
					id="container" src="icons/icons8-health-data-32.png"
					draggable="true" ondragstart="drag(event)"></a> 
					
					<a href="#"
					class="list-group-item list-group-item-action"><img
					id="label" src="icons/icons8-tags-32.png"
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
					draggable="true" ondragstart="drag(event)"
					style="height: 32px; width: 32px;"></a> 
					
					<a href="#"
					class="list-group-item list-group-item-action"><img id="button"
					src="icons/icons8-button-32.png" draggable="true"
					ondragstart="drag(event)"></a> 
					
					<a href="#"
					class="list-group-item list-group-item-action"><img
					id="dataTable" src="icons/icons8-data-grid-32.png" draggable="true"
					ondragstart="drag(event)"></a>
					
					
					<a href="#"
					class="list-group-item list-group-item-action"><img
					id="jumbotron" src="icons/icons8-billboard-30.png" draggable="true"
					ondragstart="drag(event)"></a>
					
					<a href="#"
					class="list-group-item list-group-item-action"><img
					id="cards" src="icons/icons8-credit-card-32.png" draggable="true"
					ondragstart="drag(event)"></a>
					
					<!-- 
					<a href="#"
					class="list-group-item list-group-item-action"><img
					id="carousel" src="icons/icons8-merry-go-round-32.png" draggable="true"
					ondragstart="drag(event)"></a>
					-->
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<div id="container" style="height: 90%; margin: 10px 10px 10px 15px;">
				<tiles:useAttribute name="title" />
				<tiles:insert attribute="header">
					<h3>
						<bean:message key="<%=(String) title%>" />
					</h3>
				</tiles:insert>

				<html:form>

					<div class="row">
						<div class="col-sm-9">
							<bean:write name="page" property="parentComponent.title" />
							>>
							<bean:write name="page" property="title" />
						</div>

						<div class="col-sm">
							<div class="form-inline">

								<div id="componentSelected" class="form-group mx-sm-1 mb-1">
									<html:select property="componentSelected"
										styleClass="form-control-sm" style="width:200px;"
										onchange="configure();">
										<html:option value="0">
											<bean:message key="label.select" />
										</html:option>
										<html:optionsCollection name="designForm"
											property="componentList" label="name" value="uuid" />
									</html:select>
								</div>

								<button type="button" class="btn-sm btn-primary mx-sm-1 mb-1"
									data-toggle="modal" data-target="#ExemploModalCentralizado">
									<bean:message key="label.button.edit" />
								</button>

								<button type="button" class="btn-sm btn-danger mx-sm-1 mb-1" onclick="remove();">
									<bean:message key="label.button.remove"/>
								</button>
							</div>
						</div>
					</div>
				</html:form>

				<div class="modal fade" id="ExemploModalCentralizado" tabindex="-1"
					role="dialog" aria-labelledby="TituloModalCentralizado"
					aria-hidden="true">
					<div class="modal-dialog shadow" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="TituloModalCentralizado">Propriedades</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Fechar">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div id="properties"></div>
							</div>
						</div>
					</div>
				</div>
				<p>
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
