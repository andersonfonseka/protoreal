<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<a href="Projects.do?method=startEdit" class="btn btn-primary"><bean:message
		key="label.new" /></a>
<p>
<div class="row">
	<logic:iterate name="projects" id="project">

		<div class="col-sm-3">
			<div class="card">
				<div class="card-header">
					<div class="dropdown">
						<span style="font-weight: bold;"><bean:write name="project" property="title" /></span>
						<button class="test btn btn-light float-md-right" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#"><bean:message key="label.edit" /></a> 
							<a class="dropdown-item" href='Projects.do?method=remove&id=<bean:write name="project" property="uuid"/>'><bean:message key="label.remove" /></a> 
							<a class="dropdown-item" href="#"><bean:message key="label.export" /></a>
						</div>
					</div>
				</div>

				<div class="card-body">
					<p class="card-text">
						<bean:write name="project" property="description" />
					</p>
							
					<a	href='Projects.do?method=preview&siteId=<bean:write name="project" property="uuid"/>'
						class="btn-sm btn-dark"><bean:message key="label.preview" /></a>
					
					<a
						href='Pages.do?method=main&siteId=<bean:write name="project" property="uuid"/>'
						class="btn-sm btn-info"><bean:message key="label.pages" /></a>
				</div>
			</div>
		</div>

		<br />
	</logic:iterate>
</div>