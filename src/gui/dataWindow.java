package gui;

import dataBase.DisplayDatabase;
import gui.streamWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class dataWindow<MyIntegerTableCell> {
	private GridPane gridPane;
	private Stage stage;
	
	@SuppressWarnings("rawtypes")
	private TableView tableview;

	
	@SuppressWarnings("rawtypes")
	public void start(final Stage primaryStage)
	{
		int i=0;
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
				// Starten des n�chsten Fensters
				streamWindow streamWin = new streamWindow();
				streamWin.start(stage);
				primaryStage.close();
			}
		});
		
        Button btn_data = new Button("Datenbase");
        btn_data.setDisable(true);
        HBox data_btn = new HBox(10);
        data_btn.setAlignment(Pos.CENTER);
        data_btn.getChildren().add(btn_data);
		gridPane.add(data_btn, 5, 1);
		
		
        Button btn_open = new Button("Open");
        btn_open.setDisable(true);
        HBox btnOpen = new HBox(10);
        btnOpen.setAlignment(Pos.CENTER);
        btnOpen.getChildren().add(btn_open);
		gridPane.add(btnOpen, 5, 2);
		
		
        Button btn_openPicture = new Button("Open Picture");
        btn_openPicture.setDisable(true);
        HBox btnOpenPicture = new HBox(10);
        btnOpenPicture.setAlignment(Pos.CENTER);
        btnOpenPicture.getChildren().add(btn_openPicture);
		gridPane.add(btnOpenPicture, 5, 5);

		/*********************** Table *****************************************************************/
		tableview = new TableView();
		tableview.setEditable(false);
		DisplayDatabase.buildData(tableview);
		HBox dataTable = new HBox(10);
		dataTable.setAlignment(Pos.CENTER);
		dataTable.getChildren().add(tableview);
		gridPane.add(dataTable, 0, 0, 1, 7);
		
		/*********************** ComboBox **************************************************************/
		
		Label choiceRow = new Label("ID f�r Foto:");
		gridPane.setHalignment(choiceRow, HPos.RIGHT);
		HBox txtBox = new HBox(10);
		txtBox.setAlignment(Pos.CENTER_RIGHT);
		txtBox.getChildren().add(choiceRow);
		gridPane.add(choiceRow, 5, 4);
		
		final ComboBox<Integer> comboBox = new ComboBox();
		
		for(i = 1;i<=DisplayDatabase.getTableRow();i++)
		{
			comboBox.getItems().addAll(i);
		}
		
		if(i > 1) {
			btn_openPicture.setDisable(false);
		}
		
		/*********************** Button Event f�r die ComboBox ******************************************/
		btn_openPicture.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
				stage = new Stage();
		        //Starten des n�chsten Fensters
		       	imageViewWindow imgWin = new imageViewWindow();
		       	imgWin.start(stage,comboBox.getValue());
            }
        });
		
		/*********************** ComboBox **************************************************************/		
		HBox coiceBox = new HBox(1);
		coiceBox.setAlignment(Pos.CENTER);
		coiceBox.getChildren().add(comboBox);
		gridPane.add(comboBox, 5, 3);
		//gridPane.add(comboBox, 5, 3, 5, 1);
		
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
		gridPane.setAlignment(Pos.TOP_RIGHT);
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(20, 20, 20, 20));
	}
	
}
