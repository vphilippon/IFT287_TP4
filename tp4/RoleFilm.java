package tp4;

import com.odi.DatabaseRootNotFoundException;
import com.odi.ObjectStore;
import com.odi.Transaction;
import com.odi.util.OSHashMap;
import com.odi.util.query.FreeVariableBindings;
import com.odi.util.query.FreeVariables;
import com.odi.util.query.Query;
import java.util.Date;
import java.util.Map;
import java.util.Set;

class RoleFilm {
    
    private Map<Integer,TupleRoleFilm> allRoleFilms;

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

    public boolean existe(String nomActeur, String filmTitre, Date anneeSortie, String role) {
        FreeVariables freeV = new FreeVariables();
        freeV.put("na", String.class);
        freeV.put("ft", String.class);
        freeV.put("as", Date.class);
        freeV.put("r", String.class);
        Query query = new Query(TupleRoleFilm.class, "getFilmTitre() == ft && getAnneeSortie() == as && getNomActeur() == na && getRoleActeur() == r", freeV);
        FreeVariableBindings freeVB = new FreeVariableBindings();
        freeVB.put("na", nomActeur);
        freeVB.put("ft", filmTitre);
        freeVB.put("as", anneeSortie);
        freeVB.put("r", role);
        
        
        return !(query.select(allRoleFilms.values(), freeVB).isEmpty());
        
//        boolean retour = false; // TEMP
//        stmtRoleFilmExiste.setString(1,nomActeur);
//        stmtRoleFilmExiste.setString(2,filmTitre);
//        stmtRoleFilmExiste.setDate(3,anneeSortie);
//        stmtRoleFilmExiste.setString(4,role);
//        ResultSet rs = stmtRoleFilmExiste.executeQuery();
//        retour = rs.next();
//        rs.close();
    }
    
    public boolean existe(String filmTitre, Date anneeSortie, String role) {
        boolean retour = false; // TEMP
//        stmtRoleFilmExistePourAutreActeur.setString(1,filmTitre);
//        stmtRoleFilmExistePourAutreActeur.setDate(2,anneeSortie);
//        stmtRoleFilmExistePourAutreActeur.setString(3,role);
//        ResultSet rs = stmtRoleFilmExistePourAutreActeur.executeQuery();
//        retour = rs.next();
//        rs.close();
        return retour;
    }

    public void ajouter(String nomActeur, String filmTitre, Date anneeSortie, String role) {
//        stmtAjouteoRleFilm.setString(1,nomActeur);
//        stmtAjouteoRleFilm.setString(2,role);
//        stmtAjouteoRleFilm.setString(3,filmTitre);
//        stmtAjouteoRleFilm.setDate(4,anneeSortie);
//        stmtAjouteoRleFilm.executeUpdate();
    }

    public boolean aDesRoles(String titre, Date anneeSortie) {
        boolean retour = false; // TEMP
//        stmtGetActeurOfFilm.setString(1,titre);
//        stmtGetActeurOfFilm.setDate(2,anneeSortie);
//        ResultSet rs = stmtGetActeurOfFilm.executeQuery();
//        retour = rs.next();
//        rs.close();
        return retour;
    }
    
    public Set<TuplePersonne> getActeurs(String titre, Date anneeSortie) {
        Set<TuplePersonne> listePersonne = null;
//        stmtGetActeurOfFilm.setString(1,titre);
//        stmtGetActeurOfFilm.setDate(2,anneeSortie);
//        ResultSet rs = stmtGetActeurOfFilm.executeQuery();
//        while(rs.next()){
//            listePersonne.add(new TuplePersonne(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getInt(4)));
//        }
//        rs.close();
        return listePersonne;
    }

    public Set<TupleRoleFilm> rolesDeActeur(String nom) {
        Set<TupleRoleFilm> listeRole = null;
//        stmtGetRoleOfActeur.setString(1,nom);
//        ResultSet rs = stmtGetRoleOfActeur.executeQuery();
//        while(rs.next()){
//            listeRole.add(new TupleRoleFilm(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
//        }
//        rs.close();
        return listeRole;
    }
}
