package br.fit2mapping.domain;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.repository.FeatureRepository;
import br.fit2mapping.domain.engine.FeatureManager;
import br.fit2mapping.domain.engine.RelationshipManager;

public class Feature {

	private Integer id;
	
	private String label;

	private FeatureType type;
	
	private List<Relationship> relationships;
	
	private boolean selected;
	
	public Feature(String label) {
		super();
		this.label = label;
		this.relationships = new ArrayList<Relationship>();
		this.setType(FeatureType.Normal);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		
		FeatureRepository featureRepository = FeatureRepository.getInstance();
		
		if (featureRepository.getContent(id) != null){
			label = ((Figure) featureRepository.getContent(id)).getName();
		}
		
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void addRelationship(Relationship relationship){
		relationship.setSource(this);
		relationships.add(relationship);
		
		if (relationship.getRelationshipType().equals(RelationshipType.Alternative)){
			this.setType(FeatureType.VariationPoint);
		}

		if (relationships.size() > 0){
			this.setType(FeatureType.VariationPoint);
		}
		
		if (relationship.getTarget().getType().equals(FeatureType.Normal) && 
				relationship.getRelationshipType().equals(RelationshipType.Mandatory)){
			relationship.getTarget().setSelected(true);
		}
		
		RelationshipManager.getInstance().addRelationship(relationship);
	}
	
	public Relationship addRelationship(Feature feature, RelationshipType relationshipType){
		
		Relationship relationship = new Relationship();
		relationship.setTarget(feature);
		relationship.setRelationshipType(relationshipType);

		addRelationship(relationship);
		
		return relationship;
	}
	
	public void removeRelationship(Relationship relationship){

		relationships.remove(relationship);
		
		if (relationships.size() == 0){
			this.setType(FeatureType.Normal);
		}
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public FeatureType getType() {
		return type;
	}

	public void setType(FeatureType type) {
		this.type = type;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSimpleSelected(boolean selected){
		this.selected = selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		
		RelationshipManager manager = RelationshipManager.getInstance();
		
		if (this.selected){
			
			// Handling Mandatory relationships
			for (Relationship relationship: this.relationships){
				
				if (relationship.getRelationshipType().equals(RelationshipType.Mandatory)){
					relationship.getSource().setSimpleSelected(true);
					relationship.getTarget().setSelected(true);
				}
			}
			
			// Handling Alternative relationships	
			Relationship relationshipSource = manager.getRelationshipByTarget(this);
			
			if (relationshipSource != null) {
				Feature feature = relationshipSource.getSource();
				feature.setSimpleSelected(true);
				
				for(Relationship alternativeRelations: feature.getRelationships()){
					if (alternativeRelations.getRelationshipType().equals(RelationshipType.Alternative)){
						alternativeRelations.getTarget().setSelected(false);	
					}
				}
			}
			
			this.setSimpleSelected(true);
		
		
			// Handling rules (Requires, Excludes)
			List<Relationship> relations = manager.getRelationshipsByTarget(this);
			
			for (Relationship relationship: relations){
				for(RelationshipRule rule:	relationship.getRelationshipRules()){
					if (rule.getRuleType().equals(RelationshipRuleType.Requires)){
						rule.getFeature().setSelected(true);
					}
					
					if (rule.getRuleType().equals(RelationshipRuleType.Excludes)){
						rule.getFeature().setSelected(false);
					}
				}
			}
		
		} else {
			unselectAll(this);
		}
	}
	
	private void unselectAll(Feature feature){
		
		feature.setSimpleSelected(false);
		
		for (Relationship relationship: feature.getRelationships()){
			relationship.getTarget().setSimpleSelected(false);
			unselectAll(relationship.getTarget());
		}
	}

	public static void navigate(Feature feature){
		
		if (feature.isSelected() && feature.getType().equals(FeatureType.Normal)){
			System.out.println(feature.getLabel() + "(" + feature.getType() + ")[" + feature.isSelected() + "]");	
		}
		
		for (Relationship relationship : feature.getRelationships()) {
			//System.out.println("(" + relationship.getRelationshipType() + ")");
			//for(RelationshipRule rule: relationship.getRelationshipRules()){
				//System.out.println(rule.getRuleType() + " " + rule.getFeature().getLabel());
			//}
			navigate(relationship.getTarget());
		}
	}
	
	public void navigate(Feature feature, DefaultMutableTreeNode node){
		
//		System.out.println(feature.getLabel() + "(" + feature.getType() + ")");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(feature);
		node.add(node2);
		
		for (Relationship relationship : feature.getRelationships()) {
//			System.out.println("(" + relationship.getRelationshipType() + ")");
//			for(RelationshipRule rule: relationship.getRelationshipRules()){
//				System.out.println(rule.getRuleType() + " " + rule.getFeature().getLabel());
//			}
			navigate(relationship.getTarget(), node2);
		}
	}
	
	public static void consist(Feature feature){
		
		if (feature.getType().equals(FeatureType.VariationPoint)){

			for (Relationship relationship2: feature.getRelationships()){
				
				if (relationship2.getTarget().getType().equals(FeatureType.VariationPoint) && 
						relationship2.getRelationshipType().equals(RelationshipType.Mandatory)){
					
					Feature feature2 = relationship2.getTarget();
					
					for (Relationship relationship: feature2.getRelationships()){

						if (relationship.getTarget().isSelected()){
							feature2.setSelected(true);
							consist(relationship.getTarget());
						}	
					}
					
					if (!feature2.isSelected()){
						System.out.println("[ " + feature2.getLabel() + " ] is Mandatory. ");
					}
					
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return label;
	}
	
	public void save(){
		FeatureManager.getInstance().addFeature(this);
	}
	
}
