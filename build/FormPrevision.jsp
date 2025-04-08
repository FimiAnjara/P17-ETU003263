<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FormulairePrevision</title>
</head>
<body>
    <h1>Ajouter une previson</h1>
      <% String error = (String)request.getAttribute("error");
    if(error!=null) {%>
        <p><%= error %></p>
    <% }%>

    <form action="TraitPrevision" method="post">
        <div>
            <label for="libelle">Libelle </label>
            <input type="text" name="libelle" placeholder="ex: frais" required>
        </div>
        <div>
            <label for="montant">Montant </label>
            <input type="number" id="montant" name="montant" required placeholder="ex : 600">
        </div>
        <div>
            <label for="dateDebut">Date de debut </label>
            <input type="date" id="dateDebut" name="dateDebut" required>
        </div>
        <div>
            <label for="dateFin">Date de debut </label>
            <input type="date" id="dateFin" name="dateFin" required>
        </div>

        <button>Ajouter</button>
    </form>
    <div>
    <a href="accueil.html">Retour</a>
    </div>

</body>
</html>