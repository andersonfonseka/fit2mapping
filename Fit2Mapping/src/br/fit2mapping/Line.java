package br.fit2mapping;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Line extends Line2D.Double {

	private Integer id;
	
	private String name;
	
	private int posX, posY;
	private int posX2, posY2;
	
	public Line() {
		super();
	}

	public Line(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
		
		setPosX((int) arg0);
		setPosY((int) arg1);
		setPosX2((int) arg2);
		setPosY2((int) arg3);
	}

	public Line(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void draw(Graphics2D graphics2d){
		graphics2d.setColor(Color.BLACK);
		graphics2d.setStroke(new BasicStroke());
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX2() {
		return posX2;
	}

	public void setPosX2(int posX2) {
		this.posX2 = posX2;
	}

	public int getPosY2() {
		return posY2;
	}

	public void setPosY2(int posY2) {
		this.posY2 = posY2;
	}
	
}
