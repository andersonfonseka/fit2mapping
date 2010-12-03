package br.fit2mapping.diagram.bean;

import java.util.ArrayList;
import java.util.List;

public class RequirementBean extends FigureBean {

	private FigureBean figureBean;
	
	private List<FigureBean> actors = new ArrayList<FigureBean>();
	
	private String preCondition;
	
	private List<ScenarioBean> scenarios = new ArrayList<ScenarioBean>();
	
	private String postCondition;
	
	private List<RequirementBean> extensions = new ArrayList<RequirementBean>();

	private List<RequirementBean> includes = new ArrayList<RequirementBean>();
	
	private String specialRequirements;

	public RequirementBean(FigureBean figureBean){
		this.figureBean = figureBean;
		
		this.setId(figureBean.getId());
		this.setName(figureBean.getName());
		this.setDescription(figureBean.getDescription());
	}
	
	public List<FigureBean> getActors() {
		return actors;
	}

	public void addActors(FigureBean actor) {
		this.actors.add(actor);
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public List<ScenarioBean> getScenarios() {
		return scenarios;
	}

	public void addScenarios(ScenarioBean scenarios) {
		this.scenarios.add(scenarios);
	}

	public String getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	public List<RequirementBean> getExtensions() {
		return extensions;
	}

	public void addExtensions(RequirementBean extensions) {
		this.extensions.add(extensions);
	}

	public List<RequirementBean> getIncludes() {
		return includes;
	}

	public void addIncludes(RequirementBean includes) {
		this.includes.add(includes);
	}

	public String getSpecialRequirements() {
		return specialRequirements;
	}

	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}
	
}
