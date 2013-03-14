package tp4;

import java.util.Date;

public class TupleRoleFilm {
    
    static private int courantId = 0;
    private int Id;
    private TuplePersonne nomActeur;
    private String roleActeur;
    private String filmTitre;
    private Date anneeSortie;
    
    TupleRoleFilm(TuplePersonne nomActeur, String roleActeur, String filmTitre, Date anneeSortie) {
        this.Id = ++courantId;
        this.nomActeur = nomActeur;
        this.roleActeur = roleActeur;
        this.filmTitre = filmTitre;
        this.anneeSortie = anneeSortie;
    }
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
    }
    
    public String getFilmTitre() {
        return this.filmTitre;
    }

    public TuplePersonne getNomActeur() {
        return this.nomActeur;
    }
    
    public String getRoleActeur() {
        return this.roleActeur;
    }
    
    public Date getAnneeSortie() {
        return this.anneeSortie;
    }

    public void setNomActeur(TuplePersonne nomActeur) {
        this.nomActeur = nomActeur;
    }

    public void setRoleActeur(String roleActeur) {
        this.roleActeur = roleActeur;
    }

    public void setFilmTitre(String filmTitre) {
        this.filmTitre = filmTitre;
    }

    public void setAnneeSortie(Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }
    
    
}
