package sn.esmt.ingc.ro.ui;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

import static java.lang.Double.parseDouble;



public class GraphicEdge extends Path implements Runnable {
	protected int idSource, idTarget;
	protected TextField weight;
	private MouseEvent ev;

	protected double startX, startY, endX, endY;

	public GraphicEdge() {
		super();

	}

	public GraphicEdge(int idSource, double startX, double startY) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.getElements().add(new MoveTo(startX, startY));
		this.idSource = idSource;
		weight=new TextField();

	}

	public GraphicEdge(int idSource, int idTarget, double weight, double startX, double startY, double endX,
			double endY) {
		super();
		this.idSource = idSource;
		this.idTarget = idTarget;
		this.weight.setText(String.valueOf(weight));
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	// public

	public GraphicEdge(int idSource, double weight, double startX, double startY, double endX, double endY) {
		super();
		this.idSource = idSource;
		this.weight.setText(String.valueOf(weight));
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

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

	@Override
	public void run() {
		if (this.getElements().size() == 1)
			this.getElements().add(new LineTo(ev.getX(), ev.getY()));
		else
			this.getElements().set(1, new LineTo(ev.getX(), ev.getY()));
		ev.consume();


	}

	public void setLastPoint(int idTarget, double endX, double endY,double weight) {
		this.idTarget = idTarget;
		this.endX=endX;
		this.endY=endY;
		if(this.weight==null)this.weight=new TextField();
		this.weight.setText(String.valueOf(weight));
		this.getElements().set(1, new LineTo(endX, endY));
	}
	
	public void putWeight( Group group ) {
		weight.setEditable(true);
		weight.setPrefColumnCount(1);

		weight.setPrefWidth(50);
		weight.setPrefHeight(10);

		weight.setStyle("-fx-background-color:transparent;");


		weight.setLayoutX((startX+endX)/2);
		weight.setLayoutY((startY+endY)/2);
		
		group.getChildren().add(weight);
	}

	public MouseEvent getEv() {
		return ev;
	}

	public void setEv(MouseEvent ev) {
		this.ev = ev;
	}

}