<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%= request.getAttribute("sitePreview") %>

<div id="divMainContainer" class="style-3" style="float:left;height: 93%; width: 100%;position:fixed;padding:5px;overflow-y:auto;display: block;">
<%= request.getAttribute("pageRendered")%>
</div>