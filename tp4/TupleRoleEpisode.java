package tp4;

public class TupleRoleEpisode {
    
    static private int courantId = 0;
    private int Id;
    private TuplePersonne nomActeur;
    private String roleActeur;
    private TupleSerie serie;
    private TupleEpisode episode;
    
    public TupleRoleEpisode(TuplePersonne nomActeur, String roleActeur, TupleSerie serie, 
            TupleEpisode episode) {
        this.Id = ++courantId;
        this.nomActeur = nomActeur;
        this.roleActeur = roleActeur;
        this.serie = serie;
        this.episode = episode;
    }

    public static int getCourantId() {
        return courantId;
    }

    public int getId() {
        return Id;
    }

    public TuplePersonne getNomActeur() {
        return nomActeur;
    }

    public void setNomActeur(TuplePersonne nomActeur) {
        this.nomActeur = nomActeur;
    }

    public String getRoleActeur() {
        return roleActeur;
    }

    public void setRoleActeur(String roleActeur) {
        this.roleActeur = roleActeur;
    }

    public TupleSerie getSerie() {
        return serie;
    }

    public void setSerie(TupleSerie serie) {
        this.serie = serie;
    }

    public TupleEpisode getEpisode() {
        return episode;
    }

    public void setEpisode(TupleEpisode episode) {
        this.episode = episode;
    }
    
    
    
    
}
