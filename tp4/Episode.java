package tp4;

import java.util.Date;

class Episode {

    private Connexion cx;
//    private PreparedStatement stmtEpisodeExiste;
//    private PreparedStatement stmtAjouterEpisode;

    public Episode(Connexion cx) {
        this.cx = cx;
        init();
    }

    private void init() {
//        stmtEpisodeExiste = cx.getConnection().prepareStatement(
//                "SELECT * FROM Episode WHERE titreSerie = ? AND anneeSortieSerie = ?"
//                        + " AND noSaison = ? AND noEpisode = ?");
//        stmtAjouterEpisode = cx.getConnection().prepareStatement(
//                "INSERT INTO Episode (titre, titreSerie, anneeSortieSerie,"
//                        + " noSaison, noEpisode, description, dateDiffusion)"
//                        + " VALUES (?, ?, ?, ?, ?, ?, ?)");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String serieTitre, Date serieDate, int noSaison, int noEpisode) {
        boolean episodeExiste = false;  // TEMP
//        stmtEpisodeExiste.setString(1, serieTitre);
//        stmtEpisodeExiste.setDate(2, serieDate);
//        stmtEpisodeExiste.setInt(3, noSaison);
//        stmtEpisodeExiste.setInt(4, noEpisode);
//        ResultSet rs = stmtEpisodeExiste.executeQuery();
//        episodeExiste = rs.next();
//        rs.close();
        return episodeExiste;
    }

    void ajouter(String titre, String titreSerie, Date anneeSortieSerie,
            int noSaison, int noEpisode, String description, Date dateDiffusion) {
//        stmtAjouterEpisode.setString(1, titre);
//        stmtAjouterEpisode.setString(2, titreSerie);
//        stmtAjouterEpisode.setDate(3, anneeSortieSerie);
//        stmtAjouterEpisode.setInt(4, noSaison);
//        stmtAjouterEpisode.setInt(5, noEpisode);
//        stmtAjouterEpisode.setString(6, description);
//        stmtAjouterEpisode.setDate(7, dateDiffusion);
//        stmtAjouterEpisode.executeUpdate();
    }
}
