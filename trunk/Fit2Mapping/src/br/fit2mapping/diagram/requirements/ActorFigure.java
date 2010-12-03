package br.fit2mapping.diagram.requirements;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.fit2mapping.Figure;

@SuppressWarnings("serial")
public class ActorFigure extends Figure {
	
	public ActorFigure() {
		super();
	}

	public ActorFigure(double arg0, double arg1) {
		super(arg0, arg1, 35, 61);
	}
	
	public void draw(Graphics2D graphics2d){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ActorFigure.class.getResourceAsStream("/img/ator.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		FontMetrics metrics = graphics2d.getFontMetrics();

		int pos = (metrics.stringWidth(this.getName()) - (int) this.width) /2;
		
		graphics2d.drawImage(img, (int) x, (int) y, null);
		graphics2d.drawString(this.getName(), (int) (this.x - pos) , (int) (this.y + this.getHeight() - 5));
		
		getSelected(graphics2d);
	}
}
