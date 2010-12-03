package br.fit2mapping;

import java.awt.geom.Point2D;


@SuppressWarnings("serial")
public class LineSourceTarget extends Line {

	private Figure source;
	
	private Figure target;
	
	private boolean deleted;
	
	private boolean selected;

	
	public LineSourceTarget() {
		super();
	}

	public LineSourceTarget(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	public LineSourceTarget(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}
	
	public LineSourceTarget(Figure source,
			Figure target) {
		super();
		this.source = source;
		this.target = target;
	}

	@Override
	public String getName() {
		return this.getSource().getName() + " - " + this.getTarget().getName();
	}

	
	public Figure getSource() {
		return source;
	}

	public void setSource(Figure source) {
		this.source = source;
	}

	public Figure getTarget() {
		return target;
	}

	public void setTarget(Figure target) {
		this.target = target;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int getPosX() {
		
		int posX = 0;
		
		if ((source.x + source.width) < target.x){
			posX = (int) (source.x + source.width);	
		} else if (source.x > (target.x + target.width)){
			posX = (int) source.x;	
		} else  if (target.x > source.x  && (target.x < (source.getX() + source.width))){
			posX = (int) (source.x + (source.width / 2)) ;	
		} else {
			posX = (int) (source.x + (source.width / 2)) ;
		}
		
		return posX;
	}

	@Override
	public int getPosY() {
		
		int posY = 0;
		
		if (source.y < target.y){
			posY = (int) (source.y + (source.height));
		} else  if (source.y > target.y){
			posY = (int) (source.y);
		} else if (source.y == target.y){
			posY = (int) (source.y + (source.height / 2));
		}
		
		return posY; 
	}

	@Override
	public int getPosX2() {

		int posX = 0;
		
		if ((target.x + target.width) < source.x){
			posX = (int) (target.x + target.width);	
		} else if (target.x > (source.x + source.width)){
			posX = (int) target.x;	
		} else  if (source.x > target.x  && (source.x < (target.x + target.width))){
			posX = (int) (target.x + (target.width / 2)) ;	
		} else {
			posX = (int) (target.x + (target.width / 2)) ;
		}
		
		return posX;
	}

	@Override
	public int getPosY2() {

		int posY = 0;
		
		if (target.y < source.y){
			posY = (int) (target.y + (target.height));
		} else  if (target.y > source.y){
			posY = (int) (target.y);
		} else if (target.y == source.y){
			posY = (int) (target.y + (target.height / 2));
		}
		
		return posY; 
	}
}
