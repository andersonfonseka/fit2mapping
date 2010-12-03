package br.fit2mapping.domain;

public enum FeatureType {

	Root(1), Normal(2), VariationPoint(3);
	
	private final Integer value;
	
	FeatureType(Integer pValue){
		value = pValue;
	}
	
}
