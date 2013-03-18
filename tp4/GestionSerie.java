package tp4;

import com.odi.*;
import com.odi.util.*;
import java.util.*;

class GestionSerie {

    private Serie       serie;
    private Episode     episode;
    private Personne    personne;
    private RoleEpisode roleEpisode;

    public GestionSerie(Serie serie, Episode episode, Personne personne,
                        RoleEpisode roleEpisode) throws Tp4Exception {
        this.episode = episode;
        this.personne = personne;
        this.serie = serie;
        this.roleEpisode = roleEpisode;
    }

    public void ajoutSerie(String titre, OSDate dateSortie, String realisateur)
            throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Verifie si la serie existe deja 
            if (serie.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible d'ajouter la serie " + titre
                        + " paru le " + dateSortie + " : la serie existe deja.");
            }
            // S'assure que le realisateur existe
            if (!personne.existe(realisateur)) {
                throw new Tp4Exception("Impossible d'ajouter la serie " + titre
                        + " paru le " + dateSortie + " : le realisateur " + realisateur
                        + " n'existe pas.");
            }
            TuplePersonne tRealisateur = personne.getPersonne(realisateur);
            // S'assure que le realisateur est nee avant la sortie du film
            if (tRealisateur.getDateNaissance().getTime() > dateSortie.getTime()) {
                throw new Tp4Exception("Impossible d'ajouter la serie " + titre
                        + " paru le " + dateSortie + " : le realisateur " + realisateur
                        + " est nee le " + tRealisateur.getDateNaissance()
                        + " et ne peut pas participer a un film cree le: " + dateSortie);
            }
            // Ajout de la serie la table des series
            TupleSerie tSerie = new TupleSerie(titre, dateSortie, tRealisateur, "", 1);
            serie.ajouter(tSerie);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void ajoutEpisode(String titre, String titreSerie, OSDate dateSortieSerie,
            int noSaison, int noEpisode, String description, OSDate dateDiffusion)
            throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Verifie si la serie existe
            if (!serie.existe(titreSerie, dateSortieSerie)) {
                throw new Tp4Exception("Impossible d'ajouter l'episode " + noEpisode
                        + " saison " + noSaison + " de la serie " + titreSerie
                        + " paru le " + dateSortieSerie + " : la serie n'existe pas.");
            }
            //verifie si l'episode existe deja
            TupleSerie tSerie = serie.getSerie(titreSerie, dateSortieSerie);
            if (episode.existe(tSerie, noSaison, noEpisode)) {
                throw new Tp4Exception("Impossible d'ajouter l'episode " + noEpisode
                        + " saison " + noSaison + " de la serie " + titreSerie
                        + " paru le " + dateSortieSerie + " : l'episode existe deja.");
            }
            // verifie si le no saison est valide
            if (noSaison > tSerie.getNbSaison()) {
                throw new Tp4Exception("Impossible d'ajouter l'episode " + noEpisode
                        + " saison " + noSaison + " de la serie " + titreSerie
                        + " paru le " + dateSortieSerie + " : il n'y a pas de saison "
                        + noSaison);
            }
            //verifie que le no episode est valide
            if (noEpisode < 1) {
                throw new Tp4Exception("Impossible d'ajouter l'episode " + noEpisode
                        + " saison " + noSaison + " de la serie " + titreSerie
                        + " paru le " + dateSortieSerie
                        + " : le numero d'episode doit etre plus grand ou egal a 1.");
            }
            // S'assure que l'episode d'avant existe
            if (noEpisode > 1 && !episode.existe(tSerie, noSaison, noEpisode - 1)) {
                throw new Tp4Exception("Impossible d'ajouter l'episode " + noEpisode
                        + " saison " + noSaison + " de la serie " + titreSerie
                        + " paru le " + dateSortieSerie + " : l'episode No "
                        + (noEpisode - 1) + " est manquant.");
            }
            // Ajout de l'episode dans la table des episodes
            TupleEpisode tEpisode = new TupleEpisode(titre, tSerie, noSaison, noEpisode,
                    description, dateDiffusion);
            episode.ajouter(tEpisode);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void ajoutRoleAEpisode(String serieTitre, OSDate serieOSDate, int noSaison,
            int noEpisode, String acteur, String roleActeur) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            //verifie que la serie existe
            if (!serie.existe(serieTitre, serieOSDate)) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : la serie n'existe pas.");
            }
            //verifie que l'episode existe
            TupleSerie tSerie = serie.getSerie(serieTitre, serieOSDate);
            if (!episode.existe(tSerie, noSaison, noEpisode)) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : l'episode n'existe pas.");
            }
            //verifie que l'acteur existe
            if (!personne.existe(acteur)) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : l'acteur n'existe pas.");
            }
            //verifie que l'acteur etait nee
            TuplePersonne tupleActeur = personne.getPersonne(acteur);
            if (tupleActeur.getDateNaissance().getTime() > serieOSDate.getTime()) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : l'acteur est nee le: " + tupleActeur.getDateNaissance()
                        + " et ne peut pas participer a une serie cree le: "
                        + serieOSDate + ".");
            }
            //verifie que l'acteur n'a pas deja un role
            TupleEpisode tEpisode = episode.getEpisode(tSerie, noSaison, noEpisode);
            if (roleEpisode.existe(tEpisode, tupleActeur, roleActeur)) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : l'acteur a deja un role dans l'episode.");
            }
            //verifie qu'un autre acteur n'a pas deja le role
            if (roleEpisode.existeRole(tEpisode, roleActeur)) {
                throw new Tp4Exception("Impossible d'ajouter le role a l'acteur "
                        + acteur + " pour l'episode " + noEpisode + " saison " + noSaison
                        + " de la serie " + serieTitre + " paru le " + serieOSDate
                        + " : un autre acteur a deja le role " + roleActeur + ".");
            }
            TupleRoleEpisode tRole = new TupleRoleEpisode(tupleActeur, roleActeur,
                    tEpisode);
            roleEpisode.ajouter(tRole);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void afficherActeursSerie(String serieTitre, OSDate serieOSDate)
            throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        //Verifie que la serie existe
        if (!serie.existe(serieTitre, serieOSDate)) {
            throw new Tp4Exception("Impossible d'afficher les acteurs de la serie "
                    + serieTitre + " paru le " + serieOSDate
                    + " : la serie n'existe pas.");
        }
        TupleSerie tSerie = serie.getSerie(serieTitre, serieOSDate);
        Set<TuplePersonne> listeActeurs = roleEpisode.acteursDeSerie(tSerie);
        StringBuilder output = new StringBuilder();
        output.append("Voici les acteurs de la serie " + tSerie + " : ");
        Iterator<TuplePersonne> it = listeActeurs.iterator();
        while (it.hasNext()) {
            output.append(it.next()).append(it.hasNext() ? ", " : ".");
        }
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }
}
