package br.fit2mapping;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import br.fit2mapping.diagram.bean.FigureBean;
import br.fit2mapping.diagram.properties.FigureProperties;
import br.fit2mapping.diagram.properties.Properties;

@SuppressWarnings("serial")
public abstract class Figure extends Rectangle2D.Double {

	private FigureBean figureBean;
	
	private boolean selected;
	
	private Properties properties;
	
	public Figure() {
		super();
	}

	public Figure(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public FigureBean getFigureBean() {
		if (figureBean == null) {
			figureBean = new FigureBean();
		}
		return figureBean;
	}

	public void setFigureBean(FigureBean figureBean) {
		this.figureBean = figureBean;
	}

	public void showProperties(){
		setProperties(FigureProperties.getInstance(this.getFigureBean()));
		properties.setVisible(true);
		properties.show();
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties prop){
		properties = prop;
	}
	
	public String getName() {
		return getFigureBean().getName();
	}

	public void setName(String name) {
		this.getFigureBean().setName(name);
	}
	
	public Integer getId() {
		return getFigureBean().getId();
	}

	public void setId(Integer id) {
		this.getFigureBean().setId(id);
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void draw(Graphics2D graphics2d){};
	
	public void getSelected(Graphics2D graphics2d) {
		if (isSelected()){
			Rectangle2D.Double quadrosupesq = new Rectangle2D.Double(this.x-5, this.y-5, 5, 5);
			Rectangle2D.Double quadrosupdir = new Rectangle2D.Double((this.x + this.width), this.y-5, 5, 5);
			Rectangle2D.Double quadroinfesq = new Rectangle2D.Double(this.x-5, (this.y + this.height), 5, 5);
			Rectangle2D.Double quadroinfdir = new Rectangle2D.Double((this.x + this.width), (this.y + this.height), 5, 5);
			
			graphics2d.setColor(Color.RED);
			graphics2d.fill(quadrosupesq);
			graphics2d.draw(quadrosupesq);
			
			graphics2d.fill(quadroinfesq);
			graphics2d.draw(quadroinfesq);
			
			graphics2d.fill(quadrosupdir);
			graphics2d.draw(quadrosupdir);
			
			graphics2d.fill(quadroinfdir);
			graphics2d.draw(quadroinfdir);
		}
	}

}
