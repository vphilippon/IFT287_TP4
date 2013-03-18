package tp4;

import com.odi.*;
import com.odi.util.*;
import java.util.*;

class GestionFilm {

    private Film     film;
    private Personne personne;
    private RoleFilm roleFilm;

    public GestionFilm(Film film, Personne personne, RoleFilm roleFilm)
            throws Tp4Exception {
        this.film = film;
        this.roleFilm = roleFilm;
        this.personne = personne;
    }

    public void ajoutFilm(String titre, OSDate dateSortie, String realisateur)
            throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Verifie si le film existe deja 
            if (film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible d'ajouter le film " + titre
                        + " paru le " + dateSortie + " : le film existe deja.");
            }
            // S'assure que le realisateur existe
            if (!personne.existe(realisateur)) {
                throw new Tp4Exception("Impossible d'ajouter le film " + titre
                        + " paru le " + dateSortie + " : le realisateur " + realisateur
                        + " n'existe pas.");
            }
            TuplePersonne tRealisateur = personne.getPersonne(realisateur);
            // S'assure que le realisateur est nee avant la sortie du film
            if (tRealisateur.getDateNaissance().getTime() > dateSortie.getTime()) {
                throw new Tp4Exception("Impossible d'ajouter le film " + titre
                        + " paru le " + dateSortie + " : le realisateur " + realisateur
                        + " est nee le : " + tRealisateur.getDateNaissance()
                        + " et ne peut pas avoir realiser un film cree le: " + dateSortie);
            }
            // Ajout du film dans la table des films
            TupleFilm tFilm = new TupleFilm(titre, dateSortie, "", 0, tRealisateur);
            film.ajouter(tFilm);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void supprimerFilm(String titre, OSDate dateSortie) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Verifie si le film existe
            if (!film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible de supprimer le film " + titre
                        + " paru le " + dateSortie + " : le film n'existe pas.");
            }
            //Verifie si un role est relier au film
            if (!roleFilm.acteursDeFilm(film.getFilm(titre, dateSortie)).isEmpty()) {
                throw new Tp4Exception("Impossible de supprimer le film " + titre
                        + " paru le " + dateSortie
                        + " : au moins un role existe pour celui-ci.");
            }
            // Supression du film dans la table Film
            TupleFilm t = film.getFilm(titre, dateSortie);
            int nb = film.enlever(t);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
            System.out.println(nb + " film supprime");
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void ajoutDescFilm(String titre, OSDate dateSortie, String description,
            int duree) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            //si le film n'existe pas
            if (!film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible d'ajouter la description "
                        + "pour le film " + titre + " paru le " + dateSortie
                        + " : le film n'existe pas.");
            }
            film.ajouterDescription(film.getFilm(titre, dateSortie), description, duree);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void ajoutActeurFilm(String titre, OSDate dateSortie, String nomActeur,
            String role) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            //verifie si l'acteur existe
            if (!personne.existe(nomActeur)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur " + nomActeur
                        + " au film " + titre + " paru le " + dateSortie
                        + " : l'acteur n'existe pas.");
            }
            //verifie si le film existe
            if (!film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur " + nomActeur
                        + " au film " + titre + " paru le " + dateSortie
                        + " : le film n'existe pas.");
            }
            //verifie que l'acteur est nee avant la sortie du film
            TuplePersonne acteur = personne.getPersonne(nomActeur);
            if (acteur.getDateNaissance().getTime() > dateSortie.getTime()) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur " + nomActeur
                        + " au film " + titre + " paru le " + dateSortie
                        + " : l'acteur est nee avant la date de sortie du film.");
            }
            //verifie qu'un role n'existe pas deja pour cette acteur
            TupleFilm tFilm = film.getFilm(titre, dateSortie);
            if (roleFilm.existe(tFilm, acteur)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur " + nomActeur
                        + " au film " + titre + " paru le " + dateSortie
                        + " : l'acteur joue deja un role dans ce film.");
            }
            //verifie que le role n'existe pas deja pour un autre acteur
            if (roleFilm.existeRole(tFilm, role)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur " + nomActeur
                        + " au film " + titre + " paru le " + dateSortie
                        + " : un autre acteur joue deja le role + " + role
                        + " dans ce film.");
            }
            roleFilm.ajouter(new TupleRoleFilm(acteur, tFilm, role));
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }

    public void afficherActeurDeFilm(String titre, OSDate dateSortie) throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        if (!film.existe(titre, dateSortie)) {
            throw new Tp4Exception("Impossible d'afficher les acteurs du film " + titre
                    + " paru le " + dateSortie + " : le film n'existe pas.");
        }
        TupleFilm tFilm = film.getFilm(titre, dateSortie);
        Set<TuplePersonne> tuples = roleFilm.acteursDeFilm(tFilm);
        StringBuilder output = new StringBuilder();
        output.append("Voici les acteurs du film " + tFilm + " : ");
        Iterator<TuplePersonne> it = tuples.iterator();
        while (it.hasNext()) {
            output.append(it.next()).append(it.hasNext() ? ", " : ".");
        }
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }
}
