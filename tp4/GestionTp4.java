package tp4;

// Copié du fichier GestionBibliotheque.java fournit sur le site: http://pages.usherbrooke.ca/vducharme/ift287/
/**
  * Ouvre une connexion avec la BD relationnelle et
  * alloue les gestionnaires de transactions et de tables.
  * <pre>
  * 
  * @param bd nom de la bade de données
  *</pre>
  */
public class GestionTp4 {
    public Connexion cx;
    public Film film;
    public Personne personne;
    public RoleFilm roleFilm;
    public Serie serie;
    public Episode episode;
    public RoleEpisode roleEpisode;
    public GestionFilm gestionFilm;
    public GestionPersonne gestionPersonne;
    public GestionSerie gestionSerie;
    
    public GestionTp4(String bd) throws Exception{
        cx = new Connexion(bd);
        film = new Film(cx);
        personne = new Personne(cx);
        roleFilm = new RoleFilm(cx);
        serie = new Serie(cx);
        episode = new Episode(cx);
        roleEpisode = new RoleEpisode(cx);
        gestionFilm = new GestionFilm(film, personne, roleFilm);
        gestionPersonne = new GestionPersonne(personne, film, roleFilm, serie, roleEpisode);
        gestionSerie = new GestionSerie(serie, episode, personne, roleEpisode);
    }
    
    public void fermer()
    {
        // fermeture de la connexion
        cx.fermer();
    }
}
