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
	

	
	public void start(Stage primaryStage)
	{
		
		primaryStage.setTitle("ImageView");
		
		DBImgShow imgShow = new DBImgShow();

        StackPane sp = new StackPane();

        ImageView imageView = new ImageView();

        try
        {
        	
        	//Foto aus DB holen 
        	byte[] imgData = imgShow.getImageDB();
        	
        	System.out.println("Länge at DB: " + imgData.length);
        	
        	BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imgData));
        	System.out.println("Width at bufImg: " + bufImg.getWidth());
        	System.out.println("Height at bufImg: " + bufImg.getHeight());
        	WritableImage img2 = new WritableImage(bufImg.getWidth(),bufImg.getHeight());
        	SwingFXUtils.toFXImage(bufImg, img2);
        	System.out.println("Width at img2: " + img2.getWidth());
        	System.out.println("Height at img2: " + img2.getHeight());
        	imageView.setImage(img2);
//        	ImageIO.write(bufImg, "jpg", new File("d:/new-darksouls.jpg"));
       
        }
        catch (Exception e)
        {
          e.printStackTrace();
         // throw e;
        }
        
        
        
        sp.getChildren().add(imageView);
        
        //Adding HBox to the scene
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
