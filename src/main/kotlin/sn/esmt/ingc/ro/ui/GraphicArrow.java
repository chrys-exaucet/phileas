package sn.esmt.ingc.ro.ui;

import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

public class GraphicArrow extends GraphicEdge {
	public static final int SIZE_ARROW_HEAD = 5;

	public GraphicArrow(int idSource, int idTarget, double weight, double startX, double startY, double endX,
			double endY) {
		super(idSource, idTarget, weight, startX, startY, endX, endY);
		drawHeadArrow(idTarget, endX, endY);

	}

	public GraphicArrow(int idSource, double startX, double startY) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.getElements().add(new MoveTo(startX, startY));
		this.idSource = idSource;

	}

	public void drawHeadArrow(int idTarget, double endX, double endY) {
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
