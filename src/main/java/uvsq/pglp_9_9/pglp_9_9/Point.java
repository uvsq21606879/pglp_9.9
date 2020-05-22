package uvsq.pglp_9_9.pglp_9_9;

import java.io.CharConversionException;

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
    }*/
    @Override
    public String toString() {
    	String p = "(" + x + "," + y + ")";
        return p;
    }
    
    @Override
    public Point clone() {
        return new Point(x, y);
    }
    /**
     * constructeur du point avec un type String.
     * @param position string contenant la position.
     * @throws CharConversionException invalid String
     */
    public Point(String position) throws CharConversionException {
        position.replace(" ", "");
        if (position.charAt(0) != '(' || position.charAt(position.length() - 1) != ')') {
            System.err.println(position);
            throw new CharConversionException();
        }
        String position2 = position.substring(1, position.length() - 1);
        String[] positionSplit = position2.split(",");
        if (positionSplit.length != 2) {
            System.err.println(position);
            throw new CharConversionException();
        }
        try {
            x = Integer.parseInt(positionSplit[0]);
            y = Integer.parseInt(positionSplit[1]);
        } catch (NumberFormatException e) {
            System.err.println("Caractère inconnu lors de la conversion des nombres.");
            throw e;
        }
    }
}
