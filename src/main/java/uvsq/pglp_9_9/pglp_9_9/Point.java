package uvsq.pglp_9_9.pglp_9_9;

public class Point {
	
	 /**
     * La coordonnes en abscisse du point.
     */
    private int x;
    /**
     * La coordonnee en ordonnee du point.
     */
    private int y;
    /**
     * Constructeur de point.
     * @param x2 abscisse du point
     * @param y2 ordonnée du point
     */
    public Point(int X, int Y) {
        this.x = X;
        this.y = Y;
    }
    
    /**
     * Constructeur pa defaut
     */
    public Point() {
        x = 0;
        y = 0;
    }
    /**
     * recuperer le point.
     * @return le point
     */
    public Point getPoint() {
        return this;
    }
    /**
     * Recuperer la coordonnee x du point.
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Recuperer la coordonnee y du point.
     * @return y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Deplacer un point.
     * @param X le deplacement sur les abcisse
     * @param Y le deplacement sur les ordonnees
     */
    public void move(int X, int Y) {
        this.x = this.x + X;
        this.y = this.y + Y;
    }
  
    /**
     * Comparer un point.
     */
    @Override
    public boolean equals(Object obj) {
        if (this.equals(obj)) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point p = (Point) obj;
        if (x != p.x) {
            return false;
        }
        if (y != p.y) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
    	String p = "(" + x + "," + y + ")";
        return p;
    }
    
    @Override
    public Point clone() {
        return new Point(x, y);
    }
}
