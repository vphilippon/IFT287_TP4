package tp4;

import com.odi.util.*;

public class TuplePersonne {
    
    private String nom;
    private OSDate dateNaissance;
    private String lieuNaissance;
    private Integer sexe;
    
    public TuplePersonne(String nom, OSDate dateNaissance, String lieuNaissance, Integer sexe){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.sexe = sexe;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public OSDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(OSDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Integer getSexe() {
        return sexe;
    }

    public void setSexe(Integer sexe) {
        this.sexe = sexe;
    }
}
