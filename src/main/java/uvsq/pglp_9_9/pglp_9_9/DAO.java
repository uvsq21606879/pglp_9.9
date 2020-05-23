package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {

	/**
	 * Constructeur du DAO pour JDBC.
	 * @throws SQLException Exception liee a l'acces a la base de donnees
	 */
	public DAO() throws SQLException {
		
	}
	/**
	 * Création d'un objet.
	 * @param objet L'objet créer
	 * @return T une classe 
	 * @throws SQLException En cas d'ereur d'accàs à la BDD
	 */
	public abstract T create(T objet) throws IOException, SQLException;
	/**
	 * Pour la supression d'un element.
	 * @param objet l'objet a supprimer
	 * @throws SQLException Exception liee a l'acces a la base de donnees
	 */
	public abstract void Suprimer(T objet) throws SQLException;
	/**
	 * Mise a jour.
	 * @param objet à mettre à jour
	 * @return Modification
	 */
	public abstract T MiseAjour(T objet) throws IOException, SQLException;
	/**
	 * Trouver un element par son identifiant.
	 * @return T element cherche
	 */
	public abstract T Search(String nom) throws FileNotFoundException,
	ClassNotFoundException, IOException, SQLException;
	/**
	 * Methode pour recuperer la connection.
	 * @return connection.
	 */
	/*public Connection getConnection() {
		return connection;
	}*/
	/**
	 * obtenir tous les elements du DAO.
	 * @return les elements du DAO
	 */
	public abstract ArrayList<T> getAll();
	/**
	 * Pour redefinir la connection.
	 * @param newCon a remplacer
	 */
	/*public void setConnection(final Connection newConnection) {
		this.connection = newConnection;
	}*/
	
	/**
	 * Pour eviter les Chiffres magiques dans le code
	 */
	public enum Arg{
		ZERO(0),
		UN(1),
		DEUX(2),
		TROIS(3),
		QUATRE(4),
		CINQ(5),
		SIX(6),
		SEPT(7);
		
		
		private int num;
		
		 Arg(int n){
			    this.num = n;
			  }
		 public int get(){
			 return num;
		 }
	}
}
