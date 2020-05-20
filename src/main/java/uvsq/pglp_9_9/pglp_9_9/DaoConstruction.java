package uvsq.pglp_9_9.pglp_9_9;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConstruction {
	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 */
    public DaoConstruction() {
        connection = BDD.Connect();
    }
    /**
     * Fermer la connection.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retourner le DAO Cercle.
     * @return le dao Cercle
     * @throws SQLException 
     */
    public DAO<Cercle> getDaoCercle() throws SQLException {
        return new DaoCercle(connection);
    }
    /**
     * Retourner le DAO Carre.
     * @return le dao Carre.
     * @throws SQLException.
     */
    public DAO<Carre> getDaoCarre() throws SQLException {
        return new DaoCarre(connection);
    }
    /**
     * Retourner le DAO Rectangle.
     * @return le dao Rectangle.
     * @throws SQLException. 
     */
    public DAO<Rectangle> getDaoRectangle() throws SQLException {
        return new DaoRectangle(connection);
    }
    /**
     * Retourner le dao Triangle.
     * @return le dao Triangle
     * @throws SQLException 
     */
    public DAO<Triangle> getDaoTriangle() throws SQLException {
        return new DaoTriangle(connection);
    }
    /**
     * Retourner le DAO GroupeFigures.
     * @return le dao GroupeFigures.
     * @throws SQLException 
     */
    public DAO<GroupeFigures> getDaoGroupeFigures() throws SQLException {
        return new DaoGroupeFigures(connection);
    }
}
