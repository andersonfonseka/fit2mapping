package br.fit2mapping.diagram.requirements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import br.fit2mapping.Figure;

@SuppressWarnings("serial")
public class StepFigure extends Figure {
	
	public StepFigure() {
		super();
	}

	public StepFigure(double arg0, double arg1) {
		super(arg0, arg1, 60,20);
	}

	public void draw(Graphics2D graphics2d){
		
		Font font = new Font("Dialog", Font.BOLD, 12);
		
		FontMetrics metrics = graphics2d.getFontMetrics();
		this.width = metrics.stringWidth(this.getName()) + 10;
		this.height = metrics.getHeight() + 5;
		
		graphics2d.setFont(font);
		graphics2d.setStroke(new BasicStroke());
		graphics2d.setColor(Color.WHITE);
		graphics2d.fill(this);
		graphics2d.setColor(Color.BLACK);
		graphics2d.drawString(this.getName(), (int) this.x + 5, (int) (this.y + 15));
		graphics2d.draw(this);
		
		getSelected(graphics2d);
	}
}
