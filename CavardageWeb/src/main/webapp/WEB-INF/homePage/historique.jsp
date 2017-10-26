<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cavardage ~ Historique ~</title>
</head>
<body>

    <b:kickstart title="Cavardage">

    </b:kickstart>

    <form method="post" action="ControleurUtilisateur" class="form-group">
        <ul class="list-group">
            <c:forEach items="${listeHistorique}" var="trajet">
                <li>

                </li>
            </c:forEach>
        </ul>
    </form>
</body>
</html>
