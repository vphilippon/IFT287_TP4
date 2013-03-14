package tp4;

import java.util.Date;
import java.util.Set;

class RoleFilm {
    
    private Connexion cx;
//    private PreparedStatement stmtRoleFilmExiste;
//    private PreparedStatement stmtRoleFilmExistePourAutreActeur;
//    private PreparedStatement stmtAjouteoRleFilm;
//    private PreparedStatement stmtGetActeurOfFilm;
//    private PreparedStatement stmtGetRoleOfActeur;

    public RoleFilm(Connexion cx) {
        this.cx = cx;
        init();
    }
    
    private void init() {
//        stmtRoleFilmExiste = cx.getConnection().prepareStatement(
//                "SELECT * FROM RoleFilm WHERE nomActeur = ? AND filmTitre = ?" 
//                        + " AND anneeSortie = ? AND roleActeur = ?");
//        stmtRoleFilmExistePourAutreActeur = cx.getConnection().prepareStatement(
//                "SELECT * FROM RoleFilm WHERE filmTitre = ? AND anneeSortie = ? AND roleActeur = ?");
//        stmtAjouteoRleFilm = cx.getConnection().prepareStatement(
//                "INSERT INTO RoleFilm (nomActeur, roleActeur, filmTitre, anneeSortie) VALUES (?, ?, ?, ?)");
//        stmtGetActeurOfFilm = cx.getConnection().prepareStatement(
//                "SELECT * FROM Personne WHERE nom IN (SELECT nomActeur FROM RoleFilm" 
//                        + " WHERE filmTitre = ? AND anneeSortie = ?)");
//        stmtGetRoleOfActeur = cx.getConnection().prepareStatement(
//                "SELECT * FROM RoleFilm WHERE nomActeur = ?");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String nomActeur, String filmTitre, Date anneeSortie, String role) {
        boolean retour = false; // TEMP
//        stmtRoleFilmExiste.setString(1,nomActeur);
//        stmtRoleFilmExiste.setString(2,filmTitre);
//        stmtRoleFilmExiste.setDate(3,anneeSortie);
//        stmtRoleFilmExiste.setString(4,role);
//        ResultSet rs = stmtRoleFilmExiste.executeQuery();
//        retour = rs.next();
//        rs.close();
        return retour;
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
