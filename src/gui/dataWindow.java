package gui;

import dataBase.DisplayDatabase;
import gui.streamWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class dataWindow {
	private GridPane gridPane;
	private Stage stage;
	
	private TableView tableview;

	
	public void start(Stage primaryStage)
	{
		initGridPaneButtons();

		primaryStage.setTitle("Datenbank");

		/*********************** Buttons ***************************************************************/       
		Button btn_stream = new Button("Control Center");
		btn_stream.setDisable(false);
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
        btn_data.setDisable(true);
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
        btn_open.setDisable(true);
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

		/*********************** Table *****************************************************************/
		tableview = new TableView();
		DisplayDatabase.buildData(tableview);
		HBox dataTable = new HBox(10);
		dataTable.setAlignment(Pos.CENTER);
		dataTable.getChildren().add(tableview);
		//gridPane.add(dataTable,0,0);
		gridPane.add(tableview, 0, 0, 1, 4);
		
		
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
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(20, 20, 20, 20));
	}

}
