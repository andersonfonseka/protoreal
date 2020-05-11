<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<h5>
	<bean:write name="site" property="title" />
	<br />
</h5>
<hr />
<a href="Pages.do?method=startCreate" class="btn btn-primary"><bean:message
		key="label.new" /></a>
<p>
<div class="list-group">

	<logic:iterate name="pages" id="pg">

		<li
			class="list-group-item d-flex">


			
			<div class="dropdown">
				<button class="test btn btn-light float-md-right" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<bean:write name="pg" property="title" />
				</button>

				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<logic:notEqual name="pg" property="pageChildren" value="false">	
					<a class="dropdown-item" href='PageDesign.do?method=startDesign&id=<bean:write name="pg" property="uuid"/>'><bean:message key="label.design" /></a> 
					</logic:notEqual>
					
					<a class="dropdown-item" href='Pages.do?method=startEdit&id=<bean:write name="pg" property="uuid"/>'><bean:message key="label.edit" /></a> 
					<a class="dropdown-item" href='Pages.do?method=remove&id=<bean:write name="pg" property="uuid"/>'><bean:message key="label.remove" /></a> 
					<!--  <a class="dropdown-item" href="#"><bean:message key="label.export" /></a> -->
				</div>
			</div>

		</li>

	</logic:iterate>

</div>
