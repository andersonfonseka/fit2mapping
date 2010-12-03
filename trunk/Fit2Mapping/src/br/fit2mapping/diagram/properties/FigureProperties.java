package br.fit2mapping.diagram.properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.fit2mapping.diagram.bean.FigureBean;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class FigureProperties extends Properties {
	
	private static FigureProperties instance;

	private FigureBean figureBean;
	
	final JTextField txName = new JTextField(30);

	final JTextArea txDescription = new JTextArea(10, 40);
	
	public static FigureProperties getInstance(FigureBean pFigureBean){
		if (instance == null) {
			instance = new FigureProperties(pFigureBean);
			
		} else {
			if (!instance.isVisible()){
				instance.figureBean = pFigureBean;
				instance.setTitle(pFigureBean.getName());
				instance.txName.setText(pFigureBean.getName());
				instance.txDescription.setText(pFigureBean.getDescription());
				instance.setVisible(true);
				instance.show();
			}
		}
		
		return instance;
	}
	
	private FigureProperties (){
		super();
	}
	
	
	private FigureProperties(final FigureBean pFigureBean) {
		
		super(pFigureBean.getName());

		txDescription.setLineWrap(true);
		
		this.figureBean = pFigureBean;
		
		JPanel jPanel = new JPanel(new MigLayout());
		
		JLabel lbName = new JLabel("Name");
		txName.setText(figureBean.getName());
				
		jPanel.add(lbName, "wrap");
		jPanel.add(txName, "wrap");
		
		JLabel lbDescription = new JLabel("Description");
		txDescription.setText(figureBean.getDescription());
		
		jPanel.add(lbDescription, "wrap");
		jPanel.add(txDescription, "wrap");
		
		JButton btApply = new JButton("Apply");
		JButton btCancel = new JButton("Cancel");
		
		btApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				figureBean.setId(figureBean.getId());
				figureBean.setName(txName.getText());
				figureBean.setDescription(txDescription.getText());
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
