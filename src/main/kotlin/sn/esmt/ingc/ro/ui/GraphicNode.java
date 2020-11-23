package sn.esmt.ingc.ro.ui;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;


public class GraphicNode extends Circle {

	public static final double RADIUS = 10;

	private int idNode;
	private Label name;
	private boolean isSelected;

	public GraphicNode(double centerX, double centerY, int id) {
		super(centerX, centerY, RADIUS);
		this.idNode = id;

		this.name = new Label(String.valueOf(id));
		this.name.setLayoutX(centerX - (RADIUS / 2));
		this.name.setLayoutY(centerY - (RADIUS / 2));
		this.setOpacity(0.5);


	}

	public boolean isInside(double a, double b) {
					return (Math.pow(this.getCenterX() - a, 2) + Math.pow(this.getCenterX() - b, 2)) <= RADIUS;
	}

	public int getIdNode() {
		return idNode;
	}

	public void setIdNode(int id) {
		this.idNode = id;
	}

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
