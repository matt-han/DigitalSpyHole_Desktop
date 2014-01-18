package gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import dataBase.DBImgShow;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class imageViewWindow {
	

	
	public void start(Stage primaryStage, int userID)
	{
		
		primaryStage.setTitle("Foto");
		
		DBImgShow imgShow = new DBImgShow();

        StackPane sp = new StackPane();

        ImageView imageView = new ImageView();

        try
        {
        	
        	//Foto aus DB holen 
        	byte[] imgData = imgShow.getImageDB(userID);
        	
        	System.out.println("Länge at DB: " + imgData.length);
        	
        	// Byte Array wird in ein BufferedImage gewandelt
        	BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgData));
        	System.out.println("Width at bufImg: " + bufImg.getWidth());
        	System.out.println("Height at bufImg: " + bufImg.getHeight());
        	WritableImage img2 = new WritableImage(bufImg.getWidth(),bufImg.getHeight());
        	
        	//Foto nach JavaFX Objekt wandeln
        	SwingFXUtils.toFXImage(bufImg, img2);

        	//Foto imageView hinzufügen
        	imageView.setImage(img2);
       
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
             
        
        sp.getChildren().add(imageView);
        /*********************** Fenster Eigenschaften *************************************************/        
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
        /***********************************************************************************************/
	}

}
