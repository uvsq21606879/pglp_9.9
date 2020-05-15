package uvsq.pglp_9_9.pglp_9_9;

public class Cercle extends Figure {
	
	
	 /**
     * Le Centre du cercle.
     */
    private Point Centre;
    /**
     * Le Rayon du cercle.
     */
    private int Rayon;
    /**
     * Constructeur du cercle.
     * @param nomCercle le nom du cercle
     * @param P le centre du cercle
     * @param rayon le rayon du cercle
     */
    public Cercle(final String nomCercle, Point P, int rayon) {
        super(nomCercle);
        this.Centre = P;
        this.Rayon = rayon;
    }
   
    /**
     * Comparaison.
     */
    @Override
    public boolean equals(final Object obj) {
    	 Cercle other = (Cercle) obj;
    	 
    	if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
       
        if (Centre == null) {
            if (other.Centre != null) {
                return false;
            }
        } else if (!Centre.equals(other.Centre)) {
            return false;
        }
        if (Rayon != other.Rayon) {
            return false;
        }
        return true;
    }
    /**
     * Deplacer le cercle.
     * @param x la valeur de deplacement sur les abscises
     * @param y la valeur de deplacement sur les ordonnees
     */
    @Override
    public void move(int x, int y) {
    	// TODO Auto-generated method stub
        this.Centre.move(x, y);
    }
    /**
     * 
     * @param rayon Changer le taille du rayon
     * @throws Exception Si valeur negative
     */
    
    public void setRayon(int rayon) throws Exception {
        if (rayon > 0) {
            this.Rayon = rayon;
        } else {
            throw new Exception();
        }
    }
    
    /**
     * Retourne les coordonees du centre du cercle.
     * @return Centre
     */
    public Point getCentre() {
        return this.Centre.clone();
    }
    /**
     * Retourne le Rayon du cercle.
     * @return Rayon
     */
    public int getRayon() {
        return this.Rayon;
    }
    /**
     * Affiche les informations du cercle.
     */
    public void affiche() {
    	 super.afficherFigure();
         System.out.println("Cercle ("
                 + "centre = " + Centre + ", rayon = " + Rayon + ")");

    }
	
	

}
