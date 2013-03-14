package tp4;

import com.odi.util.*;

class TupleFilm 
{
    static private Integer courantId = 0;
    private Integer Id;
    private String titre;
    private OSDate dateSortie;
    private String description;
    private Integer duree;
    private TuplePersonne realisateur;

    public TupleFilm(String titre, OSDate dateSortie, String description, int duree, TuplePersonne realisateur) {
        this.Id = ++courantId;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.description = description;
        this.duree = duree;
        this.realisateur = realisateur;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public TuplePersonne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(TuplePersonne realisateur) {
        this.realisateur = realisateur;
    }

    public Integer getId() {
        return Id;
    } 
}
