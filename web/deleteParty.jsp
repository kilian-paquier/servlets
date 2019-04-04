<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="successMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
<jsp:useBean id="errorMessage" scope="request" type="java.lang.String" class="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: Kilian
  Date: 01/04/2019
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Supprimer un parti</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<%@ include file="nav.jsp"%>
<body>
<div class="container mt-5 pt-5 mb-5 pb-5 border border-light rounded">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Supprimer un parti</h3>
    </div>

    <form method="post" action="" class="animated fadeIn">
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <label for="selectParty">Parti à supprimer *</label>
                <select class="browser-default custom-select" name="parti_name" id="selectParty">
                    <option selected disabled value="">Choisissez un parti</option>
                    <jsp:useBean id="partyList" scope="request" type="java.util.List"/>
                    <c:forEach var="party" items="${partyList}">
                        <option value="${party.getPartyName()}">${party.getPartyName()}</option>
                    </c:forEach>
                </select>
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
                <button class="btn btn-dark w-100" type="submit" id="btnSubmit">Supprimer le parti</button>
            </div>
        </div>
    </form>
</div>
</body>
<%@ include file="footer.jsp"%>
</html>
