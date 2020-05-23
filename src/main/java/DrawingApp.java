import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import Command.Command;
import Command.DrawingTUI;
import uvsq.pglp_9_9.pglp_9_9.BDD;

public class DrawingApp {
	  /**
     * Commende entree par l'utilisateur
     */
    private Scanner saisie;
    /**
     * Anayseur de command.
     */
    private DrawingTUI drawing;
    /**
     * constructeur de la classe.
     */
    public DrawingApp() {
    	drawing = new DrawingTUI();
        saisie = new Scanner(System.in);
    }
    
    /**
     * Charger une BDD ou créer un nouvelle BDD.
     * @return e si l'utilisateur vaut charger le dessin existant ou n sinon.
     * @throws Exception nom invalide
     */
    @SuppressWarnings("resource")
    public static String ChargementDessin() throws Exception {
    	System.out.print("uvsq21606879. \nBienvenue dans le logiciel de dessin.\n");
        System.out.println("Voulez vous charger un dessin (Existant) dans une BDD ou créer un (Nouveau) ? E/N");
        Scanner in = new Scanner(System.in);
        String nomBdd = "";
        while (!nomBdd.equalsIgnoreCase("e") && !nomBdd.equalsIgnoreCase("n")
                && !nomBdd.equalsIgnoreCase("exit")) {
        	nomBdd = in.nextLine();
            if (nomBdd.equalsIgnoreCase("e")) {
                System.out.println("Entrez un nom du dessin à charger: ");
                nomBdd = in.nextLine();
                File f = new File(nomBdd);
                while (!f.exists() && !nomBdd.equalsIgnoreCase("exit") ) {
                	System.err.println("Aucun dessin n'existe à ce nom, entrez (exit) pour créer une nouvelle BDD(dessin) ");
                	nomBdd = in.nextLine();
                	f = new File(nomBdd);
                }
                if(!nomBdd.equalsIgnoreCase("exit")) 
                {
                BDD.setNomBdd(nomBdd);
                return "e";
                }
            }
        }
        BDD.setNomBdd("BDD" + (int)(Math.random() * 10000));
        return "n";
    }
    
    /**
     * enregistrer un dessin dans la BDD.
     * @param name nom du dessin qui a été modifié
     * @throws SQLException 
     */
    public static void sauvegarder() throws SQLException {
        System.out.println("Voulez vous enregistrer ce dessin dans la BDD ? : Y/N");
        Scanner s = new Scanner(System.in);
        String reponse  = s.nextLine();
        while (!reponse.equals("y") && !reponse.equals("n")) {
            reponse = s.nextLine();
        }
        int n = (int)(Math.random() * 10000);
        if (reponse.equals("y")) {
        	System.out.println("Dessin enregistre sous le nom de : " + BDD.getNomBdd());
        	BDD.setNomBdd("BDD" + n);
        } else {
            BDD.setNomBdd(BDD.getNomBdd() + n);
        }
        System.out.print("Merci à bientôt !.");
        s.close();
    }
    
    /**
     * Execution des commandes.
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    public void run() throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
        drawing.AfficherGuide();
        String cmd = saisie.nextLine();
        Command command;
        while (!cmd.equalsIgnoreCase("exit")) {
            command = drawing.nextCommand(cmd);
            if (command != null) {
                command.execute();
                BDD.CloseConnexion();
            }
            System.out.print(">");
            cmd = saisie.nextLine();
        }
    }
    /**
     * Main du programme.
     * @param args 
     * @throws Exception en cas d'erreur lors de création oude chargement de la BDD.
     */
    public static void main(String[] args) throws Exception {
        try {
            String dessin = ChargementDessin();
            if (dessin.equals("n")) {
            	System.out.println("En attante de création de la BDD ... ... ...\n");
                BDD.Bdd();
                BDD.resetBddTables();
            } else {
            	System.out.println("En attante du chargement de la BDD ... ... ...\n");
            	BDD.Bdd();
            }
            DrawingApp app = new DrawingApp();
            app.run();
            sauvegarder();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
   
}
