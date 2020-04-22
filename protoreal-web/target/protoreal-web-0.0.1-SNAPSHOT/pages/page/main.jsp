<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<h5>
	<bean:write name="site" property="title" />
	<br />
</h5>
<hr />
<a href="Pages.do?method=startEdit" class="btn btn-primary"><bean:message
		key="label.new" /></a>
<p>
<div class="list-group">

	<logic:iterate name="pages" id="pg">

		<li
			class="list-group-item d-flex justify-content-between align-items-center">

			<h6 class="card-title">
				<bean:write name="pg" property="title" />
			</h6> 
			
			<span class="badge badge-pill">
			
			<span class="badge badge-primary badge-pill"><a href="#"
				class="btn-sm btn-primary"><bean:message key="label.edit" /></a> </span> <span
			class="badge badge-danger badge-pill"><a
				href='Pages.do?method=remove&id=<bean:write name="pg" property="uuid"/>'
				class="btn-sm btn-danger"><bean:message key="label.remove" /></a></span> <span
			class="badge badge-light badge-pill"><a
				href='Pages.do?method=startDesign&id=<bean:write name="pg" property="uuid"/>'
				class="btn-sm btn-light"><bean:message key="label.design" /></a></span>
				
			</span>	

		</li>

	</logic:iterate>

</div>
