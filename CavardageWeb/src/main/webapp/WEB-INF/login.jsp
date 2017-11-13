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

<jsp:include page="formulaireConnexion.jsp"/>

</div>

<div class="col-sm-3"></div>
<div class="col-lg-12" align="middle" style="margin-top: 10px">
    <img src="img/caravage_accueil.png" id="imageAccueil" width="800">
</div>


</body>
</html>
