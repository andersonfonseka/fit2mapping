package br.fit2mapping.domain.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fit2mapping.domain.Feature;

public class FeatureManager {

	private static FeatureManager instance;
	
	private Map<Integer, Feature> features;
	
	private FeatureManager(){
		features = new HashMap<Integer, Feature>();
	}
	
	public static FeatureManager getInstance(){
		if (instance == null){
			instance = new FeatureManager();
		}
		return instance;
	}

	public void addFeature(Feature feature){
		
//		if (counter == 1){
//			feature.setType(FeatureType.Root);
//		}
//		
		features.put(feature.getId(), feature);
//		counter++;
	}
	
	public List<Feature> getFeatures(){
		return new ArrayList<Feature>(features.values());
	}
	
	public Feature getFeature(Integer id){
		return features.get(id);
	}
	
}
