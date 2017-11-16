<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 13/11/17
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-signin" method="post" action="j_security_check" >
    <h3 class="form-signin-heading" align="middle">Rentrez vos identifiants</h3>
    <label class="sr-only">Login:</label>
    <input class="form-control" type="text" name="j_username" size="25" autocomplete="off" placeholder="Nom d'utilisateur" autocomplete="off" required autofocus/>

    <label class="sr-only">Mot de passe:</label>
    <input class="form-control" type="password" name="j_password" size="15" placeholder="Mot de passe" autocomplete="off" required />

    <button class="btn btn-lg btn-primary btn-block" type="submit" name="afaire" value="connexion">Connexion</button>
</form>

<div align="middle">
    <form class= "form-signin" method="post" action="ControleurAnonyme">
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="afaire" value="inscription" id="inscriptionBouton">Inscription</button>
        </div>
        <div class="form-group">
            <button class="btn btn-lg btn-default btn-block" type="submit" id="accueilBouton">Accueil</button>
        </div>
    </form>
</div>
