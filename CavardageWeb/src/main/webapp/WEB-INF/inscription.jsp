<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<%--
  Created by IntelliJ IDEA.
  User: rety
  Date: 25/10/17
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cavardage ~ Inscription ~</title>
    <link href="css/connexion.css" rel="stylesheet">
</head>
<body>


<b:kickstart title="Cavardage">

</b:kickstart>




<div class="col-sm-3" >
    <img src="img/logo_xs.png" id="logoHeader" height="100">
</div>

<div class="container col-sm-6">

    ${messageErreur}
    <form class="form-signin" method="post" action="ControleurAnonyme" >
        <h3 class="form-signin-heading" align="middle">Rentrez vos identifiants</h3>
        <label>Identifiant :</label>
        <input class="form-control" type="text" name="login" size="25" autocomplete="off" placeholder="Login" autocomplete="off" required autofocus/>
        <label>Nom :</label>
        <input class="form-control" type="text" name="nom" size="25" autocomplete="off" placeholder="Nom" autocomplete="off" required autofocus/>
        <label>Mot de passe :</label>
        <input class="form-control" type="password" name="mdp" size="15" placeholder="Mot de passe" autocomplete="off" required />
        <label>Répétez le mot de passe :</label>
        <input class="form-control" type="password" name="mdp_confirmer" size="15" placeholder="Mot de passe" autocomplete="off" required />

        <button class="btn btn-lg btn-primary btn-block" type="submit" name="afaire" value="inscrire">Inscription</button>
    </form>

    <div align="middle">
        <form class= "form-signin" method="post" action="ControleurAnonyme">
            <div class="form-group">
                <button class="btn btn-lg btn-default btn-block" type="submit" style="margin-top: 10px">Accueil</button>
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
