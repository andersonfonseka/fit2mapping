package br.fit2mapping.diagram.features;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import br.fit2mapping.Figure;
import br.fit2mapping.LineSourceTarget;

@SuppressWarnings("serial")
public class LineOptional extends LineSourceTarget {

	
	public LineOptional() {
		super();
	}

	public LineOptional(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}
	
	public LineOptional(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}

	public LineOptional(Figure source, Figure target) {
		super(source, target);
	}

	
	public void draw(Graphics2D graphics2d){
		
		Ellipse2D.Double double1 = new Ellipse2D.Double(getPosX2() - 3, getPosY2() - 3, 6, 6);

		this.x1 = getPosX();
		this.x2 = getPosX2();
		this.y1 = getPosY();
		this.y2 = getPosY2();
		
		graphics2d.draw(this);
		graphics2d.setColor(Color.WHITE);
		graphics2d.fill(double1);
		graphics2d.setColor(Color.BLACK);
		graphics2d.draw(double1);
	}
}
