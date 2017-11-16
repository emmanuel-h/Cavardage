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
    <link href="css/general.css" rel="stylesheet">
</head>
<body>
    <b:kickstart title="Cavardage">

    </b:kickstart>

    <div class="col-lg-12" style="margin-bottom: 50px">
        <div class="col-lg-6">
            <img src="img/logo_xs.png" id="logoHeader"  height="100">
        </div>
    </div>

    <div class="container">

        <div class="col-lg-3 divSideText">
            <div style="margin-top: 15px">
                <form action="ControleurUtilisateur" method="post">
                    <div class="form-group">
                        <c:if test="${aAfficher == 'accueil'}">
                        <button class="btn btn-default" type="submit" name="afaire" value="accueil" style="width: 100%;background-color: #d9d9d9;">Accueil</button>
                        </c:if>
                        <c:if test="${aAfficher != 'accueil'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="accueil" style="width: 100%">Accueil</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'trajetsEnCours' or (aAfficher == 'detailsTrajet' and not empty reservation) or aAfficher == 'gestionTrajet'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="trajetsEnCours" style="width: 100%;background-color: #d9d9d9;">Trajets en cours</button>
                        </c:if>
                        <c:if test="${aAfficher != 'trajetsEnCours' and (aAfficher != 'detailsTrajet' or empty reservation) and aAfficher != 'gestionTrajet'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="trajetsEnCours" style="width: 100%">Trajets en cours</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'creerTrajetTemp' or aAfficher == 'creerTrajet'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="creerTrajet" style="width: 100%;background-color: #d9d9d9;">Création d'un trajet</button>
                        </c:if>
                        <c:if test="${aAfficher != 'creerTrajetTemp' and aAfficher != 'creerTrajet'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="creerTrajet" style="width: 100%">Création d'un trajet</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'vehicules'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirVehicules" style="width: 100%;background-color: #d9d9d9;">Véhicules</button>
                        </c:if>
                        <c:if test="${aAfficher != 'vehicules'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirVehicules" style="width: 100%">Véhicules</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'historique' or aAfficher == 'detailsHistorique'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirHistorique" style="width: 100%;background-color: #d9d9d9;">Historique</button>
                        </c:if>
                        <c:if test="${aAfficher != 'historique' and aAfficher != 'detailsHistorique'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirHistorique" style="width: 100%">Historique</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'appreciations' or aAfficher == 'detailsAppreciation'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirAppreciations" style="width: 100%;background-color: #d9d9d9;">Appreciations</button>
                        </c:if>
                        <c:if test="${aAfficher != 'appreciations' and aAfficher != 'detailsAppreciation'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="voirAppreciations" style="width: 100%">Appreciations</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'parametres' or aAfficher == 'suppressionCompte'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="parametres" style="width: 100%;background-color: #d9d9d9;">Paramètres</button>
                        </c:if>
                        <c:if test="${aAfficher != 'parametres' and aAfficher != 'suppressionCompte'}">
                            <button class="btn btn-default" type="submit" name="afaire" value="parametres" style="width: 100%">Paramètres</button>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <c:if test="${aAfficher == 'rechercherTrajet' or (aAfficher == 'detailsTrajet' and empty reservation) }">
                            <button class="btn btn-default" type="submit" name="afaire" value="rechercherTrajet" style="width: 100%;background-color: #d9d9d9;">Rechercher un trajet</button>
                        </c:if>
                        <c:if test="${aAfficher != 'rechercherTrajet' and (aAfficher != 'detailsTrajet' or not empty reservation)}">
                            <button class="btn btn-default" type="submit" name="afaire" value="rechercherTrajet" style="width: 100%">Rechercher un trajet</button>
                        </c:if>
                    </div>
                    <hr>
                    <div class="form-group">
                        <button class="btn btn-default" type="submit" name="afaire" value="deconnexion" style="width: 100%">Déconnexion</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-lg-9 divCenterText">
            <div style="margin-top: 15px">
                <c:if test="${aAfficher == 'accueil'}">
                    <form action="ControleurUtilisateur" method="post">
                        <button type="submit" class="btn btn-default col-lg-3 col-lg-offset-8" style="margin-bottom: 5px" value="supprimerNotifs" name="afaire">Supprimer les notifications</button>
                    </form>
                    <div class="col-lg-12">
                        <c:forEach items="${listeNotif}" var="notif">
                            <div class="alert alert-warning">
                                <strong>Info : &nbsp;</strong>${notif.message} <a href="
                            <c:url value="ControleurUtilisateur">
                                <c:param name="idNotif" value="${notif.notification}" />
                                <c:param name="afaire" value="supprimerNotif" />
                            </c:url>" style="color: #ffbb33; text-decoration: none" class="glyphicon glyphicon-remove"></a>
                            </div>
                        </c:forEach>
                        <c:if test="${empty listeNotif}">
                            <div class="alert alert-warning">
                                Vous n'avez pas de nouvelles notifications
                            </div>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${aAfficher == 'trajetsEnCours'}">
                    <jsp:include page="trajetsEnCours.jsp"></jsp:include>
                </c:if>
                <c:if test="${aAfficher == 'creerTrajetTemp'}">
                    <jsp:include page="creationTrajetTemp.jsp"></jsp:include>
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
                <c:if test="${aAfficher == 'detailsHistorique'}">
                    <jsp:include page="detailsHistorique.jsp"></jsp:include>
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
    </div>
</body>
</html>
