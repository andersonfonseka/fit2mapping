package br.fit2mapping.diagram.repository;

import java.awt.Shape;
import java.util.HashMap;
import java.util.Map;

public class FeatureRepository {

	private static FeatureRepository instance;
	
	private static Integer elementCount = 1;
	
	private Map<Integer, Shape> models = new HashMap<Integer, Shape>(); 

	private FeatureRepository(){}
	
	public static FeatureRepository getInstance(){
		if (instance == null) {
			instance = new FeatureRepository();
		}
		return instance;
	}
	
	
	public void addContent(Shape shape){
		models.put(elementCount, shape);
		elementCount++;
	}
	
	public Shape getContent(Integer id){
		return models.get(id);
	}
	
	public void removeContent(Integer id){
		models.remove(id);
	}
	
	public Map<Integer, Shape> getContent(){
		return models;
	}

	public static Integer getElementCount() {
		return elementCount;
	}
	
	
	
}
