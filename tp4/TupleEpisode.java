package tp4;

import java.util.Date;

class TupleEpisode 
{
    static private int courantId = 0;
    private int Id;
    private String titreEpisode;
    private TupleSerie serie;
    private int noSaison;
    private int noEpisode;
    private String description;
    private Date dateDiffusion;

    public TupleEpisode(String titreEpisode, TupleSerie serie, int noSaison, int noEpisode, String description, Date dateDiffusion) {
        this.Id = ++courantId;
        this.titreEpisode = titreEpisode;
        this.serie = serie;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.description = description;
        this.dateDiffusion = dateDiffusion;
    }
    
    public int getId(){
        return this.Id;
    }

    public String getTitreEpisode() {
        return titreEpisode;
    }

    public void setTitreEpisode(String titreEpisode) {
        this.titreEpisode = titreEpisode;
    }

    public TupleSerie getSerie() {
        return serie;
    }

    public void setSerie(TupleSerie serie) {
        this.serie = serie;
    }

    public int getNoSaison() {
        return noSaison;
    }

    public void setNoSaison(int noSaison) {
        this.noSaison = noSaison;
    }

    public int getNoEpisode() {
        return noEpisode;
    }

    public void setNoEpisode(int noEpisode) {
        this.noEpisode = noEpisode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDiffusion() {
        return dateDiffusion;
    }

    public void setDateDiffusion(Date dateDiffusion) {
        this.dateDiffusion = dateDiffusion;
    }
    
    
}
