package tp4;

import com.odi.*;
import com.odi.util.*;
import com.odi.util.query.*;
import java.util.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Film {

    private Connexion cx;
    private Map<Integer,TupleFilm> allFilms;
    private PreparedStatement stmtFilmExiste;
    private PreparedStatement stmtAjouterFilm;
    private PreparedStatement stmtSuppFilm;
    private PreparedStatement stmtAjoutDescFilm;
    private PreparedStatement stmtGetFilmFrom;

    public Film(Connexion cx) throws SQLException {
        Transaction tr = Transaction.begin(ObjectStore.UPDATE);
        try {
      try {
          allFilms = (Map<Integer,TupleFilm>) cx.getDatabase().getRoot("allFilms");
          }
      catch (DatabaseRootNotFoundException e) {
          /* Creation de la racine */
          cx.getDatabase().createRoot("allFilms", allFilms = new OSHashMap<Integer,TupleFilm>(10));
          }
      /* Fin de la transaction avec retention des objets creux */
      tr.commit(ObjectStore.RETAIN_HOLLOW);
      }
    catch (Exception e)
        {
        tr.abort(ObjectStore.RETAIN_HOLLOW);
        throw e;
        }
    }

    private void init() throws SQLException {
        stmtFilmExiste = cx.getConnection().prepareStatement(
                "SELECT * FROM Film WHERE titre = ? AND dateSortie = ?");
        stmtAjouterFilm = cx.getConnection().prepareStatement(
                "INSERT INTO Film (titre, dateSortie, realisateur) VALUES (?, ?, ?)");
        stmtSuppFilm = cx.getConnection().prepareStatement(
                "INSERT INTO Film (titre, dateSortie, realisateur) VALUES (?, ?, ?)");
        stmtAjoutDescFilm = cx.getConnection().prepareStatement(
                "UPDATE Film SET description=?, duree=? WHERE titre = ? AND dateSortie = ?");
        stmtGetFilmFrom = cx.getConnection().prepareStatement(
                "SELECT * FROM Film WHERE realisateur = ?");
    }

    public Connexion getConnexion() {
        return cx;
    }

    public boolean existe(String titre, Date dateSortie) throws SQLException {
        return ("".equals(allFilms.get(titre)) && (allFilms.get(dateSortie) != null));
    }

    public void ajouter(String titre, Date dateSortie, String realisateur) throws SQLException {
        stmtAjouterFilm.setString(1,titre);
        stmtAjouterFilm.setDate(2,dateSortie);
        stmtAjouterFilm.setString(3,realisateur);
        stmtAjouterFilm.executeUpdate();
    }

    public int enlever(String titre, Date dateSortie) throws SQLException {
        stmtSuppFilm.setString(1,titre);
        stmtSuppFilm.setDate(2,dateSortie);
        return stmtSuppFilm.executeUpdate();
    }

    public TupleFilm getFilm(String titre, Date dateSortie) throws SQLException {
        stmtFilmExiste.setString(1,titre);
        stmtFilmExiste.setDate(2,dateSortie);
        ResultSet rs = stmtFilmExiste.executeQuery();
        rs.next();
        TupleFilm t = new TupleFilm(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getInt(4),rs.getString(5)); 
        rs.close();
        return t;
    }

    public void ajouterDescription(String titre, Date anneeSortie, String description, int duree) throws SQLException {
        stmtAjoutDescFilm.setString(1,description);
        stmtAjoutDescFilm.setInt(2,duree);
        stmtAjoutDescFilm.setString(3,titre);
        stmtAjoutDescFilm.setDate(4,anneeSortie);
        stmtAjoutDescFilm.executeUpdate();
    }

    public List<TupleFilm> filmDeRealisateur(String nom) throws SQLException {
        List<TupleFilm> listeFilms = new ArrayList<TupleFilm>();
        stmtGetFilmFrom.setString(1,nom);
        ResultSet rs = stmtGetFilmFrom.executeQuery();
        while(rs.next()){
            listeFilms.add(new TupleFilm(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getInt(4), rs.getString(5)));
        }
        rs.close();
        return listeFilms;
    }
}
