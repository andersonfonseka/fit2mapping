package br.fit2mapping.diagram.requirements;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import br.fit2mapping.Figure;
import br.fit2mapping.LineSourceTarget;

@SuppressWarnings("serial")
public class LineExtend extends LineSourceTarget {
	
	public LineExtend() {
		super();
	}

	public LineExtend(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public LineExtend(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}
	
	public LineExtend(Figure source, Figure target) {
		super(source, target);
	}

	public void draw(Graphics2D graphics2d){
		Stroke s = new BasicStroke(0.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 15.0f, new float[]{10.0f,10.0f}, 0.0f);
		
		this.x1 = getPosX();
		this.x2 = getPosX2();
		this.y1 = getPosY();
		this.y2 = getPosY2();

//		FontMetrics metrics = graphics2d.getFontMetrics();
		//-  metrics.stringWidth("<<extend>>")
		
		graphics2d.setStroke(s);
		graphics2d.drawString("<<extend>>", (int) (x1 + x2) /2 , (int) (y1 + y2)/2);
		graphics2d.draw(this);
	}
	
}
