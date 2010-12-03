package br.fit2mapping.domain.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fit2mapping.domain.Feature;
import br.fit2mapping.domain.Relationship;

public class RelationshipManager {

	private static RelationshipManager instance;
	
	private static Integer counter;
	
	private Map<Integer, Relationship> relationships;
	
	private RelationshipManager(){
		counter = 1;
		relationships = new HashMap<Integer, Relationship>();
	}
	
	public static RelationshipManager getInstance(){
		if (instance == null){
			instance = new RelationshipManager();
		}
		return instance;
	}

	public void addRelationship(Relationship relationship){
		relationship.setId(counter);
		relationships.put(relationship.getId(), relationship);
		counter++;
	}
	
	public List<Relationship> getRelationships(){
		return new ArrayList<Relationship>(relationships.values());
	}

	public Relationship getRelationshipByTarget(Feature feature){
		
		Relationship relationship = null;
		
		for (Relationship rel: relationships.values()) {
			if (rel.getTarget().getId().equals(feature.getId())){
				relationship = rel;	
			}
		}
		return relationship;
	}
	
	public List<Relationship> getRelationshipsByTarget(Feature feature){
		
		List<Relationship> relations = new ArrayList<Relationship>();
		
		for (Relationship rel: relationships.values()) {
			if (rel.getTarget().getId().equals(feature.getId())){
				relations.add(rel);	
			}
		}
		return relations;
	}
	
}
