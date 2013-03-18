package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;

class Serie {

    private Map<Integer, TupleSerie> allSeries;

    @SuppressWarnings("unchecked")
    public Serie(Connexion cx) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allSeries = (Map<Integer, TupleSerie>) cx.getDatabase().getRoot(
                        "allSeries");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allSeries",
                        allSeries = new OSHashMap<Integer, TupleSerie>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public boolean existe(String serieTitre, OSDate serieOSDate) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", long.class);
        Query query = new Query(TupleSerie.class,
                "getTitre().equals(t) && getDateSortie().getTime() == d", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", serieTitre);
        freeVB.put("d", serieOSDate.getTime());
        return !(query.select(allSeries.values(), freeVB).isEmpty());
    }

    public void ajouter(TupleSerie newSerie) {
        allSeries.put(newSerie.getId(), newSerie);
    }

    @SuppressWarnings("unchecked")
    public Set<TupleSerie> serieDeRealisateur(TuplePersonne realisateur) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("r", TuplePersonne.class);
        Query query = new Query(TupleSerie.class, "getRealisateur() == r", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("r", realisateur);
        return query.select(allSeries.values(), freeVB);
    }

    public TupleSerie getSerie(String titre, OSDate anneeSortie) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", long.class);
        Query query = new Query(TupleSerie.class,
                "getTitre().equals(t) && getDateSortie().getTime() == d", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", titre);
        freeVB.put("d", anneeSortie.getTime());
        return (TupleSerie) query.pick(allSeries.values(), freeVB);
    }
}
