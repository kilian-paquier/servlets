<%--
  Created by IntelliJ IDEA.
  User: Pozzi
  Date: 04/04/2019
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur 403</title>
    <meta name="description" content="Page d'accueil">
    <meta name="keywords" content="Université, Sherbrooke, Complexe, Sportif, Vert, Or, Accueil">
    <jsp:include page="nav.jsp"/>
</head>
<body>

<section class="container">


    <div class="row">
        <div class="col-sm-12 col-md-7">
            <h1>Erreur 403</h1>
            <p class="text-justify">Tabarnak ! Nous avons cherché partout votre page, mais elle est introuvable. Vous devez ne pas avoir les droits d'accès à cette page</p>
        </div>
        <div class="col-sm-12 col-md-5">
            <a class="nav-link" href="accueil">Retour à l'accueil</a>
            <a class="nav-link" href="connexion">Retour à la page de connexion</a>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
