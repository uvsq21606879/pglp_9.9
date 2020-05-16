package uvsq.pglp_9_9.pglp_9_9;

public class Rectangle extends Figure {

	/**
	 * position du coin en haut à gauche du rectangle.
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


	protected Rectangle(final String nomRectangle, Point HG, int longueur,int largeur) {
		super(nomRectangle);
		// TODO Auto-generated constructor stub
		this.HautGauche = HG.clone();
		this.Longueur = longueur;
		this.Largeur = largeur;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		HautGauche.move(x, y);

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Rectangle R = (Rectangle) obj;
		if (HautGauche == null) {
			if (R.HautGauche != null) {
				return false;
			}
		} else if (!HautGauche.equals(R.HautGauche) || Longueur != R.Longueur || Largeur != R.Largeur ) {
			return false;
		}

		return true;
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
	public void afficher() {
		super.afficherFigure();
		System.out.println("Rectangle (longueur = "
				+ Longueur + ", largeur = " + Largeur + ", position = " + HautGauche + ")");
	}

	/**
	 * Obtenir la position du coin en haut a gauche.
	 * @return position du coin en haut a gauche.
	 */
	public Point getTopLeft() {
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
	


}