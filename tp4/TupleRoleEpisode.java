package tp4;

import java.util.Date;

public class TupleRoleEpisode {
    
    static private int courantId = 0;
    private int Id;
    private String nomActeur;
    private String roleActeur;
    private String titreSerie;
    private String titreEpisode;
    private int noSaison;
    private int noEpisode;
    private Date anneeSortieSerie;
    
    public TupleRoleEpisode(String nomActeur, String roleActeur, String titreSerie, 
            String titreEpisode, int noSaison, int noEpisode, Date anneeSortieSerie) {
        this.Id = ++courantId;
        this.nomActeur = nomActeur;
        this.roleActeur = roleActeur;
        this.titreSerie = titreSerie;
        this.titreEpisode = titreEpisode;
        this.noSaison = noSaison;
        this.noEpisode = noEpisode;
        this.anneeSortieSerie = anneeSortieSerie;
    }
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
    }
    
    public String getNomActeur() {
        return this.nomActeur;
    }

    public String getRoleActeur() {
        return this.roleActeur;
    }

    public String getTitreSerie() {
        return this.titreSerie;
    }

    public String getTitreEpisode() {
        return this.titreEpisode;
    }

    public int getNoSaison() {
        return this.noSaison;
    }

    public int getNoEpisode() {
        return this.noEpisode;
    }

    public Date getAnneeSortieSerie() {
        return this.anneeSortieSerie;
    }
}
