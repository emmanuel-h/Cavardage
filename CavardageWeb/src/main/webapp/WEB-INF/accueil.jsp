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
    <link href="css/general.css" rel="stylesheet">
</head>
<body>

<b:kickstart title="Cavardage">

</b:kickstart>

<div class="row">

    <div class="col-lg-6">
        <img src="img/logo_xs.png" id="logoHeader"  height="100" style="margin-bottom: 1px">
    </div>

    <form class="col-lg-offset-2 col-lg-1" method="post" action="ControleurGeneral">
        <div class="form-group">
            <button class="btn btn-default pull-right" type="submit" name="afaire" value="connexion">Connexion</button>
        </div>
    </form>

    <form class="col-lg-1" method="post" action="ControleurAnonyme">
        <div class="form-group">
            <button class="btn btn-primary" type="submit" name="afaire" value="inscription">Inscription</button>
        </div>
    </form>

</div>

<!--
<div class="container col-lg-6">
    <img src="img/logo_xs.png" id="logoHeader"  height="100" style="margin-bottom: 1px">
</div>

<div class="container col-lg-4 col-lg-offset-2" style="margin-bottom: 60px" id="connexionInscription">

    <form class="form-inline col-lg-2" method="post" action="ControleurGeneral" >
        <div class="form-group">
            <button class="btn btn-default" type="submit" name="afaire" value="connexion">Connexion</button>
        </div>
    </form>

            <form class="form-inline col-lg-2" method="post" action="ControleurAnonyme" >
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" name="afaire" value="inscription">Inscription</button>
                </div>
            </form>

</div>

-->

<div class="clearfix"></div>

<div class="container divCenterText">

    <c:if test="${not empty message}">
        <div class="alert alert-warning" style="margin-top: 10px">
            ${message}
        </div>
    </c:if>

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

</body>
</html>

