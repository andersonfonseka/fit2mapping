package br.fit2mapping.diagram.toolbar;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import br.fit2mapping.diagram.editor.DiagramEditor;
import br.fit2mapping.diagram.features.FeatureFigure;
import br.fit2mapping.diagram.features.LineAlternative;
import br.fit2mapping.diagram.features.LineAssociation;
import br.fit2mapping.diagram.features.LineExclude;
import br.fit2mapping.diagram.features.LineMandatory;
import br.fit2mapping.diagram.features.LineOptional;
import br.fit2mapping.diagram.features.LineRequires;
import br.fit2mapping.diagram.features.StakeHolderFigure;
import br.fit2mapping.diagram.mapping.LineMapping;
import br.fit2mapping.diagram.requirements.ActorFigure;
import br.fit2mapping.diagram.requirements.RequirementFigure;
import br.fit2mapping.diagram.requirements.ScenarioFigure;
import br.fit2mapping.diagram.requirements.StepFigure;
import br.fit2mapping.diagram.util.ImageUtil;

public class ExplorerTreeViewCellRenderer extends DefaultTreeCellRenderer {
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		
		if (node.getUserObject() instanceof DiagramEditor){
			DiagramEditor editor = (DiagramEditor) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getTitle());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/propico.jpg"));
			return jLabel;
		}
		
		if (node.getUserObject() instanceof FeatureFigure){
			FeatureFigure editor = (FeatureFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/featico.jpg"));
			return jLabel;
		}
		
		if (node.getUserObject() instanceof StakeHolderFigure){
			StakeHolderFigure editor = (StakeHolderFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/stakico.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof ActorFigure){
			ActorFigure editor = (ActorFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/atorico.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof RequirementFigure){
			RequirementFigure editor = (RequirementFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/requico.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof ScenarioFigure){
			ScenarioFigure editor = (ScenarioFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/scenico.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof StepFigure){
			StepFigure editor = (StepFigure) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText("StepFigure " + editor.getId());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/stepico.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof LineAssociation){
			LineAssociation editor = (LineAssociation) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/assoico.jpg"));
			return jLabel;
		}
		
		if (node.getUserObject() instanceof LineMandatory){
			LineMandatory editor = (LineMandatory) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/mandatory.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof LineOptional){
			LineOptional editor = (LineOptional) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/optional.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof LineAlternative){
			LineAlternative editor = (LineAlternative) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/alternative.jpg"));
			return jLabel;
		}
		
		if (node.getUserObject() instanceof LineRequires){
			LineRequires editor = (LineRequires) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/requires.jpg"));
			return jLabel;
		}

		if (node.getUserObject() instanceof LineExclude){
			LineExclude editor = (LineExclude) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/excludes.jpg"));
			return jLabel;
		}
		
		if (node.getUserObject() instanceof LineMapping){
			LineMapping editor = (LineMapping) node.getUserObject();
			JLabel jLabel = new JLabel();
			jLabel.setText(editor.getName());
			jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/mappico.jpg"));
			return jLabel;
		}

		
		JLabel jLabel = new JLabel();
		jLabel.setText(node.toString());
		jLabel.setIcon(ImageUtil.getInstance().getImageIcon("/img/propico.jpg"));
		// TODO Auto-generated method stub
		return jLabel;
	}

}
