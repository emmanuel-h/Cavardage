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
    <meta charset="UTF-8">
    <title>Cavardage ~ Admin</title>
</head>
<body>

    <b:kickstart title="Cavardage">

    </b:kickstart>

    <div class="container">
        <div class="col-lg-3">
            <form method="post" action="ControleurAdmin">
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="boutonAdmin" value="gererVille">Gérer les villes</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="boutonAdmin" value="gererGabarit">Gérer les gabarits</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="boutonAdmin" value="statistiques">Statistiques</button>
                </div>
                <hr>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="boutonAdmin" value="deconnexion">Déconnexion</button>
                </div>
            </form>
        </div>

        <div class="col-lg-9">
            <c:if test="${aAfficher == 'gestionVille'}">
                <jsp:include page="gestionVille.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'gestionGabarit'}">
                <jsp:include page="gestionGabarit.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'statistiques'}">
                <jsp:include page="statistiques.jsp"></jsp:include>
            </c:if>
        </div>
    </div>

</body>
</html>
