package tp4;

import java.util.Date;
import java.util.Set;

class Serie {

    private Connexion cx;
//    private PreparedStatement stmtSerieExiste;
//    private PreparedStatement stmtAjouterSerie;
//    private PreparedStatement stmtSerieDeRealisateur;
//    private PreparedStatement stmtSerieAvecActeur;
    
    public Serie(Connexion cx) {
        this.cx = cx;
        init();
    }

    private void init() {
//        stmtSerieExiste = cx.getConnection().prepareStatement(
//                "SELECT * FROM Serie WHERE titre = ? AND anneeSortie = ?");
//        stmtAjouterSerie = cx.getConnection().prepareStatement(
//                "INSERT INTO Serie (titre, anneeSortie, realisateur, description, nbSaison)" 
//                        + " VALUES (?, ?, ?, ?, ?)");
//        stmtSerieDeRealisateur = cx.getConnection().prepareStatement(
//                "SELECT * FROM Serie WHERE realisateur = ?");
//        stmtSerieAvecActeur = cx.getConnection().prepareStatement(
//                "SELECT * FROM Serie WHERE titre IN" 
//                        + " (SELECT titreSerie FROM RoleEpisode WHERE nomActeur = ?)");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String serieTitre, Date serieDate) {
        boolean retour = false; // TEMP
//        stmtSerieExiste.setString(1,serieTitre);
//        stmtSerieExiste.setDate(2,serieDate);
//        ResultSet rs = stmtSerieExiste.executeQuery();
//        retour = rs.next();
//        rs.close();
        return retour;
    }
    
    // Surcharge de la methode pour garder la possibilité d'ajouter une description
    // pour une serie et le nbSaison
    public void ajouter(String titre, Date dateSortie, String realisateur, 
            String description, int nbSaison) {
//        stmtAjouterSerie.setString(1, titre);
//        stmtAjouterSerie.setDate(2, dateSortie);
//        stmtAjouterSerie.setString(3, realisateur);
//        stmtAjouterSerie.setString(4, description);
//        stmtAjouterSerie.setInt(5, nbSaison);
//        stmtAjouterSerie.executeUpdate();            
    }
    
    // Surcharge de la methode pour respecter les formats d'entree du fichier
    public void ajouter(String titre, Date dateSortie, String realisateur) {
//        stmtAjouterSerie.setString(1, titre);
//        stmtAjouterSerie.setDate(2, dateSortie);
//        stmtAjouterSerie.setString(3, realisateur);
//        stmtAjouterSerie.setString(4, ""); //valeur par defaut
//        stmtAjouterSerie.setInt(5, 1); //valeur par defaut
//        stmtAjouterSerie.executeUpdate();
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
        TupleSerie laSerie = null; // TEMP
        
//        stmtSerieExiste.setString(1, titre);
//        stmtSerieExiste.setDate(2, anneeSortie);
//        
//        ResultSet rs = stmtSerieExiste.executeQuery();
//        rs.next();
        
//        laSerie = new TupleSerie(rs.getString("titre"), rs.getDate("anneeSortie"), rs.getString("realisateur"), 
//                                 rs.getString("description"), rs.getInt("nbSaison"));
//        rs.close();
        return laSerie;
    }

}
