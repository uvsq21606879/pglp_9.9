package uvsq.pglp_9_9.pglp_9_9;

public class Triangle extends Figure {
	
	/**
	 * Les trois points du triangle.
	 */
	private Point  Point1;
	private Point  Point2;
	private Point  Point3;
	
	    /**
	     * Constructeur du triangle.
	     * @param nomTriangle Le nom du triangle
	     * @param Point1 Le 1er point du triangle
	     * @param Point2 Le 2eme point du triangle
	     * @param Point3 Le 3eme point du triangle
	     */
	    public Triangle(final String nomTriangle, Point point1, Point point2, Point point3) {
	        super(nomTriangle);
	        this.Point1 = point1;
	        this.Point2 = point2;
	        this.Point3 = point3;
	    }
		@Override
		public void move(int x, int y) {
			// TODO Auto-generated method stub
			 	this.Point1.move(x, y);
		        this.Point2.move(x, y);
		        this.Point3.move(x, y);
			
		}
		
	    /**
	     * Affiche les informations du triangle.
	     */
	    public void afficher() {
	    	 super.afficherFigure();
	        String s = " Triangle (" + this.Point1 + ", " + this.Point2
	                + ", " + this.Point3 + ").\n";
	        System.out.println(s);
	    }
	    
	    /**
	     * Retourner un point du triangle
	     * @param p Index du point a retourner 
	     * @return un point parmis les 3 points du triangle
	     */
	    
	    public Point getPoint(int p) {
	        switch(p) {
	        case 0:
	          return Point1.clone();
	          case 1:
		          return Point2.clone();
	        case 2:
		          return Point3.clone();
	        default:
	        	throw new IndexOutOfBoundsException();
	      }

	    }
	    
	    /**
	     * comparaison.
	     */
	    @Override
	    public boolean equals(final Object obj) {
	       
	        Triangle Point = (Triangle) obj;
	        if (Point1 == null) {
	            if (Point.Point1 != null) {
	                return false;
	            }
	        } else if (!Point1.equals(Point.Point1)) {
	            return false;
	        }
	        if (Point2 == null) {
	            if (Point.Point2 != null) {
	                return false;
	            }
	        } else if (!Point2.equals(Point.Point2)) {
	            return false;
	        }
	        if (Point3 == null) {
	            if (Point.Point3 != null) {
	                return false;
	            }
	        } else if (!Point3.equals(Point.Point3)) {
	            return false;
	        }
	        return true;
	    }
	    
	    /**
	     * Retourne le triangle sous forme d'une chaine de caracteres.
	     */
	    @Override
	    public String toString() {
	    	
	         return " Triangle (" + this.Point1 + ", " + this.Point2
	                + ", " + this.Point3 + ").";
	       
	    }


	
}
