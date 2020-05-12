<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="/ProjectCreate.do?method=create">
	<html:hidden property="op" name="projectForm" value="C"/>
	
	<div class="form-group">
		<label for="inputProjectTitle"><bean:message key="label.title"/></label> 
		<html:text styleClass="form-control" property="title" size="25" />
	</div>

	<div class="form-group">
		<label for="inputProjectDescription"><bean:message key="label.description"/></label>
		<html:textarea property="description" cols="5" rows="10" styleClass="form-control"></html:textarea>
	</div>
	
	<html:submit styleClass="btn btn-primary">
		<bean:message key="label.button.submit"/>
	</html:submit>
	
	<html:reset styleClass="btn btn-danger">
		<bean:message key="label.button.cancel"/>
	</html:reset>
</html:form>
