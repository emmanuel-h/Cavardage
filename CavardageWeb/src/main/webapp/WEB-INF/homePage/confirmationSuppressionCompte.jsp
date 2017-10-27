<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 27/10/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
${message}<br>
<form action="ControleurUtilisateur" method="post">
    <label>Veuillez entrer votre mot de passe</label>
    <input type="password" name="motDePasse">
    <input type="hidden" name="confirmation" value="ok">
    <button type="submit" name="afaire" value="supprimerCompte">Supprimer compte</button>
</form>
