package br.fit2mapping.diagram.element;

import br.fit2mapping.LineSourceTarget;
import br.fit2mapping.diagram.Diagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.features.LineAlternative;
import br.fit2mapping.diagram.features.LineAssociation;
import br.fit2mapping.diagram.features.LineExclude;
import br.fit2mapping.diagram.features.LineMandatory;
import br.fit2mapping.diagram.features.LineOptional;
import br.fit2mapping.diagram.features.LineRequires;
import br.fit2mapping.diagram.mapping.LineMapping;
import br.fit2mapping.diagram.repository.FeatureRepository;
import br.fit2mapping.diagram.requirements.LineDependency;
import br.fit2mapping.diagram.requirements.LineExtend;
import br.fit2mapping.diagram.requirements.LineInclude;
import br.fit2mapping.diagram.toolbar.ExplorerTreeViewBar;
import br.fit2mapping.domain.Feature;
import br.fit2mapping.domain.Relationship;
import br.fit2mapping.domain.RelationshipRuleType;
import br.fit2mapping.domain.RelationshipType;
import br.fit2mapping.domain.engine.FeatureManager;
import br.fit2mapping.domain.engine.RelationshipManager;

public class LineFactory {

	private static LineFactory instance;
	
	private FeatureManager featureManager = FeatureManager.getInstance();
	
	public static LineFactory getInstance(){
		if (instance == null) {
			instance = new LineFactory();
		}
		return instance;
	}

	private LineFactory(){}
	
	public void drawLineMandatory(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineMandatory double1 = new LineMandatory(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			
			Feature featureSource = featureManager.getFeature(diagram.getLineGenericSource().getId());
			Feature featureTarget = featureManager.getFeature(diagram.getLineGenericTarget().getId());
			featureSource.addRelationship(featureTarget, RelationshipType.Mandatory);
			
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}
	
	public void drawLineOptional(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineOptional double1 = new LineOptional(diagram.getLineGenericSource(), diagram.getLineGenericTarget());

			Feature featureSource = featureManager.getFeature(diagram.getLineGenericSource().getId());
			Feature featureTarget = featureManager.getFeature(diagram.getLineGenericTarget().getId());
			featureSource.addRelationship(featureTarget, RelationshipType.Optional);
			
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}

	public void drawLineAlternative(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineAlternative double1 = new LineAlternative(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			
			Feature featureSource = featureManager.getFeature(diagram.getLineGenericSource().getId());
			Feature featureTarget = featureManager.getFeature(diagram.getLineGenericTarget().getId());
			featureSource.addRelationship(featureTarget, RelationshipType.Alternative);
			
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}
	
	public void drawLineRequires(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineRequires double1 = new LineRequires(diagram.getLineGenericSource(), diagram.getLineGenericTarget());

			Feature featureSource = featureManager.getFeature(diagram.getLineGenericSource().getId());
			Relationship relationship = RelationshipManager.getInstance().getRelationshipByTarget(featureSource);
			
			Feature featureTarget = featureManager.getFeature(diagram.getLineGenericTarget().getId());
			relationship.addRelationshipRule(featureTarget, RelationshipRuleType.Requires);

			
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}

	public void drawLineExcludes(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineExclude double1 = new LineExclude(diagram.getLineGenericSource(), diagram.getLineGenericTarget());

			Feature featureSource = featureManager.getFeature(diagram.getLineGenericSource().getId());
			Relationship relationship = RelationshipManager.getInstance().getRelationshipByTarget(featureSource);
			
			Feature featureTarget = featureManager.getFeature(diagram.getLineGenericTarget().getId());
			relationship.addRelationshipRule(featureTarget, RelationshipRuleType.Excludes);
			
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}
	
	public void drawLineAssociation(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineAssociation double1 = new LineAssociation(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}

	public void drawLineMapping(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineMapping double1 = new LineMapping(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}
	
	public void drawLineInclude(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineInclude double1 = new LineInclude(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}

	public void drawLineExtend(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineExtend double1 = new LineExtend(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}

	public void drawLineDependency(Diagram diagram){
		if (diagram.getLineGenericSource() != null && diagram.getLineGenericTarget() != null){
			LineDependency double1 = new LineDependency(diagram.getLineGenericSource(), diagram.getLineGenericTarget());
			completeLineDraw(diagram,double1);
			
			ExplorerTreeViewBar.getInstance().addDiagram(double1);
		}
	}
	
	private void completeLineDraw(Diagram diagram, LineSourceTarget line) {

		FeatureRepository repository = FeatureRepository.getInstance();
		
		line.setId(FeatureRepository.getElementCount());
		
		repository.addContent(line);
		diagram.getShapes().put(line.getId(), line);

		diagram.setLineGenericSource(null);
		diagram.setLineGenericTarget(null);
		diagram.setObjectType(ObjectEnum.None);
	}
	
}
