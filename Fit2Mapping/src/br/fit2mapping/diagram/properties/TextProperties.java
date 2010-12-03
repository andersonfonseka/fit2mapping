package br.fit2mapping.diagram.properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.fit2mapping.Figure;
import br.fit2mapping.diagram.Diagram;
import br.fit2mapping.diagram.FeatureDiagram;
import br.fit2mapping.diagram.RequirementDiagram;

@SuppressWarnings("serial")
public class TextProperties extends JPanel {
	
	JPanel jPanel;
	
	JPanel jPanel2 = new JPanel();
	JLabel label = new JLabel("Name :");
	JTextField field = new JTextField(20);
	JButton button10 = new JButton("Apply");
	
	public TextProperties(JPanel panel) {
		
		jPanel = panel;
		
		setVisible(true);
		
		button10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jPanel instanceof Diagram){
					Diagram diagram = (Diagram) jPanel;
					if (diagram.getSelectedFigure() != null && diagram.getSelectedFigure() instanceof Figure){
						((Figure) diagram.getSelectedFigure()).setName(field.getText());
					}
					diagram.repaint();
				}
			}
		});
		
		jPanel2.add(label);
		jPanel2.add(field);
		jPanel2.add(button10);
		
		add(jPanel2);
		
		if (jPanel instanceof FeatureDiagram){
			FeatureDiagram diagram = (FeatureDiagram) jPanel;
			if (diagram.getSelectedFigure() != null && diagram.getSelectedFigure() instanceof Figure){
				field.setText(((Figure) diagram.getSelectedFigure()).getName());
			}
		}
		
		if (jPanel instanceof RequirementDiagram){
			RequirementDiagram diagram = (RequirementDiagram) jPanel;
			if (diagram.getSelectedFigure() != null && diagram.getSelectedFigure() instanceof Figure){
				field.setText(((Figure) diagram.getSelectedFigure()).getName());
			}
		}

	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}
	
	
}
