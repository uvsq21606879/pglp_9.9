package Command;

import java.io.IOException;
import java.sql.SQLException;

import uvsq.pglp_9_9.pglp_9_9.*;


public class CommandMove implements Command{
	/**
     * La figure à deplacer.
     */
    private Figure figure;
    /**
     * Le vecteur de deplacement.
     */
    private Point deplacement;
    /**
     * constructeur de la classe.
     * @param fig la figure à déplacer
     * @param Vdeplacement le vecteur de déplacement de la figure
     */
    public CommandMove(Figure fig,Point Vdeplacement) {
    	figure = fig;
    	this.deplacement = Vdeplacement;
    }
    /**
     * execution de la commande.
     * @throws SQLException 
     * @throws IOException 
     */
    public void execute() throws SQLException, IOException {
        figure.move(deplacement.getX(), deplacement.getY());
        DaoConstruction factory = new DaoConstruction();
        if (figure.getClass() == Cercle.class) {
            DAO<Cercle> dao = factory.getDaoCercle();
            dao.MiseAjour((Cercle) figure);
        } else if (figure.getClass() == Carre.class) {
            DAO<Carre> dao = factory.getDaoCarre();
            dao.MiseAjour((Carre) figure);
        } else if (figure.getClass() == Rectangle.class) {
            DAO<Rectangle> dao = factory.getDaoRectangle();
            dao.MiseAjour((Rectangle) figure);
        } else if (figure.getClass() == Triangle.class) {
            DAO<Triangle> dao = factory.getDaoTriangle();
            dao.MiseAjour((Triangle) figure);
        } else {
            DAO<GroupeFigures> dao = factory.getDaoGroupeFigures();
            dao.MiseAjour((GroupeFigures) figure);
        }
        factory.close();
    }
}
