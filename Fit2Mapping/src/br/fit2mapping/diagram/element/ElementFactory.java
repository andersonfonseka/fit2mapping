package br.fit2mapping.diagram.element;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.Diagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.features.FeatureFigure;
import br.fit2mapping.diagram.features.StakeHolderFigure;
import br.fit2mapping.diagram.repository.FeatureRepository;
import br.fit2mapping.diagram.requirements.ActorFigure;
import br.fit2mapping.diagram.requirements.RequirementFigure;
import br.fit2mapping.diagram.requirements.ScenarioFigure;
import br.fit2mapping.diagram.requirements.StepFigure;
import br.fit2mapping.diagram.toolbar.ExplorerTreeViewBar;
import br.fit2mapping.domain.Feature;

public class ElementFactory {

	private static ElementFactory instance;
	
	public static ElementFactory getInstance(){
		if (instance == null) {
			instance = new ElementFactory();
		}
		return instance;
	}

	private ElementFactory(){}
	
	public void drawFeature(Diagram diagram){
		FeatureFigure double1 = new FeatureFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double1);
		
		Feature feature = new Feature(double1.getName());
		feature.setId(double1.getId());
		feature.save();
		
		ExplorerTreeViewBar.getInstance().addDiagram(double1);
	}
	
	public void drawStakeHolder(Diagram diagram){
		StakeHolderFigure double2 = new StakeHolderFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double2);
		ExplorerTreeViewBar.getInstance().addDiagram(double2);
	}

	public void drawRequirement(Diagram diagram){
		RequirementFigure double1 = new RequirementFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double1);
		ExplorerTreeViewBar.getInstance().addDiagram(double1);
	}

	public void drawScenario(Diagram diagram){
		ScenarioFigure double1 = new ScenarioFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double1);
		ExplorerTreeViewBar.getInstance().addDiagram(double1);
	}

	public void drawStep(Diagram diagram){
		StepFigure double1 = new StepFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double1);
		ExplorerTreeViewBar.getInstance().addDiagram(double1);
	}
	
	public void drawActor(Diagram diagram){
		ActorFigure double1 = new ActorFigure(diagram.getxPos(), diagram.getyPos());
		completeFigureDraw(diagram, double1);
		ExplorerTreeViewBar.getInstance().addDiagram(double1);
	}

	private void completeFigureDraw(Diagram diagram, Figure figure) {

		FeatureRepository repository = FeatureRepository.getInstance();
		
		figure.setId(FeatureRepository.getElementCount());
		figure.setName(figure.getClass().getSimpleName() + " " + FeatureRepository.getElementCount());
		
		repository.addContent(figure);
		diagram.getShapes().put(figure.getId(), figure);
		
		diagram.setSelectedFigure(figure);
		diagram.setObjectType(ObjectEnum.None);
	}

	
}
