package br.fit2mapping.domain.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import br.fit2mapping.domain.Feature;
import br.fit2mapping.domain.FeatureType;
import br.fit2mapping.domain.engine.FeatureManager;

public class DomainEngineeringTreeView extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	JPanel jpanel = new JPanel();
	
	DefaultMutableTreeNode node = new DefaultMutableTreeNode();


	public DomainEngineeringTreeView(){
		
		super();
		
		setSize(new Dimension(300,600));
		setTitle("Product Configuration ");
		setVisible(true);
		setResizable(true);
		setClosable(true);
		
		jpanel.setBackground(Color.WHITE);
		
		Feature feature = FeatureManager.getInstance().getFeature(1);
		feature.navigate(feature, node);
	}

	public void generate(){
		
		final JTree jTree = new JTree(node);
		jTree.setRootVisible(false);
		jTree.setExpandsSelectedPaths(true);
		jTree.setShowsRootHandles(true);
		jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree.setCellRenderer(new CheckBoxCellRenderer());
		
		jTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                jTree.getLastSelectedPathComponent();
				
				if (node == null)
					return;

				Object nodeInfo = node.getUserObject();
						Feature feature = (Feature) nodeInfo;

						if (feature.getType().equals(FeatureType.Normal) && !feature.isSelected()){
							feature.setSelected(true);
						} else if (feature.getType().equals(FeatureType.Normal) && feature.isSelected()) {
							feature.setSelected(false);
						}
						
				jTree.repaint();		
			}
		});
		
		JButton button = new JButton("Consistir");
		
		button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Feature.consist(FeatureManager.getInstance().getFeature(1));
			}
		});
		
		JButton button1 = new JButton("Selecionados");
		
		button1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Feature.navigate(FeatureManager.getInstance().getFeature(1));
			}
		});
		
		for (int i = 0; i < jTree.getRowCount(); i++){
			jTree.expandRow(i);
		}
		
		BoxLayout layout = new BoxLayout(jTree,BoxLayout.PAGE_AXIS);
		BoxLayout layout1 = new BoxLayout(button1,BoxLayout.PAGE_AXIS);
		BoxLayout layout2 = new BoxLayout(button,BoxLayout.PAGE_AXIS);
		
		jTree.setLayout(layout);
		button1.setLayout(layout1);
		button.setLayout(layout2);
		
		jpanel.add(jTree, BorderLayout.CENTER);
		jpanel.add(button1, BorderLayout.PAGE_START);
		jpanel.add(button, BorderLayout.PAGE_END);
		
		add(jpanel);
		
	}
}
