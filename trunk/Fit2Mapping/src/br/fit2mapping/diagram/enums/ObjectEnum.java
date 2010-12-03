package br.fit2mapping.diagram.enums;

public enum ObjectEnum {

	// FeatureFigure DiagramEditor
	None(0),
	Stakeholder(1),
	Feature(2),
	Properties(3),
	Mandatory(4),
	Optional(5),
	Alternative(6),
	Association(7),
	Requires(8),
	Excludes(9),
	Delete(10),
	Requirement(11),
	Actor(12),
	Include(13),
	Extends(14),
	Generalization(15),
	Dependency(16),
	Scenario(17),
	Step(18);
	
	Integer value;
	
	private ObjectEnum(Integer pValue){
		value = pValue;
	}
	
}
