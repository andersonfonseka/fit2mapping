package br.fit2mapping.diagram.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.util.ImageUtil;

@SuppressWarnings("serial")
public class ExplorerTreeViewToolbar extends JToolBar {

	private ImageUtil imageUtil = ImageUtil.getInstance();
	
	private static DefaultMutableTreeNode nodeSelected;
	
	public ExplorerTreeViewToolbar(final DefaultMutableTreeNode node){
		
		super(HORIZONTAL);
		
		nodeSelected = node;
		
		JButton btnProperties = new JButton(imageUtil.getImageIcon("/img/propico.jpg"));
		btnProperties.setToolTipText("Properties");

		
		JButton btnDelete = new JButton(imageUtil.getImageIcon("/img/resetico.jpg"));
		btnDelete.setToolTipText("Delete");

		btnProperties.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nodeSelected != null && nodeSelected.getUserObject() instanceof DiagramEditor){
					((DiagramEditor) nodeSelected.getUserObject()).showProperties();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				diagram.deleteSelected();
				repaint();
			}
		});

		add(btnProperties);
		add(btnDelete);
		
	}
	
}
