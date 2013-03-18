package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class RoleFilm {

    private Map<Integer, TupleRoleFilm> allRoleFilms;

    @SuppressWarnings("unchecked")
    public RoleFilm(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allRoleFilms = (Map<Integer, TupleRoleFilm>) cx.getDatabase().getRoot(
                        "allRoleFilms");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allRoleFilms",
                        allRoleFilms = new OSHashMap<Integer, TupleRoleFilm>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public boolean existe(TupleFilm tf, TuplePersonne tp) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        freeV.put("tp", TuplePersonne.class);
        Query query = new Query(TupleRoleFilm.class,
                "getFilm() == tf && getActeur() == tp", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        freeVB.put("tp", tp);
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
    }

    public boolean existeRole(TupleFilm tf, String role) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleFilm.class,
                "getFilm() == tf && getRole().equals(r)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        freeVB.put("r", role);
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleRoleFilm newRoleFilm) {
        allRoleFilms.put(newRoleFilm.getId(), newRoleFilm);
    }

    @SuppressWarnings("unchecked")
    public Set<TuplePersonne> acteursDeFilm(TupleFilm tf) {
        Set<TuplePersonne> listeActeurs = new OSHashSet<TuplePersonne>();
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        Query query = new Query(TupleRoleFilm.class, "getFilm() == tf", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        Set<TupleRoleFilm> result = query.select(allRoleFilms.values(), freeVB);
        Iterator<TupleRoleFilm> roleIterator = result.iterator();
        while (roleIterator.hasNext()) {
            listeActeurs.add(roleIterator.next().getActeur());
        }
        return listeActeurs;
    }

    @SuppressWarnings("unchecked")
    public Set<TupleFilm> filmsDeActeur(TuplePersonne tp) {
        Set<TupleFilm> listeFilms = new OSHashSet<TupleFilm>();
        FreeVariables freeV = new FreeVariables();
        freeV.put("tp", TuplePersonne.class);
        Query query = new Query(TupleRoleFilm.class, "getActeur() == tp", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tp", tp);
        Set<TupleRoleFilm> result = query.select(allRoleFilms.values(), freeVB);
        Iterator<TupleRoleFilm> roleIterator = result.iterator();
        while (roleIterator.hasNext()) {
            listeFilms.add(roleIterator.next().getFilm());
        }
        return listeFilms;
    }
}
