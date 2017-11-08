<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%@ taglib prefix="background-color" uri="http://bootstrapjsp.org/" %>
<%--
  Created by IntelliJ IDEA.
  User: emmanuelh
  Date: 08/11/17
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>

    <link href="css/connexion.css" rel="stylesheet">
</head>
<body>

<b:kickstart title="Cavardage">

</b:kickstart>


<div class="col-sm-3" >
        <img src="img/logo_xs.png" id="logoHeader" height="100">
</div>

<div class="container col-sm-6">

<form class="form-signin" method="post" action="j_security_check" >
        <h3 class="form-signin-heading" align="middle">Rentrez vos identifiants</h3>
        <label class="sr-only">Login:</label>
        <input class="form-control" type="text" name="j_username" size="25" autocomplete="off" placeholder="Nom d'utilisateur" required autofocus/>

        <label class="sr-only">Mot de passe:</label>
        <input class="form-control" type="password" name="j_password" size="15" placeholder="Mot de passe" required />

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

</div>

<div class="col-sm-3"></div>
<div class="col-lg-12" align="middle" style="margin-top: 10px">
    <img src="img/caravage_accueil.png" id="imageAccueil" width="800">
</div>


</body>
</html>
