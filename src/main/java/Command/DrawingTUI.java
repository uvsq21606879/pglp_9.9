package Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import uvsq.pglp_9_9.pglp_9_9.*;
import uvsq.pglp_9_9.pglp_9_9.DAO.Arg;


public class DrawingTUI {

	/**
	 * La commande de creation d'un cercle.
	 * @param la figure.
	 * @param req description du cercle
	 * @return le cercle ou null en cas d'erreurs
	 */
	private Figure createCercle(String figure, String[] req) {
		String[] traitement;
		if(req[Arg.UN.get()].contains("Cercle")) 
			traitement = req[Arg.UN.get()].split("Cercle");
		else 
			traitement = req[Arg.UN.get()].split("cercle");
		
		if (!traitement[Arg.ZERO.get()].equals("")
				|| !(traitement[Arg.UN.get()].startsWith("(") && traitement[Arg.UN.get()].endsWith(")"))) {
			System.err.println("Commande non valide, parenthèses manquantes");
		} else {
			traitement[Arg.UN.get()] = traitement[Arg.UN.get()].substring(Arg.UN.get(), traitement[Arg.UN.get()].length() - Arg.UN.get());
			traitement = traitement[Arg.UN.get()].split(",");
			if (traitement.length != Arg.TROIS.get()) {
				System.err.println("Commande non valide, " + traitement.length + "/" + Arg.TROIS.get() + " arguments");
			} else {
				Point centre;
				int rayon;
				try {
					centre = new Point(traitement[Arg.ZERO.get()] + "," + traitement[Arg.UN.get()]);
					rayon = Integer.parseInt(traitement[Arg.DEUX.get()]);
					return new Cercle(figure, centre, rayon);
				} catch (Exception e) {
					System.err.println("Commande non valide");
				}
			}
		}
		return null;
	}

	/**
	 * la commande de creation d'un Carre.
	 * @param la figure.
	 * @param req description du Carre
	 * @return le Carre ou null en cas d'erreurs
	 */
	private Figure createCarre(String figure, String[] req) {
		String[] traitement;
		if(req[Arg.UN.get()].contains("Carre")) 
			traitement = req[Arg.UN.get()].split("Carre");
		else 
			traitement = req[Arg.UN.get()].split("carre");
		
		if (!traitement[Arg.ZERO.get()].equals("")
				|| !(traitement[Arg.UN.get()].startsWith("(") && traitement[Arg.UN.get()].endsWith(")"))) {
			System.err.println("Commande non valide, parenthèses manquantes");
		} else {
			traitement[Arg.UN.get()] = traitement[Arg.UN.get()].substring(Arg.UN.get(), traitement[Arg.UN.get()].length() - Arg.UN.get());
			traitement = traitement[Arg.UN.get()].split(",");
		Point HautGauche;
		int longueur;
		try {
			HautGauche = new Point(traitement[Arg.ZERO.get()] + "," + traitement[Arg.UN.get()]);
			longueur = Integer.parseInt(traitement[Arg.DEUX.get()]);
			return new Carre(figure, HautGauche, longueur);
		} catch (Exception e) {
			System.err.println("Commande non valide, impossible de créer la forme");
		}
		}

		return null;
	}

	/**
	 * la commande de creation d'un Rectangle.
	 * @param la figure.
	 * @param req description du Rectangle
	 * @return le Rectangle ou null en cas d'erreurs
	 */
	private Figure createRectangle(String figure, String[] req) {
		String[] traitement;
		if(req[Arg.UN.get()].contains("Rectangle")) 
			traitement = req[Arg.UN.get()].split("Rectangle");
		else 
			traitement = req[Arg.UN.get()].split("rectangle");
		if (!traitement[Arg.ZERO.get()].equals("")
				|| !(traitement[Arg.UN.get()].startsWith("(") && traitement[Arg.UN.get()].endsWith(")"))) {
			System.err.println("Commande non valide, parenthèses manquantes");
		} else {
			traitement[Arg.UN.get()] = traitement[Arg.UN.get()].substring(Arg.UN.get(), traitement[Arg.UN.get()].length() - Arg.UN.get());
			traitement = traitement[Arg.UN.get()].split(",");
			if (traitement.length != Arg.QUATRE.get()) {
				System.err.println("Commande non valide, "
						+ traitement.length + "/" + Arg.QUATRE.get() + " arguments");
			} else {
				Point HautGauche;
				int longueur;
				int largeur;
				try {
					HautGauche = new Point(traitement[Arg.ZERO.get()] + "," + traitement[Arg.UN.get()]);
					longueur = Integer.parseInt(traitement[Arg.DEUX.get()]);
					largeur = Integer.parseInt(traitement[Arg.TROIS.get()]);
					return new Rectangle(
							figure, HautGauche, longueur, largeur);
				} catch (Exception e) {
					System.err.println("Commande non reconnue");
				}
			}
		}
		return null;
	}
	
	/**
	 * la commande de creation d'un Triangle.
	 * @param la figure.
	 * @param req description du Triangle
	 * @return le Triangle ou null en cas d'erreurs
	 */
    private Figure createTriangle(String figure, String[] req) {
    	String[] traitement;
		if(req[Arg.UN.get()].contains("Triangle")) 
			traitement = req[Arg.UN.get()].split("Triangle");
		else 
			traitement = req[Arg.UN.get()].split("Triangle");
		
        if (!traitement[Arg.ZERO.get()].equals("")
                || !(traitement[Arg.UN.get()].startsWith("(") && traitement[Arg.UN.get()].endsWith(")"))) {
            System.err.println("Commande non valide, Veuillez vérifier les parenthéses");
        } else {
            traitement[Arg.UN.get()] = traitement[Arg.UN.get()].substring(Arg.UN.get(), traitement[Arg.UN.get()].length() - Arg.UN.get());
            traitement = traitement[Arg.UN.get()].split(",");
            if (traitement.length != Arg.SIX.get()) {
                System.err.println("Commande non valide, " + traitement.length + "/" + Arg.SIX.get() + " arguments");
            }
            Point point1,point2,point3 ;
            try {
                point1 = new Point(traitement[Arg.ZERO.get()] + "," + traitement[Arg.UN.get()]);
                point2 = new Point(traitement[Arg.DEUX.get()] + "," + traitement[Arg.TROIS.get()]);
                point3 = new Point(traitement[Arg.QUATRE.get()] + "," + traitement[Arg.CINQ.get()]);
                return new Triangle(figure, point1, point2, point3);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Commande non reconnue");
            }
        }
        return null;
    }
    /**
	 * la commande de creation d'un Groupe.
	 * @param la groupe de figure.
	 * @param req description du groupe
     * @return le groupe ou null en cas d'erreurs
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    private Figure createGroupe(String figure, String[] req) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
    	String[] traitement;
		if(req[Arg.UN.get()].contains("Groupe")) 
			traitement = req[Arg.UN.get()].split("Groupe");
		else 
			traitement = req[Arg.UN.get()].split("groupe");
        if (!traitement[Arg.ZERO.get()].equals("")
                || !(traitement[Arg.UN.get()].startsWith("(") && traitement[Arg.UN.get()].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            traitement[Arg.UN.get()] = traitement[Arg.UN.get()].substring(Arg.UN.get(), traitement[Arg.UN.get()].length() - Arg.UN.get());
            traitement = traitement[Arg.UN.get()].split(",");
            return createGroupeComposants(figure, traitement);
        }
        return null;
    }
    
    /**
     *la commande de creation d'un groupe.
     * @param figure nom du groupe
     * @param composants Composants du groupe
     * @return le groupe sinon null en cas d'erreurs
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    private Figure createGroupeComposants(String figure, String[] composants) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
            GroupeFigures Grpfig = new GroupeFigures(figure);
            for (String s : composants) {
                Figure f = this.getFigure(s);
                if (f != null) {
                	Grpfig.Ajouter(f);
                } else {
                    return null;
                }
            }
            return Grpfig;
    }
    
    /**
     * obtenir une figure.
     * @param la figure a chercher
     * @return la figure si elle est trouvee sinn null
     * @throws SQLException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    private Figure getFigure (final String figure) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
        DaoConstruction construction = new DaoConstruction();
        DAO<Cercle> daoCercle = construction.getDaoCercle();
        DAO<Carre> daoCarre = construction.getDaoCarre();
        DAO<Rectangle> daoRetangle = construction.getDaoRectangle();
        DAO<Triangle> daoTriangle = construction.getDaoTriangle();
        DAO<GroupeFigures> daoGroupeForme = construction.getDaoGroupeFigures();
        Figure f = daoCercle.Search(figure);
        if (f == null) {
            f = daoCarre.Search(figure);
        }
        if (f == null) {
            f = daoRetangle.Search(figure);
        }
        if (f == null) {
            f = daoTriangle.Search(figure);
        }
        if (f == null) {
            f = daoGroupeForme.Search(figure);
        }
        if (f == null) {
            System.err.println("Aucune Figure n'existe à ce nom : "
                    + figure);
        }
        construction.close();
        return f;
    }
    
    /**
     * la commande de suppression de figure.
     * @param figure commande de suppression
     * @return la commande de suppression
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    private Command remove(String figure) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
        String cmd = figure.replace(" ", "");
        String[] split = cmd.split("remove");
        if (!split[Arg.ZERO.get()].equals("")
                || !(split[Arg.UN.get()].startsWith("(") && split[Arg.UN.get()].endsWith(")"))) {
            System.err.println("Commande remove non valide, parenthèses manquantes");
        } else {
            split[Arg.UN.get()] = split[Arg.UN.get()].substring(Arg.UN.get(), split[Arg.UN.get()].length() - Arg.UN.get());
            split = split[Arg.UN.get()].split(",");
            ArrayList<Figure> liste = new ArrayList<Figure>();
            for (String var : split) {
                Figure f = this.getFigure(var);
                if (f != null) {
                    liste.add(f);
                } else {
                    System.err.println("Commande non reconnue");
                    return null;
                }
            }
            return new CommandRemove(liste);
        }
        return null;
    }
    
    /**
     * Commande de creation d'une figure.
     * @param figure la commande
     * @return la figure cree
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    private Figure create(String figure) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
        String[] split;
        split = figure.split("=");
        split[Arg.ZERO.get()] = split[Arg.ZERO.get()].trim();
        String variableName = split[Arg.ZERO.get()];
        if (split[Arg.ZERO.get()].contains(" ")) {
            System.out.println("Le nom de la variable contient des espaces");
        } else {
            split[Arg.UN.get()] = split[Arg.UN.get()].replace(" ", "");
            Figure fig = null;
            if (split[Arg.UN.get()].contains("Cercle") || split[Arg.UN.get()].contains("cercle")) {
                fig = this.createCercle(variableName, split);
            } else if (split[Arg.UN.get()].contains("Carre") || split[Arg.UN.get()].contains("carre")) {
                fig = this.createCarre(variableName, split);
            } else if (split[Arg.UN.get()].contains("Rectangle") || split[Arg.UN.get()].contains("rectangle")) {
                fig = this.createRectangle(variableName, split);
            } else if (split[Arg.UN.get()].contains("Triangle") || split[Arg.UN.get()].contains("triangle")) {
                fig = this.createTriangle(variableName, split);
            } else if (split[Arg.UN.get()].contains("Groupe") || split[Arg.UN.get()].contains("groupe")) {
                fig = this.createGroupe(variableName, split);
            }
            return fig;
        }
        return null;
    }
    /**
     * la commande de deplacement d'une figure.
     * @param command commande move
     * @return la commande de move
     */
    private Command move(String command) {
        String cmd = command.replace(" ", "");
        String[] split = cmd.split("move");
        if (!split[Arg.ZERO.get()].equals("")
                || !(split[Arg.UN.get()].startsWith("(") && split[Arg.UN.get()].endsWith(")"))) {
            System.err.println("Commande non valide, parenthèses manquantes");}
            	else {
            split[Arg.UN.get()] = split[Arg.UN.get()].substring(Arg.UN.get(), split[Arg.UN.get()].length() - Arg.UN.get());
            split = split[Arg.UN.get()].split(",");
            if (split.length != Arg.TROIS.get()) {
                System.err.println("Commande non valide, " + split.length + "/" + Arg.TROIS.get() + " arguments");
            } else {
                String figure;
                Point deplacement;
                try {
                    figure = split[Arg.ZERO.get()];
                    deplacement = new Point(split[Arg.UN.get()] + "," + split[Arg.DEUX.get()]);
                    Figure f = this.getFigure(figure);
                    if (f != null) {
                        return new CommandMove(f, deplacement);
                    }
                } catch (Exception e) {
                    System.err.println("Commande non valide");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private Command  Show() {
    	return new CommandShow(this);
    }
    /**
     * Analyseur de commande.
     * @param command commande a analyser.
     * @return la commande a executer ou null si elle pas interpretee.
     * @throws IOException 
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws FileNotFoundException 
     */
    public Command nextCommand(final String command) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
    	
    	if (command.contains("=")) {
            Figure f = this.create(command);
            if (f != null) {
                return new CommandCreate(f);
            }
        } else if (command.contains("remove")) {
            return this.remove(command);
        } else if (command.contains("move")) {
            return this.move(command);
        } else if (command.contains("show")) {
            return this.Show();
        } else if (!command.equalsIgnoreCase("exit")) {
            System.err.println("Commande non reconnu");
        }
        return null;
    }
    
	/**
	 * Afficher le guide d'utilisation du logiciel de dessin
	 */
	
	public void AfficherGuide() {
		
		System.out.println("\t \t Guide d'utilisation : \n"
                + "-Créer un cercle :                  "
                + "         NomForme = Ce"
                + "rcle((x,y),rayon)\n"
                + "-Créer un carré :                   "
                + "         NomForme = Ca"
                + "rre((x,y),longueur)\n"
                + "-Créer un rectangle :               "
                + "         NomForme = Re"
                + "ctangle((x,y), longueur, largeur)\n"
                + "-Créer un triangle :                "
                + "         NomForme = Tr"
                + "iangle((x,y),(x,y),(x,y))\n"
                + "-Créer un groupe de formes :        "
                + "         NomForme = Gr"
                + "oupe(NomForme, ...)\n"
                + "-Déplacer une forme :               "
                + "         move(NomForme"
                + ",(x,y))\n"
                + "-Déplacer un groupe de formes :     "
                + "         move(NomGroupe"
                + ",(x,y))\n"
                + "-Supprimer une forme :              "
                + "         remove(NomForme, ...)\n"
                + "-Supprimer un groupe :              "
                + "         remove(NomGroupe"
                + ", ...)\n"
                + "-Afficher les groupes et les formes de la BDD : "
                + "  show\n"
                + "-Quitter l'application :             "
                + "             exit\n");
		System.out.print(">");
		
	}
	
}
