<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<spring:theme code="styleSheet" var="app_css"/>
	<spring:url value="/${app_css}" var="standard_css_url"/>
	<link rel="stylesheet" type="text/css" media="screen" href="${standard_css_url}" />
</head>
<body>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3>
			User : ${pageContext.request.userPrincipal.name} |
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<c:url value="/admin" var="adminUrl" />
				<a href="${adminUrl}">admin page</a> | 
			</sec:authorize>
			<a href="${logoutUrl}">Logout</a>
		</h3>
	</c:if>
	
    <h3>List of providers</h3>
    
    <sec:authorize access="isAuthenticated()">
	    <c:if test="${not empty providers}">
	    	<table id="list-box">
	    		<thead id="head">
	    			<tr>
	    				<th>ID</th>
	    				<th>Name</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${providers}" var="provider">
	    				<tr align="center">
	    					<td>${provider.id}</td>
	    					<td>${provider.name}</td>
	    				</tr>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	    </c:if>
	</sec:authorize>
</body>
</html>