package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.FreeVariableBindings;
import com.odi.util.query.FreeVariables;
import com.odi.util.query.Query;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class Film {

    private Connexion cx;
    private Map<Integer,TupleFilm> allFilms;

    public Film(Connexion cx) throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
      try {
           allFilms = (Map<Integer,TupleFilm>) cx.getDatabase().getRoot("allFilms");
      }
      catch (DatabaseRootNotFoundException e) {
          /* Creation de la racine */
          cx.getDatabase().createRoot("allFilms", allFilms = new OSHashMap<Integer,TupleFilm>(10));
          }
      /* Fin de la transaction avec retention des objets creux */
      tr.commit(ObjectStore.RETAIN_HOLLOW);
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String titre, Date dateSortie){
        return ("".equals(allFilms.get(titre)) && (allFilms.get(dateSortie) != null));
    }

    public void ajouter(TupleFilm newFilm){
        allFilms.put(newFilm.getIdFilm(), newFilm);
    }

    public int enlever(TupleFilm t){
        Object o = allFilms.remove(t.getIdFilm());
        if (o == null) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    public TupleFilm rechercher(int id){
        Object o = allFilms.get(id);
        
        if(o instanceof TupleFilm){
            if (o != null){
                return (TupleFilm)o;
            }
            else {
                return null;
            }
        }
        return null;
    }

    public void listerFilm(String titre, Date dateSortie){
        System.out.println("Liste des films");
        
        Iterator<TupleFilm> filmIterator = allFilms.values().iterator();
        
        while (filmIterator.hasNext()){
            (filmIterator.next()).afficher();
        }
    }

    public void ajouterDescription(String titre, Date anneeSortie, String description, int duree) throws SQLException {
        stmtAjoutDescFilm.setString(1,description);
        stmtAjoutDescFilm.setInt(2,duree);
        stmtAjoutDescFilm.setString(3,titre);
        stmtAjoutDescFilm.setDate(4,anneeSortie);
        stmtAjoutDescFilm.executeUpdate();
    }

    public void filmDeRealisateur(String nom) throws SQLException {
        System.out.println("Liste des livres pour un auteur");

    FreeVariables freeV = new FreeVariables();
    freeV.put("x", String.class);
    Query query = new Query(TupleFilm.class, "getFilm() == x", freeV);
    FreeVariableBindings freeVB = new FreeVariableBindings();
    freeVB.put("x", nom);
    Collection<TupleFilm> queryResults = query.select(allFilms.values(), freeVB);

    Iterator<TupleFilm> livreIter = queryResults.iterator();

    while (livreIter.hasNext()) {
        livreIter.next().afficher();
        }
    }
}
