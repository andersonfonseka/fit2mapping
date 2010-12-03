package br.fit2mapping.diagram.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.MappingDiagram;
import br.fit2mapping.diagram.RequirementSpecificationDiagram;
import br.fit2mapping.diagram.enums.ObjectEnum;
import br.fit2mapping.diagram.util.ImageUtil;

@SuppressWarnings("serial")
public class MappingDiagramToolbar extends JToolBar {

	private ImageUtil imageUtil = ImageUtil.getInstance();
	
	private MappingDiagram diagram;
	
	public MappingDiagramToolbar(MappingDiagram mappingDiagram){
		
		super(1);
		
		this.diagram = mappingDiagram;
		
		
		JButton btnProperties = new JButton(imageUtil.getImageIcon("/img/propico.jpg"));
		btnProperties.setToolTipText("Properties");

		JButton btnAssociation = new JButton(imageUtil.getImageIcon("/img/mappico.jpg"));
		btnAssociation.setToolTipText("Mapping");
		
		JButton btnDelete = new JButton(imageUtil.getImageIcon("/img/resetico.jpg"));
		btnDelete.setToolTipText("Delete");

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

		add(btnProperties);
		addSeparator();
		add(btnAssociation);
		addSeparator();
		add(btnDelete);
		
	}
	
}
