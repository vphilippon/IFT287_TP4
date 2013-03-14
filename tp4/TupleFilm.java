package tp4;

import java.sql.Date;

class TupleFilm 
{
    static private int courantID = 0;
    private int ID;
    private String titre;
    private Date dateSortie;
    private String description;
    private int duree;
    private String realisateur;

    public TupleFilm(String titre, Date dateSortie, String description, int duree, String realisateur) {
        this.ID = ++courantID;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.description = description;
        this.duree = duree;
        this.realisateur = realisateur;
    }
    
    public int getCourantID(){
        return courantID;
    }
    
    public int getID(){
        return this.ID;
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

    public String getRealisateur() {
        return this.realisateur;
    }

    public String getTitre() {
        return this.titre;
    }
}
