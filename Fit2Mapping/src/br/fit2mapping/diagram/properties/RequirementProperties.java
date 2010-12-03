package br.fit2mapping.diagram.properties;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import br.fit2mapping.diagram.bean.RequirementBean;

@SuppressWarnings("serial")
public class RequirementProperties extends Properties {

	private static RequirementProperties instance;

	private RequirementBean requirementBean;
	
	final JTextField txName = new JTextField(30);
	final JTextArea txDescription = new JTextArea(5, 40);
	final JTextArea txPreCondition = new JTextArea(5, 40);
	final JTextArea txPostCondition = new JTextArea(5, 40);
	final JTextArea txSpecialRequirement = new JTextArea(5, 40);
	
	public static RequirementProperties getInstance(RequirementBean pRequirementBean){
		if (instance == null) {
			instance = new RequirementProperties(pRequirementBean);
			
		} else {
			if (!instance.isVisible()){
				instance.requirementBean = pRequirementBean;
				instance.setTitle(pRequirementBean.getName());
				
				instance.txName.setText(pRequirementBean.getName());
				instance.txDescription.setText(pRequirementBean.getDescription());
				instance.txPreCondition.setText(pRequirementBean.getPreCondition());
				instance.txPostCondition.setText(pRequirementBean.getPostCondition());
				instance.txSpecialRequirement.setText(pRequirementBean.getSpecialRequirements());
				
				instance.setVisible(true);
				instance.show();
			}
		}
		
		return instance;
	}
	
	private RequirementProperties (){
		super();
	}
	
	
	private RequirementProperties(final RequirementBean pRequirementBean) {
		
		super(pRequirementBean.getName());

		setSize(new Dimension(400, 500));
		
		txDescription.setLineWrap(true);
		txPreCondition.setLineWrap(true);
		txPostCondition.setLineWrap(true);
		txSpecialRequirement.setLineWrap(true);
		
		int posX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 400) / 2;
		int posY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 600) / 2;
		
		setLocation(posX, posY);

		
		this.requirementBean = pRequirementBean;
		
		JPanel jPanel = new JPanel(new MigLayout());
		
		JLabel lbName = new JLabel("Name");
		txName.setText(requirementBean.getName());
				
		jPanel.add(lbName, "wrap");
		jPanel.add(txName, "wrap");
		
		JLabel lbDescription = new JLabel("Description");
		txDescription.setText(requirementBean.getDescription());
		
		jPanel.add(lbDescription, "wrap");
		jPanel.add(txDescription, "wrap");

		JLabel lbPreCondition = new JLabel("Pre-Condition");
		txDescription.setText(requirementBean.getPreCondition());
		
		jPanel.add(lbPreCondition, "wrap");
		jPanel.add(txPreCondition, "wrap");

		JLabel lbPostCondition = new JLabel("Post-Condition");
		txDescription.setText(requirementBean.getPostCondition());
		
		jPanel.add(lbPostCondition, "wrap");
		jPanel.add(txPostCondition, "wrap");

		JLabel lbSpecialRequirement = new JLabel("Special RequirementFigure");
		txDescription.setText(requirementBean.getSpecialRequirements());
		
		jPanel.add(lbSpecialRequirement, "wrap");
		jPanel.add(txSpecialRequirement, "wrap");
		
		JButton btApply = new JButton("Apply");
		JButton btCancel = new JButton("Cancel");
		
		btApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requirementBean.setName(txName.getText());
				requirementBean.setDescription(txDescription.getText());
				requirementBean.setPreCondition(txPreCondition.getText());
				requirementBean.setPostCondition(txPostCondition.getText());
				requirementBean.setSpecialRequirements(txSpecialRequirement.getText());
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
