<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Prevision" %>
<%@ page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <h1>Liste des prévisions et réalisations</h1>
    <% String error = (String) request.getAttribute("error");
    if(error != null) { %>
        <p><%= error %></p>
    <% } %>
    
    <% 
    List<Prevision> listPrevision = (List<Prevision>) request.getAttribute("listPrevision");
    if(listPrevision != null && !listPrevision.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Libellé Crédit</th>
                <th>Dépense</th>
                <th>Reste</th>         
                <th>Date de debut</th>   
                <th>Date de fin</th>         
            </tr>
            <% for(Prevision prevision : listPrevision) { %>
                <tr>
                    <td><%= prevision.getLibelle() %></td>
                    <td><%= prevision.getDepense() %></td>
                    <td><%= prevision.getMontant() - prevision.getDepense() %></td>
                    <td><%= prevision.getDateDebut() %></td>
                    <td><%= prevision.getDateFin() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <div>
            <p>Aucune prévision et réalisation</p>
        </div>
    <% } %>
    <a href="accueil.html">Accueil</a> 
</body>
</html>