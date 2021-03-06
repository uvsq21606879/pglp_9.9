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
		@Override
	    public void afficher() {
	    	 super.afficher();
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
	     * Retourne le triangle sous forme d'une chaine de caracteres.
	     */
	    @Override
	    public String toString() {
	    	
	         return " Triangle (" + this.Point1 + ", " + this.Point2
	                + ", " + this.Point3 + ").";
	       
	    }
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return false;
		}


	
}
