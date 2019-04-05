<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kilian
  Date: 01/04/2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Liste des candidats</title>

</head>

<%@include file="nav.jsp" %>

<body>
<div class="container pt-5 mt-5 mb-5 pb-5 border border-light rounded">
    <div class="row mb-3 mt-3">
        <div class="col-12 col-lg-8 offset-lg-2 text-danger" id="error">
            ${message}
        </div>
    </div>
    <div class="heading text-center pt-4">
        <h3 class="card-title">Liste des candidats</h3>
    </div>
    <div class="row animated fadeIn">
        <div class="col-12">
            <table class="table table-hover table-striped text-nowrap w-100">
                <thead>
                <tr>
                    <th>Nom du candidat</th>
                    <th>Parti</th>
                </tr>
                </thead>
                <tbody>
                <jsp:useBean id="candidateList" scope="request" type="java.util.List"/>
                <c:forEach var="candidate" items="${candidateList}">
                    <tr>
                        <td><c:out
                                value="${candidate.getFirstName()} ${candidate.getLastName()}"> ${candidate.getId()} </c:out></td>
                        <td><c:out
                                value="${candidate.getParty().getPartyName()}">${candidate.getParty().getPartyName()}</c:out></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>

                </tfoot>
            </table>
        </div>
    </div>
</div>
</body>

<%@include file="footer.jsp" %>
</html>
