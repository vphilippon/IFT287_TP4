package tp4;

import com.odi.*;

/**
 * Gestionnaire d'une connexion avec une BD via Object Store.
 * 
 * <pre>
 * Marc Frappier - 83 427 378
 * Universite de Sherbrooke
 * 
 * Modifier par Mathieu Larocque
 * </pre>
 */
public class Connexion {

    private Database db;
    private Session  session;

    /**
     * ouverture d'une connexion
     */
    public Connexion(String dbName) throws Tp4Exception {
        try {
            /* Creation de la session et ajout de ce thread a la session. */
            session = Session.create(null, null);
            session.join();
            /* Ouverture de la BD ou creation si necessaire */
            try {
                db = Database.open(dbName, ObjectStore.UPDATE);
            } catch (DatabaseNotFoundException e) {
                db = Database.create(dbName, ObjectStore.ALL_READ | ObjectStore.ALL_WRITE);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new Tp4Exception("Impossible d'ouvrir la connexion");
        }
    }

    /**
     * retourne la dataBase de la connexion
     */
    public Database getDatabase() {
        return db;
    }

    /**
     * fermeture d'une connexion
     */
    public void fermer() {
        try {
            db.close();
        } catch (Exception e) {
        } finally {
            session.terminate();
        }
    }
}
