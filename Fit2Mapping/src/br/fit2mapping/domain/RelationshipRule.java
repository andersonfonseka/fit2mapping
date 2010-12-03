package br.fit2mapping.domain;

public class RelationshipRule {

	private Feature feature;
	
	private RelationshipRuleType ruleType;
	
	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public RelationshipRuleType getRuleType() {
		return ruleType;
	}

	public void setRuleType(RelationshipRuleType ruleType) {
		this.ruleType = ruleType;
	}
	
}
