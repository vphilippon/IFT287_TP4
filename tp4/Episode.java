package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class Episode {

    private Map<Integer, TupleEpisode> allEpisodes;

    @SuppressWarnings("unchecked")
    public Episode(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allEpisodes = (Map<Integer, TupleEpisode>) cx.getDatabase().getRoot(
                        "allEpisodes");
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
        Query query = new Query(TupleEpisode.class, "getSerie() == ser "
                + "&& getNoSaison().equals(sai) && getNoEpisode().equals(no)", freeV);
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
        Query query = new Query(TupleEpisode.class, "getSerie() == ser "
                + "&& getNoSaison().equals(sai) && getNoEpisode().equals(no)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("ser", serie);
        freeVB.put("sai", noSaison);
        freeVB.put("no", noEpisode);
        return (TupleEpisode) query.pick(allEpisodes.values(), freeVB);
    }

    public void ajouter(TupleEpisode newEpisode) {
        allEpisodes.put(newEpisode.getId(), newEpisode);
    }
}
