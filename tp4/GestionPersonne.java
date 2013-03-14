package tp4;

import com.odi.*;
import java.util.*;
import com.odi.util.*;

class GestionPersonne {

    private Personne personne;
    private Film film;
    private Serie serie;
    private RoleFilm roleFilm;
    private RoleEpisode roleEpisode;

    public GestionPersonne(Personne personne, Film film, RoleFilm roleFilm,
            Serie serie, RoleEpisode roleEpisode) throws Tp4Exception {
        this.personne = personne;
        this.film = film;
        this.serie = serie;
        this.roleFilm = roleFilm;
        this.roleEpisode = roleEpisode;
    }

    public void ajoutPersonne(String nom, OSDate dateNaissance,
            String lieuNaissance, int sexe) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Vérifie si la personne existe déjà 
            if (personne.existe(nom)) {
                throw new Tp4Exception("Impossible d'ajouter, la personne "
                        + nom + " existe deja.");
            }
            TuplePersonne pers = new TuplePersonne(nom, dateNaissance, lieuNaissance, sexe);
            personne.ajouter(pers);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void supprimerPersonne(String nom) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Si la personne n'existe pas
            if (!personne.existe(nom)) {
                throw new Tp4Exception("Impossible de supprimer, la personne "
                        + nom + " n'existe pas.");
            }

            // S'il est le realisateur d'au moins un film
            TuplePersonne pers = personne.getPersonne(nom);
            if (!film.filmDeRealisateur(pers).isEmpty()) {
                throw new Tp4Exception("Impossible de supprimer, la personne "
                        + nom + " a realise au moins un film.");
            }
            // S'il est le realisateur d'au moins une serie
            if (!serie.serieDeRealisateur(personne.getPersonne(nom)).isEmpty()) {
                throw new Tp4Exception("Impossible de supprimer, la personne "
                        + nom + " a realise au moins une serie.");
            }
            // S'il a un role dans au moins un film
            if (!roleFilm.rolesDeActeur(personne.getPersonne(nom)).isEmpty()) {
                throw new Tp4Exception("Impossible de supprimer, la personne "
                        + nom + " a un role dans au moins un film.");
            }
            // S'il a au moins un role dans au moins un episode d'au moins une serie
            if (!roleEpisode.rolesDeActeur(pers).isEmpty()) {
                throw new Tp4Exception("Impossible de supprimer, la personne "
                        + nom + " a un role dans au moins une series.");
            }

            int nb = personne.enlever(pers);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
            System.out.println(nb + " personne supprime.");
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void afficherRealisateur() {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        Set<TuplePersonne> listeRealisateur = film.realisateurDeFilms();

        StringBuilder output = new StringBuilder();
        Iterator<TuplePersonne> it = listeRealisateur.iterator();
        while (it.hasNext()) {
            output.append(it.next().getNom()).append(it.hasNext() ? ", " : ".");
        }
        System.out.println("Voici les realisateurs de films : ");
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }

    public void afficherFilmDeActeur(String nom) throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        if (!personne.existe(nom)) {
            throw new Tp4Exception("Impossible d'afficher, l'acteur " + nom
                    + " n'existe pas.");
        }
        Set<TupleRoleFilm> tuples = roleFilm.rolesDeActeur(personne.getPersonne(nom));

        StringBuilder output = new StringBuilder();
        Iterator<TupleRoleFilm> it = tuples.iterator();
        while (it.hasNext()) {
            output.append(it.next().getFilm()).append(
                    it.hasNext() ? ", " : ".");
        }
        System.out.println("L'acteur " + nom + " a participe aux films : ");
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }

    void afficherSerieAvecActeur(String nom) throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        if (!personne.existe(nom)) {
            throw new Tp4Exception("Impossible d'afficher, l'acteur " + nom
                    + " n'existe pas.");
        }

        Set<TupleRoleEpisode> listeRoleEpisode = roleEpisode.serieAvecActeur(personne.getPersonne(nom));

        StringBuilder output = new StringBuilder();
        Iterator<TupleRoleEpisode> it = listeRoleEpisode.iterator();
        while (it.hasNext()) {
            output.append(it.next().getSerie().getTitre()).append(it.hasNext() ? ", " : ".");
        }
        System.out.println("Voici les series de l'acteur : ");
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }
}
