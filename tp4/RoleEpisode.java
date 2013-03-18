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
                allRoleEpisodes = (Map<Integer, TupleRoleEpisode>) cx.getDatabase().getRoot(
                        "allRoleEpisodes");
                Iterator<TupleRoleEpisode> it = allRoleEpisodes.values().iterator();
                int max = 0;
                while(it.hasNext()){
                    int courrant = it.next().getId();
                    max = courrant > max ? courrant : max;
                }
                TupleRoleEpisode.setCourantId(max);
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

    public boolean existe(TupleEpisode episode, TuplePersonne acteur, String roleActeur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("e", TupleEpisode.class);
        freeV.put("a", TuplePersonne.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleEpisode.class,
                "getEpisode() == e && getActeur() == a && getRole().equals(r)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("e", episode);
        freeVB.put("a", acteur);
        freeVB.put("r", roleActeur);
        return !(query.select(allRoleEpisodes.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleRoleEpisode roleEpisode) {
        allRoleEpisodes.put(roleEpisode.getId(), roleEpisode);
    }

    public boolean existeRole(TupleEpisode episode, String roleActeur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("e", TupleEpisode.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleEpisode.class,
                "getEpisode() == e && getRole().equals(r)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("e", episode);
        freeVB.put("r", roleActeur);
        return !(query.select(allRoleEpisodes.values(), freeVB).isEmpty());
    }

    @SuppressWarnings("unchecked")
    public Set<TuplePersonne> acteursDeSerie(TupleSerie serie) {
        Set<TuplePersonne> listeActeur = new OSHashSet<TuplePersonne>();
        FreeVariables freeV = new FreeVariables();
        freeV.put("s", TupleSerie.class);
        Query query = new Query(TupleRoleEpisode.class, "getEpisode().getSerie() == s",
                freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("s", serie);
        Set<TupleRoleEpisode> result = query.select(allRoleEpisodes.values(), freeVB);
        Iterator<TupleRoleEpisode> roleIterator = result.iterator();
        while (roleIterator.hasNext()) {
            listeActeur.add(roleIterator.next().getActeur());
        }
        return listeActeur;
    }

    @SuppressWarnings("unchecked")
    public Set<TupleSerie> serieAvecActeur(TuplePersonne acteur) {
        Set<TupleSerie> listeSerie = new OSHashSet<TupleSerie>();
        FreeVariables freeV = new FreeVariables();
        freeV.put("a", TuplePersonne.class);
        Query query = new Query(TupleRoleEpisode.class, "getActeur() == a", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("a", acteur);
        Set<TupleRoleEpisode> result = query.select(allRoleEpisodes.values(), freeVB);
        Iterator<TupleRoleEpisode> roleIterator = result.iterator();
        while (roleIterator.hasNext()) {
            listeSerie.add(roleIterator.next().getEpisode().getSerie());
        }
        return listeSerie;
    }
}
