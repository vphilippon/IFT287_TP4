package tp4;

import java.util.Date;

public class TupleSerie {
    
    static private int courantId = 0;
    private int Id;
    private String titre;
    private Date anneeSortie;
    private String realisateur;
    private String description;
    private int nbSaison;

    public TupleSerie(String titre, Date anneeSortie, String realisateur, 
            String description, int nbSaison) {
        this.Id = ++courantId;
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.realisateur = realisateur;
        this.description = description;
        this.nbSaison = nbSaison;
    }
    
    
    public int getCourantId(){
        return courantId;
    }
    
    public int getId(){
        return this.Id;
    }

    public String getTitre() {
        return this.titre;
    }

    public Date getAnneeSortie() {
        return this.anneeSortie;
    }

    public String getRealisateur() {
        return this.realisateur;
    }

    public String getDescription() {
        return this.description;
    }

    public int getNbSaison() {
        return this.nbSaison;
    }
}
