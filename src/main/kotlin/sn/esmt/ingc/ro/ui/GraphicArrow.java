package sn.esmt.ingc.ro.ui;

import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;


/*
 * Classe GraphicArrow permettant de tracer un flèche. Elle hérite de la classe GraphicEdge
 * permettant de tracer une arrête. 
 */
public class GraphicArrow extends GraphicEdge {
	
	//Une constante definissant la taille de bout de flèche
	public static final int SIZE_ARROW_HEAD = 5;

	//Constructeur
	public GraphicArrow(int idSource, double startX, double startY) {
		super(idSource, startX, startY);
	}
	
	//Place le dernier point et trace la tête de la flèche.
	public void setLastPoint(int idTarget, double endX, double endY) {
		 
		super.setLastPoint(idTarget, endX, endY);
		this.drawHeadArrow(idTarget, endX, endY);
	}

	/*
	 * Methode permettant de tracer la tête de la flèche prennant en paramètre
	 * l'id du noeud cible et les coordonnées de son centre
	 * */
	private void drawHeadArrow(int idTarget, double endX, double endY) {
		
		this.idTarget = idTarget;
		this.endX = endX;
		this.endY = endY;
		
		// ArrowHead
		double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		
		// point1
		double x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * SIZE_ARROW_HEAD + endX;
		double y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * SIZE_ARROW_HEAD + endY;
		
		// point2
		double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * SIZE_ARROW_HEAD + endX;
		double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * SIZE_ARROW_HEAD + endY;

		
		getElements().addAll(new MoveTo(x1, y1), new LineTo(x2, y2), new LineTo(endX, endY), new ClosePath());

	}

}
