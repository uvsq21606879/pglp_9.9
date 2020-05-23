package Command;

import java.sql.SQLException;
import java.util.ArrayList;

import uvsq.pglp_9_9.pglp_9_9.*;

public class CommandRemove implements Command {
	
	 /**
     * La liste des figures à supprimer.
     */
    private ArrayList<Figure> liste;
    /**
     * constructeur de la classe.
     * @param fig liste des figures à supprimer
     */
    public CommandRemove(final ArrayList<Figure> fig) {
        liste = fig;
    }
    /**
     * Execution de la commande.
     * @throws SQLException 
     */
    public void execute() throws SQLException {
        DaoConstruction factory = new DaoConstruction();
        for (Figure forme : liste) {
            if (forme.getClass() == Cercle.class) {
                DAO<Cercle> dao = factory.getDaoCercle();
                dao.Suprimer((Cercle) forme);
            } else if (forme.getClass() == Carre.class) {
                DAO<Carre> dao = factory.getDaoCarre();
                dao.Suprimer((Carre) forme);
            } else if (forme.getClass() == Rectangle.class) {
                DAO<Rectangle> dao = factory.getDaoRectangle();
                dao.Suprimer((Rectangle) forme);
            } else if (forme.getClass() == Triangle.class) {
                DAO<Triangle> dao = factory.getDaoTriangle();
                dao.Suprimer((Triangle) forme);
            } else {
                DAO<GroupeFigures> dao = factory.getDaoGroupeFigures();
                dao.Suprimer((GroupeFigures) forme);
            }
        }
        factory.close();
    }
}
