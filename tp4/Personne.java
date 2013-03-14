package tp4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.odi.*;
import com.odi.util.*;

public class Personne {

    private Connexion cx;
    private Map<String, TuplePersonne> allPersonnes;

    public Personne(Connexion cx) throws Exception {
        this.cx = cx;

        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allPersonnes = (Map<String, TuplePersonne>) cx.getDatabase().getRoot("allPersonnes");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allPersonnes", allPersonnes = new OSHashMap<String, TuplePersonne>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public Connexion getConnexion() {
        return cx;
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

    public List<TuplePersonne> realisateurDeFilms() {
        // TODO à switcher vers RoleFilm pour itérer, surement
        List<TuplePersonne> listeRealisateur = new ArrayList<TuplePersonne>();
//        ResultSet rs = stmtGetRealisateur.executeQuery();
//        while (rs.next()) {
//            listeRealisateur.add(new TuplePersonne(rs.getString(1), rs
//                    .getDate(2), rs.getString(3), rs.getInt(4)));
//        }
//        rs.close();
        return listeRealisateur;
    }

    public List<TuplePersonne> acteursDeSerie(String serieTitre, Date serieDate) {
        // TODO à switcher ver RoleEpisode pour itérer, surement
        List<TuplePersonne> listeActeur = new ArrayList<TuplePersonne>();
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
