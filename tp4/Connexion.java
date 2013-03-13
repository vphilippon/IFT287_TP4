package tp4;

import com.odi.*;

/**
 * Gestionnaire d'une connexion avec une BD relationnelle via JDBC.
 * 
 * <pre>
 * Marc Frappier - 83 427 378
 * Universite de Sherbrooke
 * version 2.0 - 13 novembre 2004
 * ift287 - exploitation de bases de donnees
 * 
 * Modifie par Vincent Ducharme - 30 janvier 2013
 * 
 * Ce programme permet d'ouvrir une connexion avec une BD via JDBC.
 * La methode serveursSupportes() indique les serveurs supportes.
 * 
 * Pre-condition
 *   le driver JDBC approprie doit etre accessible.
 * 
 * Post-condition
 *   la connexion est ouverte en mode autocommit false et serialisable, 
 *   (s'il est supporte par le serveur).
 * </pre>
 */
public class Connexion {

	private Database db;

	private Session session;

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
				db = Database.create(dbName, ObjectStore.ALL_READ
						| ObjectStore.ALL_WRITE);
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
