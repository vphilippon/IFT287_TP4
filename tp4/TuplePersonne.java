package tp4;
import com.odi.util.*;

public class TuplePersonne {
    
    private String nom;
    private OSDate dateNaissance;
    private String lieuNaissance;
    private int sexe;
    
    public TuplePersonne(String nom, OSDate dateNaissance, String lieuNaissance, int sexe){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.sexe = sexe;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOSDateNaissance(OSDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public String getNom() {
        return nom;
    }
    
    public OSDate getDateNaissance() {
        return dateNaissance;
    }
    
    public String getLieuNaissance() {
        return lieuNaissance;
    }
    
    public int getSexe() {
        return sexe;
    }
}
