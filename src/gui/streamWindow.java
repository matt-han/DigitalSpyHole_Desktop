package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class streamWindow {
	
	private GridPane gridPane;
	private Stage stage;
	private Timestamp timestamp;
	final private double stream_width = 365; 
	final private double stream_height = 255;  
    
    HttpURLConnection connection;
	
    public void start(final Stage primaryStage) {
    	
    	initGridPaneButtons();

        primaryStage.setTitle("Control");
        
        Text ctrl_txt = new Text("Control");
        ctrl_txt.getStyleClass().add("txt_title");
		gridPane.add(ctrl_txt, 0, 0, 2, 1);
        
        /*********************** Buttons ***************************************************************/
        Button btn_stream = new Button("Control");
		btn_stream.setDisable(true);
		HBox btnStream = new HBox(10);
		btnStream.setAlignment(Pos.CENTER);
		btnStream.getChildren().add(btn_stream);
		//gridPane.add(btnStream, 7, 1);
		gridPane.add(btnStream, 0, 1, 2, 1);

		
        Button btn_data = new Button("Database");
        btn_data.setDisable(false);
        HBox data_btn = new HBox(10);
        data_btn.setAlignment(Pos.CENTER);
        data_btn.getChildren().add(btn_data);
		//gridPane.add(data_btn, 7, 2);
		gridPane.add(data_btn, 0, 2, 2, 2);

		
        Button btn_open = new Button("Open");
        btn_open.setDisable(false);
        HBox btnOpen = new HBox(10);
        btnOpen.setAlignment(Pos.CENTER);
        btnOpen.getChildren().add(btn_open);
		//gridPane.add(btnOpen, 7, 3);
		gridPane.add(btnOpen, 0, 4, 2, 4);
		btn_open.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	inputDbOpenDoor();
            	OpenDoor();
            }
        }); 
		
		
		/*********************** Media Stream **********************************************************/
		 WebView webview = new WebView();
         webview.setVisible(true);
         webview.setMaxWidth(stream_width);
         webview.setMaxHeight(stream_height);
         WebEngine webengine = webview.getEngine();
         webengine.setJavaScriptEnabled(true);
         File file = new File("http://spyhole.no-ip.biz:1900/javascript_simple.html");
         //File file = new File("http://192.168.178.27:8080/javascript_simple.html");
         System.out.println(file.exists() + " file exitence");
         webengine.load(file.toString());
         gridPane.add(webview, 5, 1, 9, 16);
         
        /** Database Button */
		btn_data.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	stage = new Stage();
            	//Starten des nächsten Fensters
            	dataWindow dataWin = new dataWindow();
            	dataWin.start(stage);
            	//mediaPlayer.setMute(true);
            //	mediaPlayer.stop();
            	primaryStage.close();
           
            }
        });
     	
        /*********************** Fenster Eigenschaften *************************************************/
        StackPane root = new StackPane();
        root.getChildren().addAll(gridPane);
        primaryStage.setScene(new Scene(root, 550, 520));
        root.getStylesheets().add("myStyle.css");
        primaryStage.show();
        /***********************************************************************************************/

	}
        
    private void initGridPaneButtons()
    {
    	gridPane = new GridPane();
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25, 25, 5, 25));
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
    
 /***
  * 
  * 
  */
    public void OpenDoor() {
    	
    	
        try {
			URL u = new URL( "http://spyhole.no-ip.biz?open=open");
			u.openStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
     
    }
    

}
