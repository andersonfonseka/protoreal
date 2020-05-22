<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<div id="divMainContainer" class="style-3" style="float:left;height: 85%; width: 95%;position:fixed;padding:5px;overflow-y:auto;display: block;box-shadow: 1px 1px 1px #888888;border: 1px solid rgba(0,0,0,.125);">

<div id="container-designer" style="position:relative;min-height:99.4%;height:auto;width:100%;background-color:white;display:inline-block;padding:2px;"
	ondrop="drop(event)" ondragover="allowDrop(event)">
	<%= request.getAttribute("pageRendered").toString() %>
</div>

</div>


