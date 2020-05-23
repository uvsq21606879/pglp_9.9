package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class DaoCarre extends DAO<Carre> {


	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoCarre(Connection Connect) throws SQLException {
		this.connection=Connect;
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Methode pour ajouter un Carre au DAO.
	 * @param objet le Carre a ajouter
	 * @return la creation
	 */

	@Override
	public Carre create(Carre carre) throws IOException, SQLException {
		
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO Figure (figure) VALUES(?)");
			prepare.setString(1, carre.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"INSERT INTO Carre (figure,HG_x,HG_y,longueur)"
							+ " VALUES(?, ?, ?, ?)");
			prepare.setString(Arg.UN.get(), carre.getFigure());
			prepare.setInt(Arg.DEUX.get(), carre.getHautGauche().getX());
			prepare.setInt(Arg.TROIS.get(), carre.getHautGauche().getY());
			prepare.setInt(Arg.QUATRE.get(), carre.getLongueur());
			prepare.executeUpdate();
		} catch (SQLException e) {
			return null;
		}
		return carre;
	}
	/**
	 * Supprimer un carre du DAO
	 * @param carre a supprimer
	 */

	@Override
	public void Suprimer(Carre carre) throws SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM RelationComposition WHERE IdComposant = ?");
			prepare.setString(Arg.UN.get(), carre.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {

		}

		try {

			PreparedStatement prepare = connection.prepareStatement(
					"DELETE FROM Carre WHERE figure = ?");
			prepare.setString(Arg.UN.get(), carre.getFigure());
			prepare.executeUpdate();
			prepare = connection.prepareStatement(
					"DELETE FROM Figure WHERE figure = ?");
			prepare.setString(Arg.UN.get(), carre.getFigure());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mettre a jour un carre dans le DAO 
	 */
	@Override
	public Carre MiseAjour(Carre carre) throws FileNotFoundException, IOException, SQLException {

		Carre CarreAmodifier = null;
		try {
			CarreAmodifier = this.Search(carre.getFigure());
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		if (CarreAmodifier != null) {
			try {
				PreparedStatement prepare = connection.prepareStatement(
						"UPDATE Carre SET HG_x = ?, HG_y = ?, longueur = ? WHERE figure = ?");
				prepare.setInt(Arg.UN.get(), carre.getHautGauche().getX());
				prepare.setInt(Arg.DEUX.get(), carre.getHautGauche().getY());
				prepare.setInt(Arg.TROIS.get(), carre.getLongueur());
				prepare.setString(Arg.QUATRE.get(), carre.getFigure());
				prepare.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return CarreAmodifier;
			}
		} else {
			return null;
		}
		System.out.println("Mise à jour de "+carre.getFigure()+" avec succès");
		return carre;
	}

	/**
	 * Chercher  un carre dans le DAO et le retourner.
	 * @param carre element a obrenit.
	 * @return le carre si il est  touve sinon null.
	 */
	@Override
	public Carre Search(String carre) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		
		Carre carreSearch = null;
		try {
			PreparedStatement req = connection.prepareStatement(
					"SELECT * FROM Carre WHERE figure = ?");
			req.setString(Arg.UN.get(), carre);
			ResultSet res = req.executeQuery();
			if (((java.sql.ResultSet) res).next()) {
				Point point = new Point(res.getInt("HG_x"), res.getInt("HG_y"));
				try {
					carreSearch = new Carre(carre, point, res.getInt("longueur"));
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return carreSearch;

	}
	
	/**
	 * Retourne tout les carre du DAO
	 */
	@Override
	public ArrayList<Carre> getAll() {
		// TODO Auto-generated method stub
		  ArrayList<Carre> res = new ArrayList<Carre>();
	        try {
	            PreparedStatement prepare = connection.prepareStatement(
	                    "SELECT figure FROM Carre");
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
	            return new ArrayList<Carre>();
	        }
	        return res;
	}



}
