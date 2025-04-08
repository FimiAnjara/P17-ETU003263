package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

public class loginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String pwd = req.getParameter("password");
        try {
            int user = Utilisateur.checkUser(userName, pwd) ;
            if(user != -1 ){
                HttpSession session = req.getSession();
                session.setAttribute("connecter", user);
                req.getRequestDispatcher("accueil.html").forward(req, resp);
            }else{
                req.setAttribute("error", "Veuillez verifier votre mot de passe");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
