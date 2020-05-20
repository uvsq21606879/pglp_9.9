package uvsq.pglp_9_9.pglp_9_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoGroupeFigures extends DAO<GroupeFigures> {

	/**
	 * La connexion a la BDD.
	 */
	private Connection connection;
	/**
	 * Constructeur de la classe.
	 * @param Connect connection a la BDD
	 */
	public DaoGroupeFigures(Connection Connect) throws SQLException {
		this.connection=Connect;
	
	}
	
	/**
	 * Creation de la relation de Groupe et ses composants
	 * @param IdGroupe identifiant du groupe
	 * @param IdComposant identifiat du composant a ajouter avec ce groupe
	 * @throws IOException
	 * @throws SQLException
	 */

	public void createRelationC(String IdGroupe, String IdComposant) throws IOException, SQLException {

		try {
			PreparedStatement prepare = connection.prepareStatement(
					"INSERT INTO RelationComposition (IdGroupe, IdComposant VALUES(?, ?)");
			prepare.setString(Arg.UN.get(), IdGroupe);
			prepare.setString(Arg.DEUX.get(),  IdComposant);
			prepare.executeUpdate();
		} catch (SQLException e) {

		}
	}
	/**
	 * @param IdGroupe Groupe a chercher
	 * @return Une liste contenant les composant du Groupe
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Figure> SearchComposition(String IdGroupe) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<Figure> Search = new ArrayList<Figure>();
		DaoConstruction construction = new DaoConstruction();
		try {
			PreparedStatement prepare = connection.prepareStatement(
					"SELECT IdComposant FROM RelationComposition WHERE IdGroupe = ?");
			prepare.setString(Arg.UN.get(), IdGroupe);
			ResultSet result = prepare.executeQuery();
			DAO<Rectangle> daoRectangle = construction.getDaoRectangle();
			DAO<Triangle> daoTriangle = construction.getDaoTriangle();
			DAO<Cercle> daoCercle = construction.getDaoCercle();
			DAO<Carre> daoCarre = construction.getDaoCarre();

			while (result.next()) {
				Figure figure = daoCercle.Search(result.getString("IdComposant"));
				if (figure == null) 
					figure = daoRectangle.Search(result.getString("IdComposant"));
				if (figure == null) 
					figure = daoTriangle.Search(result.getString("IdComposant"));
				if (figure == null) 
					figure = daoCarre.Search(result.getString("IdComposant"));
				if (figure == null)
					figure = this.Search(result.getString("IdComposant"));
				Search.add(figure);
			}
			construction.close();
		} catch (SQLException e) {
			construction.close();
			return new ArrayList<Figure>();
		}
		return Search;
	}
	
	
	/**
	 * Suprimer une fugure
	 * @param la figure a supprimer
	 */
	@Override
	public void Suprimer(GroupeFigures figure) throws SQLException {
        try {
            
            try {
                PreparedStatement prepare = connection.prepareStatement(
                        "DELETE FROM RelationComposition WHERE IdGroupe = ?");
                prepare.setString(Arg.UN.get(), figure.getFigure());
                prepare.executeUpdate();
            } catch (SQLException e) {
            }
           
            try {
                PreparedStatement prepare = connection.prepareStatement(
                        "DELETE FROM RelationComposition WHERE IdComposant = ?");
                prepare.setString(Arg.UN.get(), figure.getFigure());
                prepare.executeUpdate();
            } catch (SQLException e) {
            }
            
            PreparedStatement prepare = connection.prepareStatement(
                    "DELETE FROM GroupeFigures WHERE figure = ?");
            prepare.setString(Arg.UN.get(), figure.getFigure());
            prepare.executeUpdate();
            prepare = connection.prepareStatement(
                    "DELETE FROM Figure WHERE figure = ?");
            prepare.setString(Arg.UN.get(), figure.getFigure());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	/**
	 * Mettre a jour une goupe
	 * @param l'objet a mettre a jour
	 * @return la mise a jour
	 */
	@Override
	public GroupeFigures MiseAjour(GroupeFigures objet) throws IOException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Figure> contenu = null;
		try {
			contenu = this.SearchComposition(objet.getFigure());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (!contenu.isEmpty()) {
            try {
                PreparedStatement prepare = connection.prepareStatement(
                        "DELETE FROM RelationComposition WHERE IdGroupe = ?");
                prepare.setString(Arg.UN.get(), objet.getFigure());
                prepare.executeUpdate();
            } catch (SQLException e) {
            }
            DaoConstruction construction = new DaoConstruction();
            for (Figure f : objet.getListe()) {
                if (f.getClass() == Cercle.class) {
                    DAO<Cercle> dao = construction.getDaoCercle();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                	DAO<Carre> dao = construction.getDaoCarre();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                	DAO<Rectangle> dao = construction.getDaoRectangle();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                	DAO<Triangle> dao = construction.getDaoTriangle();
                    dao.create((Triangle) f);
                } else {
                    this.create((GroupeFigures) f);
                }
                this.createRelationC(objet.getFigure(), f.getFigure());
            }
            construction.close();
        } else {
            return null;
        }
        return objet;
	}
/**
 * Chercher une figure dans le GroupeFigures
 * @param la figure a chercher
 */
	@Override
	public GroupeFigures Search(String figure)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
	        GroupeFigures groupeSearch = null;
	        try {
	            PreparedStatement prepare = connection.prepareStatement(
	                    "SELECT * FROM GroupeFigures WHERE figure = ?");
	            prepare.setString(Arg.UN.get(), figure);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                groupeSearch = new GroupeFigures(figure);
	                ArrayList<Figure> liste = SearchComposition(figure);
	                for (Figure F : liste) {
	                    groupeSearch.Ajouter(F);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return groupeSearch;
	}
 /**
  * Retourner la liste de tout les figures dans GroupeFigures
  */
	@Override
	public ArrayList<GroupeFigures> getAll() {
		 ArrayList<GroupeFigures> search = new ArrayList<GroupeFigures>();
	        try {
	            PreparedStatement prepare = connection.prepareStatement(
	                    "SELECT figure FROM GroupeFigures");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                try {
						search.add(this.Search(result.getString("figure")));
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<GroupeFigures>();
	        }
	        return search;
	}

	@Override
	public GroupeFigures create(GroupeFigures object) throws IOException, SQLException {
        DaoConstruction construction = new DaoConstruction();
        try {
            PreparedStatement prepare = connection.prepareStatement(
                    "INSERT INTO Figure (figure) VALUES(?)");
            prepare.setString(Arg.UN.get(), object.getFigure());
            prepare.executeUpdate();
            prepare = connection.prepareStatement(
                    "INSERT INTO GroupeFigures (figure) VALUES(?)");
            prepare.setString(Arg.UN.get(), object.getFigure());
            prepare.executeUpdate();
            for (Figure f : object.getListe()) {
                if (f.getClass() == Cercle.class) {
                   DAO<Cercle> dao = construction.getDaoCercle();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                   DAO<Carre> dao = construction.getDaoCarre();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                   DAO<Rectangle> dao = construction.getDaoRectangle();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                   DAO<Triangle> dao = construction.getDaoTriangle();
                    dao.create((Triangle) f);
                } else {
                    this.create((GroupeFigures) f);
                }
                this.createRelationC(object.getFigure(), f.getFigure());
            }
            construction.close();
        } catch (SQLException e) {
            construction.close();
            return null;
        }
        return object;
	}
	
  

}
