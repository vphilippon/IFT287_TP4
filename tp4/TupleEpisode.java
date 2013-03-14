package IFT287_TP4.tp4;

import java.sql.Date;

class TupleEpisode 
{
    static private int superID = 0;
    private int ID;
    private String titreEpisode;
    private String titreSerie;
    private Date dateSortieSerie;
    private int noSaison;
    private int noEpisode;
    private String description;
    private Date dateDiffusion;

    public TupleEpisode(String titreEpisode, String titreSerie, Date dateSortieSerie, int noSaison, int noEpisode, String description, Date dateDiffusion) {
        this.ID = ++superID;
        this.titreEpisode = titreEpisode;
        this.titreSerie = titreSerie;
        this.dateSortieSerie = dateSortieSerie;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.description = description;
        this.dateSortieSerie = dateSortieSerie;
    }
    public int getSuperID(){
        return superID;
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
