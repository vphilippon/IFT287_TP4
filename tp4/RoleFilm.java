package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class RoleFilm {
    
    private Map<Integer,TupleRoleFilm> allRoleFilms;

    @SuppressWarnings("unchecked")
    public RoleFilm(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try{
                allRoleFilms = (Map<Integer,TupleRoleFilm>) cx.getDatabase().getRoot("allRoleFilms");
            } catch(DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allRoleFilms", allRoleFilms = new OSHashMap<Integer, TupleRoleFilm>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch(Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    public TupleFilm getRoleFilm(TuplePersonne tp, TupleFilm tf, String roleActeur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tp", TuplePersonne.class);
        freeV.put("tf", TupleFilm.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleFilm.class, "getNomActeur() == tp && getFilm() == tf && r.equals(getRoleActeur())", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tp", tp);
        freeVB.put("tf", tf);
        freeVB.put("r", roleActeur);
        
        return (TupleFilm) query.pick(allRoleFilms.values(), freeVB);
    }

    public boolean existe(TupleFilm tf, TuplePersonne tp, String role) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        freeV.put("tp", TuplePersonne.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleFilm.class, "getFilm() == tf && getNomActeur() == tp && getRoleActeur() == r", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        freeVB.put("tp", tp);
        freeVB.put("r", role);
        
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
    }
    
    public boolean existe(TupleFilm tf, String role) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleFilm.class, "getFilm() == tf && getRoleActeur() == r", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        freeVB.put("r", role);
        
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleRoleFilm newRoleFilm) {
        allRoleFilms.put(newRoleFilm.getId(), newRoleFilm);
    }

    public boolean aDesRoles(TupleFilm tf) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("tf", TupleFilm.class);
        Query query = new Query(TupleRoleFilm.class, "getFilm() == tf", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("tf", tf);
        
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
    }
    
    public Set<TuplePersonne> getActeurs(TupleFilm tf) {
        Set<TuplePersonne> listeActeurs = new OSHashSet<TuplePersonne>();
        Iterator<TupleRoleFilm> roleIterator = allRoleFilms.values().iterator();
        
        while(roleIterator.hasNext()) {
            listeActeurs.add(roleIterator.next().getNomActeur());
        }
        return listeActeurs;
    }

    public Set<TupleRoleFilm> rolesDeActeur(TuplePersonne tp) {
        Set<TupleRoleFilm> listeRole = new OSHashSet<TupleRoleFilm>();
        Iterator<TupleRoleFilm> filmIterator = allRoleFilms.values().iterator();
        
        while (filmIterator.hasNext()) {
            listeRole.add(filmIterator.next());
        }
        return listeRole;
    }
}
