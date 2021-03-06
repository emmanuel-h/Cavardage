<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 02/11/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<datalist id="listeVille">
    <c:forEach items="${listeVilles}" var="ville">
        <option value="${ville.nomVille}(${ville.departement})" ></option>
    </c:forEach>
</datalist>
<c:if test="${not empty messageErreur}">
    <div class="alert alert-danger" >${messageErreur}</div>
</c:if>
<div id="villeIdentique" hidden class="alert alert-warning">La ville de départ est la même que la ville d'arrivée</div>
<form action="ControleurUtilisateur" method="post" name="myFormVille" onsubmit="return verifierVille()">
    <legend>Proposer un trajet</legend>
    <div>
        <div class="col-lg-6">
            <div class="form-group">
                <label for="villeDepart">Ville de départ : </label>
                <input class="form-control" type="text" list="listeVille" id="villeDepart" name="villeDepart" autocomplete="off"  required>
            </div>
            <div class="form-group">
                <label for="villeArrivee">Ville d'arrivée : </label>
                <input class="form-control" type="text" list="listeVille" id="villeArrivee" name="villeArrivee" autocomplete="off"  required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" name="afaire" id="bouton_continuer" value="enregistrerTrajetTemp" >Continuer</button>
            </div>
        </div>
    </div>
</form>


<script>

    function verifierVille(){
        var villeDepart = document.getElementById("villeDepart").value;
        var villeArrivee = document.getElementById("villeArrivee").value;
        if(villeDepart == villeArrivee || villeDepart == "" || villeArrivee == ""){
            document.getElementById("villeIdentique").style.display = "block";
            return false;
        }else{
            document.getElementById("villeIdentique").style.display = "none";
            return true;
        }
    }
</script>