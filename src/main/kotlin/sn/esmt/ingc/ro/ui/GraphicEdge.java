package sn.esmt.ingc.ro.ui;


import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import static java.lang.Double.parseDouble;

public class GraphicEdge extends Path implements Runnable {
	
	private static double WEIGHT=100;
	//variable stockant l'id du noeud source ainsi que du noeud cible
	protected int idSource, idTarget;
	
	//permet d'afficher le poids de l'arrête sur la zone de dessin et de la stocker;
	protected TextField weight;
	
	
	private MouseEvent ev;
	
	//Coordonnnées des points de debuts et de fin de l'arrêtes sur la zone de dessin
	protected double startX, startY, endX, endY;

	//Constructeur
	public GraphicEdge() {
		super();
	}

	//Constructeur
	public GraphicEdge(int idSource, double startX, double startY) {
		super();
		this.startX = startX;
		this.startY = startY;
		
		this.idSource = idSource;
		
		//se positionne au point départ correspondant au centre du noeud source
		this.getElements().add(new MoveTo(startX, startY));
		
		this.weight=new TextField(String.valueOf(WEIGHT));
		this.weight.setEditable(true);
		this.weight.setPrefColumnCount(1);
		this.weight.setPrefWidth(50);
		this.weight.setPrefHeight(10);
		this.weight.setStyle("-fx-background-color:transparent;");
	
	}
	
	//Getters and setters
	
	public int getIdSource() {
		return idSource;
	}

	public void setIdSource(int idSource) {
		this.idSource = idSource;
	}

	public int getIdTarget() {
		return idTarget;
	}

	public void setIdTarget(int idTarget) {
		this.idTarget = idTarget;
	}

	public TextField getWeight() {
		return weight;
	}
	public double getWeightDouble() {
		return parseDouble(weight.getText());
	}
	public void setWeight(double weight) {
		this.weight.setText(String.valueOf(weight));
	}

	// Methode de l'interface Runnable permettant à la ligne de suivre le mouvement de la souris 
	@Override
	public void run() {
		if (this.getElements().size() == 1)
			this.getElements().add(new LineTo(ev.getX(), ev.getY()));
		else
			this.getElements().set(1, new LineTo(ev.getX(), ev.getY()));
		ev.consume();
	}

	//Méthode mettant le dernier point et place le textField du poids
	public void setLastPoint(int idTarget, double endX, double endY) {
		this.idTarget = idTarget;
		this.endX=endX;
		this.endY=endY;
		
		this.getElements().set(1, new LineTo(endX, endY));
		this.weight.setLayoutX((startX+endX)/2);
		this.weight.setLayoutY((startY+endY)/2);
	}
	
	//Méthode permettant de placeer le poids sur le group
	public void putWeight( Group group ) {	
		
		group.getChildren().add(weight);
	}

	
	public MouseEvent getEv() {
		return ev;
	}

	public void setEv(MouseEvent ev) {
		this.ev = ev;
	}

}
