package br.fit2mapping.diagram;

import java.awt.Color;
import java.awt.Shape;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Iterator;

import br.fit2mapping.Figure;
import br.fit2mapping.Line;
import br.fit2mapping.LineSourceTarget;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.editor.FeatureDiagramEditor;
import br.fit2mapping.diagram.element.ElementFactory;
import br.fit2mapping.diagram.element.LineFactory;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.features.FeatureFigure;
import br.fit2mapping.diagram.features.LineAlternative;
import br.fit2mapping.diagram.features.LineAssociation;
import br.fit2mapping.diagram.features.LineExclude;
import br.fit2mapping.diagram.features.LineMandatory;
import br.fit2mapping.diagram.features.LineOptional;
import br.fit2mapping.diagram.features.LineRequires;
import br.fit2mapping.diagram.features.StakeHolderFigure;
import br.fit2mapping.diagram.properties.TextProperties;
import br.fit2mapping.diagram.util.DragNDropUtil;
import br.fit2mapping.diagram.util.ObjectCopyUtil;

@SuppressWarnings("serial")
public class FeatureDiagram extends Diagram {
	
	public FeatureDiagram(FeatureDiagramEditor editor){
		setDiagramEditor(editor);
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setDropTarget(new DropTarget(this, new DropTargetListener() {
			
			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
			}
			
			@Override
			public void drop(DropTargetDropEvent dtde) {

				DragNDropUtil dragNDropUtil = DragNDropUtil.getInstance();
				
				@SuppressWarnings("unused")
				String model = "";
				Iterator<Shape> shp = null;
				
				if (dragNDropUtil.getSelectedObject() instanceof DiagramEditor){
					model = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getTitle();
					shp = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getDiagram().getShapes().values().iterator();
				} else if (dragNDropUtil.getSelectedObject() instanceof FeatureFigure){
					FeatureFigure figure = ObjectCopyUtil.getInstance().getFeatureCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				} else if (dragNDropUtil.getSelectedObject() instanceof StakeHolderFigure){
					StakeHolderFigure figure = ObjectCopyUtil.getInstance().getStakeHolderCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				}

				if (shp == null){
					repaint();
					return;
				}
				
				while(shp.hasNext()){
					
					Shape shape = shp.next();
					
					if (shape instanceof StakeHolderFigure){
						StakeHolderFigure figure = ObjectCopyUtil.getInstance().getStakeHolderCopy(shape);
						getShapes().put(figure.getId(), figure);	
					}else if (shape instanceof FeatureFigure){
						FeatureFigure figure = ObjectCopyUtil.getInstance().getFeatureCopy(shape);
						getShapes().put(figure.getId(), figure);	
					}
				}

				shp = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getDiagram().getShapes().values().iterator();
				
				while(shp.hasNext()){

					Shape shape = shp.next();
					
					if (shape instanceof LineMandatory){
						LineMandatory line = ObjectCopyUtil.getInstance().getLineMandatoryCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}else if (shape instanceof LineOptional){
						LineOptional line = ObjectCopyUtil.getInstance().getLineOptionalCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}else if (shape instanceof LineAlternative){
						LineAlternative line = ObjectCopyUtil.getInstance().getLineAlternativeCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}else if (shape instanceof LineRequires){
						LineRequires line = ObjectCopyUtil.getInstance().getLineRequiresCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}else if (shape instanceof LineExclude){
						LineExclude line = ObjectCopyUtil.getInstance().getLineExcludesCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}else if (shape instanceof LineAssociation){
						LineAssociation line = ObjectCopyUtil.getInstance().getLineAssociationCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					}
				}
				
				repaint();
			}
			
			@Override
			public void dragOver(DropTargetDragEvent dtde) {}
			
			@Override
			public void dragExit(DropTargetEvent dte) {}
			
			@Override
			public void dragEnter(DropTargetDragEvent dtde) {}
		}));
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		boolean selecionado = false;
		
		setxPos(e.getX());
		setyPos(e.getY());
		
		for (Shape shape: getShapes().values()){
			if (shape instanceof Figure){
				((Figure) shape).setSelected(false);	
			}
		}
		
		for (Shape shape: getShapes().values()){
			
			RectangularShape elipse = null;
			
			if (shape instanceof Rectangle2D.Double){
				elipse = (Rectangle2D.Double) shape;	
			}

			if (shape instanceof Ellipse2D.Double){
				elipse = (Ellipse2D.Double) shape;
			}
			
			if (shape instanceof Ellipse2D.Double || shape instanceof Rectangle2D.Double){

				if ((getxPos() > elipse.getX() && getyPos() > elipse.getY())
						&& (getxPos() < (elipse.getX() + elipse.getWidth())
							&& getyPos() < (elipse.getY() + elipse.getHeight()))){
					setSelectedFigure(elipse);
				
					
					if (getSelectedFigure() instanceof Figure){
						 
						Figure figure = (Figure) getSelectedFigure();

						 if (!figure.isSelected()){
							 figure.setSelected(true);	 
							 new TextProperties(this);
						 } else {
							 figure.setSelected(false);
						 }
					}
	
					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericSource() == null){
						if (getSelectedFigure() instanceof StakeHolderFigure){
							setLineGenericSource((StakeHolderFigure) getSelectedFigure());	
						}
					}

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericTarget() != null){
						if (getLineGenericSource() != getSelectedFigure()){
							setLineGenericTarget((FeatureFigure) getSelectedFigure());
								LineFactory.getInstance().drawLineAssociation(this);	
						}
					}
					
					if ((getObjectType().equals(ObjectEnum.Mandatory) || 
							getObjectType().equals(ObjectEnum.Optional) || 
							getObjectType().equals(ObjectEnum.Alternative) || 
							getObjectType().equals(ObjectEnum.Requires) || 
							getObjectType().equals(ObjectEnum.Excludes)) && getLineGenericSource() == null){
						
						if (getSelectedFigure() instanceof FeatureFigure){
							setLineGenericSource((FeatureFigure) getSelectedFigure());	
						}
					}
					
					if ((getObjectType().equals(ObjectEnum.Mandatory) || 
							getObjectType().equals(ObjectEnum.Optional) || 
							getObjectType().equals(ObjectEnum.Alternative) || 
							getObjectType().equals(ObjectEnum.Requires) || 
							getObjectType().equals(ObjectEnum.Excludes)) && getLineGenericTarget() != null){

						if (getLineGenericSource() != getSelectedFigure()){
							if (getSelectedFigure() instanceof FeatureFigure){
								setLineGenericTarget((FeatureFigure) getSelectedFigure());

								if (getObjectType().equals(ObjectEnum.Mandatory)){
									LineFactory.getInstance().drawLineMandatory(this);	
								} else if (getObjectType().equals(ObjectEnum.Optional)){
									LineFactory.getInstance().drawLineOptional(this);
								} else if (getObjectType().equals(ObjectEnum.Alternative)){
									LineFactory.getInstance().drawLineAlternative(this);
								} else if (getObjectType().equals(ObjectEnum.Requires)){
									LineFactory.getInstance().drawLineRequires(this);
								} else if (getObjectType().equals(ObjectEnum.Excludes)){
									LineFactory.getInstance().drawLineExcludes(this);
								}
							}
						}
					}
					
					selecionado = true;
					break;
				}
			}
		}
		
		if (!selecionado){
			if (getObjectType().equals(ObjectEnum.None)){
				return;
			}
			
			if (getObjectType().equals(ObjectEnum.Feature)){
				ElementFactory.getInstance().drawFeature(this);
			}

			if (getObjectType().equals(ObjectEnum.Stakeholder)){
				ElementFactory.getInstance().drawStakeHolder(this);
			}
		}
		
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		setxPos(e.getX());
		setyPos(e.getY());
		
		for (Shape shape: getShapes().values()){
			if (shape instanceof Figure){
				((Figure) shape).setSelected(false);	
			}
			
			if (shape instanceof LineSourceTarget){
				((LineSourceTarget) shape).setSelected(false);	
			}
		}
		
		for (Shape shape: getShapes().values()){
			
			RectangularShape elipse = null;
			LineSourceTarget line = null;
			
			if (shape instanceof Rectangle2D.Double){
				elipse = (Rectangle2D.Double) shape;	
			}

			if (shape instanceof Ellipse2D.Double){
				elipse = (Ellipse2D.Double) shape;
			}
			
			if (shape instanceof Line){
			 	line = (LineSourceTarget) shape;
			}
			
			if (shape instanceof Ellipse2D.Double || shape instanceof Rectangle2D.Double){
				
				setSelectedFigure(null);
				
				if ((getxPos() > elipse.getX() && getyPos() > elipse.getY())
							&& (getxPos() < (elipse.getX() + elipse.getWidth())
								&& getyPos() < (elipse.getY() + elipse.getHeight()))){
					setSelectedFigure(elipse);

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericSource() == null){
						if (getSelectedFigure() instanceof StakeHolderFigure){
							setLineGenericSource((StakeHolderFigure) getSelectedFigure());
						}
					}

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericTarget() == null){
						if (getLineGenericSource() != getSelectedFigure()){
							setLineGenericTarget((FeatureFigure) getSelectedFigure());
						}
					}

					if ((getObjectType().equals(ObjectEnum.Mandatory) || 
							getObjectType().equals(ObjectEnum.Optional) || 
								getObjectType().equals(ObjectEnum.Alternative) || 
									getObjectType().equals(ObjectEnum.Requires) || 
									getObjectType().equals(ObjectEnum.Excludes)) && getLineGenericSource() == null){
						
						if (getSelectedFigure() instanceof FeatureFigure){
							setLineGenericSource((FeatureFigure) getSelectedFigure());	
						}
					}
					
					if ((getObjectType().equals(ObjectEnum.Mandatory) || 
							getObjectType().equals(ObjectEnum.Optional) || 
								getObjectType().equals(ObjectEnum.Alternative) || 
									getObjectType().equals(ObjectEnum.Requires) || 
									getObjectType().equals(ObjectEnum.Excludes)) && getLineGenericTarget() == null){

						if (getLineGenericSource() != getSelectedFigure()){
							if (getSelectedFigure() instanceof FeatureFigure){
								setLineGenericTarget((FeatureFigure) getSelectedFigure());
							}
						}
					}
					
					break;
				}
			}
			
			if (line != null){
				if ((getxPos() > (int) line.getX1()-5 && getxPos() < (int) line.getX2()+5) 
						&& (getyPos() > (int) line.getY1()-5 && getyPos() < (int) line.getY2()+5)){
					setSelectedFigure(line);
					line.setSelected(true);
					break;
				}
				
				if ((getxPos() > (int) line.getX2()-5 && getxPos() < (int) line.getX2()+5) 
						&& (getyPos() > (int) line.getY2()-5 && getyPos() < (int) line.getY2()+5)){
					setSelectedFigure(line);
					line.setSelected(true);
					break;
				}
			}		
		}

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}

}
