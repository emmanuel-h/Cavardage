<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 01/11/17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Statistiques du site au ${date}</h3>
<ul class="list-group">
    <li class="list-group-item">
<table class="table table-bordered table-hover">
    <tbody>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre d'utilisateurs inscrits sur le site">Nombre d'utilisateurs inscrits</td>
            <td class="col-lg-4">${stat.nbUtilisateur}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre d'utilisateurs ayant effectué au moins un trajet en tant que conducteur">Nombre de conducteurs</td>
            <td class="col-lg-4">${stat.nbConducteurs}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre d'utilisateurs ayant effectué au moins un trajet en tant que passager">Nombre de passagers</td>
            <td class="col-lg-4">${stat.nbPassagers}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre total de réservations effectuées sur le site par tous les utilisateurs">Nombre de réservations effectuées</td>
            <td class="col-lg-4">${stat.nbTrajetsReserves}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre total de trajets terminés par tous les utilisateurs">Nombre de trajets finis</td>
            <td class="col-lg-4">${stat.nbTrajetsFinis}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Somme des prix de tous les trajets terminés par tous les utilisateurs">Prix total des trajets terminés</td>
            <td class="col-lg-4">${stat.totalPrixTrajets} €</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Nombre de villes desservies par le site">Nombre de villes desservies</td>
            <td class="col-lg-4">${stat.nbVilles}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Ville avec le plus grand nombre de départs">Ville de départ la plus fréquente</td>
            <td class="col-lg-4">${stat.topVilleDepart}</td>
        </tr>
        <tr>
            <td class="col-lg-4" data-toggle="tooltip" title="Ville avec le plus grand nombre d'arrivées">Ville d'arrivée la plus fréquente</td>
            <td class="col-lg-4">${stat.topVilleArrivee}</td>
        </tr>
    </tbody>
</table>
    </li>
</ul>
<h5>Résultat obtenu en ${stat.duration} ms</h5>
<br>
<form method="post" action="ControleurAdmin">
    <button type="submit" class="btn btn-primary" name="boutonAdmin" value="actualiserStat">Actualiser</button>
</form>
