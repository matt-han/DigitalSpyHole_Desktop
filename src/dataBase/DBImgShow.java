package dataBase;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBImgShow {
	
	public static byte[] getImageDB(int userID)
	{
		Connection c = null;

	    Blob img ;
	    byte[] imgData = null ;
	    
		try
		{
			String SQL = "SELECT * FROM tb_images WHERE ID = "+userID;
			c = DBConnection.connect();

			Statement st = c.createStatement();		
			ResultSet rs = st.executeQuery(SQL);

		    while (rs.next ())
		    {   
		    	// 4 -> 4 Spalte in der DB ist der BLOB
		      img = rs.getBlob(4);
		      imgData = img.getBytes(1,(int)img.length());

			}
	    
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error on Building the Picture from Database");
		}
		
		
		return imgData;
	}
}
