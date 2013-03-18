package tp4;

public class TupleRoleEpisode {

    static private Integer courantId = 0;
    private Integer  Id;
    private TuplePersonne  acteur;
    private String         role;
    private TupleEpisode   episode;

    public TupleRoleEpisode(TuplePersonne acteur, String role, TupleEpisode episode) {
        this.Id = ++courantId;
        this.acteur = acteur;
        this.role = role;
        this.episode = episode;
    }

    public Integer getId() {
        return Id;
    }

    public TuplePersonne getActeur() {
        return acteur;
    }

    public void setActeur(TuplePersonne acteur) {
        this.acteur = acteur;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public TupleEpisode getEpisode() {
        return episode;
    }

    public void setEpisode(TupleEpisode episode) {
        this.episode = episode;
    }

    public static void setCourantId(Integer courant) {
        courantId = courant;
    }
}
