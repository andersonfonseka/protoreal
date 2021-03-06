<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:form action="/PageEdit.do?method=create">
	<html:hidden property="op" name="pageForm" value="U"/>
	<html:hidden property="uuid" name="pageForm"/>
	<html:hidden property="checkDisplayMenu" name="pageForm"/>
	<html:hidden property="checkHideMenu" name="pageForm"/>
	<html:hidden property="checkShowTitle" name="pageForm"/>
	
	<div class="form-group">
	<label for="inputProjectName"><bean:message key="label.type"/></label> 
	<html:select property="pageType" styleClass="form-control">
        <html:optionsCollection name="pageForm" property="typeList" label="label" value="value" />
    </html:select>
    </div>
    
    <div class="form-group">
	<label for="inputProjectName"><bean:message key="label.containerType"/></label> 
	<html:select property="containerType" styleClass="form-control">
        <html:optionsCollection name="pageForm" property="containerTypeList" label="label" value="value" />
    </html:select>
    </div>
	
	<div class="form-group">
	<label for="inputProjectName"><bean:message key="label.pages.parent"/></label> 
	<html:select property="parentPage" styleClass="form-control">
        <html:option value=""><bean:message key="label.select"/></html:option>
        <html:optionsCollection name="pageForm" property="pageList" label="title" value="uuid" />
    </html:select>
    </div>

	<div class="form-group">
		<label for="inputProjectTitle"><bean:message key="label.title"/></label> 
		<html:text styleClass="form-control" property="title" size="25" />
	</div>

	<div class="form-group" style="margin-left: 20px;">
		<html:checkbox styleClass="form-check-input" property="showTitle" onchange="changeCheckbox('checkShowTitle')"/>
		<label for="inputProjectName"><bean:message key="label.pages.showTitle"/></label> 
	</div>

	<div class="form-group" style="margin-left: 20px;">
		<html:checkbox styleClass="form-check-input" property="displayOnMenu" onchange="changeCheckbox('checkDisplayMenu')"/>
		<label for="inputProjectName"><bean:message key="label.pages.displayOnMenu"/></label> 
	</div>
	
	<div class="form-group" style="margin-left: 20px;">
		<html:checkbox styleClass="form-check-input" property="hideMenu" onchange="changeCheckbox('checkHideMenu')"/>
		<label for="inputProjectName"><bean:message key="label.pages.hideMenu"/></label> 
	</div>
	
	<div class="form-group">
		<label for="inputProjectDescription"><bean:message key="label.description"/></label>
		<html:textarea property="description" cols="5" rows="10" styleClass="form-control"></html:textarea>
	</div>
	
	<html:submit styleClass="btn btn-primary">
		<bean:message key="label.button.submit"/>
	</html:submit>
	
	<html:cancel styleClass="btn btn-danger" onclick="cancelarOp('X');">
		<bean:message key="label.button.cancel"/>
	</html:cancel>
</html:form>
