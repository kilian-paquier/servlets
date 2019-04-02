<link href="css/style.css" rel="stylesheet">
<link href="lib/material-design-4.7.1/css/bootstrap.min.css" rel="stylesheet">
<link href="lib/material-design-4.7.1/css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.18/b-1.5.6/b-flash-1.5.6/b-html5-1.5.6/b-print-1.5.6/datatables.min.css"/>

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
                    <a class="nav-link" href="index.jsp">Liste électorale</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">Candidats</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <a class="dropdown-item" href="addCandidate.jsp">Ajouter</a>
                        <a class="dropdown-item" href="modifyCandidate.jsp">Modifier</a>
                        <a class="dropdown-item" href="deleteCandidate.jsp">Supprimer</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">Votants</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <a class="dropdown-item" href="addVoter.jsp" name="add">Ajouter</a>
                        <a class="dropdown-item" href="modifyVoter.jsp" name="modify">Modifier</a>
                        <a class="dropdown-item" href="deleteVoter.jsp" name="delete">Supprimer</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                       aria-expanded="false">Partis</a>
                    <div class="dropdown-menu dropdown-secondary">
                        <a class="dropdown-item" href="addParty.jsp">Ajouter</a>
                        <a class="dropdown-item" href="modifyParty.jsp">Modifier</a>
                        <a class="dropdown-item" href="deleteParty.jsp">Supprimer</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="vote.jsp">Voter</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="results.jsp">Résultats</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Connexion</a>
                </li>
            </ul>
        </div>
    </nav>
</header>