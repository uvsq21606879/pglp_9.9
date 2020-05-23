package Command;

import java.io.IOException;
import java.sql.SQLException;

import uvsq.pglp_9_9.pglp_9_9.*;


public class CommandCreate implements Command {
	
	 /**
     * Figure a creer.
     */
    private Figure figure;
    /**
     * constructeur de la classe.
     * @param f Figure à créer
     */
    public CommandCreate(Figure f) {
        figure = f;
    }
    /**
     * execution de la commande.
     * @throws SQLException 
     * @throws IOException 
     */
    public void execute() throws IOException, SQLException {
        Figure f;
        DaoConstruction factory = new DaoConstruction();
        if (figure.getClass() == Cercle.class) {
            DAO<Cercle> dao = factory.getDaoCercle();
            f = dao.create((Cercle) figure);
        } else if (figure.getClass() == Carre.class) {
        	DAO<Carre> dao = factory.getDaoCarre();
            f = dao.create((Carre) figure);
        } else if (figure.getClass() == Rectangle.class) {
        	DAO<Rectangle> dao = factory.getDaoRectangle();
            f = dao.create((Rectangle) figure);
        } else if (figure.getClass() == Triangle.class) {
        	DAO<Triangle> dao = factory.getDaoTriangle();
            f = dao.create((Triangle) figure);
        } else {
        	DAO<GroupeFigures> dao = factory.getDaoGroupeFigures();
        	System.out.println(figure.getFigure());
            f = dao.create((GroupeFigures) figure);
        }
        factory.close();
        if (f != null) {
            System.out.println("Ajout de la Figure " + figure.getFigure() + " réussi. commande (show) pour voir les figures de la BDD");
        } else {
            System.out.println("Une Figure existe déjà à ce nom : " + figure.getFigure());
        }
    }
	
}
