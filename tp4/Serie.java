package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;

import java.util.*;

class Serie {

    private Map<Integer, TupleSerie> allSeries;
    
    public Serie(Connexion cx) throws Exception {
        
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            try {
                allSeries = (Map<Integer, TupleSerie>) cx.getDatabase().getRoot("allSeries");
            } catch (DatabaseRootNotFoundException e) {
                cx.getDatabase().createRoot("allSeries", allSeries = new OSHashMap<Integer, TupleSerie>(10));
            }
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }                                                                                        
    }

    public boolean existe(String serieTitre, Date serieDate) {
        
        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", Date.class);
        Query query = new Query(TupleSerie.class, "getTitre().equals(t) && getAnneeSortie().equals(d)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", serieTitre);
        freeVB.put("d", serieDate); 
        
        return !(query.select(allSeries.values(), freeVB).isEmpty());
        
    }
    
    public void ajouter(TupleSerie newSerie) {
        allSeries.put(newSerie.getId(), newSerie);
    }

    public Set<TupleSerie> serieDeRealisateur(String nom) {
        Set<TupleSerie> listeSerie = null; // TEMP;
//        stmtSerieDeRealisateur.setString(1,nom);
//        ResultSet rs = stmtSerieDeRealisateur.executeQuery();
//        while(rs.next()){
//            listeSerie.add(new TupleSerie(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4), rs.getInt(5)));
//        }
//        rs.close();
        return listeSerie;
    }
    
    public Set<TupleSerie> serieAvecActeur(String nom) {
        Set<TupleSerie> listeSerie = null; // TEMP;
//        stmtSerieAvecActeur.setString(1,nom);
//        ResultSet rs = stmtSerieAvecActeur.executeQuery();
//        while(rs.next()){
//            listeSerie.add(new TupleSerie(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4), rs.getInt(5)));
//        }
//        rs.close();
        return listeSerie;
    }

    public TupleSerie getSerie(String titre, Date anneeSortie) {

        FreeVariables freeV = new FreeVariables();
        freeV.put("t", String.class);
        freeV.put("d", String.class);
        Query query = new Query(TupleSerie.class, "getTitre().equals(t) && getAnneeSortie().equals(d)", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("t", titre);
        freeVB.put("d", anneeSortie); 
        
        return (TupleSerie) query.pick(allSeries.values(), freeVB);
    }

}
