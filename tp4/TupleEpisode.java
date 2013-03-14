package tp4;

import java.util.Date;

class TupleEpisode 
{
    static private int courantID = 0;
    private int ID;
    private String titreEpisode;
    private String titreSerie;
    private Date dateSortieSerie;
    private int noSaison;
    private int noEpisode;
    private String description;
    private Date dateDiffusion;

    public TupleEpisode(String titreEpisode, String titreSerie, Date dateSortieSerie, int noSaison, int noEpisode, String description, Date dateDiffusion) {
        this.ID = ++courantID;
        this.titreEpisode = titreEpisode;
        this.titreSerie = titreSerie;
        this.dateSortieSerie = dateSortieSerie;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.description = description;
        this.dateSortieSerie = dateSortieSerie;
    }
    public int getCourantID(){
        return courantID;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getTitreEpisode() {
        return this.titreEpisode;
    }

    public String getTitreSerie() {
        return this.titreSerie;
    }

    public Date getDateSortieSerie() {
        return this.dateSortieSerie;
    }

    public int getNoSaison() {
        return this.noSaison;
    }

    public int getNoEpisode() {
        return this.noEpisode;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDateDiffusion() {
        return this.dateDiffusion;
    }
}
