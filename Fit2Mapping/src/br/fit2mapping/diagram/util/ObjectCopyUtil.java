package br.fit2mapping.diagram.util;

import java.awt.Shape;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.features.FeatureFigure;
import br.fit2mapping.diagram.features.LineAlternative;
import br.fit2mapping.diagram.features.LineAssociation;
import br.fit2mapping.diagram.features.LineExclude;
import br.fit2mapping.diagram.features.LineMandatory;
import br.fit2mapping.diagram.features.LineOptional;
import br.fit2mapping.diagram.features.LineRequires;
import br.fit2mapping.diagram.features.StakeHolderFigure;
import br.fit2mapping.diagram.requirements.ActorFigure;
import br.fit2mapping.diagram.requirements.LineDependency;
import br.fit2mapping.diagram.requirements.LineExtend;
import br.fit2mapping.diagram.requirements.LineInclude;
import br.fit2mapping.diagram.requirements.RequirementFigure;
import br.fit2mapping.diagram.requirements.ScenarioFigure;
import br.fit2mapping.diagram.requirements.StepFigure;

public class ObjectCopyUtil {

	private static ObjectCopyUtil instance;
	
	private ObjectCopyUtil(){}
	
	public static ObjectCopyUtil getInstance(){
		if (instance == null) {
			instance = new ObjectCopyUtil();
		}
		return instance;
	}

	public StakeHolderFigure getStakeHolderCopy(Shape fig){
		StakeHolderFigure figureDest = new StakeHolderFigure();
		if (fig instanceof StakeHolderFigure){
			StakeHolderFigure figure = (StakeHolderFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}
	
	public FeatureFigure getFeatureCopy(Shape fig){
		FeatureFigure figureDest = new FeatureFigure();
		if (fig instanceof FeatureFigure){
			FeatureFigure figure = (FeatureFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}

	public ActorFigure getActorCopy(Shape fig){
		ActorFigure figureDest = new ActorFigure();
		if (fig instanceof ActorFigure){
			ActorFigure figure = (ActorFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}

	public RequirementFigure getRequirementCopy(Shape fig){
		RequirementFigure figureDest = new RequirementFigure();
		if (fig instanceof RequirementFigure){
			RequirementFigure figure = (RequirementFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}

	public ScenarioFigure getScenarioCopy(Shape fig){
		ScenarioFigure figureDest = new ScenarioFigure();
		if (fig instanceof ScenarioFigure){
			ScenarioFigure figure = (ScenarioFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}

	public StepFigure getStepCopy(Shape fig){
		StepFigure figureDest = new StepFigure();
		if (fig instanceof StepFigure){
			StepFigure figure = (StepFigure) fig;
			copyProperties(figure, figureDest); 
		}
		return figureDest;
	}

	
	public LineAssociation getLineAssociationCopy(Shape fig, Map<Integer, Shape> shapes){
		LineAssociation lineDest = new LineAssociation();
		if (fig instanceof LineAssociation){
			LineAssociation lineSource = (LineAssociation) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}

	public LineDependency getLineDependencyCopy(Shape fig, Map<Integer, Shape> shapes){
		LineDependency lineDest = new LineDependency();
		if (fig instanceof LineDependency){
			LineDependency lineSource = (LineDependency) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}
	
	public LineInclude getLineIncludeCopy(Shape fig, Map<Integer, Shape> shapes){
		LineInclude lineDest = new LineInclude();
		if (fig instanceof LineInclude){
			LineInclude lineSource = (LineInclude) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}

	public LineExtend getLineExtendCopy(Shape fig, Map<Integer, Shape> shapes){
		LineExtend lineDest = new LineExtend();
		if (fig instanceof LineExtend){
			LineExtend lineSource = (LineExtend) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}
	
	public LineMandatory getLineMandatoryCopy(Shape fig, Map<Integer, Shape> shapes){
		LineMandatory lineDest = new LineMandatory();
		if (fig instanceof LineMandatory){
			LineMandatory lineSource = (LineMandatory) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}

	public LineAlternative getLineAlternativeCopy(Shape fig, Map<Integer, Shape> shapes){
		LineAlternative lineDest = new LineAlternative();
		if (fig instanceof LineAlternative){
			LineAlternative lineSource = (LineAlternative) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}

	public LineOptional getLineOptionalCopy(Shape fig, Map<Integer, Shape> shapes){
		LineOptional lineDest = new LineOptional();
		if (fig instanceof LineOptional){
			LineOptional lineSource = (LineOptional) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}

	public LineRequires getLineRequiresCopy(Shape fig, Map<Integer, Shape> shapes){
		LineRequires lineDest = new LineRequires();
		if (fig instanceof LineRequires){
			LineRequires lineSource = (LineRequires) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}
	
	public LineExclude getLineExcludesCopy(Shape fig, Map<Integer, Shape> shapes){
		LineExclude lineDest = new LineExclude();
		if (fig instanceof LineExclude){
			LineExclude lineSource = (LineExclude) fig;
			copyProperties(lineSource, lineDest); 
			lineDest.setSource((Figure) shapes.get(lineSource.getSource().getId()));
			lineDest.setTarget((Figure) shapes.get(lineSource.getTarget().getId()));
		}
		return lineDest;
	}
	
	private void copyProperties(Shape line,
			Shape lineDest) {
		try {
			BeanUtils.copyProperties(lineDest, line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
