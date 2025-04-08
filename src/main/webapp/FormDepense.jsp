<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Prevision" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FormulaireDepense</title>
</head>
<body>
    <h1>Ajouter une depense</h1>
      <% String error = (String)request.getAttribute("error");
    if(error!=null) {%>
        <p><%= error %></p>
    <% }%>

    <form action="FormulaireDepense" method="POST">
        <% List<Prevision> prevision = (List<Prevision>) request.getAttribute("listPrevision");
         if(prevision != null && !prevision.isEmpty()) { %>
        <div>
            <label for="prevision">choisir une prevision</label>
            <select name="idPrevision" required>
            <option disabled selected>selectionner une prévision</option>
            <% for(Prevision dep : prevision) {%>
           
                <option value="<%= dep.getId() %>"><%=  dep.getLibelle() %></option>
            <% }%>
            </select>
        </div>
        <% }else {%>
           <div>
                <p>Aucune prevision</p>
           </div>
        <%}%>
        <div>
            <label for="montant">Montant </label>
            <input type="number" id="montant" name="montant" required placeholder="ex : 600">
        </div>
        <div>
            <label for="date">Date </label>
            <input type="date" id="date" name="date" required>
        </div>
        <button>Ajouter</button>
    </form>
    <div>
    <a href="accueil.html">Retour</a>
    
    </div>

</body>
</html>