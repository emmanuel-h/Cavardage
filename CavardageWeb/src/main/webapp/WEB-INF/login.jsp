<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
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
</head>
<body>

<b:kickstart title="Cavardage">

</b:kickstart>

<form class="form-inline" method="post" action="j_security_check" >
    <div class="form-group">
        <label>Login:</label>
        <input class="form-control" type="text" name="j_username" size="25" autocomplete="off" required />
    </div>
    <div class="form-group">
        <label>Mot de passe:</label>
        <input class="form-control" type="password" name="j_password" size="15" required />
    </div>
    <div class="form-group">
        <button class="btn btn-default" type="submit" name="afaire" value="connexion">Connexion</button>
    </div>
</form>

<form class="form-inline" method="post" action="ControleurAnonyme">
    <button class="btn btn-default" type="submit">Accueil</button>
</form>
</body>
</html>
