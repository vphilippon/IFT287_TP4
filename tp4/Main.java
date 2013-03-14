package tp4;

/*
 * Projet : Tp4
 *
 * Membres :
 * - Guillaume Harvey 12 059 595
 * - Kevin Labrie 12 113 777
 * - Vincent Philippon 12 098 838
 * - Mathieu Larocque 10 129 032
 * 
 */

import java.io.*;
import java.text.ParseException;
import java.util.StringTokenizer;

public class Main {

    private static GestionTp4 gestionTp4;

    public static void main(String args[]) throws Exception {
        if (args.length < 1) {
                System.out.println("Usage: java <bd>.odb [<fichier-transactions>]");
                return;
        }
        
        try {
            InputStream sourceTransaction = new FileInputStream(args[1]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(sourceTransaction));
            gestionTp4 = new GestionTp4(args[0]);
            traiterTransactions(reader);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            gestionTp4.fermer();
        }
    }
    
        
    /**
     * Decodage et traitement d'une transaction :
     * @throws Exception 
     */
    static void executerTransaction(StringTokenizer tokenizer) throws Exception {
        try {
            String command = tokenizer.nextToken();
            if ("ajoutPersonne".startsWith(command)){
                gestionTp4.gestionPersonne.ajoutPersonne(readString(tokenizer) /* nom */,
                        readDate(tokenizer) /* dateNaissance */,
                        readString(tokenizer) /* lieuNaissance */,
                        readInt(tokenizer) /* sexe */);

            }else if ("supPersonne".startsWith(command)){
                gestionTp4.gestionPersonne.supprimerPersonne(readString(tokenizer) /* nom */);

            }else if ("ajoutFilm".startsWith(command)){
                gestionTp4.gestionFilm.ajoutFilm(readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */,
                        readString(tokenizer) /* realisateur */);

            }else if ("supFilm".startsWith(command)){
                gestionTp4.gestionFilm.supprimerFilm(readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */);

            }else if ("ajoutDescFilm".startsWith(command)){
                gestionTp4.gestionFilm.ajoutDescFilm(readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */,
                        readString(tokenizer) /* description */,
                        readInt(tokenizer) /* duree */);

            }else if ("ajoutActeurFilm".startsWith(command)){
                gestionTp4.gestionFilm.ajoutActeurFilm(
                        readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */,
                        readString(tokenizer) /* nom */,
                        readString(tokenizer) /* role */);

            }else if ("ajoutSerie".startsWith(command)){
                gestionTp4.gestionSerie.ajoutSerie(
                        readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */,
                        readString(tokenizer) /* nom realisateur */);

            }else if ("ajoutEpisode".startsWith(command)){
                gestionTp4.gestionSerie.ajoutEpisode(
                        readString(tokenizer) /* titre episode */,
                        readString(tokenizer) /* titre serie */,
                        readDate(tokenizer) /* annee serie */,
                        readInt(tokenizer) /* no saison */,
                        readInt(tokenizer) /* no episode */,
                        readString(tokenizer) /* description */,
                        readDate(tokenizer) /* date episode */);

            }else if ("ajoutActeurEpisode".startsWith(command)){
                gestionTp4.gestionSerie.ajoutRoleAEpisode(
                        readString(tokenizer) /* titre serie */,
                        readDate(tokenizer) /* annee serie */,
                        readInt(tokenizer) /* no saison */,
                        readInt(tokenizer) /* no episode */,
                        readString(tokenizer) /* nom acteur */,
                        readString(tokenizer) /* Role de l'acteur */);

            }else if ("listeActeursSerie".startsWith(command)){
                gestionTp4.gestionSerie.afficherActeursSerie(
                        readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */);

            }else if ("listeSerieActeur".startsWith(command)){
                gestionTp4.gestionPersonne.afficherSerieAvecActeur(
                        readString(tokenizer) /* nom */);

            }else if ("listeRealisateurs".startsWith(command)){
                gestionTp4.gestionPersonne.afficherRealisateur();

            }else if ("listeActeursFilm".startsWith(command)){
                gestionTp4.gestionFilm.afficherActeurDeFilm(
                        readString(tokenizer) /* titre */,
                        readDate(tokenizer) /* annee */);

            }else if ("listeFilmsActeur".startsWith(command)){
                gestionTp4.gestionPersonne.afficherFilmDeActeur(
                        readString(tokenizer) /* nom */);

            }else{
                System.out.println(" : Transaction non reconnue");
            }
            
        } catch (Tp4Exception e) {
            System.out.println("** " + e.toString());
        }
    }

    /** Les methodes suivantes n'ont pas besoin d'etre modifiees */

    public static BufferedReader ouvrirFichier(String[] args)
            throws FileNotFoundException {
        if (args.length < 4) {
            // lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        } else {
            // lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[3])));
        }
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
    
    static void traiterTransactions(BufferedReader reader) throws Exception {
            //afficherAide();
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction)) {
                    /* découpage de la transaction en mots */
                    StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
                    if (tokenizer.hasMoreTokens()) {
                            executerTransaction(tokenizer);
                    }
                    transaction = lireTransaction(reader);
            }
    }
    
    /**
    * Vérifie si la fin du traitement des transactions est atteinte.
    */
    static boolean finTransaction(String transaction) {
        /* fin de fichier atteinte */
        if (transaction == null) {
             return true;
        }

        StringTokenizer tokenizer = new StringTokenizer(transaction, " ");

        /* ligne ne contenant que des espaces */
        if (!tokenizer.hasMoreTokens()) {
             return false;
        }
        
        /* commande "exit" */
        String commande = tokenizer.nextToken();
        if (commande.equals("exit")) {
             return true;
        } else {
             return false;
        }
    }
    
    /**
    * lecture d'une date en format YYYY-MM-DD
    */
    static String readDate(StringTokenizer tokenizer) throws Tp4Exception {
        if (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            try {
                FormatDate.convertirDate(token);
                return token;
            } catch (ParseException e) {
                throw new Tp4Exception(
                            "Date en format YYYY-MM-DD attendue à la place  de \""
                            + token + "\"");
            }
        } else {
            throw new Tp4Exception("autre paramètre attendu");
        }
    }
    
    /** lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception {
        if (tokenizer.hasMoreElements()) {
            return tokenizer.nextToken();
        } else {
            throw new Exception("autre parametre attendu");
        }
    }

    /**
     * lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception {
        if (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            try {
                return Integer.valueOf(token).intValue();
            } catch (NumberFormatException e) {
                throw new Exception("Nombre attendu a la place de \"" + token
                        + "\"");
            }
        } else {
            throw new Exception("autre parametre attendu");
        }
    }
/* Not sure if needed
    static Date readDate(StringTokenizer tokenizer) throws Exception {
        if (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();

            try {
                // TODO VALIDER SI OK APRES CORRECTION
                Date dt = new Date(FormatDate.convertirDate(token).getTime());
                return dt;
            } catch (ParseException e) {
                throw new Tp4Exception("Date en format YYYY-MM-DD attendue à la place  de \"" +
                  token + "\"");
            }
            
        } else {
            throw new Exception("autre parametre attendu");
        }
    }
*/
    public static boolean isStringNotEmpty(String s) {
        return (s != null && s.length() > 0);
    }

}