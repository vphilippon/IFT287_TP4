package tp4;

import java.util.Date;

public class TupleRoleFilm {
    
    static private int courantId = 0;
    private int Id;
    private TuplePersonne nomActeur;
    private String roleActeur;
    private TupleFilm film;
    
    TupleRoleFilm(TuplePersonne nomActeur, TupleFilm film, String roleActeur) {
        this.Id = ++courantId;
        this.nomActeur = nomActeur;
        this.roleActeur = roleActeur;
        this.film = film;
    }
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
    }
    
    public TupleFilm getFilm() {
        return this.film;
    }

    public void setFilm(TupleFilm film) {
        this.film = film;
    }
    

    public TuplePersonne getNomActeur() {
        return this.nomActeur;
    }
    
    public String getRoleActeur() {
        return this.roleActeur;
    }

    public void setNomActeur(TuplePersonne nomActeur) {
        this.nomActeur = nomActeur;
    }

    public void setRoleActeur(String roleActeur) {
        this.roleActeur = roleActeur;
    }  
}
