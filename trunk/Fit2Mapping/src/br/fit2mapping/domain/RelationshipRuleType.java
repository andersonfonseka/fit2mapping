package br.fit2mapping.domain;

public enum RelationshipRuleType {

	Requires(1), Excludes(2);
	
	private final Integer value;
	
	RelationshipRuleType(Integer pValue){
		value = pValue;
	}

	public Integer getValue() {
		return value;
	}
	
	
	
}
