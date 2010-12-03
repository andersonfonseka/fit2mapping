package br.fit2mapping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.fit2mapping.diagram.bean.FigureBean;
import br.fit2mapping.diagram.bean.RequirementBean;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.editor.FeatureDiagramEditor;
import br.fit2mapping.diagram.editor.MappingDiagramEditor;
import br.fit2mapping.diagram.editor.RequirementDiagramEditor;
import br.fit2mapping.diagram.editor.RequirementSpecificationDiagramEditor;
import br.fit2mapping.diagram.properties.ExplorerProperties;
import br.fit2mapping.diagram.properties.FigureProperties;
import br.fit2mapping.diagram.properties.RequirementProperties;
import br.fit2mapping.diagram.toolbar.ExplorerTreeViewBar;
import br.fit2mapping.diagram.toolbar.ExplorerTreeViewToolbar;
import br.fit2mapping.domain.view.DomainEngineeringTreeView;

@SuppressWarnings("serial")
public class Main extends JFrame {

	static JDesktopPane desktopPane = new JDesktopPane();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main(){
		
		super("Fit2Mapping");
		
	    Container content = getContentPane();
	    content.setBackground(Color.white);
	    
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		content.add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Diagrams");
		JMenuItem item = new JMenuItem("Feature Diagram");
		JMenuItem item1 = new JMenuItem("Requirement Diagram");
		JMenuItem item2 = new JMenuItem("Requirement Specification Diagram");
		JMenuItem item3 = new JMenuItem("Mapping Diagram");
		JMenuItem item4 = new JMenuItem("Product Configuration");

		final ExplorerTreeViewBar treeViewBar = ExplorerTreeViewBar.getInstance();
		treeViewBar.setVisible(true);
		desktopPane.add(treeViewBar);
				
		FigureProperties figureProperties = FigureProperties.getInstance(new FigureBean());
		figureProperties.setVisible(false);
		desktopPane.add(figureProperties);

		RequirementProperties requirementProperties = RequirementProperties.getInstance(new RequirementBean(new FigureBean()));
		requirementProperties.setVisible(false);
		desktopPane.add(requirementProperties);

		ExplorerProperties explorerProperties = ExplorerProperties.getInstance(new DiagramEditor());
		explorerProperties.setVisible(false);
		desktopPane.add(explorerProperties);
		
		
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FeatureDiagramEditor featureDiagramEditor = new FeatureDiagramEditor();
				featureDiagramEditor.setVisible(true);
				featureDiagramEditor.moveToFront();
		
				treeViewBar.addDiagram(featureDiagramEditor);
				
				desktopPane.add(featureDiagramEditor);

				try {
					featureDiagramEditor.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RequirementDiagramEditor requirementDiagramEditor = new RequirementDiagramEditor();
				requirementDiagramEditor.setVisible(true);
				requirementDiagramEditor.moveToFront();
		
				treeViewBar.addDiagram(requirementDiagramEditor);
				
				desktopPane.add(requirementDiagramEditor);

				try {
					requirementDiagramEditor.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RequirementSpecificationDiagramEditor requirementDiagramEditor = new RequirementSpecificationDiagramEditor();
				requirementDiagramEditor.setVisible(true);
				requirementDiagramEditor.moveToFront();
		
				treeViewBar.addDiagram(requirementDiagramEditor);
				
				desktopPane.add(requirementDiagramEditor);

				try {
					requirementDiagramEditor.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MappingDiagramEditor mappingDiagramEditor = new MappingDiagramEditor();
				mappingDiagramEditor.setVisible(true);
				mappingDiagramEditor.moveToFront();
		
				treeViewBar.addDiagram(mappingDiagramEditor);
				
				desktopPane.add(mappingDiagramEditor);

				try {
					mappingDiagramEditor.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DomainEngineeringTreeView view = new DomainEngineeringTreeView();
				view.generate();
				view.setVisible(true);
				desktopPane.add(view);

			}
		});
		
		menu.add(item);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);

		bar.add(menu);
		setJMenuBar(bar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}

