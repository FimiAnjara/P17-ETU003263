package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Prevision {
    int id;
    String libelle;
    double montant;
    Date dateDebut;
    Date dateFin;
    Double depense;
    public Prevision(int id,String libelle,double montant,Date dataDebut,Date dateFin){
        setId(id);
        setLibelle(libelle);
        setMontant(montant);
        setDateDebut(dateFin);
        setDateFin(dateFin);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Double getDepense() {
        return depense;
    }
    public void setDepense(Double depense) {
        this.depense = depense;
    }

    public Boolean save() throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        try  {
            conn = Util.Connexion.openConnection();
            pstmt = conn.prepareStatement("INSERT INTO GestionWebDynamique_prevision (libelle,montant,DateDebut,DateFin) VALUES (?,?,?,?)");
            pstmt.setString(1, this.libelle);
            pstmt.setDouble(2, this.montant);
            pstmt.setDate(3, this.dateDebut);
            pstmt.setDate(4, this.dateFin);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted>0;
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'insertion du prévision : " + e.getMessage());
        }finally{
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    public static List<Prevision> findAll() throws Exception{
        Connection conn = null;
        Statement stmt = null;
        List<Prevision>  listPrevisions = new ArrayList<>();
        try  {
            conn = Util.Connexion.openConnection();
            String sql = "SELECT*FROM GestionWebDynamique_prevision";
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Prevision Prevision = new Prevision(resultSet.getInt("id"),resultSet.getString("libelle"),resultSet.getDouble("montant"),resultSet.getDate("dateDebut"),resultSet.getDate("dateFin"));
                Prevision.setId(resultSet.getInt("id"));
                listPrevisions.add(Prevision);
            }
            for(Prevision prev:listPrevisions){
                double dep = Depense.getSommeDepense(prev.getId());
                prev.setDepense(dep);
            }
            return listPrevisions;
        } catch (Exception e) {
            throw new Exception("Erreur lors du findAll prévision : " + e.getMessage());
        }finally{
            if(stmt!=null){
                stmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        }
    }
    public static Prevision find(int id_prevision) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        Prevision prev = null;

        try  {
            conn = Util.Connexion.openConnection();
            String sql = "SELECT * FROM GestionWebDynamique_prevision where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_prevision);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                prev = new Prevision(resultSet.getInt("id"), resultSet.getString("libelle"), resultSet.getDouble("montant"), resultSet.getDate("DateDebut"), resultSet.getDate("DateFin"));
            }
            if(resultSet!=null){
                resultSet.close();
            }
            return prev;          
        } catch (Exception e) {
            throw new Exception("Erreur lors du find prevision : " + e.getMessage());
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
