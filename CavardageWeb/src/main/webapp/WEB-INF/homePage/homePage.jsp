<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 26/10/17
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>

    <jsp:include page="js/ajouterEtape.js"></jsp:include>

    <b:kickstart title="Cavardage">

    </b:kickstart>

    ${testLogin}

    <div class="container">
        <div class="col-lg-3">
            <form action="ControleurUtilisateur" method="post">
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="trajetsEnCours">Trajets en cours</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="creerTrajet">Création d'un trajet</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="voirVehicules">Véhicules</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="voirHistorique">Historique</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="voirAppreciations">Appréciations</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="parametres">Paramètres</button>
                </div>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="rechercherTrajet">Rechercher un trajet</button>
                </div>
                <hr>
                <div class="form-group">
                    <button class="btn btn-default" type="submit" name="afaire" value="deconnexion">Déconnexion</button>
                </div>
            </form>
        </div>
        <div class="col-lg-9">
            <c:if test="${aAfficher == 'trajetsEnCours'}">
                <jsp:include page="trajetsEnCours.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'creerTrajet'}">
                <jsp:include page="creationTrajet.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'vehicules'}">
                <jsp:include page="vehicules.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'historique'}">
                <jsp:include page="historique.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'appreciations'}">
                <jsp:include page="appreciation/appreciations.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'parametres'}">
                <jsp:include page="parametres.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'suppressionCompte'}">
                <jsp:include page="confirmationSuppressionCompte.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'rechercherTrajet'}">
                <jsp:include page="recherche/initFormrecherche.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'detailsTrajet'}">
                <jsp:include page="detailsTrajet.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'gestionTrajet'}">
                <jsp:include page="gestionTrajet.jsp"></jsp:include>
            </c:if>
            <c:if test="${aAfficher == 'detailsAppreciation'}">
                <jsp:include page='appreciation/detailsAppreciation.jsp'></jsp:include>
            </c:if>
        </div>
    </div>
</body>
</html>
