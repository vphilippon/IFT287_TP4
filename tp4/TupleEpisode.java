package tp4;

import com.odi.util.*;

public class TupleEpisode {

    static private Integer courantId = 0;
    private Integer        Id;
    private String         titreEpisode;
    private TupleSerie     serie;
    private Integer        noSaison;
    private Integer        noEpisode;
    private String         description;
    private OSDate         dateDiffusion;

    public TupleEpisode(String titreEpisode, TupleSerie serie, int noSaison,
                        int noEpisode, String description, OSDate dateDiffusion) {
        this.Id = ++courantId;
        this.titreEpisode = titreEpisode;
        this.serie = serie;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.description = description;
        this.dateDiffusion = dateDiffusion;
    }

    public Integer getId() {
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

    public Integer getNoSaison() {
        return noSaison;
    }

    public void setNoSaison(Integer noSaison) {
        this.noSaison = noSaison;
    }

    public Integer getNoEpisode() {
        return noEpisode;
    }

    public void setNoEpisode(Integer noEpisode) {
        this.noEpisode = noEpisode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OSDate getDateDiffusion() {
        return dateDiffusion;
    }

    public void setDateDiffusion(OSDate dateDiffusion) {
        this.dateDiffusion = dateDiffusion;
    }
}
