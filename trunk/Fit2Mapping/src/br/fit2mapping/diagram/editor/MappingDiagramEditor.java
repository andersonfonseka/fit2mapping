package br.fit2mapping.diagram.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import br.fit2mapping.diagram.MappingDiagram;
import br.fit2mapping.diagram.toolbar.MappingDiagramToolbar;

@SuppressWarnings("serial")
public class MappingDiagramEditor extends DiagramEditor {
	
	private static Integer counter =1;
	
	MappingDiagram diagram = new MappingDiagram(this);
	
	public MappingDiagramEditor() {
		
		super();
		
		setTitle("Mapping Diagram " + counter++);
		
		JPanel panelFeatureDiagram = new JPanel();
		panelFeatureDiagram.setLayout(new BorderLayout());
		
		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL,0,10,0, 100);
		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL,0,10,0, 100);
		
		hbar.addAdjustmentListener(new MyAdjustHorizontal());
		vbar.addAdjustmentListener(new MyAdjustVertical());
		
		panelFeatureDiagram.add(diagram, BorderLayout.CENTER);
		panelFeatureDiagram.add(hbar, BorderLayout.SOUTH);
		panelFeatureDiagram.add(vbar, BorderLayout.EAST);

		setDiagram(diagram);
		
		MappingDiagramToolbar diagramToolbar = new MappingDiagramToolbar(diagram);
		
		add(diagramToolbar, BorderLayout.WEST);
		add(panelFeatureDiagram, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

		
	class MyAdjustHorizontal implements AdjustmentListener{
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			diagram.setLocation(-e.getValue(), (int) diagram.getLocation().getY());
			diagram.repaint();
		}
	}

	class MyAdjustVertical implements AdjustmentListener{
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			diagram.setLocation((int) diagram.getLocation().getX(), -e.getValue());
			diagram.repaint();
		}
	}
}
