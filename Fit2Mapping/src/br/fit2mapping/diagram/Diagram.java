package br.fit2mapping.diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import br.fit2mapping.Figure;
import br.fit2mapping.Line;
import br.fit2mapping.LineSourceTarget;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.enums.ObjectEnum;

@SuppressWarnings("serial")
public abstract class Diagram extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	private Shape selectedFigure;
	private ObjectEnum objectType = ObjectEnum.None;

	private int xPos;
	private int yPos;

	private Figure lineGenericSource;
	private Figure lineGenericTarget;

	private DiagramEditor diagramEditor;

	private Integer elementCount = 1;

	private Map<Integer, Shape> shapes = new HashMap<Integer, Shape>();

	public ObjectEnum getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectEnum objectType) {
		this.objectType = objectType;
	}

	public Shape getSelectedFigure() {
		return selectedFigure;
	}

	public void setSelectedFigure(Shape selectedFigure) {
		this.selectedFigure = selectedFigure;
	}

	public Map<Integer, Shape> getShapes() {
		return shapes;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Figure getLineGenericSource() {
		return lineGenericSource;
	}

	public void setLineGenericSource(Figure lineGenericSource) {
		this.lineGenericSource = lineGenericSource;
	}

	public Figure getLineGenericTarget() {
		return lineGenericTarget;
	}

	public void setLineGenericTarget(Figure lineGenericTarget) {
		this.lineGenericTarget = lineGenericTarget;
	}

	public DiagramEditor getDiagramEditor() {
		return diagramEditor;
	}

	public void setDiagramEditor(DiagramEditor diagramEditor) {
		this.diagramEditor = diagramEditor;
	}

	public Integer getElementCount() {
		return elementCount;
	}

	public void setElementCount(Integer elementCount) {
		this.elementCount = elementCount;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		setxPos(e.getX());
		setyPos(e.getY());

		if (getSelectedFigure() instanceof Figure){
			Figure double1 = (Figure) getSelectedFigure();
			double1.x = getxPos() - (double1.width / 2);
			double1.y = getyPos() - (double1.height / 2);
		}
		
		repaint();
	}


	public void deleteSelected(){
		
		if (getSelectedFigure() instanceof Line){
			getShapes().remove(((Line) getSelectedFigure()).getId());
			
		}
		
		if (getSelectedFigure() instanceof Figure){
			
			Iterator<Shape> shps = getShapes().values().iterator();
			
			while(shps.hasNext()){
				
				Shape shp = shps.next();
				
				if (shp instanceof LineSourceTarget){
					LineSourceTarget sourceTarget = (LineSourceTarget) shp;
					
					if (sourceTarget.getSource().equals(getSelectedFigure())){
						sourceTarget.setDeleted(true);
					}
					
					if (sourceTarget.getTarget().equals(getSelectedFigure())){
						sourceTarget.setDeleted(true);
					}
				}
			}
			getShapes().remove(((Figure) getSelectedFigure()).getId());
		}
		
		repaint();
	}


	@Override
	public void paint(Graphics g) {
	
		super.paint(g);
		
		Graphics2D graphics2d = (Graphics2D) g;
		
		for (Shape shape: getShapes().values()){
			if (shape instanceof Figure){
				((Figure) shape).draw(graphics2d);
			}

			if (shape instanceof LineSourceTarget && !((LineSourceTarget) shape).isDeleted()){
				((LineSourceTarget) shape).draw(graphics2d);
			}
			
			graphics2d.setColor(Color.BLACK);
		}
	}
}
