package gui;

import gui.dataWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class streamWindow {
	
	private GridPane gridPane;
	private Stage stage;
	//private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	
	private static final String MEDIA_URL = "http://192.168.178.27:8080/video/mjpg.cgi";
    private static String arg1;
	
    public void start(Stage primaryStage) {
    	
    	initGridPaneButtons();
    	
    //	TextArea txtOutput = new TextArea(); 	
    //	txtOutput.getStyleClass().add("txt_Stream");
	//	gridPane.add(txtOutput, 0, 1);
    	
        primaryStage.setTitle("Stream");
        
        /*********************** Buttons ***************************************************************/
        Button btn_stream = new Button("Control Center");
		btn_stream.setDisable(true);
		HBox btnStream = new HBox(10);
		btnStream.setAlignment(Pos.CENTER);
		btnStream.getChildren().add(btn_stream);
		gridPane.add(btnStream, 5, 0);
		btn_stream.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event)
			{
				stage = new Stage();
				// Starten des nächsten Fensters
				streamWindow streamWin = new streamWindow();
				streamWin.start(stage);
			}
		});
		
        Button btn_data = new Button("Datenbase");
        btn_data.setDisable(false);
        HBox data_btn = new HBox(10);
        data_btn.setAlignment(Pos.CENTER);
        data_btn.getChildren().add(btn_data);
		gridPane.add(data_btn, 5, 1);
		btn_data.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	stage = new Stage();
            	//Starten des nächsten Fensters
            	dataWindow dataWin = new dataWindow();
            	dataWin.start(stage);
           
            }
        });
		
        Button btn_open = new Button("Open");
        btn_open.setDisable(false);
        HBox btnOpen = new HBox(10);
        btnOpen.setAlignment(Pos.CENTER);
        btnOpen.getChildren().add(btn_open);
		gridPane.add(btnOpen, 5, 2);
		btn_open.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
           /* 	stage = new Stage();
            	//Starten des nächsten Fensters
            	StartWindow2 sw2 = new StartWindow2();
            	sw2.start(stage);
           */
            }
        }); 

        // create media player
        Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        
        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
       // ((Group)scene.getRoot()).getChildren().add(mediaView);
        gridPane.add(mediaView, 0, 0, 5, 35);
       
        StackPane root = new StackPane();
        root.getChildren().addAll(gridPane);
        primaryStage.setScene(new Scene(root, 750, 450));
        root.getStylesheets().add("myStyle.css");
        primaryStage.show();
   
	}
        
    private void initGridPaneButtons()
    {
    	gridPane = new GridPane();
    	gridPane.setAlignment(Pos.TOP_RIGHT);
    	gridPane.setHgap(30);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(0, 25, 0, 25));
    }
    
	
}
