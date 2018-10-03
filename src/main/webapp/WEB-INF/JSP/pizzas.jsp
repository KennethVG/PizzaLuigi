<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<html>
<head>
    <c:import url='/WEB-INF/JSP/head.jsp'>
        <c:param name='title' value="Pizza's"/>
    </c:import>
</head>
<body>
<c:import url='/WEB-INF/JSP/menu.jsp'/>
<h1>Pizza's</h1>
<ul class='zebra'>
    <c:forEach var='entry' items='${pizzas}'>
        <li>${entry.key}: ${entry.value.naam} ${entry.value.prijs}&euro;
            <%--<c:if test='${entry.value.pikant}'>--%>
                <%--pikant--%>
            <%--</c:if>--%>

            <c:choose>
                <c:when test='${entry.value.pikant}'>
                    pikant
                </c:when>
                <c:otherwise>
                    niet pikant
                </c:otherwise>
            </c:choose>

            <%--URL MET QUERY PARAMETER--%>
            <%--<c:url value='/pizzas' var='url'>--%>
                <%--<c:param name='id' value='${entry.key}'/>--%>
            <%--</c:url>--%>

            <%--URL MET PATH VARIABELE--%>
            <spring:url value='/pizzas/{id}' var='url'>
                <spring:param name='id' value='${entry.key}'/>
            </spring:url>
            <a href='${url}'>Detail</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
