package tp4;

import java.util.Date;

class TupleFilm 
{
    static private int courantId = 0;
    private int Id;
    private String titre;
    private Date dateSortie;
    private String description;
    private int duree;
    private TuplePersonne realisateur;

    public TupleFilm(String titre, Date dateSortie, String description, int duree, TuplePersonne realisateur) {
        this.Id = ++courantId;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.description = description;
        this.duree = duree;
        this.realisateur = realisateur;
    }
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
    }

    public Date getDateSortie() {
        return this.dateSortie;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDuree() {
        return this.duree;
    }

    public TuplePersonne getRealisateur() {
        return this.realisateur;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setRealisateur(TuplePersonne realisateur) {
        this.realisateur = realisateur;
    }
    
    
}
