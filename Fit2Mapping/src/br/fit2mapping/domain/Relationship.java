package br.fit2mapping.domain;

import java.util.ArrayList;
import java.util.List;

public class Relationship {

	private Integer id;
	
	private Feature source;
	
	private Feature target;
	
	private RelationshipType relationshipType;
	
	private List<RelationshipRule> relationshipRules;

	public Relationship(){
		relationshipRules = new ArrayList<RelationshipRule>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Feature getSource() {
		return source;
	}

	public void setSource(Feature source) {
		this.source = source;
	}

	public Feature getTarget() {
		return target;
	}

	public void setTarget(Feature target) {
		this.target = target;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}
	
	public void addRelationshipRule(RelationshipRule rule){
		relationshipRules.add(rule);
	}
	
	public Relationship addRelationshipRule(Feature feature, RelationshipRuleType ruleType){
		
		RelationshipRule relationshipRule = new RelationshipRule();
		relationshipRule.setFeature(feature);
		relationshipRule.setRuleType(ruleType);

		addRelationshipRule(relationshipRule);
		
		return this;
	}


	public List<RelationshipRule> getRelationshipRules() {
		return relationshipRules;
	}

	@Override
	public String toString() {
		return getRelationshipType().name();
	}
	
}
