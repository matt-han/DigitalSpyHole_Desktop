/***** DisplayDatabase.java *****/
package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;


public class DisplayDatabase {
	// Tableview and data
	private static ObservableList<ObservableList> data;
	private static int complRow;
	
	public static int getTableRow()
	{
		return complRow;
	}

	// Connection database
	public static void buildData(TableView tableview)
	{
		Connection c;
		data = FXCollections.observableArrayList();
		complRow = 0;
		try
		{
			System.out.println("DB daten holen");
			c = DBConnection.connect();
			
			String SQL = "SELECT * from tb_doorlogger";
			
			ResultSet rs = c.createStatement().executeQuery(SQL);

			/** Spalten ermitteln und der Tabelle übergeben*/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
			{				
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<ObservableList, String> param)
					{
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});
				tableview.getColumns().addAll(col);
			}

			/** Daten in die ObservableList hinzufügen -> Zeilen auslesen*/
			while (rs.next())
			{
				
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
				{
					
					row.add(rs.getString(i));
				}
				System.out.println("Zeile added "+row );
				data.add(row);
				complRow++;
			}
			
			/** Ausgelesene Daten der Tabelle hinzufügen */
			tableview.setItems(data);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error");
		}
		
	}
}