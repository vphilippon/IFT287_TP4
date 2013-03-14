package tp4;

import com.odi.util.*;

public class TupleSerie {
    
    static private Integer courantId = 0;
    private Integer Id;
    private String titre;
    private OSDate anneeSortie;
    private TuplePersonne realisateur;
    private String description;
    private Integer nbSaison;

    public TupleSerie(String titre, OSDate anneeSortie, TuplePersonne realisateur, 
            String description, Integer nbSaison) {
        this.Id = ++courantId;
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.realisateur = realisateur;
        this.description = description;
        this.nbSaison = nbSaison;
    }

    public Integer getId() {
        return Id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public OSDate getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(OSDate anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public TuplePersonne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(TuplePersonne realisateur) {
        this.realisateur = realisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbSaison() {
        return nbSaison;
    }

    public void setNbSaison(Integer nbSaison) {
        this.nbSaison = nbSaison;
    }
}
