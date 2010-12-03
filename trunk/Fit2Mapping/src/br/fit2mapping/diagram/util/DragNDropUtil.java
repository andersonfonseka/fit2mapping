package br.fit2mapping.diagram.util;

public class DragNDropUtil {

	private static DragNDropUtil instance;
	
	private Object selectedObject;
	
	private DragNDropUtil(){}
	
	public static DragNDropUtil getInstance(){
		if (instance == null) {
			instance = new DragNDropUtil();
			
		}
		return instance;
	}

	public Object getSelectedObject() {
		return selectedObject;
	}

	public void setSelectedObject(Object selectedObject) {
		this.selectedObject = selectedObject;
	}
	
}
