package br.fit2mapping.diagram.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.FeatureDiagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.util.ImageUtil;

@SuppressWarnings("serial")
public class FeatureDiagramToolbar extends JToolBar {

	private ImageUtil imageUtil = ImageUtil.getInstance();
	
	private FeatureDiagram diagram;
	
	public FeatureDiagramToolbar(FeatureDiagram featureDiagram){
		
		super(1);
		
		this.diagram = featureDiagram;
		
		JButton btnStakeholder = new JButton(imageUtil.getImageIcon("/img/stakico.jpg"));
		btnStakeholder.setToolTipText("Stakeholder");
		
		JButton btnFeature = new JButton(imageUtil.getImageIcon("/img/featico.jpg"));
		btnFeature.setToolTipText("Feature");
		
		JButton btnProperties = new JButton(imageUtil.getImageIcon("/img/propico.jpg"));
		btnProperties.setToolTipText("Properties");
		
		JButton btnMandatory = new JButton(imageUtil.getImageIcon("/img/mandatory.jpg"));
		btnMandatory.setToolTipText("Mandatory");
		
		JButton btnOptional = new JButton(imageUtil.getImageIcon("/img/optional.jpg"));
		btnOptional.setToolTipText("Optional");

		JButton btnAlternative = new JButton(imageUtil.getImageIcon("/img/alternative.jpg"));
		btnAlternative.setToolTipText("Alternative");
		
		JButton btnAssociation = new JButton(imageUtil.getImageIcon("/img/assoico.jpg"));
		btnAssociation.setToolTipText("Association");
		
		JButton btnRequires = new JButton(imageUtil.getImageIcon("/img/requires.jpg"));
		btnRequires.setToolTipText("Requires");
		
		JButton btnExcludes = new JButton(imageUtil.getImageIcon("/img/excludes.jpg"));
		btnExcludes.setToolTipText("Excludes");
		
		JButton btnDelete = new JButton(imageUtil.getImageIcon("/img/resetico.jpg"));
		btnDelete.setToolTipText("Delete");
		
		btnFeature.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Feature);
			}
		});

		btnStakeholder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Stakeholder);
			}
		});
		
		btnMandatory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Mandatory);
			}
		});

		btnOptional.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Optional);
			}
		});

		btnAlternative.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Alternative);
			}
		});
		
		btnAssociation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Association);
			}
		});

		btnRequires.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Requires);
			}
		});

		btnExcludes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.setObjectType(ObjectEnum.Excludes);
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
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagram.deleteSelected();
				repaint();
			}
		});

		add(btnStakeholder);
		add(btnFeature);
		add(btnAssociation);
		add(btnProperties);
		addSeparator();
		add(btnMandatory);
		add(btnOptional);
		add(btnAlternative);
		addSeparator();
		add(btnRequires);
		add(btnExcludes);
		addSeparator();
		add(btnDelete);
		
	}
	
}
