package br.fit2mapping.diagram.editor;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import br.fit2mapping.diagram.Diagram;
import br.fit2mapping.diagram.properties.ExplorerProperties;
import br.fit2mapping.diagram.properties.FigureProperties;
import br.fit2mapping.diagram.properties.Properties;

@SuppressWarnings("serial")
public class DiagramEditor extends JInternalFrame {

	private Integer id;
	
	private String description;
	
	private Diagram diagram;
	
	private Properties properties;
	
	public DiagramEditor(){
		
		setSize(new Dimension(640,480));
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setVisible(true);
		setResizable(true);
	
	}
	
	private void close(){ 
        if (isVisible()) { 
            setVisible(false); 
        } 
    } 
 
    @Override public void doDefaultCloseAction() { 
        super.doDefaultCloseAction(); 
        close(); 
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void showProperties(){
		setProperties(ExplorerProperties.getInstance(this));
		properties.setVisible(true);
		properties.show();
	}

	
}
