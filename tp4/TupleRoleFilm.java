package tp4;

public class TupleRoleFilm {

    static private Integer courantId = 0;
    private Integer        Id;
    private TuplePersonne  acteur;
    private String         role;
    private TupleFilm      film;

    TupleRoleFilm(TuplePersonne acteur, TupleFilm film, String role) {
        this.Id = ++courantId;
        this.acteur = acteur;
        this.role = role;
        this.film = film;
    }

    public Integer getId() {
        return this.Id;
    }

    public TupleFilm getFilm() {
        return this.film;
    }

    public void setFilm(TupleFilm film) {
        this.film = film;
    }

    public TuplePersonne getActeur() {
        return this.acteur;
    }

    public void setActeur(TuplePersonne acteur) {
        this.acteur = acteur;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
