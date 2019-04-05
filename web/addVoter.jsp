<jsp:useBean id="errorMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="successMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: Kilian
  Date: 01/04/2019
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Ajout/Modification de votants</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<%@ include file="nav.jsp" %>
<jsp:useBean id="voterAccount" scope="request" type="com.model.Voter" class="com.model.Voter"/>
<body>
<div class="container border-light border rounded mt-5 pt-5 mb-5 pb-5">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Ajout/Modification d'un votant</h3>
    </div>
    <form method="post" action="" class="animated fadeIn">
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="nom" type="text" name="nom" class="form-control"
                           value="${voterAccount.lastName}">
                    <label for="nom">Nom *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="prenom" type="text" name="prenom" class="form-control" value="${voterAccount.firstName}">
                    <label for="prenom">Prénom *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="ville" type="text" name="ville" class="form-control" value="${voterAccount.city}">
                    <label for="ville">Ville *</label>
                </div>
            </div>
        </div>
        <jsp:useBean id="option" scope="request" type="java.lang.String"/>
        <c:choose>
            <c:when test="${option == 'add'}">
                <div class="row mb-3 mt-3">
                    <div class="col-12 col-lg-8 offset-lg-2">
                        <div class="md-form">
                            <input id="login" type="text" name="login" class="form-control" value="">
                            <label for="login">Identifiant utilisateur *</label>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row mb-3 mt-3">
                    <div class="col-12 col-lg-8 offset-lg-2">
                        <div class="md-form">
                            <input id="login" type="text" name="login" class="form-control" value="${voterAccount.login}" disabled>
                            <label for="login">Identifiant utilisateur *</label>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:if test="${option == 'add'}">
            <div class="row mb-3 mt-3">
                <div class="col-12 col-lg-8 offset-lg-2">
                    <div class="md-form">
                        <input id="password" type="password" name="password" class="form-control" value="">
                        <label for="password">Mot de passe *</label>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="">
                    <label for="birthDate">Date de naissance *</label>
                    <input id="birthDate" type="date" name="naissance" class="form-control" placeholder=""
                           value="${voterAccount.birthDate}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2 text-danger" id="error">
                ${errorMessage}
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2 text-info" id="success">
                ${successMessage}
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit" id="btnSubmit">Créer/Modifier le votant</button>
            </div>
        </div>
    </form>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>
