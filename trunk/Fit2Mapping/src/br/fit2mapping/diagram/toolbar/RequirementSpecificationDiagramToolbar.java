package br.fit2mapping.diagram.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.RequirementSpecificationDiagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.util.ImageUtil;

@SuppressWarnings("serial")
public class RequirementSpecificationDiagramToolbar extends JToolBar {

	private ImageUtil imageUtil = ImageUtil.getInstance();
	
	private RequirementSpecificationDiagram diagram;
	
	public RequirementSpecificationDiagramToolbar(RequirementSpecificationDiagram requirementDiagram){
		
		super(1);
		
		this.diagram = requirementDiagram;
		
		JButton btnScenario = new JButton(imageUtil.getImageIcon("/img/scenico.jpg"));
		btnScenario.setToolTipText("Scenario");
		
		JButton btnStep = new JButton(imageUtil.getImageIcon("/img/stepico.jpg"));
		btnStep.setToolTipText("Step");
		
		JButton btnProperties = new JButton(imageUtil.getImageIcon("/img/propico.jpg"));
		btnProperties.setToolTipText("Properties");

		JButton btnAssociation = new JButton(imageUtil.getImageIcon("/img/assoico.jpg"));
		btnAssociation.setToolTipText("Association");
		
		JButton btnDelete = new JButton(imageUtil.getImageIcon("/img/resetico.jpg"));
		btnDelete.setToolTipText("Delete");
		
		btnScenario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Scenario);
			}
		});

		btnStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Step);
			}
		});

		btnProperties.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (diagram.getSelectedFigure() instanceof Figure){
					((Figure) diagram.getSelectedFigure()).showProperties();
				}
			}
		});

		
		btnAssociation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Association);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.deleteSelected();
				repaint();
			}
		});

		add(btnScenario);
		add(btnStep);
		add(btnProperties);
		addSeparator();
		add(btnAssociation);
		addSeparator();
		add(btnDelete);
		
	}
	
}
