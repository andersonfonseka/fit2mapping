package br.fit2mapping.diagram.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.fit2mapping.Figure;
import br.fit2mapping.LineSourceTarget;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.editor.FeatureDiagramEditor;
import br.fit2mapping.diagram.editor.MappingDiagramEditor;
import br.fit2mapping.diagram.editor.RequirementDiagramEditor;
import br.fit2mapping.diagram.editor.RequirementSpecificationDiagramEditor;
import br.fit2mapping.diagram.util.DragNDropUtil;

@SuppressWarnings("serial")
public class ExplorerTreeViewBar extends JInternalFrame {

	private List<Object> diagrams = new ArrayList<Object>();
	
	DefaultMutableTreeNode node = new DefaultMutableTreeNode();
	DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("Domain Engineering");
	DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Diagrams");
	DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Features");
	DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("Requirements");
	DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("Mappings");
	
	DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("Elements");
	
	JTree jTree;
	
	JPanel jpanel = new JPanel();
	
	DefaultMutableTreeNode nodeSelected;
	
	ExplorerTreeViewToolbar explorerTreeViewToolbar = new ExplorerTreeViewToolbar(nodeSelected);
	
	
	private static ExplorerTreeViewBar instance;
	
	public static ExplorerTreeViewBar getInstance(){
		if (instance == null) {
			instance = new ExplorerTreeViewBar();
		}
		return instance; 
	}
	
	private ExplorerTreeViewBar(){
		
		super();
		
		setSize(new Dimension(300,600));
		setTitle("Domain Explorer ");
		setVisible(true);
		setResizable(true);
		
		jpanel.setBackground(Color.WHITE);
		
		node.add(node0);
		node0.add(node1);
		node1.add(node2);
		node1.add(node3);
		node1.add(node4);
		node0.add(node5);
		
		jTree = new JTree(node);
		jTree.setSize(new Dimension(this.getWidth(), this.getHeight()));
		jTree.setRootVisible(false);
		jTree.setExpandsSelectedPaths(true);
		jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree.setCellRenderer(new ExplorerTreeViewCellRenderer());
		jTree.setDragEnabled(true);
		
		add(new ExplorerTreeViewToolbar(nodeSelected), BorderLayout.NORTH);
		jpanel.add(jTree, BorderLayout.CENTER);
		add(jpanel);
		
	}
	
	public void addDiagram(Object diagram){
		
		diagrams.add(diagram);

		node.add(node0);
		node0.add(node1);
		node1.add(node2);
		node1.add(node3);
		node1.add(node4);
		node0.add(node5);
		
		DefaultMutableTreeNode nodeX = new DefaultMutableTreeNode(diagram);

		if (diagram instanceof FeatureDiagramEditor){
			node2.add(nodeX);	
		}

		if (diagram instanceof RequirementDiagramEditor || diagram instanceof RequirementSpecificationDiagramEditor){
			node3.add(nodeX);	
		}
		
		if (diagram instanceof MappingDiagramEditor){
			node4.add(nodeX);	
		}

		
		if (diagram instanceof Figure || diagram instanceof LineSourceTarget){
			node5.add(nodeX);	
		}
	
		jTree = new JTree(node);
		jTree.putClientProperty("JTree.lineStyle", "Horizontal");
		jTree.setSize(new Dimension(this.getWidth(), this.getHeight()));
		jTree.setRootVisible(false);
		jTree.setExpandsSelectedPaths(true);
		jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree.setCellRenderer(new ExplorerTreeViewCellRenderer());
		jTree.setDragEnabled(true);
		
		for (int i = 0; i < jTree.getRowCount(); i++){
			jTree.expandRow(i);
		}
		
		jTree.setDragEnabled(true);
		
		jTree.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                jTree.getLastSelectedPathComponent();
				
				if (node == null)
					return;

				Object nodeInfo = node.getUserObject();
				DragNDropUtil.getInstance().setSelectedObject(nodeInfo);
			}
		});
		
		jTree.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {

				int rowSelected = jTree.getRowForLocation(e.getX(), e.getY());
				TreePath treePath = jTree.getPathForLocation(e.getX(), e.getY());

				if (rowSelected != -1 && e.getClickCount() == 1){
					nodeSelected = (DefaultMutableTreeNode) treePath.getLastPathComponent();
					explorerTreeViewToolbar = new ExplorerTreeViewToolbar(nodeSelected);
					
					jpanel.removeAll();
					add(explorerTreeViewToolbar, BorderLayout.NORTH);
					jpanel.add(jTree, BorderLayout.CENTER);
				}
				
				if (rowSelected != -1 && e.getClickCount() == 2){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
					
					if (node == null)
						return;
	
					Object nodeInfo = node.getUserObject();
	
					if (nodeInfo instanceof DiagramEditor){
						DiagramEditor editor = (DiagramEditor) nodeInfo;
						if (!editor.isVisible()){
							editor.setVisible(true);
							editor.show();
						}
					}
					
					jTree.repaint();		
				}
			}
		});
		
		jpanel.removeAll();
		add(explorerTreeViewToolbar, BorderLayout.NORTH);
		jpanel.add(jTree, BorderLayout.CENTER);
	}
}
