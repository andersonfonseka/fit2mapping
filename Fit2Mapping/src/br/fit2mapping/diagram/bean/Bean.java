package br.fit2mapping.diagram.bean;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.repository.FeatureRepository;

public class Bean {

	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		if (FeatureRepository.getInstance().getContent(id) != null){
			name =  ((Bean) ((Figure) FeatureRepository.getInstance().getContent(id)).getFigureBean()).name;	
		}
		return name;
	}

	public void setName(String name) {
		
		if (FeatureRepository.getInstance().getContent(id) != null){
			((Bean) ((Figure) FeatureRepository.getInstance().getContent(id)).getFigureBean()).name = name;	
		}
		
		this.name = name;
	}
	
}
