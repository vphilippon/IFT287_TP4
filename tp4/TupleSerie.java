package tp4;

import com.odi.util.*;

public class TupleSerie {
    
    static private int courantId = 0;
    private int Id;
    private String titre;
    private OSDate anneeSortie;
    private TuplePersonne realisateur;
    private String description;
    private int nbSaison;

    public TupleSerie(String titre, OSDate anneeSortie, TuplePersonne realisateur, 
            String description, int nbSaison) {
        this.Id = ++courantId;
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.realisateur = realisateur;
        this.description = description;
        this.nbSaison = nbSaison;
    }
    
    public int getId(){
        return this.Id;
    }

    public String getTitre() {
        return this.titre;
    }

    public OSDate getAnneeSortie() {
        return this.anneeSortie;
    }

    public TuplePersonne getRealisateur() {
        return this.realisateur;
    }

    public String getDescription() {
        return this.description;
    }

    public int getNbSaison() {
        return this.nbSaison;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnneeSortie(OSDate anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public void setRealisateur(TuplePersonne realisateur) {
        this.realisateur = realisateur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbSaison(int nbSaison) {
        this.nbSaison = nbSaison;
    }
    
    
}
