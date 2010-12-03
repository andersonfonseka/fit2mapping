package br.fit2mapping.diagram.features;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import br.fit2mapping.Figure;
import br.fit2mapping.LineSourceTarget;

@SuppressWarnings("serial")
public class LineAssociation extends LineSourceTarget {
	
	public LineAssociation() {
		super();
	}

	public LineAssociation(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public LineAssociation(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}
	
	public LineAssociation(Figure source, Figure target) {
		super(source, target);
	}

	public void draw(Graphics2D graphics2d){
		
		this.x1 = getPosX();
		this.x2 = getPosX2();
		this.y1 = getPosY();
		this.y2 = getPosY2();

		graphics2d.draw(this);
		
		if (isSelected()){
			
			Rectangle2D.Double quadrosupesq = new Rectangle2D.Double(this.x1, this.y1, 5, 5);
			Rectangle2D.Double quadroinfdir = new Rectangle2D.Double(this.x2, this.y2, 5, 5);
			
			graphics2d.setColor(Color.RED);
			graphics2d.fill(quadrosupesq);
			graphics2d.draw(quadrosupesq);
			graphics2d.setColor(Color.BLACK);
			
			graphics2d.setColor(Color.RED);
			graphics2d.fill(quadroinfdir);
			graphics2d.draw(quadroinfdir);
			graphics2d.setColor(Color.BLACK);
		}
	}
}
