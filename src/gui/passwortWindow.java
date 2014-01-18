package gui;


import java.sql.Connection;
import java.sql.ResultSet;

import dataBase.DBImgSave;
import dataBase.DBConnection;
import gui.registrationWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class passwortWindow extends Application {

	private GridPane gridPane;
	private Stage stage;
	private String userAnm = null;
	private String pwAnm = null;
	private TextField userTextField;
	private TextField pwTextField;
	private PasswordField passwordField;
	private Label errMsg = new Label();
	
	public static void main(String[] args)
	{
		launch(args);
		
	}

	@Override
	public void start(final Stage pwWind)
	{
		initGridPane();
		txtRegis();
		
	/*********************** Buttons ***************************************************************/
	
		Button btn_RegOK = new Button("OK");	
		HBox okBtn = new HBox(10);
		okBtn.setAlignment(Pos.BOTTOM_RIGHT);
		okBtn.getChildren().add(btn_RegOK);
		gridPane.add(okBtn, 0, 4);
		
		btn_RegOK.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event)
			{
				userAnm = userTextField.getText();
				pwAnm = passwordField.getText();
				
				System.out.println("userAnm: "+userAnm);
				System.out.println("PW: "+pwAnm);
				/*
		 		if(userAnm.length() <=0 )
		 		{
		 			errMsg.setText("Bitte User eingeben");
		 			System.out.println("Bitte User eingeben");			
		 		}
		 		else if(pwAnm.length() <=0 )
		 		{
		 			errMsg.setText("Bitte Passwort eingeben");
		 			System.out.println("Bitte Passwort eingeben");
		 			
		 		} else if(pwEqual(userAnm, pwAnm)== true) {			*/	
	            	stage = new Stage();
	            	
	            	//Starten des nächsten Fensters
	            	streamWindow strWin = new streamWindow();
	            	strWin.start(stage);        
					pwWind.close(); 								/*
				}
		 		else
		 		{
		 			errMsg.setText("Falsche Eingabe");
		 			System.out.println("Falsche Eingabe");
		 		}
				*/
				
				
			}
		});
		
		errMsg = new Label("");
		errMsg.getStyleClass().add("label-errMsg");
		gridPane.add(errMsg, 1, 3);
		
		Button btn_reg = new Button("Registrierung");
		HBox regBtn = new HBox(10);
		regBtn.setAlignment(Pos.BOTTOM_RIGHT);
		regBtn.getChildren().add(btn_reg);
		gridPane.add(regBtn, 1, 4);
		
		btn_reg.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event)
			{  			
				stage = new Stage();
		        //Starten des nächsten Fensters
		       	registrationWindow regWin = new registrationWindow();
		       	regWin.start(stage);
		       	pwWind.close();
			}

		});
		
		/**
		 * Der folgende Button wurde für Testzwecke implementiert. Wenn dieser betätigt wird wird das
		 * angegeben Foto in der DBImgSave Klasse binär in der Datenbank abgespeichert.
		 */
		/*
		Button btn_Image = new Button("SaveImage");
		HBox imgBtn = new HBox(10);
		imgBtn.setAlignment(Pos.BOTTOM_RIGHT);
		imgBtn.getChildren().add(btn_Image);
		gridPane.add(imgBtn, 2, 4);
		
		btn_Image.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event)
			{  			
				stage = new Stage();
		        //Starten des nächsten Fensters
				System.out.println("ImageSave Button");
								
				DBImgSave imgSave = new DBImgSave();
				imgSave.inputPictureDB();
			}

		});
		 */	
	/*********************** Fenster Eigenschaften *************************************************/	
		pwWind.setTitle("Login");	
        StackPane root = new StackPane();
        root.getChildren().add(gridPane);
        pwWind.setScene(new Scene(root, 340, 220));
        root.getStylesheets().add("myStyle.css");
        pwWind.show();
    /***********************************************************************************************/

	}
	
	private void txtRegis()
	{
		
		/*********************** Labels ****************************************************************/
		Text regis_txt = new Text("Login");
		regis_txt.getStyleClass().add("txt_title");
		gridPane.add(regis_txt, 0, 0, 2, 1);
		
		Label userName_txt = new Label("Name:");
		userName_txt.getStyleClass().add("txt_label_login");
		gridPane.add(userName_txt, 0, 1);
		
		Label pw_txt = new Label("Password:");
		pw_txt.getStyleClass().add("txt_label_login");
		gridPane.add(pw_txt, 0, 2);
		
		/*********************** Textfeld **************************************************************/
		userTextField = new TextField();
		gridPane.add(userTextField, 1, 1);
		
		// Einfaches Textfeld wo das Passwort im Klartext angezeigt wird 
		pwTextField = new TextField();
		gridPane.add(pwTextField, 1, 2);
		
		pwTextField.setManaged(false);
		pwTextField.setVisible(false);
		
		/*********************** Passwordfeld ***********************************************************/
		// Passwortfeld was das eingegebene nur als Punkte darstellt
		passwordField = new PasswordField();
		gridPane.add(passwordField, 1, 2);

		/*********************** CheckBox **************************************************************/
		CheckBox checkBox = new CheckBox("Show/Hide");
		gridPane.add(checkBox, 0, 3);

		// Eigenschaften des Textfelde und des Passwortfeldes werden mit
		// der Checkbox verbunden. u.a. wechselt die Sichtbarkeit der 
		// beiden Felder immer. Es soll immer nur ein Feld sichbar sein.
		pwTextField.managedProperty().bind(checkBox.selectedProperty());
		pwTextField.visibleProperty().bind(checkBox.selectedProperty());

		passwordField.managedProperty().bind(checkBox.selectedProperty().not());
		passwordField.visibleProperty().bind(checkBox.selectedProperty().not());

		// Beide Felder werden mit einander bidirektional verbunden. So das 
		// jedes Feld den selben Text enthält.
		pwTextField.textProperty().bindBidirectional(
				passwordField.textProperty());
			
	}


    private void initGridPane()
    {
    	gridPane = new GridPane();
    	gridPane.setAlignment(Pos.CENTER);
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25, 25, 25, 25));
    }
    

	private static boolean pwEqual(String user, String pw)
 	{

 		Connection c;
 		String pwInput = null;
 		 		
 		try
 		{

 			c = DBConnection.connect();
 			String SQL = "SELECT DISTINCT password,user from tb_user WHERE user = '"+user+"'";
 			System.out.println("Zusammengesetzter String lautet: "+SQL);
 			
 			ResultSet rs = c.createStatement().executeQuery(SQL);
 			
 			while (rs.next())
 			{
				pwInput = rs.getString(1);
		
 			} 
 					
 		} catch (Exception e)
 		{
 			e.printStackTrace();
 			System.out.println("Error on Building Data");
 			return false;
 		}
 		
 		if(pwInput.equals(pw)) {

 			return true;
 		}
 		return false;
 	}
    
}
