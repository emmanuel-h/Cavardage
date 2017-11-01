<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 01/11/17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<label>Nombre d'utilisateurs inscrits : </label> <label>${stat.nbUtilisateur}</label> <br>
<label>Nombre de réservations acceptées : </label> <label>${stat.nbTrajetsReserves}</label> <br>
<label>Nombre de trajets terminés : </label> <label>${stat.nbTrajetsFinis}</label> <br>
<label>Prix total des trajets terminés : </label> <label>${stat.totalPrixTrajets} €</label> <br>
