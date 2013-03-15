package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class RoleEpisode {
    
    private Map<Integer, TupleRoleEpisode> allRoleEpisodes;

    @SuppressWarnings("unchecked")
    public RoleEpisode(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allRoleEpisodes = (Map<Integer, TupleRoleEpisode>) cx.getDatabase().getRoot("allRoleEpisodes");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allRoleEpisodes", 
                        allRoleEpisodes = new OSHashMap<Integer, TupleRoleEpisode>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    public boolean existe(TupleSerie serie, TupleEpisode episode, TuplePersonne acteur, String roleActeur) {
        
        FreeVariables freeV = new FreeVariables();
        freeV.put("s", TupleSerie.class);
        freeV.put("e", TupleEpisode.class);
        freeV.put("a", TuplePersonne.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleEpisode.class, "getSerie() == s && getEpisode() == e && getNomActeur() == a && getRole().equals(r)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("s", serie);
        freeVB.put("e", episode);
        freeVB.put("a", acteur);
        freeVB.put("r", roleActeur);
        
        return !(query.select(allRoleEpisodes.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleRoleEpisode roleEpisode) {
        allRoleEpisodes.put(roleEpisode.getId(), roleEpisode);
    }

    @SuppressWarnings("unchecked")
    public Set<TupleRoleEpisode> rolesDeActeur(TuplePersonne personne) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("a", TuplePersonne.class);
        Query query = new Query(TupleRoleEpisode.class, "getNomActeur() == a", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("a", personne);
        
        return query.select(allRoleEpisodes.values(), freeVB);
    }
    
    public boolean existeRole(TupleSerie serie, TupleEpisode episode, String roleActeur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("s", TupleSerie.class);
        freeV.put("e", TupleEpisode.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleEpisode.class, "getSerie() == s && getEpisode() == e && getRole().equals(r)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("s", serie);
        freeVB.put("e", episode);
        freeVB.put("r", roleActeur);
        
        return !(query.select(allRoleEpisodes.values(), freeVB).isEmpty());
    }

	public Set<TuplePersonne> acteursDeSerie(TupleSerie serie) {
        Set<TuplePersonne> listeActeur = new OSHashSet<TuplePersonne>();
        Iterator<TupleRoleEpisode> roleIterator = allRoleEpisodes.values().iterator();
        
        while(roleIterator.hasNext()){
            listeActeur.add(roleIterator.next().getNomActeur());
        }

        return listeActeur;
    }
        
    @SuppressWarnings("unchecked")
    public Set<TupleRoleEpisode> serieAvecActeur(TuplePersonne acteur) {
        
        FreeVariables freeV = new FreeVariables();
        freeV.put("a", TuplePersonne.class);
        Query query = new Query(TupleRoleEpisode.class, "getNomActeur() == a", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("a", acteur);
        
        return query.select(allRoleEpisodes.values(), freeVB);
    }
    
}
