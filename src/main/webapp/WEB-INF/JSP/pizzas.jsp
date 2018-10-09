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
<c:if test='${not empty param.boodschap}'>
    <div class='boodschap'>${param.boodschap}</div>
</c:if>
<h1>Pizza's</h1>
<ul class='zebra'>
    <c:forEach var='pizza' items='${pizzas}'>
        <li>
                ${pizza.id}: <c:out value='${pizza.naam}'/> ${pizza.prijs}&euro;
            <c:choose>
                <c:when test='${pizza.pikant}'>pikant</c:when>
                <c:otherwise>niet pikant</c:otherwise>
            </c:choose>
            <spring:url value='/pizzas/{id}' var='url'>
                <spring:param name='id' value='${pizza.id}'/>
            </spring:url>
            <a href='${url}'>Detail</a>
        </li>
    </c:forEach>
    <%--<c:forEach var='entry' items='${pizzas}'>--%>
        <%--<li>${entry.key}: ${entry.value.naam} ${entry.value.prijs}&euro;--%>
            <%--&lt;%&ndash;<c:if test='${entry.value.pikant}'>&ndash;%&gt;--%>
                <%--&lt;%&ndash;pikant&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>

            <%--<c:choose>--%>
                <%--<c:when test='${entry.value.pikant}'>--%>
                    <%--pikant--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--niet pikant--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>

            <%--&lt;%&ndash;URL MET QUERY PARAMETER&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:url value='/pizzas' var='url'>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:param name='id' value='${entry.key}'/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:url>&ndash;%&gt;--%>

            <%--&lt;%&ndash;URL MET PATH VARIABELE&ndash;%&gt;--%>
            <%--<spring:url value='/pizzas/{id}' var='url'>--%>
                <%--<spring:param name='id' value='${entry.key}'/>--%>
            <%--</spring:url>--%>
            <%--<a href='${url}'>Detail</a>--%>
        <%--</li>--%>
    <%--</c:forEach>--%>
</ul>
</body>
</html>
