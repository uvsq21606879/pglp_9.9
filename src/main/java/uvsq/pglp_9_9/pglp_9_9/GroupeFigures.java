package uvsq.pglp_9_9.pglp_9_9;

import java.util.ArrayList;
import java.util.Iterator;

public class GroupeFigures extends Figure implements Iterable<Figure> {

	/**
	 * Liste des figures
	 */
	private ArrayList<Figure> figures;
	
	/**
	 * Constructeur de la classe
	 * @param newFigure
	 */
	public GroupeFigures(String newFigure) {
		super(newFigure);
		figures= new ArrayList<Figure>();
	}

	/**
	 * Deplacement d'une figure
	 */
	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		for (Figure f : figures)
			f.move(x, y);
	}

	/**
	 * ajouter une forme ou un groupe de formes aux figures.
	 * @param f forme ou groupe à ajouter au groupe
	 */
	public void Ajouter(Figure f) {
		if (!figures.contains(f) && f != this)
			figures.add(f);
	}

	/**
	 * afficher les formes de ce groupe.
	 */
	@Override
	public void afficher() {
		super.afficher();
		System.out.println("Groupe (");
		for (Figure f : figures) {
			f.afficher();
			}
		System.out.println(")");
	}

	/**
	 * Supprimer une figure de la liste
	 * @param F la figure a supprimer
	 */
	public void Suprimer(Figure F) {
		figures.remove(F);
	}

	/**
	 * Retourner la liste de figures
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Figure> getListe() {
		return (ArrayList<Figure>) figures.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Implementation de l'interface itterable
	 */
	public Iterator<Figure> iterator() {
		// TODO Auto-generated method stub
		return figures.iterator();
	}


}
