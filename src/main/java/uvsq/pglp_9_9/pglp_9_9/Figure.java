package uvsq.pglp_9_9.pglp_9_9;

/**
 * classe definition d'une Figure.
 */
public abstract class Figure {
	/**
	 * nom de le Figure.
	 */
	protected String Figure;
	/**
	 * avoir le nom de le Figure.
	 * @return le nom de la Figure.
	 */
	public String getFigure() {
		return Figure+ "";
	}
	/**
	 * Set le nom de la Figure.
	 * @param nouveau nom
	 */
	public void setFigure(String newFigure) {
		Figure = newFigure +"";
	}
	/**
	 * constructeur pour le Figure.
	 * @param le nom de la Figure
	 */
	protected Figure(String newFigure) {
		this.Figure = newFigure;
	}
	/**
	 * deplacer une figure.
	 * @param x deplacer en abscisse
	 * @param y deplacer en ordonee
	 */
	public abstract void move(int x, int y);
	/**
	 * affiche la Figure.
	 */
	public void afficher() {
		System.out.print(Figure + " : ");
	}
	
	 public abstract boolean equals(final Object obj);
}