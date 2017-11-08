<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 03/10/17
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<jsp:include page="homePage/js/date.js"></jsp:include>
<html>
<head>
    <title>Cavardage ~ Accueil ~</title>
</head>
<body>

<b:kickstart title="Cavardage">

</b:kickstart>

<!-- <h1 style="text-align: center">Cavardage</h1> -->

    <div class="col-lg-12" style="margin-bottom: 50px">
        <div class="col-lg-6">
            <img src="img/logo_xs.png" id="logoHeader"  height="100">
        </div>
<hr>

<div class="container">

    <form class="form-inline" method="post" action="ControleurGeneral" >
        <div class="form-group">
            <button class="btn btn-default" type="submit" name="afaire" value="connexion">Connexion</button>
        </div>
    </form>

        <div class="col-lg-5 col-lg-offset-1" style="margin-top: 15px">
            <form class="form-inline pull-right" method="post" action="ControleurAnonyme" >
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" name="afaire" value="inscription">Inscription</button>
                </div>
            </form>
        </div>

    </div>

<div class="container">

    <form action="ControleurAnonyme" method="post">
        <jsp:include page="homePage/recherche/rechercheTrajet.jsp"></jsp:include>
    </form>
    <c:if test="${!empty resultatsRecherche}">
    <ul class="list-group">
        <jsp:include page="homePage/recherche/resultatsRecherche.jsp"></jsp:include>
    </ul>
    </c:if>

    <hr>

    <h2>10 derniers ajouts:</h2>
    <div>
        <ul class="list-group">
        <form action="ControleurAnonyme" method="post">
            <li class="list-group-item">
                <table class="table table-bordered">
                    <tr>
                        <th class="col-lg-3">Ville de départ</th>
                        <th class="col-lg-3">Ville d'arrivée</th>
                        <th class="col-lg-3">Date</th>
                        <th class="col-lg-3">Heure</th>
                    </tr>
                <c:forEach items="${listeDernierTrajet}" var="trajet">
                            <tr>
                                <td class="col-lg-3">${trajet.villeDepart}(${trajet.departementDepart})</td>
                                <td class="col-lg-3">${trajet.villeArrivee}(${trajet.departementArrivee})</td>
                                <td class="col-lg-3">${trajet.date}</td>
                                <td class="col-lg-3">${trajet.heure}</td>
                            </tr>
                </c:forEach>
                </table>
            </li>
        </form>
        </ul>
    </div>

</div>

    </div>


</body>
</html>

