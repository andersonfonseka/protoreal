<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<div id="container-designer"
	style="border: solid #D3D3D3 1px; height: 95%; margin: 5px 5px 5px 0px; z-index: -1"
	ondrop="drop(event)" ondragover="allowDrop(event)">
	<%= request.getAttribute("pageRendered").toString() %>
</div>