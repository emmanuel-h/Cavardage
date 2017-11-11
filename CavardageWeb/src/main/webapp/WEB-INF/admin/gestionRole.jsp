<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: benoit
  Date: 11/11/17
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${!empty messageErreur}">
    <div class="alert alert-danger">
        <strong>Erreur : </strong>${messageErreur}
    </div>
</c:if>
<c:if test="${!empty messageSucces}">
    <div class="alert alert-success">
        <strong>Succès : </strong>${messageSucces}
    </div>
</c:if>

<datalist id="loginList">
    <c:forEach items="${listeLogin}" var="login">
        <option value="${login}" />
    </c:forEach>
</datalist>

<form method="post" action="ControleurAdmin">
    <div class="form-group">
        <label for="login">Login : </label>
        <input type="text" class="form-control" list="loginList" id="login" name="login" autocomplete="off" required>
    </div>
    <div class="form-group">
        <label for="role">Nouveau rôle : </label>
        <select name="role" id="role" required>
            <c:forEach items="${listeRoles}" var="role">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <button class="btn btn-primary" type="submit" name="boutonAdmin" value="changerRole">Changer le rôle</button>
    </div>
</form>
