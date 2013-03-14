package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;

import java.util.*;

class Film {

    private Connexion cx;
    private Map<Integer, TupleFilm> allFilms;

    public Film(Connexion cx) throws Exception {
        this.cx = cx;
        
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allFilms = (Map<Integer, TupleFilm>) cx.getDatabase().getRoot("allFilms");
            } catch (DatabaseRootNotFoundException e) {
                this.cx.getDatabase().createRoot("allFilms", allFilms = new OSHashMap<Integer, TupleFilm>(10));
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

    public boolean existe(String titre, Date dateSortie) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", Date.class);
        Query query = new Query(TupleFilm.class, "getTitre() == t && getDateSortie() == d", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", titre);
        freeVB.put("d", dateSortie);
        
        return !(query.select(allFilms.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleFilm newFilm) {
        allFilms.put(newFilm.getId(), newFilm);
    }
    
    public TupleFilm getFilm(String titre, Date dateSortie) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", Date.class);
        Query query = new Query(TupleFilm.class, "getTitre() == t && getDateSortie() == d", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", titre);
        freeVB.put("d", dateSortie);
        
        return (TupleFilm) query.pick(allFilms.values(), freeVB);
    }


    public int enlever(TupleFilm t) {
        Object o = allFilms.remove(t.getId());
        if (o == null) {
            return 0;
        } else {
            return 1;
        }
    }

    // Needed? Don't think so
//    public TupleFilm rechercher(int id) {
//        Object o = allFilms.get(id);
//
//        if (o instanceof TupleFilm) {
//            if (o != null) {
//                return (TupleFilm) o;
//            } else {
//                return null;
//            }
//        }
//        return null;
//    }

    public void listerFilm(String titre, Date dateSortie) {
        System.out.println("Liste des films");

        Iterator<TupleFilm> filmIterator = allFilms.values().iterator();

        while (filmIterator.hasNext()) {
            // TODO afficher
//            (filmIterator.next()).afficher();
        }
    }

    public void ajouterDescription(String titre, Date anneeSortie, String description, int duree) {
        // TODO Stuff ici
//        stmtAjoutDescFilm.setString(1, description);
//        stmtAjoutDescFilm.setInt(2, duree);
//        stmtAjoutDescFilm.setString(3, titre);
//        stmtAjoutDescFilm.setDate(4, anneeSortie);
//        stmtAjoutDescFilm.executeUpdate();
    }

    public Set<TupleFilm> filmDeRealisateur(String nom) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("n", String.class);
        Query query = new Query(TupleFilm.class, "getRealisateur() == n", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("n", nom);
        
        return query.select(allFilms.values(), freeVB);
    }
}
