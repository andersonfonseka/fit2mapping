package br.fit2mapping.diagram.bean;

import java.util.ArrayList;
import java.util.List;

public class ScenarioBean extends Bean {
	
	private FigureBean figureBean;
	
	private List<StepBean> steps = new ArrayList<StepBean>();
	
	public ScenarioBean(FigureBean figureBean) {
		super();
		this.figureBean = figureBean;

		this.setId(figureBean.getId());
		this.setName(figureBean.getName());
	}

	public List<StepBean> getSteps() {
		return steps;
	}

	public void addSteps(StepBean steps) {
		this.steps.add(steps);
	}
	
}
