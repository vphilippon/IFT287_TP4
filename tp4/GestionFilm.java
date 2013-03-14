package tp4;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.odi.ObjectStore;
import com.odi.Transaction;

class GestionFilm {
    
    private Film film;
    private Personne personne;
    private RoleFilm roleFilm;


    public GestionFilm(Film film, Personne personne, RoleFilm roleFilm) throws Tp4Exception{
        this.film = film;
        this.roleFilm = roleFilm;
        this.personne = personne;
    }

    public void ajoutFilm(String titre, Date dateSortie, String realisateur) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Vérifie si le film existe déja 
            if (film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible d'ajouter, le film " + titre + " paru le " + dateSortie + " existe deja.");
            }
            // S'assure que le réalisateur existe
            if (!personne.existe(realisateur)){
                throw new Tp4Exception("Impossible d'ajouter, le réalisateur " + realisateur + " n'existe pas.");
            }
            Date realisateurNaissance = personne.getPersonne(realisateur).getDateNaissance();
            // S'assure que le réalisateur est né avant la sortie du film
            if (realisateurNaissance.after(dateSortie)){
                throw new Tp4Exception("Le réalisateur " + realisateur + " est né le: " + realisateurNaissance + 
                        " et ne peut pas avoir réaliser un film créé le: " + dateSortie);
            }  
            // Ajout du film dans la table des films
            TuplePersonne tRealisateur = personne.getPersonne(realisateur);
            TupleFilm tFilm = new TupleFilm(titre, dateSortie, "", 0, tRealisateur);
            film.ajouter(tFilm);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    public void supprimerFilm(String titre, Date dateSortie) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            // Vérifie si le film existe
            if (!film.existe(titre, dateSortie)) {
                throw new Tp4Exception("Impossible de supprimer, le film " + titre + " paru le " + dateSortie + " n'existe pas.");
            }
            //Verifie si un role est relier au film
            if(roleFilm.aDesRoles(titre, dateSortie)){
                throw new Tp4Exception("Impossible de supprimer, le film " + titre + ", un/des role(s) y sont encore rattache.");
            }
            // Supression du film dans la table Film
            TupleFilm t = film.getFilm(titre, dateSortie);
            int nb = film.enlever(t);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
            System.out.println(nb + " film supprimé");
        }
        catch (Exception e)
        {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    public void ajoutDescFilm(String titre, Date anneeSortie, String description, int duree) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try{
            //si le film n'existe pas
            if(!film.existe(titre, anneeSortie)){
                throw new Tp4Exception("Impossible d'ajouter la description, le film " + titre + " paru le " + anneeSortie + " n'existe pas.");
            }
            film.ajouterDescription(film.getFilm(titre, anneeSortie), description, duree);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        }
        catch(Exception e){
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    
    public void ajoutActeurFilm(String titre, Date anneeSortie, String nomActeur, String role) throws Exception {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
            //verifie si l acteur existe
            if (!personne.existe(nomActeur)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur au film, l'acteur " + nomActeur + " n'existe pas.");
            }
            
            //verifie si le film existe
            if (!film.existe(titre, anneeSortie)) {
                throw new Tp4Exception("Impossible d'ajouter l'acteur au film, le film " + titre + " paru le " + anneeSortie + " n'existe pas.");
            }
            
            //verifie que l'acteur est nee avant la sortie du film
            TuplePersonne acteur = personne.getPersonne(nomActeur);
            if(acteur.getDateNaissance().after(anneeSortie)){
                throw new Tp4Exception("Impossible d'ajouter l'acteur au film, l'acteur " + nomActeur + " est nee avant la date de sortie du film.");
            }
            
            //verifie que le role n existe pas deja pour cette acteur
            if (roleFilm.existe(nomActeur, titre, anneeSortie, role)) {
                throw new Tp4Exception("L'acteur " + nomActeur + " joue deja le role " + role + " dans le film " + titre + ".");
            }
            
            //@TODO voir si cette verification devrait etre la ou pas!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //verifie que le role n'existe pas deja pour un autre acteur
            if (roleFilm.existe(titre, anneeSortie, role)) {
                throw new Tp4Exception("Un autre acteur joue deja le role " + role + " dans le film " + titre + ".");
            }

            roleFilm.ajouter(nomActeur, titre, anneeSortie, role);
            tr.commit(ObjectStore.RETAIN_HOLLOW);
        } catch (Exception e) {
            tr.abort(ObjectStore.RETAIN_HOLLOW);
            throw e;
        }
    }
    
    public void afficherActeurDeFilm(String titre, Date anneeSortie) throws Tp4Exception {
        Transaction tr = Transaction.begin(ObjectStore.READONLY);
        if(!film.existe(titre, anneeSortie)){
            throw new Tp4Exception("Le film " + titre + " paru en " + anneeSortie + " n'existe pas.");
        }
        
        Set <TuplePersonne> tuples = roleFilm.getActeurs(titre, anneeSortie);
        StringBuilder output = new StringBuilder();
        Iterator<TuplePersonne> it = tuples.iterator();
        while(it.hasNext()){
            output.append(it.next().getNom()).append(it.hasNext() ?", ":".");
        }
        System.out.println(output.toString());
        tr.commit(ObjectStore.RETAIN_HOLLOW);
    }
}
