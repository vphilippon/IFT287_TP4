package tp4;

import java.util.Map;

import com.odi.DatabaseRootNotFoundException;
import com.odi.ObjectStore;
import com.odi.Transaction;
import com.odi.util.OSHashMap;
import com.odi.util.query.FreeVariableBindings;
import com.odi.util.query.FreeVariables;
import com.odi.util.query.Query;

class Episode {

    private Map<Integer, TupleEpisode> allEpisodes;

    public Episode(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allEpisodes = (Map<Integer, TupleEpisode>) cx.getDatabase().getRoot("allEpisodes");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allEpisodes", 
                        allEpisodes = new OSHashMap<Integer, TupleEpisode>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public boolean existe(TupleSerie serie, Integer noSaison, Integer noEpisode) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("ser", TupleSerie.class);
        freeV.put("sai", Integer.class);
        freeV.put("no", Integer.class);
        Query query = new Query(TupleFilm.class, "getSerie() == ser " 
                + "&& getNoSaison() == sai && getNoEpisode() == no", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("ser", serie);
        freeVB.put("sai", noSaison);
        freeVB.put("no", noEpisode);
        
        return !(query.select(allEpisodes.values(), freeVB).isEmpty());
    }
    
    public TupleEpisode getEpisode(TupleSerie serie, Integer noSaison, Integer noEpisode) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("ser", TupleSerie.class);
        freeV.put("sai", Integer.class);
        freeV.put("no", Integer.class);
        Query query = new Query(TupleFilm.class, "getSerie() == ser " 
                + "&& getNoSaison() == sai && getNoEpisode() == no", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("ser", serie);
        freeVB.put("sai", noSaison);
        freeVB.put("no", noEpisode);
        
        return (TupleEpisode) query.pick(allEpisodes.values(), freeVB);
    }

    void ajouter(TupleEpisode newEpisode) {
        allEpisodes.put(newEpisode.getId(), newEpisode);
    }
}
