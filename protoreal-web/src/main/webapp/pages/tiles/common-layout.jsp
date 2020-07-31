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
<!-- <link href="vendor/bootstrap-md/css/bootstrap-material-design.css" rel="stylesheet">-->


<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

<!-- Bootstrap core JavaScript -->
<script src="js/protoreal.js"></script>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

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

				<logic:messagesPresent>
					<div class="modal" id="meuModal"  tabindex="-1" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title"><bean:message key="label.messages"/></h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Fechar">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<html:errors />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										data-dismiss="modal"><bean:message key="label.button.close"/></button>
								</div>
							</div>
						</div>
					</div>

					<script>
						$('#meuModal').modal('show');
					</script>
				</logic:messagesPresent>

				<tiles:insert attribute="body" />

			</div>
		</div>

	</div>
</body>

</html>
