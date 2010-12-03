package br.fit2mapping.domain;

public enum RelationshipType {

	Mandatory(1), Alternative(2), Optional(3);
	
	private final Integer value;
	
	RelationshipType(Integer pValue){
		value = pValue;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
