<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<html>
<head>
    <c:import url='/WEB-INF/JSP/head.jsp'>
        <c:param name='title' value='Pizza Luigi'/>
    </c:import>
</head>
<body>
<c:import url='/WEB-INF/JSP/menu.jsp'/>
<h1>Pizza Luigi</h1>
<img src='<c:url value="/images/pizza.jpg"/>' alt='pizza' class='fullwidth'>
<h2>${boodschap}</h2>
<h2>De zaakvoerder</h2>
<dl>
    <dt>Naam</dt>
    <dd>${zaakvoerder.naam}</dd>
    <dt>Aantal kinderen</dt>
    <dd>${zaakvoerder.aantalKinderen}</dd>
    <dt>Gehuwd</dt>
    <dd>${zaakvoerder.gehuwd ? 'Ja' : 'Nee'}</dd>
    <dt>Adres</dt>
    <dd>${zaakvoerder.adres.straat} ${zaakvoerder.adres.huisNr}<br>
        ${zaakvoerder.adres.postcode} ${zaakvoerder.adres.gemeente}</dd>
</dl>
<c:if test='${not empty laatstBezocht}'>
    <p>Je bezocht onze website laatst op <spring:eval expression='laatstBezocht.waarde'/>.</p>
</c:if>
<p>Deze pagina werd ${aantalKeerBekeken} keer bekeken.</p>
<p>${identificatie.emailAdres}</p>
</body>
</html>
