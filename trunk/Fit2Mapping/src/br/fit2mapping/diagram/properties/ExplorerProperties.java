package br.fit2mapping.diagram.properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.fit2mapping.diagram.bean.FigureBean;
import br.fit2mapping.diagram.editor.DiagramEditor;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ExplorerProperties extends Properties {
	
	private static ExplorerProperties instance;

	private DiagramEditor diagramEditor;
	
	final JTextField txName = new JTextField(30);

	final JTextArea txDescription = new JTextArea(10, 40);
	
	public static ExplorerProperties getInstance(DiagramEditor pDiagramEditor){
		if (instance == null) {
			instance = new ExplorerProperties(pDiagramEditor);
			
		} else {
			if (!instance.isVisible()){
				instance.diagramEditor = pDiagramEditor;
				instance.setTitle(pDiagramEditor.getTitle());
				instance.txName.setText(pDiagramEditor.getTitle());
				instance.txDescription.setText(pDiagramEditor.getDescription());
				instance.setVisible(true);
				instance.show();
			}
		}
		
		return instance;
	}
	
	private ExplorerProperties (){
		super();
	}
	
	
	private ExplorerProperties(final DiagramEditor pDiagramEditor) {
		
		super(pDiagramEditor.getName());

		txDescription.setLineWrap(true);
		
		this.diagramEditor = pDiagramEditor;
		
		JPanel jPanel = new JPanel(new MigLayout());
		
		JLabel lbName = new JLabel("Name");
		txName.setText(diagramEditor.getName());
				
		jPanel.add(lbName, "wrap");
		jPanel.add(txName, "wrap");
		
		JLabel lbDescription = new JLabel("Description");
		txDescription.setText(diagramEditor.getDescription());
		
		jPanel.add(lbDescription, "wrap");
		jPanel.add(txDescription, "wrap");
		
		JButton btApply = new JButton("Apply");
		JButton btCancel = new JButton("Cancel");
		
		btApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				diagramEditor.setId(diagramEditor.getId());
				diagramEditor.setTitle(txName.getText());
				diagramEditor.setDescription(txDescription.getText());
				doDefaultCloseAction();
			}
		});
		
		btCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		
		jPanel.add(btApply);
		jPanel.add(btCancel);
		
		add(jPanel);
		
	}
	
}
