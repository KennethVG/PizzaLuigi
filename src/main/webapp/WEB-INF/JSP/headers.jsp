<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
<head>
    <c:import url='/WEB-INF/JSP/head.jsp'>
        <c:param name='title' value="Headers"/>
    </c:import>
</head>
<body>
<c:import url='/WEB-INF/JSP/menu.jsp'/>
Je browser wordt uitgevoerd op
${opWindows ? "Windows" : "een niet-Windows besturingssysteem"}.
</body>
</html>
