package uvsq.pglp_9_9.pglp_9_9;

public class Carre extends Figure {
	
	 /**
     * position du coin en haut a gauche du carre.
     */
    private Point HautGauche;
    /**
     * longueur des cotes du carre.
     */
    private int Longueur;

	public Carre(String newFigure, Point HG, int longueur) throws Exception {
		super(newFigure);
		// TODO Auto-generated constructor stub
		this.HautGauche = HG.clone();
        setLongueur(longueur);
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
	        Carre C = (Carre) obj;
	        if (HautGauche == null) {
	            if (C.HautGauche != null) {
	                return false;
	            }
	        } else if (!HautGauche.equals(C.HautGauche)) {
	            return false;
	        }
	        if (Longueur != C.Longueur) {
	            return false;
	        }
	        return true;
}
	
	 /**
     * afficher un carre.
     */
	@Override
    public void afficher() {
        super.afficher();
        System.out.println("Carre (longueur = "
                + Longueur + ", position du Point en haut à gauche = " + HautGauche + ")");
    }
    
    /**
     * Retourner le point HautGauche du Carre
     */
    public Point getHautGauche() {
        return HautGauche.clone();
    }
    
    /**
     * Retourner la longueur du Carre
     */
    public int getLongueur() {
        return Longueur;
    }
    
    /**
     * Changer la longeur du Carre
     * @param longueur la nouvelle longueur
     * @throws Exception en cas d'erreur longueur negative.
     */
    public void setLongueur(int longueur) throws Exception {
        if (longueur > 0) {
            this.Longueur = longueur;
        } else {
            throw new Exception();
        }
    }
	@Override
    public String toString() {
		return "Carre (longueur = " + Longueur + ", position du Point en haut à gauche = " + HautGauche + ")";
    		
    }
	
}
