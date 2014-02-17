package gui;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;

import dataBase.DBConnection;
import gui.passwortWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class registrationWindow extends Application {

	private Stage stage;
	private GridPane gridPane;
	private Timestamp timestamp;
	Label errMsg = new Label();

	@Override
	public void start(final Stage regisStage)
	{

		initGridPane();

		/*********************** Textfelder  Labels ******************************************************/
		Text regis_txt = new Text("Registrierung");
		regis_txt.getStyleClass().add("txt_title");
		gridPane.add(regis_txt, 0, 0, 2, 1);

		Label lbVorname = new Label("Vorname");
		gridPane.add(lbVorname, 0, 1);

		final TextField txtVorname = new TextField();
		gridPane.add(txtVorname, 1, 1);

		Label lbNachname = new Label("Nachname");
		gridPane.add(lbNachname, 0, 2);

		final TextField txtNachname = new TextField();
		gridPane.add(txtNachname, 1, 2);

		Label lbUserName = new Label("Benutzername");
		gridPane.add(lbUserName, 0, 3);

		final TextField txtUserName = new TextField();
		gridPane.add(txtUserName, 1, 3);

		Label lbEmail = new Label("Email:");
		gridPane.add(lbEmail, 0, 4);

		final TextField txtEmail = new TextField();
		gridPane.add(txtEmail, 1, 4);

		Label lbPw = new Label("Password:");
		gridPane.add(lbPw, 0, 5);

		final PasswordField txtPw = new PasswordField();
		gridPane.add(txtPw, 1, 5);

		Label lbPwAgain = new Label("Password Again:");
		gridPane.add(lbPwAgain, 0, 6);

		final PasswordField txtPw2 = new PasswordField();
		gridPane.add(txtPw2, 1, 6);

		/*********************** Buttons ***************************************************************/
		Button btn_bck = new Button("Zurück");
		HBox bckBtn = new HBox(10);
		bckBtn.setAlignment(Pos.BOTTOM_RIGHT);
		bckBtn.getChildren().add(btn_bck);
		gridPane.add(bckBtn, 1, 8);

		btn_bck.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event)
			{
				stage = new Stage();
				// Starten des nächsten Fensters
				passwortWindow pwWind = new passwortWindow();
				pwWind.start(stage);
				regisStage.close();
			}
		});
		
		Button btn_anmAbsch = new Button();
		btn_anmAbsch.setText("Anmelden");
		HBox anmAbschBtn = new HBox(10);
		anmAbschBtn.setAlignment(Pos.BOTTOM_LEFT);
		anmAbschBtn.getChildren().add(btn_anmAbsch);
		gridPane.add(anmAbschBtn, 0, 8);		
		
		btn_anmAbsch.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event)
			{

				// Abfangen ob überhaupt etwas in den Textfeldern steht.
				if (0 >= txtVorname.getLength())
				{
					System.out.println("Textfeld Name empty");
					errMsg.setText("Textfeld Name empty");
				} else if (0 >= txtEmail.getLength())
				{
					System.out.println("Textfeld Email empty");
					
					errMsg.setText("Textfeld Email empty");
				} else if (0 >= txtPw.getLength())
				{
					System.out.println("Textfeld Passwort empty");
					errMsg.setText("Textfeld Passwort empty");
				} else
				{

					if (txtPw.getText().equals(txtPw2.getText()))
					{
						/*********************** Datenbank Eintrag ***************************************************************/
						/** Vorname Nachname Email User pw Zeitstempel */

						Connection c;
						try
						{

							c = DBConnection.connect();

							timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
							String dateFormat = timestamp.toString();
							System.out.println("Timestamp: " + dateFormat);

							//SQL-Befehl mit den angegebenen Daten für die Datenbank

							String SQL = "INSERT INTO tb_user VALUES (null,'"
									+ txtVorname.getText() + "', '"
									+ txtNachname.getText() + "', '"
									+ txtEmail.getText() + "', '"
									+ txtUserName.getText() + "', '"
									+ txtPw.getText() + "', NOW())";
							System.out.println("Input DB user: "+ SQL);
							
							//Eintrag in die Datenbank
							if (c.createStatement().executeUpdate(SQL) <= 0)
							{
								System.out.println("Es wurde nix in die Datenbank eingetragen.");
							}

							System.out.println("Datensatz wurde eingetragen");

						} catch (Exception e)
						{
							e.printStackTrace();
							System.out.println("Error");
						}

					} else
					{
						System.out.println("Passwörter nicht gleich!");
						errMsg.setText("Passwörter nicht gleich!");
					}
					
				}
				
			}
		});
		
		errMsg = new Label("");
		errMsg.getStyleClass().add("label-errMsg");
		gridPane.add(errMsg, 1, 7);

		/*********************** Fenster Eigenschaften *************************************************/
		regisStage.setTitle("Registrierung");
		StackPane root = new StackPane();
		root.getChildren().add(gridPane);
		regisStage.setScene(new Scene(root, 450, 335));
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
