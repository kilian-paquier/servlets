<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="ville" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="nom" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="successMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
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
    <title>Ajout/Modification de partis</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<%@ include file="nav.jsp" %>
<body>
<div class="container border-light border rounded mt-5 pt-5 mb-5 pb-5">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Ajout/Modification d'un parti</h3>
    </div>
    <form method="post" action="" class="animated fadeIn">
        <jsp:useBean id="option" scope="request" type="java.lang.String" class="java.lang.String"/>
        <c:choose>
            <c:when test="${option == 'add'}">
                <div class="row">
                    <div class="col-12 col-lg-8 offset-lg-2">
                        <div class="md-form">
                            <input id="nom" type="text" name="nom" class="form-control" value="${nom}">
                            <label for="nom">Nom du parti *</label>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-12 col-lg-8 offset-lg-2">
                        <div class="md-form">
                            <input id="nom" type="text" name="nom" class="form-control" value="${nom}" disabled>
                            <label for="nom">Nom du parti *</label>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="ville" type="text" name="ville" class="form-control" value="${ville}">
                    <label for="ville">Ville du parti *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2 text-center text-danger" id="error">
                ${errorMessage}
            </div>
            <div class="col-12 col-lg-8 offset-lg-2 text-center text-info" id="success">
                ${successMessage}
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit" id="btnSubmit">Créer/Modifier le parti</button>
            </div>
        </div>
    </form>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>
