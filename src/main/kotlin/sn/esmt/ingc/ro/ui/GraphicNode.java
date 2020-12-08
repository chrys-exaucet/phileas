package sn.esmt.ingc.ro.ui;



import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// GraphicNode hérite de Circle une classe interne à javaFX
public class GraphicNode extends Circle {

	//Constantes
	// constante du rayon du cercle
	public static final double RADIUS = 15;
	//Marge sur l'axe des ordonnées pour placer le nom du Cercle
	public static final double MARGE= 10;
	
	// Identifiant unique du noeud
	private int idNode;
	//Nom s'affichant en bas du noeud 
	private TextField name;
	
	//Verifie si le noeud est sélectionner ou pas
	private boolean isSelected;

	//Constructeurs
	public GraphicNode(double centerX, double centerY, int id) {
		
		super(centerX, centerY, RADIUS);
		this.idNode = id;
		
		this.setOpacity(0.7);
		this.setFill(Color.BLACK); //par défaut la couleur de remplissage est le noir 
		
		this.name=new TextField(String.valueOf(id));
		this.name.setEditable(true);
		this.name.setPrefColumnCount(1);
		
		this.name.setLayoutX(centerX - (RADIUS/2));
		this.name.setLayoutY(centerY + MARGE);
		
		this.name.setMinWidth(50);
		this.name.setPrefWidth(50);
		this.name.setPrefHeight(10);
		
	
		this.name.textProperty().addListener(this::changed);
		
		this.name.setStyle("-fx-background-color:transparent;");
		
	}
	
	/* 
	 * Intervient quand on change le texte du nom, il se charge de centrer et de
	 * augmenter ou de diminuer la zone de texte 
	 * */ 
	public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		double value=this.name.getPrefWidth();
		double layoutX= this.name.getLayoutX();
		
		if(oldValue.length()<newValue.length()) {
			value+=5;
			layoutX-=3;
		}
		else if (oldValue.length()>newValue.length()) {
			value-=5;
			layoutX+=3;
		}
		
		this.name.setPrefWidth(value);
		this.name.setLayoutX(layoutX);
	}
	
	// Verifie si le curseur est dans le noeud
	public boolean isInside(double a, double b) {
					return (Math.pow(this.getCenterX() - a, 2) + Math.pow(this.getCenterX() - b, 2)) <= RADIUS;
	}

	// Getters and Setters
	public int getIdNode() {
		return idNode;
	}

	public void setIdNode(int id) {
		this.idNode = id;
	}

	public TextField getName() {
		return name;
	}

	public void setName(TextField name) {
		this.name = name;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


}
