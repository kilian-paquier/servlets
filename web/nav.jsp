<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/style.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.6/css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
      href="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.18/b-1.5.6/b-flash-1.5.6/b-html5-1.5.6/b-print-1.5.6/datatables.min.css"/>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <span class="fas fa-ghost"></span>
            <span class="px-1">Code Busters</span>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="accueil">Liste électorale</a>
                </li>
                <c:if test="${sessionScope.type == 'admin'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">Candidats</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <form method="get" action="candidat">
                            <button class="dropdown-item" name="Candidate" value="add">Ajouter</button>
                            <button class="dropdown-item" name="Candidate" value="modify">Modifier</button>
                            <button class="dropdown-item" name="Candidate" value="delete">Supprimer</button>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">Votants</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <form method="get" action="votant">
                            <button class="dropdown-item" name="Voter" value="add">Ajouter</button>
                            <button class="dropdown-item" name="Voter" value="modify">Modifier</button>
                            <button class="dropdown-item" name="Voter" value="delete">Supprimer</button>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">Partis</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <form method="get" action="parti">
                            <button class="dropdown-item" name="Party" value="add">Ajouter</button>
                            <button class="dropdown-item" name="Party" value="modify">Modifier</button>
                            <button class="dropdown-item" name="Party" value="delete">Supprimer</button>
                        </form>
                    </div>
                </li>
                </c:if>
                <c:if test="${sessionScope.type == 'candidat' || sessionScope.type == 'votant'}">
                <li class="nav-item">
                    <a class="nav-link" href="vote">Voter</a>
                </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="resultats">Résultats</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <c:choose>
                <c:when test="${sessionScope.type == 'candidat' || sessionScope.type == 'votant' || sessionScope.type == 'admin'}">
                    <li class="nav-item">
                        <a class="nav-link" href="connexion">Déconnexion</a>
                    </li>
                </c:when>
                <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="connexion">Connexion</a>
                </li>
                </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</header>
