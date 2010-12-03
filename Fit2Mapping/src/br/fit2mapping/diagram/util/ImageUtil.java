package br.fit2mapping.diagram.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import br.fit2mapping.diagram.editor.FeatureDiagramEditor;

public class ImageUtil {

	private static ImageUtil instance;
	
	public static ImageUtil getInstance(){
		if (instance == null) {
			instance = new ImageUtil();
		}
		return instance;
	}
	
	private ImageUtil(){}
	
	
	public ImageIcon getImageIcon(String path) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(FeatureDiagramEditor.class.getResourceAsStream(path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return new ImageIcon(img);
	}

	
}
