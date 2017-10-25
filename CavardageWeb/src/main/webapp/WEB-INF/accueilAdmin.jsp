<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 25/10/17
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cavardage ~ Admin</title>
</head>
<body>

    <div class="container">
        <div class="col-lg-3">
            <form method="post" action="ControleurAdmin">
                <button class="btn btn-default" type="submit" name="boutonAdmin" value="gererVille">Gérer les villes</button>
                <button class="btn btn-default" type="submit" name="boutonAdmin" value="gererGabarit">Gérer les gabarits</button>
            </form>
        </div>

        <div class="col-lg-9">
            <c:if test="${gestionVille}">
                <jsp:include page="gestionVille.jsp"></jsp:include>
            </c:if>
            <c:if test="${gestionGabarit}">
                <jsp:include page="gestionGabarit.jsp"></jsp:include>
            </c:if>
        </div>
    </div>

</body>
</html>
