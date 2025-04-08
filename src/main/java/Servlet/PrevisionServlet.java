package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Prevision;

public class PrevisionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String libelle = req.getParameter("libelle");
        String montant = req.getParameter("montant");
        String dateDebut = req.getParameter("dateDebut");
        String dateFin = req.getParameter("dateFin");

        try {
            HttpSession session = req.getSession();
            Date dateD = Date.valueOf(dateDebut);
            Date datef = Date.valueOf(dateFin);
            Prevision previson = new Prevision(0, libelle, Double.parseDouble(montant), dateD, datef);
            if(session.getAttribute("connecter")!=null){
                boolean save = previson.save();
                if (save) {
                    resp.sendRedirect("FormPrevision.jsp");
                }
            }else{
                resp.sendRedirect("index.jsp");
            }
           
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("FormPrevision.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Prevision> listprevisions = Prevision.findAll();
            req.setAttribute("listPrevision", listprevisions);
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        }
    }
}