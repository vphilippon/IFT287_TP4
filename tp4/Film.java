package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class Film {

    private Map<Integer, TupleFilm> allFilms;

    @SuppressWarnings("unchecked")
    public Film(Connexion cx) throws Exception {
        
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allFilms = (Map<Integer, TupleFilm>) cx.getDatabase().getRoot("allFilms");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allFilms", allFilms = new OSHashMap<Integer, TupleFilm>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public boolean existe(String titre, OSDate dateSortie) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", OSDate.class);
        Query query = new Query(TupleFilm.class, "getTitre() == t && getOSDateSortie() == d", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", titre);
        freeVB.put("d", dateSortie);
        
        return !(query.select(allFilms.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleFilm newFilm) {
        allFilms.put(newFilm.getId(), newFilm);
    }
    
    public TupleFilm getFilm(String titre, OSDate dateSortie) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", OSDate.class);
        Query query = new Query(TupleFilm.class, "getTitre() == t && getOSDateSortie() == d", freeV);
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

    public void ajouterDescription(TupleFilm t, String description, int duree) {
        t.setDescription(description);
        t.setDuree(duree);
        // TODO Check si fonctionne
//        System.out.println((getFilm(t.getTitre(), t.getDateSortie())).afficher()); // TODO pour test
    }

    @SuppressWarnings("unchecked")
    public Set<TupleFilm> filmDeRealisateur(TuplePersonne realisateur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("n", TuplePersonne.class);
        Query query = new Query(TupleFilm.class, "getRealisateur() == n", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("n", realisateur);
        
        return query.select(allFilms.values(), freeVB);
    }
    
    public Set<TuplePersonne> realisateurDeFilms() {
        Set<TuplePersonne> listeRealisateur = new OSHashSet<TuplePersonne>();
        Iterator<TupleFilm> filmIterator = allFilms.values().iterator();
        
        while(filmIterator.hasNext()){
            listeRealisateur.add(filmIterator.next().getRealisateur());
        }
        
        return listeRealisateur;
    }
}
