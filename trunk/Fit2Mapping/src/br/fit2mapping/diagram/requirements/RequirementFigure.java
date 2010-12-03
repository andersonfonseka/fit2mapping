package br.fit2mapping.diagram.requirements;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.bean.RequirementBean;
import br.fit2mapping.diagram.properties.RequirementProperties;

@SuppressWarnings("serial")
public class RequirementFigure extends Figure {

	private RequirementBean requirementBean;
	
	private Color color;
	
	public RequirementFigure() {
		super();
	}

	public RequirementFigure(double arg0, double arg1) {
		super(arg0, arg1, 70, 55);
	}
	
	@SuppressWarnings("static-access")
	public Color getColor() {
		return color.YELLOW;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void showProperties(){
		setProperties(RequirementProperties.getInstance(this.getRequirementBean()));
		getProperties().setVisible(true);
		getProperties().show();
	}

	public void draw(Graphics2D graphics2d){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ActorFigure.class.getResourceAsStream("/img/requirement.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		FontMetrics metrics = graphics2d.getFontMetrics();

		int pos = (metrics.stringWidth(this.getName()) - (int) this.width) /2;
		
		graphics2d.drawImage(img, (int) x, (int) y, null);
		graphics2d.drawString(this.getName(), (int) (this.x - pos) , (int) (this.y + this.getHeight() - 5));
		
		getSelected(graphics2d);
	}

	public RequirementBean getRequirementBean() {
		if (requirementBean == null) {
			requirementBean = new RequirementBean(getFigureBean()); 
		}
		return requirementBean;
	}

	public void setRequirementBean(RequirementBean requirementBean) {
		this.requirementBean = requirementBean;
	}
	
}