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
import br.fit2mapping.LineSourceTarget;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.editor.MappingDiagramEditor;
import br.fit2mapping.diagram.element.ElementFactory;
import br.fit2mapping.diagram.element.LineFactory;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.features.FeatureFigure;
import br.fit2mapping.diagram.features.LineAlternative;
import br.fit2mapping.diagram.features.LineAssociation;
import br.fit2mapping.diagram.features.LineMandatory;
import br.fit2mapping.diagram.features.LineOptional;
import br.fit2mapping.diagram.properties.TextProperties;
import br.fit2mapping.diagram.requirements.ActorFigure;
import br.fit2mapping.diagram.requirements.RequirementFigure;
import br.fit2mapping.diagram.requirements.ScenarioFigure;
import br.fit2mapping.diagram.requirements.StepFigure;
import br.fit2mapping.diagram.util.DragNDropUtil;
import br.fit2mapping.diagram.util.ObjectCopyUtil;

@SuppressWarnings("serial")
public class MappingDiagram extends Diagram {
	
	public MappingDiagram(MappingDiagramEditor editor){
		setDiagramEditor(editor);
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setDropTarget(new DropTarget(this, new DropTargetListener() {
			
			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
			}
			
			@SuppressWarnings("unused")
			@Override
			public void drop(DropTargetDropEvent dtde) {
				
				DragNDropUtil dragNDropUtil = DragNDropUtil.getInstance();
				
				String model = "";
				Iterator<Shape> shp = null;
				
				if (dragNDropUtil.getSelectedObject() instanceof DiagramEditor){
					model = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getTitle();
					shp = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getDiagram().getShapes().values().iterator();
				} else if (dragNDropUtil.getSelectedObject() instanceof ActorFigure){
					ActorFigure figure = ObjectCopyUtil.getInstance().getActorCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				} else if (dragNDropUtil.getSelectedObject() instanceof RequirementFigure){
					RequirementFigure figure = ObjectCopyUtil.getInstance().getRequirementCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				} else if (dragNDropUtil.getSelectedObject() instanceof ScenarioFigure){
					ScenarioFigure figure = ObjectCopyUtil.getInstance().getScenarioCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				} else if (dragNDropUtil.getSelectedObject() instanceof StepFigure){
					StepFigure figure = ObjectCopyUtil.getInstance().getStepCopy((Shape) dragNDropUtil.getSelectedObject());
					getShapes().put(figure.getId(), figure);	
				}

				if (shp == null){
					repaint();
					return;
				}
				
				while(shp.hasNext()){
					
					Shape shape = shp.next();
					
					if (shape instanceof ActorFigure){
						ActorFigure figure = ObjectCopyUtil.getInstance().getActorCopy(shape);
						getShapes().put(figure.getId(), figure);	
					} else if (shape instanceof RequirementFigure){
						RequirementFigure figure = ObjectCopyUtil.getInstance().getRequirementCopy(shape);
						getShapes().put(figure.getId(), figure);	
					} else if (shape instanceof ScenarioFigure){
						ScenarioFigure figure = ObjectCopyUtil.getInstance().getScenarioCopy(shape);
						getShapes().put(figure.getId(), figure);
					} else if (shape instanceof StepFigure){
						StepFigure figure = ObjectCopyUtil.getInstance().getStepCopy(shape);
						getShapes().put(figure.getId(), figure);
					} else if (shape instanceof FeatureFigure){
						FeatureFigure figure = ObjectCopyUtil.getInstance().getFeatureCopy(shape);
						getShapes().put(figure.getId(), figure);
					}
				}

				shp = ((DiagramEditor) dragNDropUtil.getSelectedObject()).getDiagram().getShapes().values().iterator();
				
				while(shp.hasNext()){

					Shape shape = shp.next();
					
					if (shape instanceof LineAssociation){
						LineAssociation line = ObjectCopyUtil.getInstance().getLineAssociationCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					} else if (shape instanceof LineMandatory){
						LineMandatory line = ObjectCopyUtil.getInstance().getLineMandatoryCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					} else if (shape instanceof LineOptional){
						LineOptional line = ObjectCopyUtil.getInstance().getLineOptionalCopy(shape, getShapes());
						getShapes().put(line.getId(), line);
					} else if (shape instanceof LineAlternative){
						LineAlternative line = ObjectCopyUtil.getInstance().getLineAlternativeCopy(shape, getShapes());
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
	
					if ((getObjectType().equals(ObjectEnum.Association)) 
													&& getLineGenericSource() == null){
							if (getSelectedFigure() instanceof FeatureFigure){
								setLineGenericSource((FeatureFigure) getSelectedFigure());	
							}
					}

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericTarget() != null){
						if (getLineGenericSource() != getSelectedFigure()){
							
							if (getSelectedFigure() instanceof RequirementFigure){
								RequirementFigure scenario = (RequirementFigure) getSelectedFigure();
								setLineGenericTarget(scenario);
							} else if (getSelectedFigure() instanceof ScenarioFigure){
								ScenarioFigure scenario = (ScenarioFigure) getSelectedFigure();
								setLineGenericTarget(scenario);
							} else if (getSelectedFigure() instanceof StepFigure){
								StepFigure scenario = (StepFigure) getSelectedFigure();
								setLineGenericTarget(scenario);
							}
								
							LineFactory.getInstance().drawLineMapping(this);	
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
			
			if (getObjectType().equals(ObjectEnum.Scenario)){
				ElementFactory.getInstance().drawScenario(this);	
			}

			if (getObjectType().equals(ObjectEnum.Step)){
				ElementFactory.getInstance().drawStep(this);
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
			
			if (shape instanceof LineSourceTarget){
			 	line = (LineSourceTarget) shape;
			}
			
			if (shape instanceof Ellipse2D.Double || shape instanceof Rectangle2D.Double){
				
				setSelectedFigure(null);
				
				if ((getxPos() > elipse.getX() && getyPos() > elipse.getY())
							&& (getxPos() < (elipse.getX() + elipse.getWidth())
								&& getyPos() < (elipse.getY() + elipse.getHeight()))){
					setSelectedFigure(elipse);

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericSource() == null){
						if (getSelectedFigure() instanceof FeatureFigure){
							setLineGenericSource((FeatureFigure) getSelectedFigure());	
						}
					}

					if (getObjectType().equals(ObjectEnum.Association) && getLineGenericTarget() == null){
						if (getLineGenericSource() != getSelectedFigure()){

							if (getSelectedFigure() instanceof RequirementFigure){
								setLineGenericTarget((RequirementFigure) getSelectedFigure());
							}
							
							if (getSelectedFigure() instanceof ScenarioFigure){
								setLineGenericTarget((ScenarioFigure) getSelectedFigure());
							}

							if (getSelectedFigure() instanceof StepFigure){
								setLineGenericTarget((StepFigure) getSelectedFigure());
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
