package tp4;

import java.util.Date;

class TupleEpisode 
{
    static private int courantId = 0;
    private int Id;
    private String titreEpisode;
    private String titreSerie;
    private Date dateSortieSerie;
    private int noSaison;
    private int noEpisode;
    private String description;
    private Date dateDiffusion;

    public TupleEpisode(String titreEpisode, String titreSerie, Date dateSortieSerie, int noSaison, int noEpisode, String description, Date dateDiffusion) {
        this.Id = ++courantId;
        this.titreEpisode = titreEpisode;
        this.titreSerie = titreSerie;
        this.dateSortieSerie = dateSortieSerie;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.description = description;
        this.dateSortieSerie = dateSortieSerie;
    }
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
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

    public void setTitreEpisode(String titreEpisode) {
        this.titreEpisode = titreEpisode;
    }

    public void setTitreSerie(String titreSerie) {
        this.titreSerie = titreSerie;
    }

    public void setDateSortieSerie(Date dateSortieSerie) {
        this.dateSortieSerie = dateSortieSerie;
    }

    public void setNoSaison(int noSaison) {
        this.noSaison = noSaison;
    }

    public void setNoEpisode(int noEpisode) {
        this.noEpisode = noEpisode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDiffusion(Date dateDiffusion) {
        this.dateDiffusion = dateDiffusion;
    }
    
    
}