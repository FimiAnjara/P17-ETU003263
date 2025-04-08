package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.Connexion;

public class Utilisateur {
    int id;
    String nom;
    String password;

    public Utilisateur(String nom, String password) {
        setNom(nom);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int checkUser(String nom, String password)throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = Connexion.openConnection();
            
            String sql = "SELECT id FROM GestionWebDynamique_Utilisateur WHERE nom = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("id"); 
            } else {
                return -1;
            }
            
        } catch (SQLException e) {
            throw new Exception("Erreur lors du check login : "+ e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}