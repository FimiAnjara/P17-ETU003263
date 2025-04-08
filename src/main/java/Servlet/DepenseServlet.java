package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import Service.DepenseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Prevision;
import model.Depense;

public class DepenseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prevision> listPrevisions = null;
        try {
            listPrevisions = Prevision.findAll();
            req.setAttribute("listPrevision", listPrevisions);
            req.getRequestDispatcher("FormDepense.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error",
                    "Une erreur est survenue lors du chargement du formulaire depense : " + e.getMessage());
            req.getRequestDispatcher("FormDepense.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String idPrevision = req.getParameter("idPrevision");
        String montant = req.getParameter("montant");
        String date = req.getParameter("date");
        Depense dep = new Depense(0, Integer.parseInt(idPrevision), Double.parseDouble(montant), Date.valueOf(date));
        try {
            HttpSession session = req.getSession();
            if (session.getAttribute("connecter") != null) {
                if (DepenseService.ajoutDepense(dep)) {
                    res.sendRedirect("FormulaireDepense");
                } else {
                    List<Prevision> listPrevisions = null;
                    listPrevisions = Prevision.findAll();
                    req.setAttribute("listPrevision", listPrevisions);
                    req.setAttribute("error", "Montant trop elevée ou date invalide");
                    req.getRequestDispatcher("FormDepense.jsp").forward(req, res);
                }
            } else {
                res.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("FormDepense.jsp").forward(req, res);
        }
    }

}
