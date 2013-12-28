package dataBase;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBImgShow {
	
	public static byte[] getImageDB()
	{
		Connection c = null;
		BufferedImage image = null;  //Buffered image coming from database
	    InputStream inStream = null; //Inputstream
	    
	    String req = "" ;
	    Blob img ;
	    byte[] imgData = null ;
	    
		try
		{
			String SQL = "SELECT * FROM testimage WHERE ID = 2";
			c = DBConnection.connect();

			Statement st = c.createStatement();		
			ResultSet rs = st.executeQuery(SQL);
			// ResultSet
			//ResultSet rs = c.createStatement().executeQuery(SQL);
		    while (rs.next ())
		    {   
		    	// 2 -> 2 Spalte in der DB ist der BLOB
		      img = rs.getBlob(2);
		      imgData = img.getBytes(1,(int)img.length());
		     
			
		/*
			//System.out.println("Zusammengesetzter String lautet: " + SQL);

			// ResultSet
			ResultSet rs = c.createStatement().executeQuery(SQL);
			System.out.println("After ResultSet");
			inStream = rs.getBinaryStream(2);
		//	inStream = rs.getBinaryStream("imgdata");
			System.out.println("After getBinary");
		//	image = javax.imageio.ImageIO.read(inStream);
		*/
		 //Blob photo = rs.getBlob(2);
		 //ObjectInputStream ois = null;
		 //ois = new ObjectInputStream(photo.getBinaryStream());
		 //image = (BufferedImage) ois.readObject();
			}
	    
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error on Building the Picture from Database");
		}
		
		
		return imgData;
	}
}
