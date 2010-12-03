package br.fit2mapping.diagram.design;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Component extends Rectangle2D.Double {
	
	private String name;
	
	public Component() {
		super();
	}

	public Component(double arg0, double arg1) {
		super(arg0, arg1, 70, 38);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void draw(Graphics2D graphics2d){
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(Component.class.getResourceAsStream("/img/component.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		graphics2d.drawImage(img, (int) x, (int) y, null);
		graphics2d.drawString(this.getName(), (int) this.x, (int) (this.y + this.getHeight() + 14));
	}
}
