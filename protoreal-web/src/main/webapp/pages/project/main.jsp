<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<a href="Projects.do?method=startEdit" class="btn btn-primary" ><bean:message key="label.new"/></a><p>

<div class="row">
<logic:iterate name="projects" id="project">

<div class="col-sm-3">
<div class="card">
	<div class="card-body">
		<h3 class="card-title"><bean:write name="project" property="title"/></h3>
		<hr/>
		<p class="card-text"><bean:write name="project" property="description"/></p>
		<a href="#" class="btn-sm btn-primary"><bean:message key="label.edit"/></a>
		<a href='Projects.do?method=remove&id=<bean:write name="project" property="uuid"/>' class="btn-sm btn-danger"><bean:message key="label.remove"/></a>
		<a href="#" class="btn-sm btn-light"><bean:message key="label.export"/></a>
		<a href='Projects.do?method=preview&id=<bean:write name="project" property="uuid"/>' class="btn-sm btn-dark"><bean:message key="label.preview"/></a>
		<a href='Pages.do?method=main&siteId=<bean:write name="project" property="uuid"/>' class="btn-sm btn-info"><bean:message key="label.pages"/></a>
	</div>
</div>
</div>

<br/>
</logic:iterate>
</div>