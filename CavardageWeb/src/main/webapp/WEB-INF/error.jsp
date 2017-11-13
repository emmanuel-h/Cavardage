<%@ taglib prefix="b" uri="http://bootstrapjsp.org/" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html">
<h:head>
    <title>Login Error</title>
    <link href="css/connexion.css" rel="stylesheet">
</h:head>
<h:body>

    <b:kickstart title="Cavardage">

    </b:kickstart>


    <div class="col-sm-3" >
        <img src="img/logo_xs.png" id="logoHeader" height="100">
    </div>

    <div class="alert alert-danger col-md-4 col-md-offset-1" align="middle">Le mot de passe ou le login est incorrect</div>

    <div class="container col-sm-6">

    <jsp:include page="formulaireConnexion.jsp"/>
    </div>

    <div class="col-sm-3"></div>
    <div class="col-lg-12" align="middle" style="margin-top: 10px">
        <img src="img/caravage_accueil.png" id="imageAccueil" width="800">
    </div>

</h:body>
</html>