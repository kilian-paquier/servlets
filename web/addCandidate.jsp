<%--
  Created by IntelliJ IDEA.
  User: Kilian
  Date: 01/04/2019
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Ajout/Modification de candidats</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<%@ include file="nav.jsp" %>
<body>
<div class="container border-light border rounded mt-5 pt-5 mb-5 pb-5">
    <div class="heading text-center pt-4">
        <h3 class="card-title">Ajout/Modification d'un candidat</h3>
    </div>
    <form method="post" id="registerForm" action="" class="animated fadeIn">
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="nom" type="text" name="nom" class="form-control">
                    <label for="nom">Nom *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="prenom" type="text" name="prenom" class="form-control">
                    <label for="prenom">Prénom *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="ville" type="text" name="ville" class="form-control">
                    <label for="ville">Ville *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="login" type="text" name="login" class="form-control">
                    <label for="login">Identifiant utilisateur *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="md-form">
                    <input id="password" type="password" name="password" class="form-control">
                    <label for="password">Mot de passe *</label>
                </div>
            </div>
        </div>
        <div class="row mb-3 mt-3">
            <div class="col-12 col-lg-8 offset-lg-2">
                <div class="">
                    <label for="parti">Parti du candidat *</label>
                    <select id="parti" class="custom-select default-browser" name="parti">
                        <option selected disabled>Sélection un parti</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2" id="success">

            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-8 offset-lg-2">
                <button class="btn btn-dark w-100" type="submit" id="btnSubmit">Créer/Modifier le candidat</button>
            </div>
        </div>
    </form>
</div>
</body>
<%@ include file="footer.jsp" %>
</html>
