package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCercle extends DAO<Cercle> {
	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoCercle(Connection Connect) throws SQLException {
		this.connection=Connect;
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Methode pour ajouter un cercle au DAO.
	 * @param objet le cercle a ajouter
	 * @return la creation
	 */

	@Override
	public Cercle create(Cercle cercle) throws IOException, SQLException {
		
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO Figure (figure) VALUES(?)");
			prepare.setString(1, cercle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"INSERT INTO Cercle (figure,centre_x,centre_y,rayon)"
							+ " VALUES(?, ?, ?, ?)");
			prepare.setString(Arg.UN.get(), cercle.getFigure());
			prepare.setInt(Arg.DEUX.get(), cercle.getCentre().getX());
			prepare.setInt(Arg.TROIS.get(), cercle.getCentre().getY());
			prepare.setInt(Arg.QUATRE.get(), cercle.getRayon());
			prepare.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return cercle;
	}
	/**
	 * Supprimer un cercle du DAO
	 * @param cercle a supprimer
	 */

	@Override
	public void Suprimer(Cercle cercle) throws SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM RelationComposition WHERE IdComposant = ?");
			prepare.setString(Arg.UN.get(), cercle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {

		}

		try {

			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM Cercle WHERE figure = ?");
			prepare.setString(Arg.UN.get(), cercle.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"DELETE FROM Figure WHERE figure = ?");
			prepare.setString(Arg.UN.get(), cercle.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mettre a jour un cercle dans le DAO 
	 */
	@Override
	public Cercle MiseAjour(Cercle cercle) throws FileNotFoundException, IOException, SQLException {

		Cercle cercleAmodifier = null;
		try {
			cercleAmodifier = this.Search(cercle.getFigure());
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		if (cercleAmodifier != null) {
			try {
				PreparedStatement prepare = connection.prepareStatement(
						"UPDATE Cercle SET centre_x = ?, centre_y = ?, rayon = ? WHERE figure = ?");
				prepare.setInt(Arg.UN.get(), cercle.getCentre().getX());
				prepare.setInt(Arg.DEUX.get(), cercle.getCentre().getY());
				prepare.setInt(Arg.TROIS.get(), cercle.getRayon());
				prepare.setString(Arg.QUATRE.get(), cercle.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return cercleAmodifier;
			}
		} else {
			return null;
		}
		System.out.println("Mise à jour de "+cercle.getFigure()+" avec succès");
		return cercle;
	}

	/**
	 * Chercher  un cercle dans le DAO et le retourner.
	 * @param cercle element a obrenit.
	 * @return le cercle si il est  touve sinon null.
	 */
	@Override
	public Cercle Search(String cercle) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		
		Cercle cercleSearch = null;
		try {
			PreparedStatement req = connection.prepareStatement(
					"SELECT * FROM Cercle WHERE figure = ?");
			req.setString(Arg.UN.get(), cercle);
			ResultSet res = req.executeQuery();
			if (((java.sql.ResultSet) res).next()) {
				Point point = new Point(res.getInt("centre_x"), res.getInt("centre_x"));
				try {
					cercleSearch = new Cercle(cercle, point, res.getInt("rayon"));
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return cercleSearch;

	}
	
	/**
	 * Retourne tout les cercle du DAO
	 */
	@Override
	public ArrayList<Cercle> getAll() {
		// TODO Auto-generated method stub
		  ArrayList<Cercle> res = new ArrayList<Cercle>();
	        try {
	            PreparedStatement prepare = connection.prepareStatement(
	                    "SELECT figure FROM Cercle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                try {
						res.add(this.Search(result.getString("figure")));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Cercle>();
	        }
	        return res;
	}


}
