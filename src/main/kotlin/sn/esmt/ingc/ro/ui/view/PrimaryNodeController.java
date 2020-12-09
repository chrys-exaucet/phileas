package sn.esmt.ingc.ro.ui.view;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sn.esmt.ingc.ro.ui.GraphicArrow;
import sn.esmt.ingc.ro.ui.GraphicEdge;
import sn.esmt.ingc.ro.ui.GraphicNode;

public class PrimaryNodeController {
	
	// Data Format customizer
	static final DataFormat NODE = new DataFormat("Noeud");
	static final DataFormat EDGE = new DataFormat("Edge");
	static final DataFormat ARROW = new DataFormat("Arrow");
	
	
	//Variable lié au chargemnt du du fichier FXML : PrimaryNode
	@FXML
	private ImageView noeud;

	@FXML
	private ImageView arrow;

	@FXML
	private ImageView arrete;

	@FXML
	private Label lblNoeud;

	@FXML
	private Label lblArrow;

	@FXML
	private Label lblArrete;

	
	@FXML
	private Group canvasGrid;

	@FXML
	private GridPane grid;

	@FXML
	private Pane paneGroup;

	private int countID = 0;

	@FXML
	private RadioButton bellmanFord;
	@FXML
	private RadioButton djikstra;
	@FXML
	private RadioButton floyd;

	
	// La liste de tous les noeud sur la zone de dessin
	private ArrayList<GraphicNode> list = new ArrayList<>();

	// Noeud focus par la souris
	private GraphicNode focusNode;
	
	// Permmet d'éviter de mettre dans un meme graphe des flèches et des arrêtes
	private int mode=0;

	// Liste de tous les arrêtes ou Fleche(arrow) ( Remarque: GraphicArrow hérite de GraphicEdges) 
	private ArrayList<GraphicEdge> realEdges=new ArrayList<>();
	
	// L'instance du Chemin (ligne ) qu'on veut ajouter
	private GraphicEdge addPath = null;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public PrimaryNodeController() {
	}


	@FXML
	private void initialize() {
		
		// Associe les radio Bouttons
		ToggleGroup group = new ToggleGroup();
		group.getToggles().addAll(bellmanFord, djikstra, floyd);
		// par defaut la radio Bellman Ford est Sélectionner
		bellmanFord.setSelected(true);
		
		//lie la largeur et hauteur  de child(panneGroup) de canvasGrid au parent de canvasGrid qui est grid
		paneGroup.prefHeightProperty().bind(grid.heightProperty());
		paneGroup.prefWidthProperty().bind(grid.widthProperty());	
	}
	
	

	
	//Methode referencer dans FXML
	
	//Méthode permettant le drag and drop
	@FXML
	private void dragged(MouseEvent e) {
		Dragboard dragboard = noeud.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.put(NODE, "0");
		dragboard.setDragView(noeud.getImage());
		dragboard.setContent(content);
		e.consume();
	}

	@FXML
	private void draggedArrow(MouseEvent e) {
		if(mode==2 || mode ==0) {
		Dragboard dragboard = arrow.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.put(ARROW, "1");
		dragboard.setDragView(arrow.getImage());
		dragboard.setContent(content);
		}
		e.consume();
		
	}

	@FXML
	private void draggedEdge(MouseEvent e) {
		if(mode==1 || mode ==0 ) {
		Dragboard dragboard = arrete.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.put(EDGE, "2");
		dragboard.setDragView(arrete.getImage());
		dragboard.setContent(content);
		}
		e.consume();
	}

	@FXML
	private void draggedNoeud(MouseEvent e) {
		Dragboard dragboard = noeud.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.put(NODE, "1");
		dragboard.setDragView(noeud.getImage());
		dragboard.setContent(content);
		e.consume();
	}

	@FXML
	private void dragOver(DragEvent e) {
		Dragboard dragboard = e.getDragboard();
		if (e.getGestureSource() == noeud && dragboard.hasContent(NODE)) {
			e.acceptTransferModes(TransferMode.COPY);
		} else if (e.getGestureSource() == arrete && dragboard.hasContent(EDGE) && list.size() >= 2) {
			if (focusNode != null) {
				e.acceptTransferModes(TransferMode.COPY);
			} else {
				e.acceptTransferModes(TransferMode.NONE);
			}
		} else if (e.getGestureSource() == arrow && dragboard.hasContent(ARROW) && list.size() >= 2) {
			if (focusNode != null) {
				e.acceptTransferModes(TransferMode.COPY);
			} else {
				e.acceptTransferModes(TransferMode.NONE);
			}
		}

		e.consume();
	}

	@FXML
	private void dragDropped(DragEvent e) {
		boolean dragCompleted = false;
		
		Dragboard dragboard = e.getDragboard();

		double x = e.getX(), y = e.getY();

		if (dragboard.hasContent(NODE)) {
			putNode(x, y);
			dragCompleted = true;
		} else if (dragboard.hasContent(ARROW)) {
			if (focusNode != null) {
				addPath = new GraphicArrow(focusNode.getIdNode(), focusNode.getCenterX(), focusNode.getCenterY());
				addPath.setOnMouseClicked(eb->{
					canvasGrid.getChildren().removeAll((GraphicArrow)eb.getSource(), ((GraphicArrow)eb.getSource()).getWeight());
				});
				
				canvasGrid.getChildren().add(addPath);
				
				if(mode!=2)mode=2;
			}
		} else if (dragboard.hasContent(EDGE)) {
			if (focusNode != null) {
				addPath = new GraphicEdge(focusNode.getIdNode(), focusNode.getCenterX(), focusNode.getCenterY());
				addPath.setOnMouseClicked(eb->{
					canvasGrid.getChildren().removeAll((GraphicEdge)eb.getSource(), ((GraphicEdge)eb.getSource()).getWeight());
				});
				canvasGrid.getChildren().add(addPath);
				if(mode!=1)mode=1;
			}
		}
		e.setDropCompleted(dragCompleted);
		e.consume();
	}

	
	
	// Met un noeud sur le group en donnant les coordonnées x et y
	private void putNode(double x, double y) {
		countID++;
		GraphicNode gn = new GraphicNode(x, y, countID);
		list.add(gn);
		gn.setOnDragOver(e -> {

			focusNode = (GraphicNode) e.getSource();

		});

		gn.setOnDragExited(e -> {
			focusNode = null;
			e.consume();
		});

		gn.setOnMouseEntered(e -> {
			nodeListenner(e);
			e.consume();
		});
		canvasGrid.getChildren().addAll(gn, gn.getName());

	}

	private void nodeListenner(MouseEvent e) {
		
		GraphicNode target = (GraphicNode) e.getSource();
		if (addPath != null && addPath.getIdSource() != target.getIdNode()) {
			if (addPath.getClass().getSimpleName().equals("GraphicEdge")) {
				addPath.setLastPoint(target.getIdNode(), target.getCenterX(), target.getCenterY());
				addPath.putWeight(canvasGrid);
				realEdges.add(addPath);

			} else if (addPath.getClass().getSimpleName().equals("GraphicArrow")) {
				((GraphicArrow) addPath).setLastPoint(target.getIdNode(), target.getCenterX(), target.getCenterY());
				addPath.putWeight(canvasGrid);
				realEdges.add(addPath);
			}
			addPath = null;
		}
	}
	
	
	//Méthode qui s'effectue quand on appuies sur le boutton résoudre
	@FXML
	private void resoudre() {
		
		colorLine(list.get(0), list.get(1));
	}
	
	/*
	 * Méthode permettant  de mettre la couleur verte sur un chemin en renseignant deux noeuds
	 * ( Astuce peut etre surchargé avec les identifiants unique des noeuds)
	 * */
	private void colorLine(GraphicNode firstNode, GraphicNode secondNode) {
		for(GraphicEdge edge : realEdges) {
			if((edge.getIdSource()==firstNode.getIdNode() || edge.getIdSource()==secondNode.getIdNode()) 
					&& (edge.getIdTarget()==firstNode.getIdNode() || edge.getIdTarget()==secondNode.getIdNode()) ) {
				edge.setStroke(Color.GREEN);
			}
		}
		
	}

	
	@FXML
	private void mouse(MouseEvent mouseEvent) {

		if (addPath != null) {
			addPath.setEv(mouseEvent);
			addPath.run();
		}
	}

	@FXML
	private void mousePressed(MouseEvent mouseEvent) {

		mouseEvent.consume();

	}

	@FXML
	private void mouseDragged(MouseEvent mouseEvent) {

		mouseEvent.consume();
	}

	// Methode appelé lors de l'appui du boutton "Reinitialiser" Permet d'effacer toutes les figures
	@FXML
	private void clearCanvas() {
		countID = 0;
		addPath=null;
		mode=0;
		list.clear();
		realEdges.clear();
		canvasGrid.getChildren().remove(1, canvasGrid.getChildren().size());

	}

}
