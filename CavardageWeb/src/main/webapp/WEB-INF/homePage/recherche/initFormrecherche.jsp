<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 30/10/17
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="ControleurUtilisateur" method="post">

<jsp:include page="rechercheTrajet.jsp"/>
</form>

<form action="ControleurUtilisateur" method="post">
    <c:if test="${resultatsRecherche == 'afficher'}" >
        <jsp:include page="resultatsRecherche.jsp"></jsp:include>
    </c:if>
</form>
