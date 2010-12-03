package br.fit2mapping.domain.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import br.fit2mapping.domain.Feature;
import br.fit2mapping.domain.FeatureType;
import br.fit2mapping.domain.Relationship;
import br.fit2mapping.domain.RelationshipType;
import br.fit2mapping.domain.engine.RelationshipManager;

public class CheckBoxCellRenderer implements TreeCellRenderer {

	private JCheckBox checkBox = new JCheckBox();
	
	private JRadioButton radioButton = new JRadioButton();
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Feature feature =  (Feature) node.getUserObject();
		
		RelationshipManager manager = RelationshipManager.getInstance();
		
		if (feature != null && feature.getType().equals(FeatureType.Normal)){
			
			Relationship relationship = manager.getRelationshipByTarget(feature);

			if (relationship.getRelationshipType().equals(RelationshipType.Mandatory)){
//				checkBox.setText(feature.getLabel());
//				checkBox.setSelected(true);
//				checkBox.setEnabled(false);
//				return checkBox;
				return new JLabel(feature.getLabel());
			}
			
			if (relationship.getRelationshipType().equals(RelationshipType.Optional)){
				checkBox.setText(feature.getLabel());
				checkBox.setSelected(feature.isSelected());
				checkBox.setEnabled(true);
				return checkBox;
			}
			
			if (relationship.getRelationshipType().equals(RelationshipType.Alternative)){
				radioButton.setText(feature.getLabel());
				radioButton.setSelected(feature.isSelected());
				return radioButton;
			}
			
		} else if (feature != null && feature.getType().equals(FeatureType.VariationPoint)){
			return new JLabel(feature.getLabel());
		} 
		
		// TODO Auto-generated method stub
		return new JLabel("Erro");
	}
	
	private ImageIcon createImageIcon(String path){
		
		URL url = CheckBoxCellRenderer.class.getResource(path);
		
		if (url != null){
			return new ImageIcon(url);
		}	else {
			return null;
		}
	}

}
