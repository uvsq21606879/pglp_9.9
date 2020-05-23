package uvsq.pglp_9_9.pglp_9_9;

public class Rectangle extends Figure {

	/**
	 * Position du Point haut à gauche du rectangle.
	 */
	private Point HautGauche;
	/**
	 * Longueur du rectangle.
	 */
	private int Longueur;
	/**
	 * Largeur du rectangle.
	 */
	private int Largeur;

	/**
	 * Constructeur du rectangle
	 * @param nomRectangle le nom du rectangle
	 * @param HG le point haut à gauche du rectangle
	 * @param longueur longueur de rectangle
	 * @param largeur largeur du rectangle
	 * @throws Exception
	 */
	public Rectangle(String nomRectangle, Point HG, int longueur,int largeur) throws Exception {
		super(nomRectangle);
		this.HautGauche = HG.clone();
		this.setLongueur(longueur);
		this.setLargeur(largeur);
	}
	/**
	 * Deplacement du rectangle
	 */
	@Override
	public void move(int x, int y) {
		HautGauche.move(x, y);
	}

	/**
	 * Retourner la longueur du rectangle.
	 * @return la longueur du rectangle
	 */
	public int getLongueur() {
		return Longueur;
	}
	/**
	 * Retourner la longueur du rectangle.
	 * @return la longueur du rectangle
	 */
	public int getLargeur() {
		return Largeur;
	}
	/**
	 * Changer la longueur du triangle
	 * @param longueur
	 * @throws Exception
	 */
	public void setLongueur(int longueur) throws Exception {
		if (longueur > 0) {
			this.Longueur = longueur;
		} else {
			throw new Exception();
		}
	}
	/**
	 * Afficher le Rectangle
	 */
	@Override
	public void afficher() {
		super.afficher();
		System.out.println("Rectangle (Point haut à gauche = " + HautGauche + ")" 
				+ " (longueur = " + Longueur + ", largeur = " + Largeur + ")" );
	}

	/**
	 * Obtenir la position du coin en haut a gauche.
	 * @return position du coin en haut a gauche.
	 */
	public Point getHautGauche() {
		return HautGauche.clone();
	}

	/**
	 * Changer la largeur du rectangle.
	 * @param largeur
	 * @throws Exception
	 */
	public void setLargeur(int largeur) throws Exception {
		if (largeur > 0) {
			this.Largeur = largeur;
		} else {
			throw new Exception();
		}
	}

	/**
	 * Routourner  le Rectangle sous forme d'une chaine de caractères
	 */
	@Override
	public String toString() {
		return "Rectangle (Point haut à gauche = " + HautGauche + ")" 
				+ " (longueur = " + Longueur + ", largeur = " + Largeur + ")" ;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}


}
