package tp4;

import com.odi.*;
import com.odi.util.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;


public class Personne {

    private Map<String, TuplePersonne> allPersonnes;

    public Personne(Connexion cx) throws Exception {

        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allPersonnes = (Map<String, TuplePersonne>) cx.getDatabase().getRoot("allPersonnes");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allPersonnes", 
                        allPersonnes = new OSHashMap<String, TuplePersonne>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public boolean existe(String nom) {
        return allPersonnes.get(nom) != null;
    }

    public TuplePersonne getPersonne(String nom) {
        return allPersonnes.get(nom);
    }

    public void ajouter(TuplePersonne newPersonne) {
        allPersonnes.put(newPersonne.getNom(), newPersonne);
    }

    public int enlever(TuplePersonne t) {
        Object o = allPersonnes.remove(t.getNom());
        if (o == null)
            return 0;
        else
            return 1;
    }

    public Set<TuplePersonne> acteursDeSerie(String serieTitre, Date serieDate) {
        // TODO à switcher ver RoleEpisode pour itérer, surement
        Set<TuplePersonne> listeActeur = null; // TEMP;
//        stmtGetActeurDeSerie.setString(1, serieTitre);
//        stmtGetActeurDeSerie.setDate(2, serieDate);
//        ResultSet rs = stmtGetActeurDeSerie.executeQuery();
//        while (rs.next()) {
//            listeActeur.add(new TuplePersonne(rs.getString(1), rs.getDate(2),
//                    rs.getString(3), rs.getInt(4)));
//        }
//        rs.close();
        return listeActeur;
    }

}
