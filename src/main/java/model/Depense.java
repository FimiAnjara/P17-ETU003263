package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Depense {
    int id;
    int id_prevision;
    double montant;
    Date date;

    public Depense(int id, int id_prevision, double montant, Date date) {
        setDate(date);
        setId(id);
        setId_prevision(id_prevision);
        setMontant(montant);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_prevision(int id_prevision) {
        this.id_prevision = id_prevision;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getId_prevision() {
        return id_prevision;
    }

    public double getMontant() {
        return montant;
    }

    public Boolean save() throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try  {
            conn = Util.Connexion.openConnection();
            pstmt = conn.prepareStatement("INSERT INTO GestionWebDynamique_depense(id_prevision,montant,date) VALUES (?,?,?)");
            pstmt.setInt(1, this.id_prevision);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.date);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted>0;
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion du depense : " + e.getMessage());
        }finally{
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }

    public static Double getSommeDepense(int id_prevision) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        double somme = 0;
        try  {
            conn = Util.Connexion.openConnection();
            String sql = "SELECT SUM(montant) montantTotal FROM GestionWebDynamique_depense where id_prevision = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_prevision);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               somme = resultSet.getDouble("montantTotal");
            }
            if(resultSet!=null){
                resultSet.close();
            }
            return somme;          
        } catch (Exception e) {
            throw new Exception("Erreur lors du somme montant depense : " + e.getMessage());
        }finally{
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
}
