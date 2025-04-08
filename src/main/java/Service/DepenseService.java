package Service;

import model.Depense;
import model.Prevision;

public class DepenseService {
    public static boolean ajoutDepense(Depense depense) throws Exception{
        try {
            double totaldepense =  Depense.getSommeDepense(depense.getId_prevision());
            Prevision prevision = Prevision.find(depense.getId_prevision());
            double reste = prevision.getMontant() - totaldepense;
            if(reste >= depense.getMontant()){
                boolean save = depense.save();
                if(save){
                    return true;
                }
            }
        } catch (Exception e) {
            throw new Exception("erreur lors de l'insertion de depense : "+ e.getMessage());
        }
        return false;
    }
}
