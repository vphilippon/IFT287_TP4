package tp4;

import com.odi.util.*;

public class TupleSerie {

    static private Integer courantId = 0;
    private Integer  Id;
    private String         titre;
    private OSDate         dateSortie;
    private TuplePersonne  realisateur;
    private String         description;
    private Integer        nbSaison;

    public TupleSerie(String titre, OSDate dateSortie, TuplePersonne realisateur,
                      String description, Integer nbSaison) {
        this.Id = ++courantId;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.realisateur = realisateur;
        this.description = description;
        this.nbSaison = nbSaison;
    }
    
    public static void setCourantId(Integer courant){
        courantId = courant;
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

    public OSDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(OSDate dateSortie) {
        this.dateSortie = dateSortie;
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

    public String toString() {
        return titre + " paru le " + dateSortie;
    }
}
