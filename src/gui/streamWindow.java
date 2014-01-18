package gui;

import java.net.URI;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;

import dataBase.DBConnection;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class streamWindow {
	
	private GridPane gridPane;
	private Stage stage;
	private Timestamp timestamp;
	private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
	
	//private static final String MEDIA_URL = "http://spyhole.no-ip.biz:1900/?action=stream";
	//private static final String MEDIA_URL = "http://192.168.178.27:8080/";
    private static String arg1;
	
    public void start(final Stage primaryStage) {
    	
    	initGridPaneButtons();
    	
        primaryStage.setTitle("Control");
        
        Text ctrl_txt = new Text("Control");
        ctrl_txt.getStyleClass().add("txt_title");
		gridPane.add(ctrl_txt, 0, 0, 2, 1);
        
        /*********************** Buttons ***************************************************************/
        Button btn_stream = new Button("Control Center");
		btn_stream.setDisable(true);
		HBox btnStream = new HBox(10);
		btnStream.setAlignment(Pos.CENTER);
		btnStream.getChildren().add(btn_stream);
		gridPane.add(btnStream, 5, 1);

		
        Button btn_data = new Button("Database");
        btn_data.setDisable(false);
        HBox data_btn = new HBox(10);
        data_btn.setAlignment(Pos.CENTER);
        data_btn.getChildren().add(btn_data);
		gridPane.add(data_btn, 5, 2);

		
        Button btn_open = new Button("Open");
        btn_open.setDisable(false);
        HBox btnOpen = new HBox(10);
        btnOpen.setAlignment(Pos.CENTER);
        btnOpen.getChildren().add(btn_open);
		gridPane.add(btnOpen, 5, 3);
		btn_open.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	inputDbOpenDoor();
            }
        }); 
		
		/*********************** Media Stream **********************************************************/
        // create media player
        Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        
        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
       // ((Group)scene.getRoot()).getChildren().add(mediaView);
        gridPane.add(mediaView, 0, 1, 5, 4);
        
        /** Database Button */
		btn_data.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	stage = new Stage();
            	//Starten des nächsten Fensters
            	dataWindow dataWin = new dataWindow();
            	dataWin.start(stage);
            	//mediaPlayer.setMute(true);
            	mediaPlayer.stop();
            	primaryStage.close();
           
            }
        });
     	
        /*********************** Fenster Eigenschaften *************************************************/
        StackPane root = new StackPane();
        root.getChildren().addAll(gridPane);
        primaryStage.setScene(new Scene(root, 750, 450));
        root.getStylesheets().add("myStyle.css");
        primaryStage.show();
        /***********************************************************************************************/

	}
        
    private void initGridPaneButtons()
    {
    	gridPane = new GridPane();
    	gridPane.setHgap(30);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(0, 25, 0, 25));
    }
    
    private void inputDbOpenDoor()
    {
    	Connection c;
		try
		{

			c = DBConnection.connect();

			timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
			String dateFormat = timestamp.toString();
			System.out.println("Timestamp: " + dateFormat);

			//SQL-Befehl mit den angegebenen Daten für die Datenbank
			String SQL = "INSERT INTO tb_doorlogger VALUES (null,'1','" + timestamp +"')";
			System.out.println("Input DB Doorlogger: "+ SQL);

			
			//Eintrag in die Datenbank
			if (c.createStatement().executeUpdate(SQL) <= 0)
			{
				System.out.println("Es wurde nix in die Datenbank eingetragen.");
			}

			System.out.println("Eintrag in die DB: ");

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
    }
    
	
}
