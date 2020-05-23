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
     * @throws Exception 
     */
    public Cercle(final String nomCercle, Point P, int rayon) throws Exception {
        super(nomCercle);
        this.Centre = P;
        setRayon(rayon);
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
    @Override
    public void afficher() {
    	 super.afficher();
         System.out.println("Cercle ("
                 + "centre = " + Centre + ", rayon = " + Rayon + ")");

    }
    @Override
    public String toString() {
    	return "Cercle (" + "centre = " + Centre + ", rayon = " + Rayon + ")";
    }


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
