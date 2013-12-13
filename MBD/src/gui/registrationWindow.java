package gui;

import gui.passwortWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class registrationWindow extends Application {
	
	Stage stage;
	GridPane gridPane;
	 
	@Override
    public void start(Stage regisStage) {
		
		initGridPane();
	
	/*********************** Buttons ***************************************************************/		
		Button btn_anmAbsch = new Button();	
		btn_anmAbsch.setText("Anmelden");
		HBox anmAbschBtn = new HBox(10);
		anmAbschBtn.setAlignment(Pos.BOTTOM_LEFT);
		anmAbschBtn.getChildren().add(btn_anmAbsch);
		gridPane.add(anmAbschBtn, 0, 4);
  
		btn_anmAbsch.setOnAction(new EventHandler<ActionEvent>() {
			
            @Override
            public void handle(ActionEvent event) {

            }
        });
        
        Button btn_bck = new Button("Zurück");
		HBox bckBtn = new HBox(10);
		bckBtn.setAlignment(Pos.BOTTOM_RIGHT);
		bckBtn.getChildren().add(btn_bck);
		gridPane.add(bckBtn, 1, 4);
		
        btn_bck.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent event) {
            	stage = new Stage();
            	//Starten des nächsten Fensters
            	passwortWindow pwWind = new passwortWindow();
            	pwWind.start(stage);
            }
        });
        
    /*********************** Textfelder ************************************************************/
		Label regis_text = new Label("Registrierung");
		regis_text.getStyleClass().add("txt_Login");
		gridPane.add(regis_text, 0, 0, 2, 1);
		
        Label lb_name = new Label("Name");
		gridPane.add(lb_name, 0, 1);
		
		TextField txt_name = new TextField();
		gridPane.add(txt_name, 1, 1);
		
		Label lb_email = new Label("Email:");
		gridPane.add(lb_email, 0, 2);
		
		TextField txt_email = new TextField();
		gridPane.add(txt_email, 1, 2);
		
		Label lb_pw = new Label("Password:");
		gridPane.add(lb_pw, 0, 3);
		
		TextField txt_pw = new TextField();
		gridPane.add(txt_pw, 1, 3);
		
    /*********************** Fenster Eigenschaften *************************************************/	
		regisStage.setTitle("Registrierung");	
        StackPane root = new StackPane();
        root.getChildren().add(gridPane);
        regisStage.setScene(new Scene(root, 340, 220));
        root.getStylesheets().add("myStyle.css");
        regisStage.show();
    /***********************************************************************************************/
	}
	
    private void initGridPane()
    {
    	gridPane = new GridPane();
    	gridPane.setAlignment(Pos.CENTER);
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25, 25, 25, 25));
    }
	
}
