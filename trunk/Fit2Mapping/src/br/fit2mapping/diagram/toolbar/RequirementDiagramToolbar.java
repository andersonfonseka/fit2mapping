package br.fit2mapping.diagram.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.RequirementDiagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.util.ImageUtil;

@SuppressWarnings("serial")
public class RequirementDiagramToolbar extends JToolBar {

	private ImageUtil imageUtil = ImageUtil.getInstance();
	
	private RequirementDiagram diagram;
	
	public RequirementDiagramToolbar(RequirementDiagram requirementDiagram){
		
		super(1);
		
		this.diagram = requirementDiagram;
		
		JButton btnStakeholder = new JButton(imageUtil.getImageIcon("/img/atorico.jpg"));
		btnStakeholder.setToolTipText("Actor");
		
		JButton btnRequirement = new JButton(imageUtil.getImageIcon("/img/requico.jpg"));
		btnRequirement.setToolTipText("Requirement");
		
		JButton btnProperties = new JButton(imageUtil.getImageIcon("/img/propico.jpg"));
		btnProperties.setToolTipText("Properties");
		
		JButton btnAssociation = new JButton(imageUtil.getImageIcon("/img/assoico.jpg"));
		btnAssociation.setToolTipText("Association");
		
		JButton btnInclude = new JButton(imageUtil.getImageIcon("/img/inclico.jpg"));
		btnInclude.setToolTipText("Include");
		
		JButton btnExtends = new JButton(imageUtil.getImageIcon("/img/exteico.jpg"));
		btnExtends.setToolTipText("Extends");
		
		JButton btnDependency = new JButton(imageUtil.getImageIcon("/img/depeico.jpg"));
		btnDependency.setToolTipText("Dependency");
		
		JButton btnGeneralization = new JButton(imageUtil.getImageIcon("/img/geneico.jpg"));
		btnGeneralization.setToolTipText("Generalization");
		
		JButton btnDelete = new JButton(imageUtil.getImageIcon("/img/resetico.jpg"));
		btnDelete.setToolTipText("Delete");
		
		btnRequirement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Requirement);
			}
		});

		btnStakeholder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Actor);
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

		btnInclude.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Include);
			}
		});

		btnExtends.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Extends);
			}
		});

		btnDependency.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Dependency);
			}
		});
		
		btnGeneralization.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Generalization);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.deleteSelected();
				repaint();
			}
		});

		add(btnStakeholder);
		add(btnRequirement);
		add(btnProperties);
		addSeparator();
		add(btnAssociation);
		add(btnDependency);
		addSeparator();
		add(btnInclude);
		add(btnExtends);
//		add(btnGeneralization);
		addSeparator();
		add(btnDelete);
		
	}
	
}
